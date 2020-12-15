package ru.appline.framework.sberbank.pages;

import org.junit.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//li[@class=\"kitt-top-menu__item kitt-top-menu__item_first\"]")
    protected List<WebElement> menuBaseList;

    @FindBy(xpath = "//a[contains(@class, 'kitt-top-menu__link_second')]")
    protected List<WebElement> menuSubList;

    @Step("Выбираем меню \"{nameOfMenuItem}\"")
    public StartPage selectBaseMenu(String nameOfMenuItem) {
        if (nameOfMenuItem.equals("")) {
            Assert.fail("Введите название категории меню для выбора.");
        }
        getRidOfCookies();
        for (WebElement menuItem : menuBaseList) {
            if (menuItem.getText().equalsIgnoreCase(nameOfMenuItem)) {
                action.moveToElement(menuItem).click().build().perform();
                return this;
            }
        }
        Assert.fail("Меню \"" + nameOfMenuItem + "\" не было найдено на стартовой странице!");
        return this;
    }

    @Step("Выбираем подменю \"{nameOfSubMenu}\"")
    public MortgagePage selectSubMenu(String nameOfSubMenu) {
        if (nameOfSubMenu.equals("")) {
            Assert.fail("Введите название категории под-меню для выбора.");
        }
        for (WebElement subMenuItem : menuSubList) {
            if (subMenuItem.getText().equalsIgnoreCase(nameOfSubMenu)) {
                wait.until(ExpectedConditions.visibilityOf(subMenuItem));
                action.moveToElement(subMenuItem).click().build().perform();
                return app.getMortgagePage();
            }
        }
        Assert.fail("Подменю \"" + nameOfSubMenu + "\" не было найдено на стартовой странице!");
        return app.getMortgagePage();
    }
}
