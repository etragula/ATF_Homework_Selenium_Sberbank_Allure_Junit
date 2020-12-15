package ru.appline.framework.sberbank.managers;

import ru.appline.framework.sberbank.pages.StartPage;
import ru.appline.framework.sberbank.pages.MortgagePage;

public class PageManager {

    private static PageManager pageManager;

    StartPage startPage;
    MortgagePage mortgagePage;

    private PageManager() {
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public MortgagePage getMortgagePage() {
        if (mortgagePage == null) {
            mortgagePage = new MortgagePage();
        }
        return mortgagePage;
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }
}
