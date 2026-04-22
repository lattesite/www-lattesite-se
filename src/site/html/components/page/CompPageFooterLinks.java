package site.html.components.page;

import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLAnchorElement;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.attributes.Role;
import lattesite.localization.locale.Locale;
import lattesite.localization.utils.LocalizationUtil;
import site.html.components.composites.CompButtonLink;
import site.pages.AboutPage;
import site.utils.CSSUtil;

import java.util.ArrayList;
import java.util.List;

public class CompPageFooterLinks {

    public CompPageFooterLinks(Locale locale, StyleContext sctx, HTMLElement parent) throws Exception {

        List<Link> perks = new ArrayList<>();
        perks.add(new Link(AboutPage.getPageTitle(locale), LocalizationUtil.localizeURL(locale, AboutPage.getInternalURL())));

        HTMLDivElement outer = new HTMLDivElement(parent);
        outer.addClass("comp-page-footer-links");
        outer.setRole(Role.CONTENTINFO);

        HTMLDivElement inner = new HTMLDivElement(outer);
        inner.addClass("comp-page-footer-links__inner");

        for (Link method : perks) {
            CompButtonLink elemLink = new CompButtonLink(
                    sctx,
                    inner,
                    method.getHref(),
                    method.getLabel(),
                    CompButtonLink.Size.SMALL,
                    CompButtonLink.Color.BLUE
            );
            if (!method.isFollow()) {
                elemLink.setRelationship(HTMLAnchorElement.Relationship.NO_FOLLOW);
            }
        }

        this.appendStyles(sctx);
    }

    private static class Link {

        private final String label;
        private final String href;
        private final boolean follow;

        public Link(String label, String href) {
            this(label, href, true);
        }

        public Link(String label, String href, boolean follow) {
            this.label = label;
            this.href = href;
            this.follow = follow;
        }

        public String getHref() {
            return this.href;
        }

        public String getLabel() {
            return this.label;
        }

        public boolean isFollow() {
            return this.follow;
        }

    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-page-footer-links", (c) -> {
            CSSUtil.addFadeUpAnimation(c);
        });

        sctx.addClass("comp-page-footer-links__inner", (b) -> {
            b.setProperty("margin", "0 auto");
            b.setProperty("max-width", CSSUtil.PAGE_WIDTH);
            b.setProperty("padding", "48px 24px 48px 24px");

            b.setProperty("display", "flex");
            b.setProperty("gap", "16px");
            b.setProperty("flex-direction", "row");
            b.setProperty("justify-content", "center");
            b.setProperty("flex-wrap", "wrap");
        });

        sctx.addClass("comp-page-footer-links__inner a", CSSUtil.BREAKPOINT_MOBILE, (c) -> {
            c.setProperty("flex-grow", "1");
        });

    }

}
