package site.html.components.page;

import lattesite.css.StyleBlock;
import lattesite.css.StyleContext;
import lattesite.html.elements.HTMLAnchorElement;
import lattesite.html.elements.HTMLDivElement;
import lattesite.html.elements.HTMLElement;
import lattesite.html.elements.HTMLSpanElement;
import lattesite.structureddata.schemas.breadcrumbs.StructuredDataBreadcrumb;
import site.utils.CSSUtil;

import java.util.List;

public class CompPageBreadcrumbs {

    public CompPageBreadcrumbs(StyleContext sctx, HTMLElement parent, List<StructuredDataBreadcrumb> breadcrumbs) {

        HTMLDivElement outer = new HTMLDivElement(parent);
        outer.addClass("comp-page-breadcrumbs-container");

        HTMLDivElement inner = new HTMLDivElement(outer);
        inner.addClass("comp-page-breadcrumbs-container__inner");

        for (int i = 0; i < breadcrumbs.size(); i++) {
            StructuredDataBreadcrumb breadcrumb = breadcrumbs.get(i);
            if (i == breadcrumbs.size() - 1) {
                new HTMLSpanElement(inner, breadcrumb.getName());
            } else {
                HTMLAnchorElement link = new HTMLAnchorElement(
                        inner,
                        breadcrumb.getItem(),
                        breadcrumb.getName()
                );
                link.setInnerHTML(breadcrumb.getName());
                link.addClass("comp-page-breadcrumbs-container__inner__link");
            }

            if (i < breadcrumbs.size() - 1) {
                HTMLSpanElement span = new HTMLSpanElement(inner, "›");
                span.addClass("comp-page-breadcrumbs-container__inner__delimiter");
            }
        }

        this.appendStyles(sctx);

    }

    private void appendStyles(StyleContext sctx) {

        sctx.addClass("comp-page-breadcrumbs-container", (c) -> {
            CSSUtil.addFadeUpAnimation(c);
        });

        sctx.addClass("comp-page-breadcrumbs-container__inner", (b) -> {
            b.setProperty("margin", "0 auto");
            b.setProperty("max-width", CSSUtil.PAGE_WIDTH);
            b.setProperty("padding", "24px 24px 24px 24px");

            b.setProperty("font-size", "16px");
        });

        StyleBlock link = sctx.addClass("comp-page-breadcrumbs-container__inner__link", (b) -> {
            b.setProperty("display", "inline-block");
            b.setProperty("margin-right", "4px");
            b.setProperty("font-weight", "400");
        });
        sctx.addClass("comp-page-breadcrumbs-container__inner__link:hover", (b) -> {
        });
        sctx.addBlock(link, CSSUtil.BREAKPOINT_MOBILE, (b) -> {
            b.setProperty("margin-bottom", "13px");
            b.setProperty("margin-right", "12px");
        });

        sctx.addClass("comp-page-breadcrumbs-container__inner__delimiter", (b) -> {
            b.setProperty("margin-right", "4px");
            b.setProperty("color", CSSUtil.COLOR_999999_LIGHT_GRAY);
            b.setProperty("font-size", "14px");
        });

    }

}
