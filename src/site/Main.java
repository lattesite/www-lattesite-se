package site;

import lattesite.LatteSiteGenerator;
import lattesite.common.services.FileService;
import lattesite.common.services.LogService;
import lattesite.css.services.StyleGeneratorService;
import lattesite.html.generator.ElementGenerator;
import lattesite.localization.locale.Locale;
import lattesite.page.services.PageGeneratorService;
import lattesite.settings.SiteSettings;
import lattesite.sitemap.services.SitemapService;
import lattesite.staticwebserver.services.StaticWebServerService;
import lattesite.structureddata.services.StructuredDataService;
import site.localization.Translation;
import site.localization.TranslationUtil;
import site.localization.TranslationsDatabase;
import site.pages.Page;
import site.pages.PageService;
import site.services.RedirectsService;
import site.utils.StringUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final Locale LOCALE_EN = new Locale("en", "https://www.lattesite.se/", "/");

    public static final List<Locale> ALL_LOCALES = List.of(
            LOCALE_EN,
            new Locale("sv", "https://www.lattesite.se/sv/"),
            new Locale("es", "https://www.lattesite.se/es/"),
            new Locale("pt", "https://www.lattesite.se/pt/"),
            new Locale("de", "https://www.lattesite.se/de/"),
            new Locale("ja", "https://www.lattesite.se/ja/"),
            new Locale("ko", "https://www.lattesite.se/ko/"),
            new Locale("ru", "https://www.lattesite.se/ru/"),
            new Locale("fr", "https://www.lattesite.se/fr/"),
            new Locale("it", "https://www.lattesite.se/it/"),
            new Locale("zh", "https://www.lattesite.se/zh/"),
            new Locale("tr", "https://www.lattesite.se/tr/"),
            new Locale("nl", "https://www.lattesite.se/nl/"),
            new Locale("pl", "https://www.lattesite.se/pl/"),
            new Locale("vi", "https://www.lattesite.se/vi/"),
            new Locale("id", "https://www.lattesite.se/id/"),
            new Locale("cs", "https://www.lattesite.se/cs/"),
            new Locale("uk", "https://www.lattesite.se/uk/"),
            new Locale("el", "https://www.lattesite.se/el/"),
            new Locale("ro", "https://www.lattesite.se/ro/"),
            new Locale("hu", "https://www.lattesite.se/hu/"),
            new Locale("th", "https://www.lattesite.se/th/"),
            new Locale("da", "https://www.lattesite.se/da/"),
            new Locale("sk", "https://www.lattesite.se/sk/"),
            new Locale("fi", "https://www.lattesite.se/fi/"),
            new Locale("bg", "https://www.lattesite.se/bg/"),
            new Locale("sr", "https://www.lattesite.se/sr/"),
            new Locale("nb", "https://www.lattesite.se/nb/"),
            new Locale("hr", "https://www.lattesite.se/hr/"),
            new Locale("lt", "https://www.lattesite.se/lt/"),
            new Locale("sl", "https://www.lattesite.se/sl/"),
            new Locale("ca", "https://www.lattesite.se/ca/"),
            new Locale("et", "https://www.lattesite.se/et/"),
            new Locale("lv", "https://www.lattesite.se/lv/"),
            new Locale("hi", "https://www.lattesite.se/hi/")
    );

    public static void main(String[] args) throws Exception {

        SiteSettings settings = new SiteSettings(
                ALL_LOCALES
        );

        LogService logService = new LogService();
        StructuredDataService structuredDataService = new StructuredDataService();
        FileService fileService = new FileService(logService);
        SitemapService sitemapService = new SitemapService(logService, fileService);
        ElementGenerator elementGenerator = new ElementGenerator();
        StyleGeneratorService styleGeneratorService = new StyleGeneratorService();
        PageGeneratorService pageGeneratorService = new PageGeneratorService(
                settings,
                logService,
                elementGenerator,
                styleGeneratorService,
                fileService
        );

        LatteSiteGenerator site = new LatteSiteGenerator(
                logService,
                settings,
                fileService,
                pageGeneratorService,
                sitemapService
        );

        PageService pageService = new PageService(
                structuredDataService
        );
        RedirectsService redirectsService = new RedirectsService(fileService);

        StaticWebServerService staticWebServerService = new StaticWebServerService(logService);
        staticWebServerService.serve(1980);

        List<Page> pages = pageService.getPages();

        site.generate(pages);
        redirectsService.generate();

        for (Locale locale : ALL_LOCALES) {
            if (locale == Main.LOCALE_EN) {
                continue;
            }
            generateTranslationFile(fileService, locale);
        }

    }

    private static void generateTranslationFile(FileService fileService, Locale locale) throws Exception {

        TranslationsDatabase db = TranslationUtil.getDB(locale);
        if (db == null) {
            throw new Exception("Database missing");
        }

        String code = "";
        code += "package site.localization.dbs;\n";
        code += "\n";
        code += "import site.localization.TranslationsDatabase;\n";
        code += "\n";
        code += "public abstract class Translations_" + locale.getCode().toUpperCase() + " {\n";
        code += "\n";
        code += "    // Contains \"en\" to \"" + locale.getCode() + "\" locale translations\n";
        code += "    public static final TranslationsDatabase translations = new TranslationsDatabase();\n";
        code += "\n";
        code += "    static {\n";
        code += "\n";

        List<Translation> missingTranslations = new ArrayList<>();

        List<Translation> all = db.getAll();
        all.sort(Comparator.comparing(Translation::getKey));
        for (Translation translation : all) {

            if (!TranslationUtil.accessedKeys.contains(translation.getKey())) {
                continue;
            }

            String keyFormatted = translation.getKey().replaceAll("\"", "\\\\\"");
            String valueFormatted = translation.getValue().replaceAll("\"", "\\\\\"");

            code += "        translations.add(\n";
            code += "            \"" + keyFormatted + "\",\n";
            code += "            \"" + valueFormatted + "\",\n";
            code += "            \"" + translation.getUpdated() + "\"\n";
            code += "        );\n";
            code += "\n";

            if (StringUtil.isEmpty(translation.getValue())) {
                missingTranslations.add(translation);
            }

        }

        if (!missingTranslations.isEmpty()) {
            code += "        /*\n";
            for (Translation translation : missingTranslations) {

                String keyFormatted = translation.getKey().replaceAll("\"", "\\\\\"");
                String valueFormatted = translation.getValue().replaceAll("\"", "\\\\\"");

                code += "        translations.add(\n";
                code += "            \"" + keyFormatted + "\", // \"en\" locale text\n";
                code += "            \"" + valueFormatted + "\" // \"" + locale.getCode() + "\" locale text\n";
                code += "        );\n";
                code += "\n";

            }
            code += "         *\n";
            code += "         * " + missingTranslations.size() + " missing translations\n";
            code += "         */\n";
            code += "\n";
        }
        code += "    }\n";
        code += "\n";
        code += "}\n";
        code += "\n";

        fileService.writeFile("src/site/localization/dbs/Translations_" + locale.getCode().toUpperCase() + ".java", code);

    }

}
