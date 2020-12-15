package ru.appline.framework.sberbank.pages;

import org.junit.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ru.appline.framework.sberbank.managers.DriverManager.getDriver;

public class MortgagePage extends BasePage {

    @FindBy(xpath = "//iframe[@sandbox]")
    protected WebElement frame;

    private static boolean switchFrameChecker = false;

    @Step("Проверка открытия страницы \"{pageTitle}\"")
    public MortgagePage checkPageIsOpened(String pageTitle) {
        Assert.assertEquals("Заголовок \"" + pageTitle + "\"отсутствует/не соответствует требуемому",
                pageTitle, getDriver().getTitle());
        return this;
    }

    @Step("Проверка заполнения поля \"{whatFieldToFill}\" значением \"{value}\"")
    public MortgagePage fillFields(String whatFieldToFill, int value) {
        switchFrameCheck();
        WebElement field = getDriver().findElement(By.xpath("//div[contains(@data-label, '" + whatFieldToFill + "')]/input"));
        scrollToElementJs(field);
        while (!field.getAttribute("value").isEmpty()) {
            field.sendKeys(Keys.BACK_SPACE);
        }
        field.sendKeys(String.valueOf(value));
        sleepForInterval(500);
        Assert.assertEquals("Значение поля \"" + whatFieldToFill + "\" не соответсвует поданному значению!",
                String.valueOf(value), field.getAttribute("value").replace(" ", ""));
        return this;
    }

    @Step("Проверка чек-бокса \"{whatFieldToFill}\" с необходимым статусом \"{value}\"")
    public MortgagePage selectCheckBoxStatus(String whatFieldToCheck, String value) {
        switchFrameCheck();
        WebElement checkbox = getDriver().findElement(By.xpath("//span[text() = '" + whatFieldToCheck + "']/../..//input"));
//        scrollToElementJs(checkbox);
        if (!checkbox.getAttribute("aria-checked").equalsIgnoreCase(value)) {
            checkbox.click();
        }
        Assert.assertEquals("Значение поля \"" + whatFieldToCheck + "\" не соответсвует поданному значению!",
                value.toLowerCase(), checkbox.getAttribute("aria-checked").toLowerCase());
        return this;
    }

    @Step("Проверка заполнения поля \"{whatFieldToCheck}\" значением \"{value}\"")
    public MortgagePage checkCalculationResults(String whatFieldToCheck, double value) {
        switchFrameCheck();
        WebElement field = getDriver().findElement(By.xpath("//span[text()='" + whatFieldToCheck +
                "']/..//span[contains(@data-e2e-id, 'medium-result')]"));
        scrollToElementJs(field);
        sleepForInterval(1500);
        if (whatFieldToCheck.equals("Процентная ставка")) {
            double actualValue = Double.parseDouble(field.getText()
                    .replace(",", ".")
                    .replace("%", ""));
            Assert.assertEquals(value, actualValue, 9e-10);
            return this;
        }
        Assert.assertEquals("Значение поля \"" + whatFieldToCheck + "\" не соответсвует \"" + value + "\"!",
                (int) value, fromStringToInteger(field.getText()));
        return this;
    }

    private void switchFrameCheck() {
        if (!switchFrameChecker) {
            getDriver().switchTo().frame(frame);
            switchFrameChecker = true;
        }
    }
}

