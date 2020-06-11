package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Driver {

    private static WebDriver driver;

    private Driver() {

    }

    public static WebDriver getDriver() {

        if (driver == null) {
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            System.out.println("browser = " + browser);
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().version("79.0").setup();
                    driver = new ChromeDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}