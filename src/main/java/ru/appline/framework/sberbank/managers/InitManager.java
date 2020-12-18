package ru.appline.framework.sberbank.managers;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.sberbank.utils.PropertyConstants.*;
import static ru.appline.framework.sberbank.managers.DriverManager.quitDriver;

public class InitManager {

    private static PropertyManager propertyManager = PropertyManager.getPropertyManager();

    private static WebDriver driver = DriverManager.getDriver();

    public static void initFramework() {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(propertyManager.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyManager.getProperty(IMPLICITY_WAIT)), TimeUnit.SECONDS);
        driver.get(propertyManager.getProperty(APP_URL));
    }

    public static void quitFramework() {
        quitDriver();
    }
}
