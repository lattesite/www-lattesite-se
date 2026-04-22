package site.html.components.page;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.attributes.Role;
import site.utils.CSSUtil;

public class CompPageContentContainer {

    public static HTMLElement appendCompPageContentContainer(StyleContext sctx, HTMLElement parent) {

        HTMLDivElement outer = new HTMLDivElement(parent);
        outer.addClass("comp-page-content-container");
        outer.setRole(Role.MAIN);

        HTMLDivElement inner = new HTMLDivElement(outer);
        inner.addClass("comp-page-content-container__inner");

        appendStyles(sctx);

        return inner;

    }

    private static void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-page-content-container", (c) -> {
            c.setProperty("min-height", "50vh");
            CSSUtil.addFadeUpAnimation(c);
        });

        sctx.addClass("comp-page-content-container__inner", (b) -> {
            b.setProperty("margin", "0 auto");
            b.setProperty("max-width", CSSUtil.PAGE_WIDTH);
            b.setProperty("padding", "24px 24px 24px 24px");
        });

    }

}
