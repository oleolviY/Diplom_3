import Page.HomePage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static config.WebDriverFactory.createWebDriver;
import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Булки»")
    public void bunsSectionTest() {
        homePage.clickSaucesButton();
        homePage.clickBunsButton();
        String expectedText = "Булки";
        String actualText = homePage.getSelectedButtonText();
        assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    public void saucesSectionTest() {
        homePage.clickSaucesButton();
        String expectedText = "Соусы";
        String actualText = homePage.getSelectedButtonText();
        assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинки»")
    public void fillingsSectionTest() {
        homePage.clickFillingsButton();
        String expectedText = "Начинки";
        String actualText = homePage.getSelectedButtonText();
        assertEquals(expectedText, actualText);
    }

    @After
    @Step("Закрытие браузера")
    public void cleanUp() {
        driver.quit();
    }
}
