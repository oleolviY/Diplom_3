import Page.AccountPage;
import Page.HomePage;
import Page.LoginPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import steps.UserSteps;

import java.time.Duration;

import static config.WebDriverFactory.createWebDriver;

public class AccountTest extends AbstractTest {
    LoginPage loginPage;
    private WebDriver driver;
    private User user;
    private UserSteps userSteps;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        userSteps = new UserSteps();
        user = new User();
        user.setName(org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(10));
        user.setPassword(org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(10));
        user.setEmail(org.apache.commons.lang3.RandomStringUtils.randomAlphabetic(10) + "@yandex.ru");
        userSteps.createUser(user);
        homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    @Test
    @DisplayName("Проверка перехода в Личный кабинет по клику на «Личный кабинет»")
    public void openPersonalAccByPersonalAccButtonTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.login(user);
        homePage.accountButtonClick();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.isHeaderAccountDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на «Конструктор")
    public void openConstructorByConstructorButtonTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.login(user);
        homePage.accountButtonClick();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickConstructorButton();
        homePage.isHeaderAssembleBurgerDisplayed();
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на логотип")
    public void openConstructorByLogoButtonTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.login(user);
        homePage.accountButtonClick();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogo();
        homePage.isHeaderAssembleBurgerDisplayed();
    }

    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void exitTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.login(user);
        homePage.accountButtonClick();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickExitButton();
        loginPage.isHeaderEntranceDisplayed();
    }

    @After
    @Step("Удаление пользователя и закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (user.getAccessToken() != null) {
            user.setAccessToken(userSteps.getToken(user));
            userSteps.delete(user);
        }
    }
}
