package com.automation.homework_3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.DriverFactory;

public class TestCase7to12 {
    private WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver= DriverFactory.createDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);


    }
    @Test
    public void testCase7(){
    driver.findElement(By.linkText("File Upload")).click();
        WebElement upload=driver.findElement(By.id("file-upload"));
        String filePath = System.getProperty("user.dir")+"/pom.xml";
        upload.sendKeys(filePath);
        driver.findElement(By.id("file-submit")).click();
        String result=driver.findElement(By.tagName("h3")).getText();
        Assert.assertTrue(result.equals("File Uploaded!"));
    }
    @Test
    public void testCase8(){
        driver.findElement(By.linkText("Autocomplete")).click();
        WebElement search= driver.findElement(By.id("myCountry"));
        search.sendKeys("u");
        for (int i = 1; i <6 ; i++) {
            search.sendKeys(Keys.ARROW_DOWN);
        }
        search.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        String result=driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.equals("You selected: United States of America"));
    }
    @Test(description = "Verify Status Code",dataProvider = "statusCode")
    public void testCases9to12(String code){

        driver.findElement(By.linkText("Status Codes")).click();
        driver.findElement(By.linkText(code)).click();
        String expected="This page returned a "+code+" status code.";
        String actual=driver.findElement(By.tagName("p")).getText().trim();
        Assert.assertTrue(actual.contains(expected));


    }
    @DataProvider(name = "statusCode")
    public Object[][] statusCode(){

        return new Object[][]{{"200"},{"301"},{"404"},{"500"}};
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }
}
