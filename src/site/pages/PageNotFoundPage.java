package site.pages;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLParagraphElement;
import lattesite.localization.locale.Locale;
import lattesite.structureddata.schemas.breadcrumbs.StructuredDataBreadcrumb;
import lattesite.structureddata.services.StructuredDataService;
import site.html.components.composites.CompSection;
import site.localization.TranslationUtil;

import java.util.Collections;
import java.util.List;

public class PageNotFoundPage extends Page {

    public static String getInternalURL() {
        return "/404/";
    }

    public static String getPageTitle(Locale locale) throws Exception {
        return TranslationUtil.translate(locale, "Page Not Found");
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    public PageNotFoundPage(StructuredDataService structuredDataService) {
        super(structuredDataService);
    }

    @Override
    public String getPageHeader(Locale locale) throws Exception {
        return TranslationUtil.translate(locale, "Someone Has Stolen This Page");
    }

    @Override
    public String getPathWithSlashes(Locale locale) throws Exception {
        return getInternalURL();
    }

    @Override
    public String getTitle(Locale locale) throws Exception {
        return getPageTitle(locale);
    }

    @Override
    protected String getSEODescription(Locale locale) throws Exception {
        return TranslationUtil.translate(locale, "and it wasn't us");
    }

    @Override
    protected List<String> getSEOKeywords(Locale locale) {
        return Collections.emptyList();
    }

    @Override
    protected List<StructuredDataBreadcrumb> getBreadcrumbs(Locale locale) throws Exception {
        return List.of(new StructuredDataBreadcrumb(
                getPageTitle(locale),
                getInternalURL()
        ));
    }

    @Override
    public void appendBodyElement(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {
        this.appendSectionIntro(locale, sctx, parent);
    }

    private void appendSectionIntro(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {

        HTMLDivElement section = new CompSection(sctx, parent);

        new HTMLParagraphElement(section, TranslationUtil.translate(locale, "This page doesn't seem to exist."));

    }

}
