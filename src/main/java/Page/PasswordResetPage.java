package Page;

import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordResetPage {
    private final By headerResetPass = By.xpath(".//h2[text()='Восстановление пароля']");
    private final By email = By.xpath(".//label[text()='Email']");
    private final By resetButton = By.xpath(".//button[text()='Восстановить']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private final WebDriver driver;

    public PasswordResetPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getLoginButton() {
        return loginButton;
    }

    @Step("Восстановить пароль")
    public void resetPassword(User user) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(headerResetPass));
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(resetButton).click();
    }

    @Step("Клик по кнопке «Войти» на странице Восстановления пароля")
    public void clickLoginButtonResetPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }
}
