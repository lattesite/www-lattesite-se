package site.html.components.composites;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLButtonElement;
import lattesite.html.elements.HTMLElement;
import site.utils.CSSUtil;

public class CompButton extends HTMLButtonElement {

    public CompButton(
            StyleContext sctx,
            HTMLElement parent,
            String label,
            Size size,
            Color color
    ) {
        super(parent, label);
        this.setText(label);

        this.addClass("comp-button");
        this.addClass("comp-button--size-" + size.id);
        this.addClass("comp-button--color-" + color.id);

        this.appendStyles(sctx);
    }

    public enum Size {

        SMALL("small"),
        NORMAL("normal"),
        LARGE("large");

        private final String id;

        Size(String id) {
            this.id = id;
        }

    }

    public enum Color {

        BLUE("blue"),
        DISABLED("disabled");

        private final String id;

        Color(String id) {
            this.id = id;
        }

    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-button", (c) -> {
            c.setProperty("text-decoration", "none");
            c.setProperty("display", "inline-block");
            c.setProperty("cursor", "pointer");
            c.setProperty("border", "none");
            c.setProperty("line-height", "1.5em");
            c.setProperty("text-align", "center");
            c.setProperty("transition", "all .24s ease");
            c.setProperty("box-sizing", "border-box");
        });
        sctx.addClass("comp-button:hover", (b) -> {
            b.setProperty("text-decoration", "none");
            b.setProperty("transform", "translateY(-10%)");
        });

        sctx.addClass("comp-button--size-small", (c) -> {
            c.setProperty("font-size", "14px");
            c.setProperty("padding", "4px 8px");
            c.setProperty("border-radius", "4px");
        });
        sctx.addClass("comp-button--size-normal", (c) -> {
            c.setProperty("font-size", "16px");
            c.setProperty("padding", "8px 16px 6px");
            c.setProperty("border-radius", "4px");
        });
        sctx.addClass("comp-button--size-large", (c) -> {
            c.setProperty("font-size", "24px");
            c.setProperty("padding", "8px 32px 6px");
            c.setProperty("border-radius", "4px");
        });

        sctx.addClass("comp-button--color-blue", (c) -> {
            c.setProperty("background-color", CSSUtil.COLOR_007BC7_BLUE_DARK);
            c.setProperty("border", "1px solid " + CSSUtil.COLOR_30B1FF_BLUE_LIGHT);
            c.setProperty("color", CSSUtil.COLOR_FFFFFF_WHITE);
        });
        sctx.addClass("comp-button--color-blue:hover", (c) -> {
            c.setProperty("background-color", "transparent");
            c.setProperty("color", CSSUtil.COLOR_30B1FF_BLUE_LIGHT);
        });

        sctx.addSelector(".comp-button--color-disabled, .comp-button:disabled", (c) -> {
            c.setProperty("background-color", "gray");
            c.setProperty("border", "1px solid gray");
            c.setProperty("color", "white");
            c.setProperty("cursor", "not-allowed");
        });
        sctx.addSelector(".comp-button--color-disabled:hover, .comp-button:disabled:hover", (c) -> {
            c.setProperty("transform", "translateY(0)");
        });

    }

}
