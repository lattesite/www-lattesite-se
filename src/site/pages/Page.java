package site.pages;

import lattesite.css.StyleContext;
import lattesite.css.fontface.FontFace;
import lattesite.css.fontface.FontFaceDisplay;
import lattesite.css.keyframes.KeyFrames;
import lattesite.html.elements.*;
import lattesite.localization.locale.Locale;
import lattesite.localization.utils.LocalizationUtil;
import lattesite.structureddata.schemas.breadcrumbs.StructuredDataBreadcrumb;
import lattesite.structureddata.schemas.breadcrumbs.StructuredDataBreadcrumbList;
import lattesite.structureddata.schemas.page.StructuredDataAboutPage;
import lattesite.structureddata.schemas.page.StructuredDataWebPage;
import lattesite.structureddata.schemas.website.StructuredDataWebSite;
import lattesite.structureddata.services.StructuredDataService;
import site.Config;
import site.Main;
import site.html.components.page.*;
import site.localization.TranslationUtil;
import site.utils.CSSUtil;
import site.utils.StructuredDataUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class Page implements lattesite.page.Page {

    private final StructuredDataService structuredDataService;

    public Page(
            StructuredDataService structuredDataService
    ) {
        this.structuredDataService = structuredDataService;
    }

    public abstract String getPageHeader(Locale locale) throws Exception;

    protected abstract String getSEODescription(Locale locale) throws Exception;

    protected abstract List<StructuredDataBreadcrumb> getBreadcrumbs(Locale locale) throws Exception;

    public boolean isHidden() {
        return false;
    }

    protected abstract List<String> getSEOKeywords(Locale locale) throws Exception;

    public List<StructuredDataBreadcrumb> getBreadcrumbs(Locale locale, boolean withBaseURL) throws Exception {
        List<StructuredDataBreadcrumb> bcs = new ArrayList<>();

        if (withBaseURL) {
            bcs.add(new StructuredDataBreadcrumb(TranslationUtil.translate(locale, "Home"), LocalizationUtil.addBaseURL(locale, FrontPage.getInternalURL())));
        } else {
            bcs.add(new StructuredDataBreadcrumb(TranslationUtil.translate(locale, "Home"), LocalizationUtil.localizeURL(locale, FrontPage.getInternalURL())));
        }

        for (StructuredDataBreadcrumb bc : this.getBreadcrumbs(locale)) {
            if (withBaseURL) {
                bcs.add(new StructuredDataBreadcrumb(bc.getName(), LocalizationUtil.addBaseURL(locale, bc.getItem())));
            } else {
                bcs.add(new StructuredDataBreadcrumb(bc.getName(), LocalizationUtil.localizeURL(locale, bc.getItem())));
            }
        }

        return bcs;
    }

    @Override
    public final void appendHeadElements(Locale locale, StyleContext styleContext, HTMLHeadElement head) throws Exception {

        String pageTitle = (this instanceof FrontPage) ? this.getTitle(locale) : this.getTitle(locale) + " · " + Config.PAGE_TITLE_SUFFIX;
        String faviconURL = "/favicon.png";

        List<String> pageKeywords = new ArrayList<>();
        pageKeywords.addAll(this.getSEOKeywords(locale));
        pageKeywords.addAll(Config.KEYWORDS(locale));

        String canonicalPageURL = LocalizationUtil.addBaseURL(locale, this.getPathWithSlashes(locale));

        String pageThumbnailImageURL = LocalizationUtil.addBaseURL(Main.LOCALE_EN, "/images/opengraph/opengraph.jpg");

        if (this.isHidden()) {
            new HTMLMetaElement(head, "noindex").setName("robots");
        }

        new HTMLTitleElement(head, pageTitle);
        new HTMLMetaElement(head, "width=device-width, initial-scale=1").setName("viewport");
        new HTMLMetaElement(head, "text/html; charset=utf-8").setHttpEquiv("content-type");
        new HTMLMetaElement(head, this.getSEODescription(locale)).setName("description");
        new HTMLMetaElement(head, "#192A35").setName("theme-color");
        new HTMLMetaElement(head, String.join(", ", pageKeywords)).setName("keywords");

        new HTMLLinkElement(head, "canonical", canonicalPageURL);
        for (Locale l : Main.ALL_LOCALES) {
            new HTMLLinkElement(head, "alternate", LocalizationUtil.addBaseURL(l, this.getPathWithSlashes(locale))).setAttribute("hreflang", l.getCode());
        }
        new HTMLLinkElement(head, "alternate", LocalizationUtil.addBaseURL(Main.LOCALE_EN, this.getPathWithSlashes(locale))).setAttribute("hreflang", "x-default");

        new HTMLLinkElement(head, "shortcut icon", faviconURL);
        new HTMLLinkElement(head, "apple-touch-icon", faviconURL, "image/x-icon");

        new HTMLMetaElement(head, canonicalPageURL).setProperty("og:url");
        new HTMLMetaElement(head, "website").setProperty("og:type");
        new HTMLMetaElement(head, Config.SITE_NAME).setProperty("og:site_name");
        new HTMLMetaElement(head, pageTitle).setProperty("og:title");
        new HTMLMetaElement(head, this.getSEODescription(locale)).setProperty("og:description");
        new HTMLMetaElement(head, pageThumbnailImageURL).setProperty("og:image");
        new HTMLMetaElement(head, "image/jpg").setProperty("og:image:type");
        new HTMLMetaElement(head, this.getSEODescription(locale)).setProperty("og:image:alt");
        new HTMLMetaElement(head, "1920").setProperty("og:image:width");
        new HTMLMetaElement(head, "1080").setProperty("og:image:height");

        new HTMLMetaElement(head, "summary_large_image").setName("twitter:card");
        new HTMLMetaElement(head, pageTitle).setName("twitter:title");
        new HTMLMetaElement(head, this.getSEODescription(locale)).setName("twitter:description");
        new HTMLMetaElement(head, pageThumbnailImageURL).setName("twitter:image");
        new HTMLMetaElement(head, "image/jpg").setName("twitter:image:type");
        new HTMLMetaElement(head, this.getSEODescription(locale)).setName("twitter:image:alt");

        lattesite.structureddata.schemas.organization.StructuredDataOrganization structuredDataOrganization = StructuredDataUtil.getOrganization(locale);

        StructuredDataBreadcrumbList structuredDataBreadcrumbList = new StructuredDataBreadcrumbList(canonicalPageURL + "#breadcrumbs");
        structuredDataBreadcrumbList.addBreadcrumbs(this.getBreadcrumbs(locale, true));
        HTMLScriptElement scriptCrumbs = new HTMLScriptElement(head);
        scriptCrumbs.setType("application/ld+json");
        scriptCrumbs.setInnerHTML(structuredDataService.toJSON(structuredDataBreadcrumbList));

        StructuredDataWebSite structuredDataWebSite = StructuredDataUtil.getWebsite(locale);
        HTMLScriptElement scriptWebSite = new HTMLScriptElement(head);
        scriptWebSite.setType("application/ld+json");
        scriptWebSite.setInnerHTML(structuredDataService.toJSON(structuredDataWebSite));

        StructuredDataWebPage sdWebPage = StructuredDataUtil.getWebPage(
                locale,
                this.getTitle(locale),
                canonicalPageURL,
                this.getSEODescription(locale)
        );
        HTMLScriptElement scriptWebPage = new HTMLScriptElement(head);
        scriptWebPage.setType("application/ld+json");
        scriptWebPage.setInnerHTML(structuredDataService.toJSON(sdWebPage));

        StructuredDataAboutPage sdAboutPage = StructuredDataUtil.getAboutPage(locale);
        HTMLScriptElement scriptSDAboutPage = new HTMLScriptElement(head);
        scriptSDAboutPage.setType("application/ld+json");
        scriptSDAboutPage.setInnerHTML(structuredDataService.toJSON(sdAboutPage));

        HTMLScriptElement scriptSDOrganization = new HTMLScriptElement(head);
        scriptSDOrganization.setType("application/ld+json");
        scriptSDOrganization.setInnerHTML(structuredDataService.toJSON(structuredDataOrganization));

        /*
         * Global CSS
         */

        styleContext.addSelector("*:focus", (sc) -> {
            sc.setProperty("outline", 0);
        });

        styleContext.addSelector("html", (sc) -> {
            sc.setProperty("scroll-behavior", "smooth");
        });

        styleContext.addSelector("body", (sc) -> {
            sc.setProperty("margin", "0 auto");
            sc.setProperty("background-color", CSSUtil.COLOR_PAGE_BACKGROUND);
            sc.setProperty("color", CSSUtil.COLOR_PAGE_TEXT);
            sc.setProperty("font-size", "18px");

            sc.setProperty("background-image", "url(/images/backgrounds/background.jpg)");
            sc.setProperty("background-position", "center center");
        });

        styleContext.addSelector("body, button, input, select", (b) -> {
            b.setProperty("font-size", "18px");
            b.setProperty("font-family", "MainFont, sans-serif");
        });

        styleContext.addSelector("img", (b) -> {
            b.setProperty("max-width", "100%");
            b.setProperty("height", "auto");
        });

        styleContext.addSelector("p", (b) -> {
            b.setProperty("margin-top", 0);
            b.setProperty("line-height", "1.5em");
        });

        styleContext.addSelector("option", (b) -> {
            b.setProperty("cursor", "pointer");
        });

        styleContext.addSelector("a", (b) -> {
            b.setProperty("color", CSSUtil.COLOR_30B1FF_BLUE_LIGHT);
            b.setProperty("text-decoration", "none");
        });
        styleContext.addSelector("a:hover", (b) -> {
            b.setProperty("text-decoration", "2px underline");
        });

        styleContext.addSelector("ul", (b) -> {
            b.setProperty("list-style", "disc");
            b.setProperty("padding-left", "2em");
        });
        styleContext.addSelector("ol", (b) -> {
            b.setProperty("padding-left", "2em");
        });
        styleContext.addSelector("ul li, ol li", (b) -> {
            b.setProperty("margin-top", "0");
            b.setProperty("line-height", "1.5");
            b.setProperty("margin-bottom", "0.25em");
        });

        styleContext.addSelector("table", (b) -> {
            b.setProperty("width", "100%");
        });

        styleContext.addSelector("code", (b) -> {
            b.setProperty("font-family", "monospace");
            b.setProperty("background-color", "rgba(0,0,0,0.50)");
            b.setProperty("overflow", "auto");
            b.setProperty("padding", "4px 4px");
        });

        styleContext.addSelector("blockquote", (b) -> {
            b.setProperty("line-height", "1.5em");
            b.setProperty("font-style", "italic");
            b.setProperty("padding", "1em 1em");
            b.setProperty("margin", "0");
            b.setProperty("background-color", CSSUtil.COLOR_FFFFFF_WHITE);
            b.setProperty("border-radius", "12px");
            b.setProperty("box-shadow", CSSUtil.BOX_SHADOW);
        });

        KeyFrames keyFramesFadeInUp = new KeyFrames("fadeInUp");
        keyFramesFadeInUp.put("0%", (b) -> {
            b.setProperty("opacity", "0");
            b.setProperty("transform", "translateY(1rem)");
        });
        keyFramesFadeInUp.put("100%", (b) -> {
            b.setProperty("opacity", "1");
            b.setProperty("transform", "translateY(0)");
        });
        styleContext.addKeyFrames(keyFramesFadeInUp);

        FontFace fontFace = new FontFace("MainFont", "url('/fonts/jost_100_900.woff2') format('woff2')");
        fontFace.setFontDisplay(FontFaceDisplay.BLOCK);
        fontFace.setFontStyle("normal");
        fontFace.setFontWeight("100 900");
        styleContext.addFontFace(fontFace);

        fontFace = new FontFace("MainFont", "url('/fonts/jost_100_900_italic.woff2') format('woff2')");
        fontFace.setFontDisplay(FontFaceDisplay.BLOCK);
        fontFace.setFontStyle("italic");
        fontFace.setFontWeight("100 900");
        styleContext.addFontFace(fontFace);

    }

    @Override
    public final void appendBodyElements(Locale locale, StyleContext sctx, HTMLBodyElement parent) throws Exception {

        new CompPageHeader(locale, sctx, parent);
        new CompPageBanner(sctx, parent, this.getPageHeader(locale), this.getSEODescription(locale));

        new CompPageAlertsContainer(locale, sctx, parent);
        new CompPageBreadcrumbs(sctx, parent, this.getBreadcrumbs(locale, false));

        HTMLElement mainContent = CompPageContentContainer.appendCompPageContentContainer(sctx, parent);
        appendBodyElement(locale, sctx, mainContent);

        new CompPageFooterSlogan(locale, sctx, parent);
        new CompPageFooterLinks(locale, sctx, parent);
        new CompPageFooterTrackingNotice(locale, sctx, parent);

        HTMLScriptElement scriptPageViews = new HTMLScriptElement(parent, "https://scripts.pageviews.online/latest.min.js");
        scriptPageViews.setAttribute("async", "true");

    }

    public abstract void appendBodyElement(Locale locale, StyleContext sc, HTMLElement parent) throws Exception;

}
