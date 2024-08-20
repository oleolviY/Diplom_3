package Page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.RestConfig.HOST;

public class HomePage {
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By accountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By assembleBurgerHeader = By.xpath(".//h1[text()='Соберите бургер']");
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By bunsButton = By.xpath(".//span[text() = 'Булки']/parent::div");
    private final By saucesButton = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By selectedButton = By.xpath(".//div[contains(@class, 'current')]");
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void openHomePage() {
        driver.get(HOST);
    }

    @Step("Клик по кнопке «Войти в аккаунт» на главной")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке «Личный кабинет» на главной")
    public void accountButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(accountButton));
        driver.findElement(accountButton).click();
    }

    @Step("Клик по кнопке «Булки»")
    public void clickBunsButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(bunsButton));
        driver.findElement(bunsButton).click();
    }

    @Step("Клик по кнопке «Соусы»")
    public void clickSaucesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(saucesButton));
        driver.findElement(saucesButton).click();
    }

    @Step("Клик по кнопке «Начинки»")
    public void clickFillingsButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(fillingsButton));
        driver.findElement(fillingsButton).click();
    }

    @Step("Получить текст выбранной кнопки")
    public String getSelectedButtonText() {
        return driver.findElement(selectedButton).getText();
    }

    @Step("Проверка наличия заголовка Соберите бургер")
    public boolean isHeaderAssembleBurgerDisplayed() {
        return driver.findElement(assembleBurgerHeader).isDisplayed();
    }

    @Step("Получить текст кнопки Оформить заказ")
    public String getCreateOrderButtonText() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(createOrderButton));
        return driver.findElement(createOrderButton).getText();
    }
}
