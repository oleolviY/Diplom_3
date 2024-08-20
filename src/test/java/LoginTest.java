import Page.HomePage;
import Page.LoginPage;
import Page.PasswordResetPage;
import Page.RegistrationPage;
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

public class LoginTest extends AbstractTest {
    private WebDriver driver;
    private User user;
    private UserSteps userSteps;

    private HomePage homePage;
    private LoginPage loginPage;


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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginButtonTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.login(user);
        String actualTextOrderButton = homePage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void accountButtonTest() {
        homePage.accountButtonClick();
        loginPage = new LoginPage(driver);
        loginPage.login(user);
        String actualTextOrderButton = homePage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginButtonRegistrationPageTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        WebElement registrationButtonElement = driver.findElement(loginPage.getRegistrationButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", registrationButtonElement);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getRegistrationButton()));
        loginPage.clickSignUpButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        WebElement elementLogin = driver.findElement(registrationPage.getLoginButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementLogin);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getLoginButton()));
        registrationPage.clickLoginButton();
        loginPage.login(user);
        String actualTextOrderButton = homePage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginButtonResetPasswordPageTest() {
        homePage.clickLoginButton();
        loginPage = new LoginPage(driver);
        WebElement elementResetPass = driver.findElement(loginPage.getPasswordResetButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementResetPass);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getPasswordResetButton()));
        loginPage.clickResetPassButton();
        PasswordResetPage resetPasswordPage = new PasswordResetPage(driver);
        WebElement elementLogin = driver.findElement(resetPasswordPage.getLoginButton());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementLogin);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(resetPasswordPage.getLoginButton()));
        resetPasswordPage.clickLoginButtonResetPasswordPage();
        loginPage.login(user);
        String actualTextOrderButton = homePage.getCreateOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @After
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
