package site.html.components.composites;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import site.utils.CSSUtil;
import site.utils.StringUtil;

public class CompAlertBox extends HTMLDivElement {

    public CompAlertBox(StyleContext sctx, HTMLElement parent, Type type) {
        this(sctx, parent, type, "");
    }

    public CompAlertBox(StyleContext sctx, HTMLElement parent, Type type, String text) {
        super(parent);
        this.addClass("comp-alert-box");
        this.addClass("comp-alert-box--type-" + type.id);

        if (!StringUtil.isEmpty(text)) {
            this.setText(text);
        }

        this.appendStyles(sctx);
    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-alert-box", (c) -> {
            c.setProperty("border-radius", "12px");
            c.setProperty("padding", "16px");
            c.setProperty("margin-bottom", "24px");
            c.setProperty("line-height", "1.5");
        });

        sctx.addClass("comp-alert-box--type-info", (c) -> {
            c.setProperty("background-color", "#E5F6FF");
            c.setProperty("border", "1px solid #8BD8FF");
            c.setProperty("color", "#0074AD");
        });

        sctx.addClass("comp-alert-box--type-pink", (c) -> {
            c.setProperty("background-color", "#F5D0F1");
            c.setProperty("border", "1px solid #ecb3d9");
            c.setProperty("color", "#980867");
        });

        sctx.addClass("comp-alert-box--type-warning", (c) -> {
            c.setProperty("background-color", "#fff3cd");
            c.setProperty("border", "1px solid #c6aa5a");
            c.setProperty("color", "#664d03");
        });

        sctx.addClass("comp-alert-box--type-danger", (c) -> {
            c.setProperty("background-color", "#f8d7da");
            c.setProperty("border", "1px solid #f5c2c7");
            c.setProperty("color", "#842029");
        });

        sctx.addClass("comp-alert-box--type-subtle", (c) -> {
            c.setProperty("background-color", CSSUtil.COLOR_FFFFFF_WHITE);
            c.setProperty("color", CSSUtil.COLOR_0A0A0A_BLACK);
        });

    }

    public enum Type {

        PINK("pink"),
        INFO("info"),
        WARNING("warning"),
        DANGER("danger"),
        SUBTLE("subtle");

        private final String id;

        Type(String id) {
            this.id = id;
        }

    }

}
