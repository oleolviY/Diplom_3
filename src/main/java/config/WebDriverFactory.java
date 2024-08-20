package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static config.Constants.*;

public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver createWebDriver() {
        if (driver != null) {
            driver.quit();
        }
        String browser = System.getProperty("browser", "chrome");
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "yandex":
                System.setProperty(YANDEX_BROWSER_DRIVER_FILENAME, WEBDRIVERS_PATH);
                ChromeOptions options = new ChromeOptions();
                options.setBinary(YANDEX_BROWSER_PATH);
                driver = new ChromeDriver(options);
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
        return driver;
    }
}
