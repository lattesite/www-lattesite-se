package site.html.components.composites;

import lattesite.css.StyleContext;
import lattesite.html.elements.*;
import site.utils.CSSUtil;

public class CompDecoratedTable {

    public static class Table extends HTMLTableElement {

        public Table(StyleContext sctx, HTMLElement parent) {
            super(createContainer(parent));
            this.addClass("comp-decorated-table__table");

            this.appendStyles(sctx);
        }

        private static HTMLElement createContainer(HTMLElement parent) {

            HTMLDivElement container = new HTMLDivElement(parent);
            container.addClass("comp-decorated-table");

            return container;

        }

        private void appendStyles(StyleContext sctx) {

            sctx.addClass("comp-decorated-table", (c) -> {
                c.setProperty("overflow-x", "auto");
                c.setProperty("overflow-y", "hidden");
                c.setProperty("border-radius", "12px");
                c.setProperty("border", "1px solid " + "rgba(255, 255, 255, 0.1)");
                c.setProperty("box-shadow", CSSUtil.BOX_SHADOW);
            });

            sctx.addClass("comp-decorated-table__table", (c) -> {
                c.setProperty("width", "100%");
                c.setProperty("border-collapse", "collapse");
                c.setProperty("background-color", CSSUtil.COLOR_111217_BLACK_ALPHA_50);
            });

        }

    }

    public static class Row extends HTMLTableRowElement {

        public Row(StyleContext sctx, HTMLTableElement parent, boolean header) {
            super(parent);
            this.addClass("comp-decorated-table__table__row");
            if (header) {
                this.addClass("comp-decorated-table__table__row--header");
            }

            this.appendStyles(sctx);
        }

        private void appendStyles(StyleContext sctx) {

            sctx.addClass("comp-decorated-table__table__row", (c) -> {
                c.setProperty("border-bottom", "1px solid " + "rgba(255, 255, 255, 0.1)");
                c.setProperty("transition", "background-color 0.24s ease");
            });
            sctx.addClass("comp-decorated-table__table__row:hover", (c) -> {
                c.setProperty("background-color", CSSUtil.COLOR_30B1FF_BLUE_LIGHT_ALPHA_10);
            });
            sctx.addClass("comp-decorated-table__table__row:last-of-type", (c) -> {
                c.setProperty("border-bottom", "none");
            });
            sctx.addClass("comp-decorated-table__table__row--header", (c) -> {
                c.setProperty("font-weight", "600");
            });
            sctx.addClass("comp-decorated-table__table__row--header:hover", (c) -> {
                c.setProperty("background-color", "transparent");
            });

        }

    }

    public static class Cell extends HTMLTableRowCellElement {

        public Cell(StyleContext sc, HTMLTableRowElement parent) {
            this(sc, parent, null);
        }

        public Cell(StyleContext sctx, HTMLTableRowElement parent, String text) {
            super(parent);
            this.addClass("comp-decorated-table__table__row__cell");
            if (text != null) {
                this.setText(text);
            }

            this.appendStyles(sctx);
        }

        private void appendStyles(StyleContext sctx) {

            sctx.addClass("comp-decorated-table__table__row__cell", (c) -> {
                c.setProperty("padding", "16px");
            });
            sctx.addClass("comp-decorated-table__table__row__cell", CSSUtil.BREAKPOINT_MOBILE, (c) -> {
            });

        }

    }

}
