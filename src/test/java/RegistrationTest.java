import Page.HomePage;
import Page.LoginPage;
import Page.RegistrationPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.UserSteps;

import java.time.Duration;

import static config.WebDriverFactory.createWebDriver;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {
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
        homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void signUpTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        WebElement elementSignUP = driver.findElement(loginPage.getRegistrationButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementSignUP);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getRegistrationButton()));
        loginPage.clickSignUpButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationUser(user);
        loginPage.login(user);
        String actualTextOrderButton = homePage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Проверка появления сообщения Некорретный пароль")
    public void signUpWithIncorrectPassTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        WebElement elementSignUP = driver.findElement(loginPage.getRegistrationButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementSignUP);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getRegistrationButton()));
        loginPage.clickSignUpButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationUserWithIncorrectPass(user);
        String actualText = registrationPage.getIncorrectPassMessageText();
        String expectedText = "Некорректный пароль";
        assertEquals(expectedText, actualText);
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
