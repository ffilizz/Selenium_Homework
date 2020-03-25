package com.automation.homework_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.DriverFactory;

public class TestCase6 {
   private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver=DriverFactory.createDriver("chrome");
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(5);

    }
    @Test
    public void testCase6(){

        WebElement copyEmail =driver.findElement(By.xpath("//a[@class='color cetc']"));
        copyEmail.click();
        String email = driver.findElement(By.id("email")).getText();
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        driver.findElement(By.name("full_name")).sendKeys("abcd");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("wooden_spoon")).click();
        BrowserUtils.wait(5);
        String expected ="Thank you for signing up. Click the button below to return to the home page.";
        String actual=driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(actual,expected);
        driver.navigate().to("https://www.tempmailaddress.com/");
        String expected2= "do-not-reply@practice.cybertekschool.com";
        String actual2=driver.findElement(By.xpath("//td[contains(text(),'do-not-reply@practice.cybertekschool.com')]")).getText();
        Assert.assertEquals(actual2.trim(),expected2);
        driver.findElement(By.xpath("//tbody[@id='schranka']/tr[1]")).click();
        String expected3="do-not-reply@practice.cybertekschool.com";
        String actual3=driver.findElement(By.id("odesilatel")).getText();
        Assert.assertEquals(actual3,expected3);
        String expected4="Thanks for subscribing to practice.cybertekschool.com!";
        String actual4=driver.findElement(By.id("predmet")).getText();
        Assert.assertEquals(actual4,expected4);


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
