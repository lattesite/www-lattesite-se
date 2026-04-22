package site.localization;

import lattesite.localization.locale.Locale;
import site.Main;
import site.localization.dbs.*;
import site.utils.DateUtil;
import site.utils.StringUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class TranslationUtil {

    public static final Set<String> accessedKeys = new HashSet<>();

    public static String translate(Locale locale, String key) throws Exception {
        return translate(locale, key, null);
    }

    public static String translate(Locale locale, String key, Map<String, String> parameters) throws Exception {

        key = StringUtil.trimNL(key);

        if (StringUtil.isEmpty(key)) {
            throw new Exception("Key is empty.");
        }

        if (key.contains("—") || key.contains("–")) {
            throw new Exception("Invalid translation key because of -: " + key);
        }
        if (key.contains("’")) {
            throw new Exception("Invalid translation key because of ’: " + key);
        }

        key = key.replaceAll("“", "\"");
        key = key.replaceAll("”", "\"");

        String translation = key;
        if (locale != Main.LOCALE_EN) {

            TranslationsDatabase db = getDB(locale);
            if (db == null) {
                throw new Exception("Database missing for " + locale.getCode());
            }

            accessedKeys.add(key);

            Translation dbTranslation = null;
            for (Translation dbt : db.getAll()) {
                if (dbt.getKey().equals(key)) {
                    dbTranslation = dbt;
                    break;
                }
            }

            if (dbTranslation == null) {
                dbTranslation = new Translation(
                        key,
                        "",
                        ""
                );
                db.add(key, "", DateUtil.toYYYYMMDDHHMMSS(DateUtil.now()));
            }

            translation = dbTranslation.getValue();

            if (StringUtil.isEmpty(translation) || StringUtil.isEmpty(dbTranslation.getUpdated())) {
                translation = key;
            }

        }

        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                translation = translation.replace("$" + entry.getKey(), entry.getValue());
            }
        }

        return translation;

    }

    public static TranslationsDatabase getDB(Locale locale) {
        switch (locale.getCode()) {
            case "es": {
                return Translations_ES.translations;
            }
            case "pt": {
                return Translations_PT.translations;
            }
            case "de": {
                return Translations_DE.translations;
            }
            case "ja": {
                return Translations_JA.translations;
            }
            case "ko": {
                return Translations_KO.translations;
            }
            case "ru": {
                return Translations_RU.translations;
            }
            case "fr": {
                return Translations_FR.translations;
            }
            case "it": {
                return Translations_IT.translations;
            }
            case "zh": {
                return Translations_ZH.translations;
            }
            case "tr": {
                return Translations_TR.translations;
            }
            case "nl": {
                return Translations_NL.translations;
            }
            case "pl": {
                return Translations_PL.translations;
            }
            case "vi": {
                return Translations_VI.translations;
            }
            case "id": {
                return Translations_ID.translations;
            }
            case "cs": {
                return Translations_CS.translations;
            }
            case "uk": {
                return Translations_UK.translations;
            }
            case "el": {
                return Translations_EL.translations;
            }
            case "sv": {
                return Translations_SV.translations;
            }
            case "ro": {
                return Translations_RO.translations;
            }
            case "hu": {
                return Translations_HU.translations;
            }
            case "th": {
                return Translations_TH.translations;
            }
            case "da": {
                return Translations_DA.translations;
            }
            case "sk": {
                return Translations_SK.translations;
            }
            case "fi": {
                return Translations_FI.translations;
            }
            case "bg": {
                return Translations_BG.translations;
            }
            case "sr": {
                return Translations_SR.translations;
            }
            case "nb": {
                return Translations_NB.translations;
            }
            case "hr": {
                return Translations_HR.translations;
            }
            case "lt": {
                return Translations_LT.translations;
            }
            case "sl": {
                return Translations_SL.translations;
            }
            case "ca": {
                return Translations_CA.translations;
            }
            case "et": {
                return Translations_ET.translations;
            }
            case "lv": {
                return Translations_LV.translations;
            }
            case "hi": {
                return Translations_HI.translations;
            }
        }
        return null;
    }

}
