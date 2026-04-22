package site.html.components.composites;

import lattesite.css.StyleContext;
import lattesite.html.elements.*;
import site.utils.StringUtil;

public class CompTOC extends HTMLDivElement {

    private final HTMLUnsortedListElement ul;

    public CompTOC(StyleContext sctx, HTMLElement parent) {
        super(parent);
        this.addClass("comp-toc");

        this.ul = new HTMLUnsortedListElement(this);

        this.appendStyles(sctx);
    }

    public void addHeader(CompHeader2 header) {

        if (StringUtil.isEmpty(header.getID())) {
            String id = createID(header.getTextContents());
            header.setID(id);
        }

        HTMLListItemElement li = new HTMLListItemElement(ul);
        new HTMLAnchorElement(
                li,
                "#" + header.getID(),
                header.getTextContents(),
                header.getTextContents()
        );
    }

    public void addHeader(CompHeader3 header) {

        if (StringUtil.isEmpty(header.getID())) {
            String id = createID(header.getTextContents());
            header.setID(id);
        }

        HTMLListItemElement li = new HTMLListItemElement(ul);
        li.setStyling("margin-left", "2em");
        new HTMLAnchorElement(
                li,
                "#" + header.getID(),
                header.getTextContents(),
                header.getTextContents()
        );
    }

    private String createID(String id) {
        id = id.toLowerCase();
        id = id.replaceAll("-", "-");
        id = id.replaceAll("'", "-");
        id = id.replaceAll("\\.", "-");
        id = id.replaceAll(",", "-");
        id = id.replaceAll(":", "-");
        id = id.replaceAll("\\?", "-");

        id = id.replaceAll("\\s+", "-");
        id = id.replaceAll("-+", "-");

        if (id.endsWith("-")) {
            id = id.substring(0, id.length() - 1);
        }
        return id;
    }

    private void appendStyles(StyleContext sctx) {

    }

}
