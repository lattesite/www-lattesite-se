package site.html.components.page;

import lattesite.css.StyleBlock;
import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLAnchorElement;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLImageElement;
import lattesite.html.elements.attributes.Role;
import lattesite.localization.locale.Locale;
import lattesite.localization.utils.LocalizationUtil;
import site.Config;
import site.html.components.composites.CompButtonLink;
import site.pages.FrontPage;
import site.utils.CSSUtil;

public class CompPageHeader {

    public CompPageHeader(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {

        HTMLDivElement container = new HTMLDivElement(parent);
        container.addClass("comp-page-header");
        container.setRole(Role.NAVIGATION);

        HTMLDivElement inner = new HTMLDivElement(container);
        inner.addClass("comp-page-header__inner");

        HTMLDivElement elemCol1 = new HTMLDivElement(inner);
        elemCol1.addClass("comp-page-header__inner__col1");

        HTMLAnchorElement elemLink = new HTMLAnchorElement(elemCol1, LocalizationUtil.localizeURL(locale, FrontPage.getInternalURL()), Config.SITE_NAME);
        new HTMLImageElement(
                elemLink,
                Config.LOGOTYPE_URL_PNG,
                Config.SLOGAN(locale),
                Config.BRAND_NAME,
                150,
                28
        );

        HTMLDivElement elemSubText = new HTMLDivElement(elemCol1);
        elemSubText.setText(Config.SLOGAN(locale));
        elemSubText.setStyling("font-size", "14px");
        elemSubText.setStyling("margin-bottom", "0.5em");

        HTMLDivElement elemCol2 = new HTMLDivElement(inner);
        elemCol2.addClass("comp-page-header__inner__col2");

        this.appendStyles(sctx);

    }

    private void appendLink(StyleContext sctx, HTMLElement parent, String label, String href) {
        this.appendLink(sctx, parent, label, href, null);
    }

    private void appendLink(StyleContext sctx, HTMLElement parent, String label, String href, HTMLAnchorElement.Relationship relationship) {
        appendLink(sctx, parent, label, href, CompButtonLink.Color.BLUE, relationship);
    }

    private CompButtonLink appendLink(StyleContext sctx, HTMLElement parent, String label, String href, CompButtonLink.Color color, HTMLAnchorElement.Relationship relationship) {
        CompButtonLink elemLink = new CompButtonLink(
                sctx,
                parent,
                href,
                label,
                CompButtonLink.Size.NORMAL,
                color
        );
        if (relationship != null) {
            elemLink.setRelationship(relationship);
        }
        return elemLink;
    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-page-header", (c) -> {
            CSSUtil.addFadeUpAnimation(c);
        });

        StyleBlock inner = sctx.addClass("comp-page-header__inner", (c) -> {
            c.setProperty("margin", "0 auto");
            c.setProperty("max-width", CSSUtil.PAGE_WIDTH);
            c.setProperty("padding", "24px 24px 24px 24px");

            c.setProperty("display", "flex");
            c.setProperty("flex-direction", "row");
            c.setProperty("gap", "12px");
        });
        sctx.addBlock(inner, CSSUtil.BREAKPOINT_MOBILE, (b) -> {
            b.setProperty("flex-direction", "column");
            b.setProperty("gap", "16px");
        });

        StyleBlock col1 = sctx.addClass("comp-page-header__inner__col1", (c) -> {
            c.setProperty("font-size", "16px");
        });

        sctx.addBlock(col1, "a", (c) -> {
            c.setProperty("position", "relative");
        });

        StyleBlock col2 = sctx.addClass("comp-page-header__inner__col2", (c) -> {
            c.setProperty("display", "flex");
            c.setProperty("gap", "24px");
            c.setProperty("justify-content", "end");
            c.setProperty("align-content", "center");
            c.setProperty("flex-grow", "1");
            c.setProperty("flex-wrap", "wrap");
        });
        sctx.addBlock(col2, CSSUtil.BREAKPOINT_MOBILE, (b) -> {
            b.setProperty("justify-content", "start");
        });

        sctx.addClass("comp-page-header__inner__col2 a", CSSUtil.BREAKPOINT_MOBILE, (c) -> {
            c.setProperty("flex-grow", "1");
        });

    }

}
