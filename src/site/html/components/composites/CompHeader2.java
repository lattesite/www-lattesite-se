package site.html.components.composites;

import lattesite.css.StyleBlock;
import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLHeader2Element;
import site.utils.CSSUtil;

public class CompHeader2 extends HTMLHeader2Element {

    public CompHeader2(
            StyleContext sctx,
            HTMLElement parent,
            String text
    ) {
        super(parent, text);
        this.addClass("comp-header-2");

        this.appendStyles(sctx);
    }

    private void appendStyles(StyleContext sctx) {

        StyleBlock container = sctx.addClass("comp-header-2", (c) -> {
            c.setProperty("font-size", "32px");
            c.setProperty("font-weight", "600");
            c.setProperty("line-height", "1.2em");
            c.setProperty("margin", "0");
            c.setProperty("padding", "0 0 12px");
            c.setProperty("word-break", "break-word");
            c.setProperty("text-wrap", "balance");
            c.setProperty("scroll-margin-top", "24px");
        });

        sctx.addBlock(container, CSSUtil.BREAKPOINT_MOBILE, (b) -> {
            b.setProperty("font-size", "28px");
        });

    }

}
