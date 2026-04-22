package site.html.components.page;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.localization.locale.Locale;
import site.Main;
import site.localization.TranslationUtil;
import site.utils.CSSUtil;

public class CompPageAlertsContainer {

    public CompPageAlertsContainer(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {

        HTMLDivElement outer = new HTMLDivElement(parent);
        outer.addClass("comp-page-alerts-container");

        HTMLDivElement inner = new HTMLDivElement(outer);
        inner.addClass("comp-page-alerts-container__inner");

        if (locale != Main.LOCALE_EN) {
            HTMLDivElement alertTranslated = new HTMLDivElement(inner);
            alertTranslated.setText(TranslationUtil.translate(locale, "This page has been translated by our highly motivated AI interns for your convenience. They're still learning, so a few mistakes may have slipped through. For the most accurate info, please refer to the English version."));
            alertTranslated.setStyling("font-size", "14px");
            alertTranslated.setStyling("font-style", "italic");
            alertTranslated.setStyling("text-align", "center");
            alertTranslated.setStyling("text-wrap", "balance");
            alertTranslated.setStyling("color", CSSUtil.COLOR_888888_GRAY);
            alertTranslated.setStyling("margin-bottom", "1em");
        }

        this.appendStyles(sctx);

    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-page-alerts-container", (c) -> {
            CSSUtil.addFadeUpAnimation(c);
        });

        sctx.addClass("comp-page-alerts-container__inner", (b) -> {
            b.setProperty("margin", "0 auto");
            b.setProperty("max-width", CSSUtil.PAGE_WIDTH);
            b.setProperty("padding", "0 24px 0 24px");
            b.setProperty("text-align", "center");
            b.setProperty("text-wrap", "balance");
        });

    }

}
