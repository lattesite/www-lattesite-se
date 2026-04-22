package site.html.components.composites;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;

public class CompSection extends HTMLDivElement {

    public CompSection(StyleContext sctx, HTMLElement parent) {
        this(sctx, parent, false);
    }

    public CompSection(StyleContext sctx, HTMLElement parent, boolean mini) {
        super(parent);
        if (mini) {
            this.addClass("comp-section--mini");
        } else {
            this.addClass("comp-section");
        }

        this.appendStyles(sctx);

    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-section", (c) -> {
            c.setProperty("margin-bottom", "64px");
        });
        sctx.addClass("comp-section--mini", (c) -> {
            c.setProperty("margin-bottom", "32px");
        });

    }

}
