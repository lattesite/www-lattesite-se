package site.utils;

import lattesite.localization.locale.Locale;
import lattesite.localization.utils.LocalizationUtil;
import lattesite.structureddata.enumeration.IsicV4Category;
import lattesite.structureddata.enumeration.SuggestedGender;
import lattesite.structureddata.schemas.audience.StructuredDataPeopleAudience;
import lattesite.structureddata.schemas.brand.StructuredDataBrand;
import lattesite.structureddata.schemas.geo.StructuredDataCountry;
import lattesite.structureddata.schemas.language.StructuredDataLanguage;
import lattesite.structureddata.schemas.media.StructuredDataImageObject;
import lattesite.structureddata.schemas.organization.StructuredDataSportsOrganization;
import lattesite.structureddata.schemas.page.StructuredDataAboutPage;
import lattesite.structureddata.schemas.page.StructuredDataWebPage;
import lattesite.structureddata.schemas.website.StructuredDataWebSite;
import site.Config;
import site.Main;
import site.pages.AboutPage;

public class StructuredDataUtil {

    public static StructuredDataSportsOrganization getOrganization(Locale locale) throws Exception {

        StructuredDataCountry sdCountry = getCountryWorld(locale);

        StructuredDataImageObject sdLogo = new StructuredDataImageObject(
                LocalizationUtil.addBaseURL(locale.getBaseURL(), "#logotype"),
                Config.LOGOTYPE_URL_PNG
        );
        StructuredDataBrand structuredDataBrand = getBrand(locale);

        StructuredDataSportsOrganization sdOrganization = new StructuredDataSportsOrganization(
                LocalizationUtil.addBaseURL(Main.LOCALE_EN, "/") + "#organization",
                Config.SITE_NAME,
                locale.getBaseURL(),
                Config.DEFAULT_SEO_DESCRIPTION(locale),
                sdCountry,
                new IsicV4Category[]{
                        IsicV4Category.CLASS_9312, // 9312 - Activities of sports clubs
                        IsicV4Category.CLASS_9329, // 9312 - Other amusement and recreation activities n.e.c
                        IsicV4Category.CLASS_9499, // 9312 - Activities of other membership organizations n.e.c
                        IsicV4Category.CLASS_8541, // 8541 - Sports and recreation education
                },
                String.join(", ", Config.KEYWORDS(locale)),
                String.join(", ", Config.KEYWORDS(locale))
        );
        sdOrganization.setFoundingDate("2026-02-01");
        sdOrganization.setEmail(Config.EMAIL);
        sdOrganization.setLogo(sdLogo);
        sdOrganization.setBrand(structuredDataBrand);
        sdOrganization.setOwns(structuredDataBrand);
        sdOrganization.setSameAs(new String[]{});

        return sdOrganization;

    }

    public static StructuredDataWebSite getWebsite(Locale locale) throws Exception {
        return new StructuredDataWebSite(
                LocalizationUtil.addBaseURL(locale.getBaseURL(), "#website"),
                Config.SITE_NAME,
                Config.DEFAULT_SEO_DESCRIPTION(locale),
                locale.getBaseURL(),
                getLanguage(locale)
        );
    }

    public static StructuredDataLanguage getLanguage(Locale locale) {
        return new StructuredDataLanguage(
                LocalizationUtil.addBaseURL(locale.getBaseURL(), "#language"),
                locale.getCode()
        );
    }

    public static StructuredDataCountry getCountryWorld(Locale locale) {
        return new StructuredDataCountry(
                LocalizationUtil.addBaseURL(locale.getBaseURL(), "#country"),
                "World"
        );
    }

    public static StructuredDataBrand getBrand(Locale locale) {
        return new StructuredDataBrand(
                LocalizationUtil.addBaseURL(locale, "/") + "#brand",
                Config.BRAND_NAME
        );
    }

    public static StructuredDataWebPage getWebPage(
            Locale locale,
            String title,
            String canonicalPageURL,
            String description
    ) throws Exception {
        StructuredDataWebSite sdWebSite = getWebsite(locale);
        StructuredDataWebPage sdWebPage = new StructuredDataWebPage(
                canonicalPageURL,
                title,
                canonicalPageURL,
                description,
                sdWebSite,
                getLanguage(locale)
        );
        sdWebPage.setAudience(getAudience(locale));
        return sdWebPage;
    }

    private static StructuredDataPeopleAudience getAudience(Locale locale) {
        StructuredDataCountry sdCountry = getCountryWorld(locale);
        return new StructuredDataPeopleAudience(
                LocalizationUtil.addBaseURL(locale, "#audience"),
                sdCountry,
                "twitch streamers, live gaming streamers",
                SuggestedGender.UNISEX
        );
    }

    public static StructuredDataAboutPage getAboutPage(Locale locale) throws Exception {
        return new StructuredDataAboutPage(
                LocalizationUtil.addBaseURL(locale, "/") + "#about-page",
                Config.SITE_NAME,
                LocalizationUtil.addBaseURL(locale, AboutPage.getInternalURL()),
                getOrganization(locale)
        );
    }

}
