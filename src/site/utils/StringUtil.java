package site.utils;

public abstract class StringUtil {

    public static boolean isEmpty(String s) {
        if (s != null && s.equals("null")) {
            throw new RuntimeException("Something went seriously wrong. The string is literally \"null\".");
        }
        return s == null || s.trim().isEmpty();
    }

    public static String trimNL(String s) {
        return s.replaceAll("\n", " ").replaceAll("\s+", " ").trim();
    }

    public static String[] trimNL(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = trimNL(strings[i]);
        }
        return strings;
    }
}
