package site.html.components.composites;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLSelectElement;
import site.utils.CSSUtil;

public class CompInputSelect extends HTMLSelectElement {

    public CompInputSelect(
            StyleContext sctx,
            HTMLElement parent
    ) {
        super(parent);

        this.addClass("comp-input-select");

        this.appendStyles(sctx);
    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-input-select", (c) -> {
            c.setProperty("background", "#fff");
            c.setProperty("border", "1px solid #E0E0E0");
            c.setProperty("box-sizing", "border-box");
            c.setProperty("color", CSSUtil.COLOR_313435_BLACK);
            c.setProperty("border-radius", "12px");
            c.setProperty("font-size", "20px");
            c.setProperty("padding", "16px 16px");
            c.setProperty("transition", "all .24s ease");
        });
        sctx.addClass("comp-input-select:hover", (b) -> {
            b.setProperty("border-color", CSSUtil.COLOR_AE640A_BROWN_POTATO);
        });
        sctx.addClass("comp-input-select:focus", (b) -> {
            b.setProperty("border-color", CSSUtil.COLOR_AE640A_BROWN_POTATO);
        });

        sctx.addClass("comp-input-select:disabled", (b) -> {
            b.setProperty("background-color", "#E9ECEF");
            b.setProperty("color", "lightgray");
            b.setProperty("cursor", "not-allowed");
            b.setProperty("border", "1px solid #CED4DA");
        });

    }

}
