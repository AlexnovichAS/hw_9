package ru.sberbank.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.sberbank.managers.DriverManager;

import java.util.List;
import java.util.Map;

public class MortgagesSecondaryHousingPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'kit-col_lg-bottom')]/h1")
    private WebElement title;

    @FindBy(xpath = "//iframe[contains(@sandbox,'allow-forms') and @title='Основной контент']")
    private WebElement iframeMainContent;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'realty-cost-input')]//label")
    private List<WebElement> boxFill;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'realty-cost-input')]")
    private WebElement realEstateValue;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'initial-fee-input')]")
    private WebElement initialInstallment;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'credit-term-input')]")
    private WebElement loanTerm;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Своя ставка']")
    private WebElement ownRate;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[contains(text(),'Скидка') and contains(text(),'при покупке недвижимости')]")
    private WebElement propertyPurchaseDiscount;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Страхование жизни и здоровья']")
    private WebElement lifeAndHealthInsurance;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'discounts-block')]//span[text()='Электронная регистрация сделки']")
    private WebElement electronicTransactionRegistration;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//li[contains(@data-e2e-id,'result-monthly-payment')]//span[contains(text(),'₽')]")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//div[@data-test-id='main-results-block']//div[contains(@class,'ppr-container')]//span[text()='Процентная ставка']/following-sibling::span/span")
    private WebElement interestRate;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//li[contains(@data-e2e-id,'result-credit-sum')]//span[contains(text(),'₽')]")
    private WebElement creditAmount;

    @FindBy(xpath = "//div[contains(@data-test-id,'main-results-block')]//span[text()='Налоговый вычет']/following-sibling::span/span")
    private WebElement taxDeduction;

    @FindBy(xpath = "//div[contains(@class,'ppr-container--inline')]//span[text()='Необходимый доход']/following-sibling::span/span")
    private WebElement necessaryIncome;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    public MortgagesSecondaryHousingPage checkOpenPage(String namePage) {
        waitUtilElementToBeVisible(title);
        Assertions.assertEquals(namePage, title.getText(), "Заголовок: " + namePage + " отсутствует/не соответствует требуемому");
        DriverManager.getDriverManager().getDriver().switchTo().frame(iframeMainContent);
        return this;
    }

    /**
     * Метод заполнения полей
     *
     * @param values - значение вводимое в поле
     * @return MortgagesSecondaryHousingPage - т.е. остаемся на этой странице
     */
   /* public MortgagesSecondaryHousingPage fillField(Map<String, String> values) {
        String nameField = null;
        for (WebElement webElement : boxFill) {
            nameField = webElement.getText().trim();
            if (values.keySet().stream().anyMatch(x -> x.equalsIgnoreCase(webElement.getText().trim()))) {
                WebElement element = webElement.findElement(By.xpath("./../input"));
                String value = values.entrySet().stream().filter(x -> x.getKey().equalsIgnoreCase(webElement.getText().trim())).findFirst().get().getValue();
                fillInputField(element, value);
                return this;
            }
        }
        Assertions.fail("Поле с наименованием '" + nameField + "' отсутствует на странице" + "'Ипотека на вторичное жильё от'");
        return this;
    }*/

    /**
     * Метод заполнения полей
     *
     * @param fieldNames - значение вводимое в поле
     * @return MortgagesSecondaryHousingPage - т.е. остаемся на этой странице
     */
    public MortgagesSecondaryHousingPage fillField1(List<String> fieldNames, List<String> fieldValues) {
        checkPageIsReady();
        WebElement element;
        for (int i = 0; i < fieldNames.size(); i++) {
            String nameField = fieldNames.get(i);
            String value = fieldValues.get(i);
            switch (nameField) {
                case "Стоимость недвижимости":
                    element = realEstateValue.findElement(By.xpath(".//input"));
                    scrollElementInCenter(element);
                    fillInputField(element, value);
                    break;
                case "Первоначальный взнос":
                    element = initialInstallment.findElement(By.xpath(".//input"));
                    scrollElementInCenter(element);
                    fillInputField(element, value);
                    break;
                case "Срок кредита":
                    element = loanTerm.findElement(By.xpath(".//input"));
                    scrollElementInCenter(element);
                    fillInputField(element, value);
                    break;
                default:
                    Assertions.fail("Чекбокс с наименованием '" + nameField + "' отсутствует на странице " +
                            "'Ипотека на вторичное жильё от'");
            }
        }
        return this;
    }

    /**
     * Управление чекбоксами при расчете ипотеки
     *
     * @param checkBoxNames - значение проверяемое в поле
     * @return MortgagesSecondaryHousingPage - т.е. остаемся на этой странице
     */
    public MortgagesSecondaryHousingPage processTicks(List<String> checkBoxNames, List<String> checkBoxValues) {
        WebElement element = null;
        String nameCheckbox;
        String value;
        for (int i = 0; i < checkBoxNames.size(); i++) {
            nameCheckbox = checkBoxNames.get(i);
            value = checkBoxValues.get(i);
            switch (nameCheckbox) {
                case "Своя ставка":
                    element = ownRate.findElement(By.xpath("./../..//input"));
                    if (!element.getAttribute("aria-checked").equals(value)) {
                        elementClickJs(element);
                    }
                    break;
                case "Скидка 0,3% при покупке недвижимости на Домклик":
                    element = propertyPurchaseDiscount.findElement(By.xpath("./../..//input"));
                    if (!element.getAttribute("aria-checked").equals(value)) {
                        elementClickJs(element);
                    }
                    break;
                case "Страхование жизни и здоровья":
                    element = lifeAndHealthInsurance.findElement(By.xpath("./../..//input"));
                    if (!element.getAttribute("aria-checked").equals(value)) {
                        elementClickJs(element);
                    }
                    break;
                case "Электронная регистрация сделки":
                    element = electronicTransactionRegistration.findElement(By.xpath("./../..//input"));
                    if (!element.getAttribute("aria-checked").equals(value)) {
                        elementClickJs(element);
                    }
                    break;
                default:
                    Assertions.fail("Чекбокс с наименованием '" + nameCheckbox + "' отсутствует на странице " +
                            "'Ипотека на вторичное жильё от'");

            }
            element = element.findElement(By.xpath("./../..//input"));
            Assertions.assertEquals(value, element.getAttribute("aria-checked"),
                    "Проверка чекбокса '" + nameCheckbox + "' была не пройдена");
        }
        return this;
    }

    /**
     * Проверка ошибки относящаяся к конкретному полю на форме
     *
     * @param resultNames - значение проверяемое в поле
     * @return RegistrationFormPage - т.е. остаемся на этой странице
     */
    public MortgagesSecondaryHousingPage checkFieldValues(List<String> resultNames, List<String> resultValues) {
        checkPageIsReady();
        WebElement element = null;
        String nameField;
        String value;
        for (int i = 0; i < resultNames.size(); i++) {
            nameField = resultNames.get(i);
            value = resultValues.get(i);
            switch (nameField) {
                case "Ежемесячный платеж":
                    waitUtilElementToBeClickable(monthlyPayment);
                    element = monthlyPayment;
                    scrollElementInCenter(element);
                    waitUtilElementToBeVisible(element);
                    break;
                case "Процентная ставка":
                    waitUtilElementToBeClickable(interestRate);
                    element = interestRate;
                    scrollElementInCenter(element);
                    waitUtilElementToBeVisible(element);
                    break;
                case "Сумма кредита":
                    waitUtilElementToBeClickable(creditAmount);
                    element = creditAmount;
                    scrollElementInCenter(element);
                    waitUtilElementToBeVisible(element);
                    break;
                case "Налоговый вычет":
                    waitUtilElementToBeClickable(taxDeduction);
                    element = taxDeduction;
                    scrollElementInCenter(element);
                    waitUtilElementToBeVisible(element);
                    break;
                case "Необходимый доход":
                    waitUtilElementToBeClickable(necessaryIncome);
                    element = necessaryIncome;
                    scrollElementInCenter(element);
                    waitUtilElementToBeVisible(element);
                    break;
                default:
                    Assertions.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                            "'Ипотека на вторичное жильё от'");

            }
            Assertions.assertEquals(value, element.getText().replaceAll("₽", "").trim(),
                    "Проверка значения поля: '" + nameField + "' не пройдена");
        }
        return this;
    }
}
