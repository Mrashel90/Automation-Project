package kk;

import Reusable_Classes.Reusable_Library;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class Asserts {

        //declare driver
        WebDriver driver = null;

        @BeforeSuite
        public void openBrowser() throws IOException {
            // navigate to url
            driver = Reusable_Library.navigate( "https://www.google.com");
        } // end of before suite

        @Test
        public void googleSearch(){
            // verify title matches with Hard Assert
            Assert.assertEquals(driver.getTitle(),"Google");




            // enter a keyword
            Reusable_Library.userInput(driver,"//*[@name='q']","Cars","Search Filed");
        } // end of test annotation

        @AfterSuite
        public void close(){
            // quit the driver
            // driver.quit();

        } // end of after suite

    }
