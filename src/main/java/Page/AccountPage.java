package Page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By headerAccount = By.xpath(".//p[text()='Конструктор']");
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Выход")
    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(headerAccount));
        driver.findElement(exitButton).click();
    }

    @Step("Клик по кнопке Конструктор")
    public void clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(headerAccount));
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по Логотипу")
    public void clickLogo() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(headerAccount));
        driver.findElement(logo).click();
    }

    @Step("Проверка наличия раздела Профиль")
    public boolean isHeaderAccountDisplayed() {
        return driver.findElement(headerAccount).isDisplayed();
    }
}
