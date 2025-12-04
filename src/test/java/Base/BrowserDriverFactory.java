package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriverFactory{

    public enum BrowserType {
        YANDEX, FIREFOX, CHROME
    }

    public static WebDriver createDriver(BrowserType browserType) {

        boolean HEADLESS = Boolean.getBoolean("headless"); 
        // true ONLY when you pass -Dheadless=true 

        WebDriver driver;

        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");

                if (HEADLESS) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                driver = new ChromeDriver(chromeOptions);
                break;
            case YANDEX:
                ChromeOptions options = new ChromeOptions();
                String binaryYandexDriverFile = "src/main/resources/yandexdriver";
                System.setProperty("webdriver.chrome.driver", binaryYandexDriverFile);
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                FirefoxOptions ffOptions = new FirefoxOptions();

                if (HEADLESS) {
                    ffOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(ffOptions);
                break;
            default:
                System.out.println("Don't know how to start " + browserType + ". Starting Firefox instead.");
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                break;
        }
        return driver;
    }
}