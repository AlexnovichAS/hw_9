package ru.sberbank.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StartPage extends BasePage {

    /**
     * @author Алехнович Александр
     * Закрытие сообщения cookies
     */
    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement cookiesBtnClose;

    /**
     * @author Алехнович Александр
     * Лист элементов главного меню
     */
    @FindBy(xpath = "//li[contains(@class,'kitt-top-menu__item_first')]")
    private List<WebElement> listBaseMenu;

    /**
     * @author Алехнович Александр
     * Лист элементов подменю в элементе главного меню "Ипотека"
     */
    @FindBy(xpath = "//div[contains(@class,'kitt-top-menu__column_subaction')]//li[@class='kitt-top-menu__item']")
    private List<WebElement> listSubMenu;

    /**
     * Закрытие сообщения cookies
     *
     * @return StartPage - т.е. остаемся на этой странице
     */
    @Step("Закрытия сообщения cookies")
    public StartPage closeCookiesDialog() {
        if (isDisplayedElement(cookiesBtnClose)) {
            waitUtilElementToBeClickable(cookiesBtnClose).click();
            wait.until(ExpectedConditions.attributeContains(cookiesBtnClose, "class", "close"));
        }
        return this;
    }

    /**
     * Функция наведения мыши на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     * @return StartPage - т.е. остаемся на этой странице
     */
    @Step("Выбираем '{nameBaseMenu}' в главном меню")
    public StartPage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().contains(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                WebElement element = menuItem.findElement(By.xpath("./a"));
                wait.until(ExpectedConditions.attributeContains(element, "aria-expanded", "true"));
                return this;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    /**
     * Функция клика на любое подменю
     *
     * @param nameSubMenu - наименование подменю
     * @return StartPage - т.е. переходим на страницу {@link StartPage}
     */
    @Step("Выбираем '{nameSubMenu}' в подменю главного меню")
    public MortgagesSecondaryHousingPage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getMortgagesSecondaryHousingPage();
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getMortgagesSecondaryHousingPage();
    }
}
