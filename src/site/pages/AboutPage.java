package site.pages;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLParagraphElement;
import lattesite.localization.locale.Locale;
import lattesite.structureddata.schemas.breadcrumbs.StructuredDataBreadcrumb;
import site.html.components.composites.CompSection;
import site.localization.TranslationUtil;

import java.util.Collections;
import java.util.List;

public class AboutPage extends Page {

    public static String getInternalURL() {
        return "/about/";
    }

    public static String getPageTitle(Locale locale) throws Exception {
        return TranslationUtil.translate(locale, "About");
    }

    public AboutPage(lattesite.structureddata.services.StructuredDataService structuredDataService) {
        super(structuredDataService);
    }

    @Override
    public String getPathWithSlashes(Locale locale) {
        return getInternalURL();
    }

    @Override
    public String getTitle(Locale locale) throws Exception {
        return getPageTitle(locale);
    }

    @Override
    public String getPageHeader(Locale locale) throws Exception {
        return TranslationUtil.translate(locale, "About LatteSite");
    }

    @Override
    protected List<String> getSEOKeywords(Locale locale) {
        return Collections.emptyList();
    }

    @Override
    protected String getSEODescription(Locale locale) throws Exception {
        return TranslationUtil.translate(locale, "About LatteSite, a lightweight Java static site generator focused on SEO, performance, reusable components, localization, and simple website generation.");
    }

    @Override
    protected List<StructuredDataBreadcrumb> getBreadcrumbs(Locale locale) throws Exception {
        return List.of(new StructuredDataBreadcrumb(
                getTitle(locale),
                getInternalURL()
        ));
    }

    @Override
    public void appendBodyElement(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {
        this.appendSectionIntro(locale, sctx, parent);
    }

    private void appendSectionIntro(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {

        HTMLDivElement section = new CompSection(sctx, parent);
        new HTMLParagraphElement(section, TranslationUtil.translate(locale,
                "LatteSite is a lightweight static site generator framework written in Java, built with a strong focus on SEO and performance. It provides a unified way to create fast static websites without stitching together multiple tools, frameworks, or languages."
        ));
        new HTMLParagraphElement(section, TranslationUtil.translate(locale,
                "The framework follows object oriented design principles and uses plain Java objects to generate HTML and reusable components. It is designed to stay simple, flexible, and easy to extend while keeping the number of moving parts low."
        ));

        new HTMLParagraphElement(section, TranslationUtil.translate(locale,
                "LatteSite includes built in support for features such as sitemap generation, structured data, localization, and a basic static web server for local development. It also maintains a clear public and static folder structure to support practical website workflows."
        ));

        new HTMLParagraphElement(section, TranslationUtil.translate(locale,
                "Its goal is to grow into a clean and dependable Java based foundation for building high performance static websites."
        ));

    }

}
