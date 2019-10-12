package kk;

import Reusable_Classes.Reusable_Library;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Action_Yahoo {

        //declare driver
        WebDriver driver = null;

        @BeforeSuite
        public void openBrowser() throws IOException {
            // navigate to url
            driver = Reusable_Library.navigate( "https://www.yahoo.com");
        } // end of before suite

        @Test
        public void yahooSearch(){
            // verify title matches with Hard Assert
            Assert.assertEquals(driver.getTitle(),"Yahoo");

            // Display the count of options
            List<WebElement> tabsCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(21px)')]"));
                    System.out.println(" tab count is "+ tabsCount.size());

            // Enter 'Nutrition' on search bar
            Reusable_Library.userInput(driver,"//*[@type='text']","Nutrition","Enter 'Nutrition");

            // Click on ‘Search’ button
            Reusable_Library.click(driver, "//*[@type='submit']", "Search");

            // Scroll down to the page
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(0,800)");

            // Display the search result Number id="yui_3_10_0_1_1569766422846_710"
            //
            //Step 8: Scroll up and click on Image link
            //
            //Step 9: Display the count of all images appear on the page
            //
            //Step 10: On top right Click on Sign In button
            //
            //Step 11: Verify the Boolean state of checkbox if it is selected as default or not
            //
            //Step 12: Enter invalid user name
            //
            //Step 13: click on ‘Next’ button
            //
            //Step 14: Capture the error message and verify that if actual message matches the following string
            //
            //               String errMsg = "Sorry, we don't recognize this email.";



        } // end of test annotation

        @AfterSuite
        public void close(){
            // quit the driver
            // driver.quit();

        } // end of after suite

    } // end of action item
