package ru.appline.framework.sberbank.basetestclasses;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import ru.appline.framework.sberbank.managers.PageManager;

import static ru.appline.framework.sberbank.managers.InitManager.initFramework;
import static ru.appline.framework.sberbank.managers.InitManager.quitFramework;

public class BaseTest {

    protected PageManager app = PageManager.getPageManager();

    @BeforeClass
    public static void doBeforeClass() {
        initFramework();
    }

    @AfterClass
    public static void doAfterClass() {
        quitFramework();
    }
}
