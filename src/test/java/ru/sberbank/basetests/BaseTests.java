package ru.sberbank.basetests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.sberbank.managers.DriverManager;
import ru.sberbank.managers.InitManager;
import ru.sberbank.managers.PageManager;
import ru.sberbank.managers.TestPropManager;
import ru.sberbank.utils.AllureListener;

import static ru.sberbank.utils.PropConst.BASE_URL;

@ExtendWith(AllureListener.class)
public class BaseTests {

    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @AfterEach
    public void afterAll() {
        InitManager.quitFramework();
    }
}
