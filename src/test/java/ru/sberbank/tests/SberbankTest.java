package ru.sberbank.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.sberbank.basetests.BaseTests;
import ru.sberbank.pages.StartPage;

import java.util.List;

public class SberbankTest extends BaseTests {

    @ParameterizedTest(name = "Проверка работы интернет магазина ДНС: {arguments}")
    @MethodSource("ru.sberbank.data.DataProvider#provideCheckingProductsList")
    public void testAddingGoodsToCartNew(String selectBaseMenu, String selectSubMenu, String title, List<String> fieldNames,
                                         List<String> fieldValues, List<String> checkBoxNames, List<String> checkBoxValues,
                                         List<String> resultNames, List<String> resultValues) {
        app.getPage(StartPage.class)
                .closeCookiesDialog()
                .selectBaseMenu(selectBaseMenu)
                .selectSubMenu(selectSubMenu)
                .checkOpenPage(title)
                .fillField(fieldNames,fieldValues)
                .processTicks(checkBoxNames,checkBoxValues)
                .checkFieldValues(resultNames,resultValues);
    }
}
