package site.html.components.composites;

import lattesite.css.StyleBlock;
import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLHeader3Element;
import site.utils.CSSUtil;

public class CompHeader3 extends HTMLHeader3Element {

    public CompHeader3(
            StyleContext sctx,
            HTMLElement parent,
            String text
    ) {
        super(parent, text);
        this.addClass("comp-header-3");

        this.appendStyles(sctx);

    }

    private void appendStyles(StyleContext sctx) {

        StyleBlock container = sctx.addClass("comp-header-3", (c) -> {

            c.setProperty("font-size", "22px");
            c.setProperty("font-weight", "600");
            c.setProperty("line-height", "1.2em");
            c.setProperty("margin", "0");
            c.setProperty("padding", "0 0 12px");
            c.setProperty("word-break", "break-word");
            c.setProperty("text-wrap", "balance");
        });

        sctx.addBlock(container, CSSUtil.BREAKPOINT_MOBILE, (b) -> {
            b.setProperty("font-size", "26px");
        });

    }

}
