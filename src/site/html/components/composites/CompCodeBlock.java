package site.html.components.composites;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLCodeElement;
import lattesite.html.elements.HTMLElement;
import site.utils.CSSUtil;

public class CompCodeBlock extends HTMLCodeElement {

    public CompCodeBlock(StyleContext sctx, HTMLElement parent, String sourceCode) {
        super(parent);
        this.addClass("comp-code-block");

        this.setText(sourceCode);

        this.appendStyles(sctx);
    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-code-block", (c) -> {
            c.setProperty("padding", "12px");
            c.setProperty("white-space", "pre-line");
            c.setProperty("display", "block");
            c.setProperty("line-height", "1.5em");
            c.setProperty("font-size", "14px");
            c.setProperty("box-sizing", "border-box");
            c.setProperty("max-width", "100%");
            c.setProperty("border", "1px solid " + "rgba(255, 255, 255, 0.1)");
            c.setProperty("box-shadow", CSSUtil.BOX_SHADOW);
        });

    }

}
