package site.pages;

import lattesite.structureddata.services.StructuredDataService;

import java.util.ArrayList;
import java.util.List;

public class PageService {

    private final StructuredDataService structuredDataService;

    public PageService(
            StructuredDataService structuredDataService
    ) {
        this.structuredDataService = structuredDataService;
    }

    public List<Page> getPages() {

        List<Page> pages = new ArrayList<>();

        pages.add(new PageNotFoundPage(structuredDataService));
        pages.add(new FrontPage(structuredDataService));
        pages.add(new AboutPage(structuredDataService));

        return pages;
    }

}
