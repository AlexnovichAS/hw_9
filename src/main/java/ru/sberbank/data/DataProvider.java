package ru.sberbank.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
import java.util.stream.Stream;

public class DataProvider {


    /**
     * Данные для тестирования
     *
     * @author Алехнович Александр
     */
    public static Stream<Arguments> provideCheckingProductsList() {
        List<Object> fieldNames = new ArrayList<>();
        Collections.addAll(fieldNames,"Стоимость недвижимости", "Первоначальный взнос", "Срок кредита");
        List<String> fieldValues = new ArrayList<>();
        Collections.addAll(fieldValues,"5 180 000", "3 058 000", "30");
        List<String> checkBoxNames = new ArrayList<>();
        Collections.addAll(checkBoxNames,"Своя ставка", "Скидка 0,3% при покупке недвижимости на Домклик", "Страхование жизни и здоровья",
                "Электронная регистрация сделки");
        List<String> checkBoxValues = new ArrayList<>();
        Collections.addAll(checkBoxValues,"false", "true", "false", "true");
        List<String> resultNames = new ArrayList<>();
        Collections.addAll(resultNames,"Сумма кредита", "Ежемесячный платеж", "Необходимый доход", "Процентная ставка");
        List<String> resultValues = new ArrayList<>();
        Collections.addAll(resultValues,"2 122 000", "20 852", "35 448", "11");
        return Stream.of(
                Arguments.of("Ипотека", "Ипотека на вторичное жильё","Ипотека на вторичное жильё от 10,4%",
                        fieldNames,fieldValues,checkBoxNames,checkBoxValues,resultNames,resultValues));
    }
}
