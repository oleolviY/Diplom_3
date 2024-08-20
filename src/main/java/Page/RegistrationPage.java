package Page;

import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final By registrationHeader = By.xpath(".//h2[text()='Регистрация']");
    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private final By incorrectPassMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForSignUpHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationHeader));
    }

    public By getLoginButton() {
        return loginButton;
    }

    @Step("Регистрация пользователя")
    public void registrationUser(User user) {
        waitForSignUpHeader();
        driver.findElement(nameField).sendKeys(user.getName());
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(registrationButton).click();
    }

    @Step("Регистрация пользователя с некорректным паролем")
    public void registrationUserWithIncorrectPass(User user) {
        waitForSignUpHeader();
        driver.findElement(nameField).sendKeys(user.getName());
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys("12345");
        driver.findElement(registrationButton).click();
        driver.findElement(incorrectPassMessage).isDisplayed();
    }

    @Step("Клик по кнопке «Войти в аккаунт» в форме регистрации")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Получить текст сообщения об ошибке")
    public String getIncorrectPassMessageText() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(incorrectPassMessage));
        return driver.findElement(incorrectPassMessage).getText();
    }
}
