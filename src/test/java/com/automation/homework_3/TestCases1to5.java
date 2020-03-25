package com.automation.homework_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.DriverFactory;

import java.util.ArrayList;
import java.util.List;

public class TestCases1to5 {

    private WebDriver driver;
    private String URL="https://practice-cybertekschool.herokuapp.com/";
    private By dateOfBirthBy=By.xpath("//input[@placeholder='MM/DD/YYYY']");
    private By firstNameBy=By.name("firstname");
    private By lastNameBy=By.name("lastname");
    private By firstNameHelpBy=By.xpath("//small[contains(text(), 'first name must be more than 2 and less than 64 characters long')]");
    private By lastNameHelpBy=By.xpath("//small[contains(text(), 'The last name must be more than 2 and less than 64 characters long')]");
    private By userNameBy= By.name("username");
    private By emailBy= By.name("email");
    private By passwordBy= By.name("password");
    private By phoneBy= By.name("phone");
    private By genderBy= By.name("gender");
    private By departmentBy= By.xpath("//select[@name='department']");
    private By progLanguageBy=By.id("inlineCheckbox2");
    private By jobTitleBy=By.name("job_title");




   @Test
   public void testCase1(){
       driver.findElement(dateOfBirthBy).sendKeys("wrong_dob", Keys.ENTER);
       BrowserUtils.wait(2);
       String expected="The date of birth is not valid";
       String actual=driver.findElement(By.xpath("//small[contains(text(),'The date of birth is not valid')]")).getText();

       Assert.assertEquals(actual,expected);
   }
   @Test
   public void testCase2(){
       List<WebElement> checkboxes = driver.findElements(By.className("form-check-label"));
       String mix="";
       for (WebElement checkbox:checkboxes){
           mix+=" "+checkbox.getText();
       }
       String expected="c++ java JavaScript";
       String actual = mix.trim();
       Assert.assertEquals(actual.toLowerCase(),expected.toLowerCase());
   }
   @Test
   public void testCase3(){

       driver.findElement(firstNameBy).sendKeys("a");
       BrowserUtils.wait(2);
       String expected="first name must be more than 2 and less than 64 characters long";
       String actual= driver.findElement(firstNameHelpBy).getText();
       Assert.assertEquals(actual,expected);

   }
    @Test
    public void testCase4(){

        driver.findElement(lastNameBy).sendKeys("a");
        BrowserUtils.wait(2);
        String expected="The last name must be more than 2 and less than 64 characters long";
        String actual= driver.findElement(lastNameHelpBy).getText();
        Assert.assertEquals(actual,expected);

    }
    @Test
    public void testCase5(){
       driver.findElement(firstNameBy).sendKeys("Mehmet");
       driver.findElement(lastNameBy).sendKeys("Bas");
       driver.findElement(userNameBy).sendKeys("elifff");
       driver.findElement(emailBy).sendKeys("fdggd@gmail.com");
       driver.findElement(passwordBy).sendKeys("abcdef12345");
       driver.findElement(phoneBy).sendKeys("571-555-5555");
       List<WebElement>genders=driver.findElements(genderBy);
       genders.get(0).click();
       driver.findElement(dateOfBirthBy).sendKeys("01/01/2000");
        Select departments= new Select(driver.findElement(departmentBy));
        departments.selectByIndex(3);
        Select jobTitels=new Select(driver.findElement(jobTitleBy));
        jobTitels.selectByVisibleText("SDET");
       driver.findElement(progLanguageBy).click();
       WebElement signUpButton=driver.findElement(By.id("wooden_spoon"));
       signUpButton.click();
       String expected="You've successfully completed registration!";
       String actual=driver.findElement(By.tagName("p")).getText();
       Assert.assertEquals(actual,expected);



    }


    @BeforeMethod
    public void setup(){
        driver= DriverFactory.createDriver("chrome");
        driver.get(URL);
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(2);

    }
    @AfterMethod
    public void teardown(){

       driver.quit();
    }


}
