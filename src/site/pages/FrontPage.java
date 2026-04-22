package site.pages;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLElement;
import lattesite.localization.locale.Locale;
import lattesite.structureddata.schemas.breadcrumbs.StructuredDataBreadcrumb;
import lattesite.structureddata.services.StructuredDataService;
import site.Config;
import site.utils.CSSUtil;

import java.util.Collections;
import java.util.List;

public class FrontPage extends Page {

    public static String getInternalURL() {
        return "/";
    }

    public FrontPage(StructuredDataService structuredDataService) {
        super(structuredDataService);
    }

    @Override
    public String getPathWithSlashes(Locale locale) {
        return getInternalURL();
    }

    @Override
    public String getTitle(Locale locale) throws Exception {
        return "LatteSite";
    }

    @Override
    public String getPageHeader(Locale locale) throws Exception {
        return "LatteSite";
    }

    @Override
    protected List<String> getSEOKeywords(Locale locale) {
        return Collections.emptyList();
    }

    @Override
    protected String getSEODescription(Locale locale) throws Exception {
        return Config.DEFAULT_SEO_DESCRIPTION(locale);
    }

    @Override
    protected List<StructuredDataBreadcrumb> getBreadcrumbs(Locale locale) {
        return Collections.emptyList();
    }

    @Override
    public void appendBodyElement(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {
        this.appendSectionIntro(locale, sctx, parent);
        this.appendStyles(sctx);
    }

    private void appendSectionIntro(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {

    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("front-page-preview-section-centered", (c) -> {
            c.setProperty("display", "flex");
            c.setProperty("flex-direction", "column");
            c.setProperty("align-items", "center");
        });

        sctx.addClass("front-page-preview-section-centered p", (c) -> {
            c.setProperty("text-align", "center");
            c.setProperty("text-wrap", "balance");
        });

        sctx.addClass("front-page-preview-section-centered h2", (c) -> {
            c.setProperty("text-align", "center");
        });

        sctx.addClass("front-page-supporters-container", (c) -> {
            c.setProperty("width", "100%");
            c.setProperty("display", "flex");
            c.setProperty("flex-wrap", "wrap");
            c.setProperty("justify-content", "center");
            c.setProperty("gap", "16px");
            c.setProperty("margin-bottom", "1em");
        });

        sctx.addClass("front-page-supporters-container", CSSUtil.BREAKPOINT_MOBILE, (c) -> {
            c.setProperty("max-height", "400px");
            c.setProperty("overflow", "hidden");
        });

        sctx.addClass("front-page-supporters-container a", (c) -> {
            c.setProperty("display", "flex");
            c.setProperty("flex-direction", "column");
            c.setProperty("align-items", "center");
            c.setProperty("gap", "8px");

            c.setProperty("padding", "16px");
            c.setProperty("width", "calc(10% - 10px)");

            c.setProperty("transition", "box-shadow 0.24s ease");

            c.setProperty("background", CSSUtil.COLOR_FFFFFF_WHITE);
            c.setProperty("border", "1px solid " + CSSUtil.COLOR_E0E0E0_LIGHT_GRAY);
            c.setProperty("box-sizing", "border-box");
            c.setProperty("border-radius", "12px");
        });
        sctx.addClass("front-page-supporters-container a", CSSUtil.BREAKPOINT_MOBILE, (c) -> {
            c.setProperty("width", "calc(20% - 0px)");
        });

        sctx.addClass("front-page-supporters-container a:hover", (c) -> {
            c.setProperty("box-shadow", "rgba(0, 0, 0, 0.24) 0px 3px 8px");
            c.setProperty("text-decoration", "none");
        });
        sctx.addClass("front-page-supporters-container a:hover img", (c) -> {
            c.setProperty("transform", "rotate(360deg)");
        });
        sctx.addClass("front-page-supporters-container a img", (c) -> {
            c.setProperty("border-radius", "100%");
            c.setProperty("object-fit", "cover");
            c.setProperty("transition", "transform .56s ease");
        });

    }

}
