package site;

import lattesite.localization.locale.Locale;
import site.localization.TranslationUtil;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final String SITE_NAME = "LatteSite";
    public static final String PAGE_TITLE_SUFFIX = "LatteSite";
    public static final String BRAND_NAME = "LatteSite";

    public static final String EMAIL = "admin@lattesite.se";

    public static String SLOGAN(Locale locale) throws Exception {
        return TranslationUtil.translate(locale, "Lightweight static site generation in Java.");
    }

    public static String DEFAULT_SEO_DESCRIPTION(Locale locale) throws Exception {
        return TranslationUtil.translate(locale, "LatteSite is an Open Source, SEO-focused, lightweight static site generator (SSG) framework in Java.");
    }

    public static final String LOGOTYPE_URL_PNG = "/logotype.png";

    public static List<String> KEYWORDS(Locale locale) throws Exception {
        List<String> keywords = List.of(
                "java",
                "static site generator",
                "seo",
                "html",
                "lightweight framework",
                "ssg",
                "website generation",
                "sitemap",
                "structured data",
                "localization"
        );
        List<String> translated = new ArrayList<>();
        for (String keyword : keywords) {
            translated.add(TranslationUtil.translate(locale, keyword));
        }
        return translated;
    }

}
