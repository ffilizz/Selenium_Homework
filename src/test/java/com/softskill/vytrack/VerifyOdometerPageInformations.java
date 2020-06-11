package com.softskill.vytrack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.Driver;

import java.util.List;

public class VerifyOdometerPageInformations {
    protected WebDriver driver;
    protected Actions actions;


    @BeforeMethod
    public void setUp() {
        driver= Driver.getDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();

    }

    @Test
    public void verifyAllInformationsforOdometerPage(){
        driver.get("https://qa2.vytrack.com/");
        BrowserUtils.wait(5);
        driver.findElement(By.id("prependedInput")).sendKeys("user42");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
        BrowserUtils.wait(5);

        WebElement fleetButton=driver.findElement(By.linkText("Fleet"));

        actions.moveToElement(fleetButton).pause(1000);

        WebElement vehicleOdometerButton=driver.findElement(By.xpath("//span[contains(text(),'Vehicle Odometer')]"));

        actions.moveToElement(vehicleOdometerButton).click().build().perform();
        BrowserUtils.wait(5);

        driver.findElement(By.xpath("//button[contains(@class,'btn dropdown-toggle')]")).click();
        BrowserUtils.wait(5);
        driver.findElement(By.xpath("//a[contains(text(),'100')]")).click();
        BrowserUtils.wait(5);

        List<WebElement> allRecords=driver.findElements(By.xpath("//table[@class='grid table-hover table table-bordered table-condensed']/tbody/tr"));
                String expected=driver.findElement(By.xpath("//label[contains(text(),'Total of 79 records')]")).getText();
                String actual="Total Of "+allRecords.size()+ " Records";
        Assert.assertEquals(actual,expected);

    }

    @AfterMethod
    public void tearDown() {
        driver.close();

    }
}
