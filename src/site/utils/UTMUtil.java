package site.utils;

public abstract class UTMUtil {

    public static String addUTM(String url) {
        if (StringUtil.isEmpty(url)) {
            return "";
        }

        String hash = "";

        if (url.contains("#")) {
            hash = url.substring(url.lastIndexOf("#"));
            url = url.substring(0, url.lastIndexOf("#"));
        }

        url += url.contains("?") ? "&" : "?";
        url += "utm_source=www.lattesite.se&utm_medium=backlink";

        url += hash;

        return url;
    }

}
