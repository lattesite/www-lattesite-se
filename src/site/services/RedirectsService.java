package site.services;

import lattesite.common.services.FileService;
import lattesite.localization.locale.Locale;
import lattesite.localization.utils.LocalizationUtil;
import site.Main;

import java.util.LinkedHashMap;
import java.util.Map;

public class RedirectsService {

    private final FileService fileService;

    public RedirectsService(FileService fileService) {
        this.fileService = fileService;
    }

    public void generate() throws Exception {

        String newContents = "";
        for (Map.Entry<String, String> item : getAll().entrySet()) {
            for (Locale locale : Main.ALL_LOCALES) {
                newContents += LocalizationUtil.localizeURL(locale, item.getKey()) + "        " + LocalizationUtil.localizeURL(locale, item.getValue()) + "        301\n";
            }
            newContents += "\n";
            newContents += "\n";
            newContents += "\n";
        }
        newContents += "\n";
        newContents += "\n";
        newContents += "\n";

        String contents = this.fileService.readFileAsString("static_root/redirects.txt");

        contents = newContents + contents;

        this.fileService.writeFile("public/_redirects", contents);
    }

    private Map<String, String> getAll() throws Exception {

        Map<String, String> redirects = new LinkedHashMap<>();

        for (String destination : redirects.values()) {
            if (redirects.containsKey(destination)) {
                throw new Exception("Circular redirect destination: " + destination);
            }
        }

        return redirects;

    }

}
