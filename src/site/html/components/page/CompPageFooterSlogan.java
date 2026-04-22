package site.html.components.page;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLImageElement;
import lattesite.localization.locale.Locale;
import site.Config;
import site.utils.CSSUtil;

public class CompPageFooterSlogan {

    public CompPageFooterSlogan(Locale locale, StyleContext sc, HTMLElement parent) throws Exception {

        HTMLDivElement outer = new HTMLDivElement(parent);
        outer.addClass("comp-page-footer-slogan");

        HTMLDivElement inner = new HTMLDivElement(outer);
        inner.addClass("comp-page-footer-slogan__inner");

        new HTMLImageElement(
                inner,
                Config.LOGOTYPE_URL_PNG,
                Config.SLOGAN(locale),
                Config.SLOGAN(locale),
                250,
                47
        );

        HTMLDivElement text = new HTMLDivElement(inner);
        text.setText(Config.SLOGAN(locale));

        this.appendStyles(sc);

    }

    private void appendStyles(StyleContext sc) {

        sc.addClass("comp-page-footer-slogan", (c) -> {
            CSSUtil.addFadeUpAnimation(c);
        });

        sc.addClass("comp-page-footer-slogan__inner", (b) -> {
            b.setProperty("margin", "0 auto");
            b.setProperty("max-width", CSSUtil.PAGE_WIDTH);
            b.setProperty("padding", "64px 24px 64px 24px");
            b.setProperty("text-align", "center");
            b.setProperty("text-wrap", "balance");

            b.setProperty("display", "flex");
            b.setProperty("gap", "16px");
            b.setProperty("flex-direction", "column");
            b.setProperty("align-items", "center");
        });
    }

}
