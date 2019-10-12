package Reusable_Classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class Reusable_Library {

    //method to navigate using webdriver
    public static WebDriver navigate(String url) throws IOException {
        WebDriver driver;
        //define the path of the chrome driver
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        //quit all open chrome browsers
        Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f /t");

        //setting up the chromeoptions
        ChromeOptions options = new ChromeOptions();
        //add the precondition arguments
        options.addArguments("start-maximized", "incognito");

        //define the chrome web driver
        driver = new ChromeDriver(options);

        //navigate to url
        driver.navigate().to(url);

        return driver;
    }//end of navigate

    //hovering over an element using mouse Actions
    public static void mouseHover(WebDriver driver, String locator, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        Actions mouseMove = new Actions(driver);
        try{
            System.out.println("Hovering to element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            mouseMove.moveToElement(element).perform();
        } catch (Exception err) {
            System.out.println("Unable to hover to element " + elementName + " --" + err);
        }
    }//end of mouse hover method

    //click on element using explicit wait
    public static void click(WebDriver driver, String locator, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        try{
            System.out.println("Clicking on element " + elementName);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
        } catch (Exception err) {
            System.out.println("Unable to click on element " + elementName + " --" + err);
        }
    }//end of click method

    //click on element using explicit wait with index
    public static void clickByIndex(WebDriver driver, String locator, int indexNumber, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        try{
            System.out.println("Clicking on element " + elementName);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(indexNumber).click();
        } catch (Exception err) {
            System.out.println("Unable to click on element " + elementName + " --" + err);
        }
    }//end of click method

    //entering info on element using explicit wait
    public static void userInput(WebDriver driver, String locator, String userValue, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            System.out.println("Entering value on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            //clear any pre populated data on the text field
            element.clear();
            //enter a new value on the text field
            element.sendKeys(userValue);
        }catch (Exception err) {
            System.out.println("Unable to enter value on element " + elementName + " --" + err);
        }
    }//end of sendKeys method

    public static String captureTextByIndex(WebDriver driver, String locator, int index, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        String text = null;
        try{
            System.out.println("Capturing text on element " + elementName);
            text = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index).getText();
        } catch (Exception err) {
            System.out.println("Unable to capture text on element " + elementName + " --" + err);
        }

        return text;
    }//end of getText method


    public static void dropDownByText(WebDriver driver, String locator, String userValue, String elementName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element = null;
        try{
            System.out.println("Selecting value " + userValue + " from element " + elementName);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            Select dDown = new Select(element);
            dDown.selectByVisibleText(userValue);
        } catch (Exception err) {
            System.out.println("Unable to select value from element " + elementName + " --" + err);
        }
    }//end of Select by text method





}//end of class