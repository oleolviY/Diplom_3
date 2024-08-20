package Page;

import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By headerLogin = By.xpath("//h2[text()='Вход']");
    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");
    private final By passwordResetButton = By.xpath("//a[text()='Восстановить пароль']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public By getRegistrationButton() {
        return registrationButton;
    }

    public By getPasswordResetButton() {
        return passwordResetButton;
    }

    @Step("Авторизация пользователя")
    public void login(User user) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickSignUpButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void clickResetPassButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(passwordResetButton));
        driver.findElement(passwordResetButton).click();
    }

    @Step("Ожидание заголовка Вход")
    public boolean isHeaderEntranceDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(headerLogin));
        return driver.findElement(headerLogin).isDisplayed();
    }
}

