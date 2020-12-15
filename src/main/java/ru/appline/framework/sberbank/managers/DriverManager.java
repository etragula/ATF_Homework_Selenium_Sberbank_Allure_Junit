package ru.appline.framework.sberbank.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.appline.framework.sberbank.utils.PropertyConstants.PATH_TO_DRIVER;

public class DriverManager {

    private static PropertyManager propertyManager = PropertyManager.getPropertyManager();

    private static WebDriver driver;

    private DriverManager() {
    }

    private static void initDriver() {
        System.setProperty("webdriver.chrome.driver", propertyManager.getProperty(PATH_TO_DRIVER));
        driver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        quitDriver();
        driver = null;
    }
}

