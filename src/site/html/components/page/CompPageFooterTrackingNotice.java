package site.html.components.page;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLParagraphElement;
import lattesite.localization.locale.Locale;
import site.localization.TranslationUtil;
import site.utils.CSSUtil;

public class CompPageFooterTrackingNotice {

    public CompPageFooterTrackingNotice(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {

        HTMLDivElement outer = new HTMLDivElement(parent);
        outer.addClass("comp-page-footer-tracking-notice");

        HTMLDivElement inner = new HTMLDivElement(outer);
        inner.addClass("comp-page-footer-tracking-notice__inner");

        HTMLParagraphElement paragraphElement = new HTMLParagraphElement(inner);
        paragraphElement.setInnerHTML(TranslationUtil.translate(locale, "<p>This website uses <a href=\"https://www.pageviews.online/\" rel=\"nofollow\">PageviewsOnline Site Analytics</a> - a privacy-first, cookie-free web analytics tool.</p>"));

        this.appendStyles(sctx);
    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-page-footer-tracking-notice", (c) -> {
            CSSUtil.addFadeUpAnimation(c);
        });

        sctx.addClass("comp-page-footer-tracking-notice__inner", (b) -> {
            b.setProperty("margin", "0 auto");
            b.setProperty("max-width", CSSUtil.PAGE_WIDTH);
            b.setProperty("padding", "0 24px 48px 24px");

            b.setProperty("text-align", "center");
            b.setProperty("font-size", "14px");

        });

    }

}
