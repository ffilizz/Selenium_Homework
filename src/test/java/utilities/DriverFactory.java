package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverFactory {

    public static WebDriver createDriver (String browserName){

        switch(browserName.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().version("79.0").setup();
                return new ChromeDriver();
            case "opera":
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case"ie":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();

        }
        return null;
    }

}

