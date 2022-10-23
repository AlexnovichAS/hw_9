package ru.sberbank.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для управления пропертями
 *
 * @author Алехнович Александр
 */
public class TestPropManager {

    /**
     * Переменна для хранения данных считанных из файла properties и переданных пользователем
     * Т.е. переменная для хранения пользовательских properties
     *
     * @author Алехнович Александр
     * @see Properties - реализован на основе {@link java.util.Hashtable}
     */
    private final Properties properties = new Properties();


    /**
     * Переменна для хранения объекта TestPropManager
     *
     * @author Алехнович Александр
     */
    private static TestPropManager INSTANCE = null;


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     * Происходит загрузка содержимого файла application.properties в переменную {@link #properties}
     *
     * @author Алехнович Александр
     * @see TestPropManager#getTestPropManager()
     */
    private TestPropManager() {
        loadApplicationProperties();
        loadCustomProperties();
    }


    /**
     * Метод ленивой инициализации TestPropManager
     *
     * @return TestPropManager - возвращает TestPropManager
     * @author Алехнович Александр
     */
    public static TestPropManager getTestPropManager() {
        if (INSTANCE == null) {
            INSTANCE = new TestPropManager();
        }
        return INSTANCE;
    }


    /**
     * Метод подгружает содержимого файла application.properties в переменную {@link #properties}
     * Либо из файла переданного пользователем через настройку -DpropFile={nameFile}
     *
     * @author Алехнович Александр
     * @see TestPropManager#TestPropManager()
     */
    private void loadApplicationProperties() {
        try {
            properties.load(new FileInputStream(
                    new File("src/main/resources/" +
                            System.getProperty("propFile", "application") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод заменяет значение содержащиеся в ключах переменной {@link #properties}
     * Заменяет на те значения, что передал пользователь через maven '-D{name.key}={value.key}'
     * Замена будет происходить только в том случае если пользователь передаст совпадающий key из application.properties
     *
     * @author Алехнович Александр
     * @see TestPropManager#TestPropManager()
     */
    private void loadCustomProperties() {
        properties.forEach((key, value) -> System.getProperties()
                .forEach((customUserKey, customUserValue) -> {
                    if (key.toString().equals(customUserKey.toString()) &&
                            !value.toString().equals(customUserValue.toString())) {
                        properties.setProperty(key.toString(), customUserValue.toString());
                    }
                }));
    }


    /**
     * Метод возвращает либо значение записанное в ключе в переменной {@link #properties},
     * либо defaultValue при отсутствие ключа в переменной {@link #properties}
     *
     * @param key          - ключ, значения которого хотите получить
     * @param defaultValue - значение по умолчанию которое хотите получить если отсутствует ключ в {@link #properties}
     * @return String - возвращает системное св-во либо переданное default значение
     * @author Алехнович Александр
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }


    /**
     * Метод возвращает значения записанное в ключе в переменной {@link #properties}, если нет переменной вернет null
     *
     * @param key - ключ, значения которого хотите получить
     * @return String - строка со значением ключа
     * @author Алехнович Александр
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
