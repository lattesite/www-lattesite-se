package site.html.components.composites;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLAnchorElement;
import lattesite.html.elements.HTMLElement;
import site.utils.CSSUtil;

public class CompButtonLink extends HTMLAnchorElement {

    public CompButtonLink(
            StyleContext sctx,
            HTMLElement parent,
            String href,
            String label,
            Size size,
            Color color
    ) {
        super(parent, href, label);
        this.setText(label);

        this.addClass("comp-button-link");
        this.addClass("comp-button-link--size-" + size.id);
        this.addClass("comp-button-link--color-" + color.id);

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

        DISABLED("disabled"),
        BLUE("blue"),
        PURPLE("purple");

        private final String id;

        Color(String id) {
            this.id = id;
        }

    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-button-link", (c) -> {
            c.setProperty("text-decoration", "none");
            c.setProperty("display", "inline-block");
            c.setProperty("cursor", "pointer");
            c.setProperty("border", "none");
            c.setProperty("line-height", "1.5em");
            c.setProperty("text-align", "center");
            c.setProperty("text-wrap", "balance");
            c.setProperty("transition", "all .24s ease");
            c.setProperty("box-shadow", CSSUtil.BOX_SHADOW);
        });
        sctx.addClass("comp-button-link:hover", (b) -> {
            b.setProperty("text-decoration", "none");
            b.setProperty("transform", "translateY(-10%)");
        });

        sctx.addClass("comp-button-link--size-small", (c) -> {
            c.setProperty("font-size", "14px");
            c.setProperty("padding", "4px 12px");
            c.setProperty("border-radius", "4px");
        });
        sctx.addClass("comp-button-link--size-normal", (c) -> {
            c.setProperty("font-size", "16px");
            c.setProperty("padding", "8px 16px");
            c.setProperty("border-radius", "4px");
        });
        sctx.addClass("comp-button-link--size-large", (c) -> {
            c.setProperty("font-size", "24px");
            c.setProperty("padding", "8px 32px");
            c.setProperty("border-radius", "4px");
        });
        sctx.addClass("comp-button-link--size-large", CSSUtil.BREAKPOINT_MOBILE, (c) -> {
            c.setProperty("font-size", "16px");
            c.setProperty("padding", "8px 32px");
            c.setProperty("border-radius", "4px");
        });

        sctx.addClass("comp-button-link--color-blue", (c) -> {
            c.setProperty("background-color", CSSUtil.COLOR_007BC7_BLUE_DARK);
            c.setProperty("border", "1px solid " + CSSUtil.COLOR_30B1FF_BLUE_LIGHT);
            c.setProperty("color", CSSUtil.COLOR_FFFFFF_WHITE);
        });
        sctx.addClass("comp-button-link--color-blue:hover", (c) -> {
            c.setProperty("background-color", CSSUtil.COLOR_30B1FF_BLUE_LIGHT_ALPHA_10);
            c.setProperty("color", CSSUtil.COLOR_30B1FF_BLUE_LIGHT);
        });

        sctx.addClass("comp-button-link--color-purple", (c) -> {
            c.setProperty("background-color", CSSUtil.COLOR_9147FF_PURPLE_LIGHT);
            c.setProperty("border", "1px solid " + CSSUtil.COLOR_9147FF_PURPLE_LIGHT);
            c.setProperty("color", CSSUtil.COLOR_FFFFFF_WHITE);
        });
        sctx.addClass("comp-button-link--color-purple:hover", (c) -> {
            c.setProperty("background-color", "white");
            c.setProperty("color", CSSUtil.COLOR_9147FF_PURPLE_LIGHT);
        });

    }

}
