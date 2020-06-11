package com.automation.homework_4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;
import utilities.Driver;
import utilities.DriverFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Checkboxes_Dropdown_List {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
       driver = Driver.getDriver();
    }

    @Test
    public void days() {

        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        BrowserUtils.wait(3);
       // List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".gwt-CheckBox>label"));
        int count = 0;
        Random random = new Random();

        while (count < 3) {
            int index = random.nextInt(checkboxes.size());

           String randomDay = checkboxes.get(index).getText();
            if (checkboxes.get(index).isEnabled()) {
               checkboxes.get(index).click();

                if (randomDay.equals("Friday")) {
                    count++;
                }
                System.out.println("randomDay = " + randomDay);
                checkboxes.get(index).click();
            }
        }

    }
    @Test
    public void todaysDate(){

        driver.get("http://practice.cybertekschool.com/dropdown");

        Select year = new Select(driver.findElement(By.id("year")));
        Select month = new Select(driver.findElement(By.id("month")));
        Select day = new Select(driver.findElement(By.id("day")));

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMMMd");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormat.format(today);
        String actual=year.getFirstSelectedOption().getText()+
                      month.getFirstSelectedOption().getText()+
                      day.getFirstSelectedOption().getText();

        Assert.assertEquals(actual,date);


    }
    @Test
    public void yearsMonthsDays() {
        driver.get("http://practice.cybertekschool.com/dropdown");
        List<WebElement> years= driver.findElements(By.id("year"));
        Select year= new Select(driver.findElement(By.id("year")));
        Random random= new Random();
        int index=random.nextInt(years.size());
        year.selectByIndex(index);

        Select month= new Select(driver.findElement(By.id("month")));
        for (int i = 0; i <12 ; i++) {
            month.selectByIndex(i);
        //??????????????????????????????????
        }
    }
    @Test
    public void departmentsSort(){
        driver.get("https://www.amazon.com");
        Assert.assertEquals(driver.findElement(By.className("nav-search-facade")).getText(),"All");
        List<WebElement> allOptions=new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        List<String> orginalOptions=new ArrayList();
        for(WebElement each:allOptions){
            orginalOptions.add(each.getText());
        }
        List<String> tempOptions= new ArrayList<>();
           tempOptions.addAll(orginalOptions);
           Collections.sort(tempOptions);
        Assert.assertTrue(!orginalOptions.equals(tempOptions));


    }
    @Test
    public void mainDepartments(){

        driver.get("https://www.amazon.com/gp/site-directory");
        List<WebElement>mainDepartments=driver.findElements(By.tagName("h2"));
        List<String> lst=new ArrayList<>();
        for(WebElement each:mainDepartments){
            lst.add(each.getText());
        }
        List<WebElement> allDepartments=new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        List<String> lst1=new ArrayList();
        for(WebElement each:allDepartments){
            lst1.add(each.getText());
        }
        if(lst1.containsAll(lst)){
            System.out.println("Main departments name is present in all depertments name ");
        }else{
            System.out.println("Main departments name is NOT present in all depertments name ");
        }
    }
    @Test
    public void links(){
        driver.get("https://www.w3schools.com/");
        List<WebElement> elements=driver.findElements(By.tagName("a"));
        for(WebElement each:elements){
            if(each.isDisplayed()){
                System.out.println(each.getText()+"-->"+each.getAttribute("href"));
            }
        }
    }
    @Test
    public void validLinks(){
        driver.get("https://www.selenium.dev/documentation/en/");
        List<WebElement> allElements=driver.findElements(By.tagName("a"));


        for(WebElement each:allElements){
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(each));
        }

//            if(each.isDisplayed()&&each.isEnabled()){
//                System.out.println("All links are valid");
//            }else{
//                System.out.println("All links are NOT valid");
//            }
//        }


    }
    @Test
    public void cart(){
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).
                sendKeys("wooden spoon", Keys.ENTER);
        List<WebElement> prices =driver.findElements(By.xpath("//span[@class='a-offscreen']"));
           int index = new Random().nextInt(prices.size());
           index=index==0?1:index;
           for(WebElement each:prices){
               System.out.println(each.getText()); //it doesn't give text !!!
           }


    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}

