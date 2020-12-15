package ru.appline.framework.sberbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.sberbank.managers.PageManager;

import static ru.appline.framework.sberbank.managers.DriverManager.getDriver;


public class BasePage {

    protected Actions action = new Actions(getDriver());

    protected PageManager app = PageManager.getPageManager();

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    protected void getRidOfCookies() {
        try {
            getDriver().findElement(By.xpath("//button[@class=\"kitt-cookie-warning__close\"]")).click();
        } catch (NoSuchElementException ex) {
        }
    }

    protected void sleepForInterval(int interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected int fromStringToInteger(String str) {
        int i = 0;
        int sign = 1;
        double result = 0;

        if (str == null || str.length() < 1) {
            return 0;
        }
        // trim white spaces
        str = str.trim();
        // check negative or positive
        if (str.charAt(0) == '-') {
            sign *= -1;
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        // calculate value
        while (str.length() > i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                result = result * 10 + (str.charAt(i) - '0');
            }
            if (str.length() == i + 1)
                break;
            i++;
        }
        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int) (sign * result);
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }
}
