package site.html.components.page;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLHeader1Element;
import lattesite.html.elements.attributes.Role;
import site.utils.CSSUtil;
import site.utils.StringUtil;

public class CompPageBanner {

    public CompPageBanner(StyleContext sctx, HTMLElement parent, String headerText, String subText) {

        HTMLDivElement container = new HTMLDivElement(parent);
        container.addClass("comp-page-banner");
        container.setRole(Role.BANNER);

        HTMLDivElement inner = new HTMLDivElement(container);
        inner.addClass("comp-page-banner__inner");

        HTMLHeader1Element elemH1 = new HTMLHeader1Element(inner, headerText);
        elemH1.addClass("comp-page-banner__inner__header");

        if (!StringUtil.isEmpty(subText)) {
            HTMLDivElement elemSubText = new HTMLDivElement(inner);
            elemSubText.addClass("comp-page-banner__inner__sub-text");
            elemSubText.setText(subText);
        }

        this.appendStyles(sctx);

    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-page-banner", (c) -> {
            CSSUtil.addFadeUpAnimation(c);
        });

        sctx.addClass("comp-page-banner__inner", (c) -> {
            c.setProperty("margin", "0 auto");
            c.setProperty("max-width", CSSUtil.PAGE_WIDTH);
            c.setProperty("padding", "128px 24px 128px 24px");

            c.setProperty("display", "flex");
            c.setProperty("flex-direction", "column;");
            c.setProperty("gap", "16px");

            c.setProperty("justify-content", "center");
            c.setProperty("align-items", "center");
        });

        sctx.addClass("comp-page-banner__inner__header", (c) -> {
            c.setProperty("text-align", "center");
            c.setProperty("font-size", "72px");
            c.setProperty("font-weight", "600");
            c.setProperty("margin", "0");
            c.setProperty("padding", "0");
            c.setProperty("text-wrap", "balance");
        });
        sctx.addClass("comp-page-banner__inner__header", CSSUtil.BREAKPOINT_MOBILE, (c) -> {
            c.setProperty("font-size", "36px");
        });

        sctx.addClass("comp-page-banner__inner__sub-text", (c) -> {
            c.setProperty("text-align", "center");
            c.setProperty("font-size", "22px");
            c.setProperty("text-wrap", "balance");
            c.setProperty("line-height", "1.5em");
        });

    }

}
