package testNG_0922;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Library;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class WW_TestNG extends Abstract_Class {
    //declare driver variable outside so it can be resused on all annotation methods
    WebDriver driver = null;
    Workbook readableFile = null;
    WritableWorkbook writableFile = null;
    WritableSheet wSheet = null;
    Sheet readableSheet = null;
    int rows = 0;

    @Test
    public void Automation_Abstract() throws IOException, BiffException {
        //access the excel readable file path.... choose java.io File for new File()
        readableFile = Workbook.getWorkbook(new File("src\\main\\resources\\Weight_Action_Item2.xls"));
        //now create a new workbook to replicate readable file
        writableFile = Workbook.createWorkbook(new File("src\\main\\resources\\Weight_Action_Item2_results.xls"),readableFile);
        //create the writable sheet
        wSheet = writableFile.getSheet(0);
        //access the readable sheet for the rows
        readableSheet = readableFile.getSheet(0);
        //get the existing row count from the readable sheet
        rows = readableSheet.getRows();

        //navigating to the url using navigate command
        driver = Reusable_Library.navigate("https://www.weightwatchers.com/us");

    }

    @Test()
    public void testScenario() throws InterruptedException, WriteException, IOException {
        for(int i=1;i<rows;i++) {

            //store the zipcode from excel in a string
            String zipCode = readableSheet.getCell(0, i).getContents();

            //re navigate to weight watchers
            driver.navigate().to("https://www.weightwatchers.com/us");

            //timeout to capture the page title
            Thread.sleep(3000);
            //verifying the page title
            String title1 = driver.getTitle();
            if (title1.equals("Weight Loss Program, Recipes & Help | Weight Watchers")) {
                System.out.println("Title matches");
            } else {
                System.out.println("Title doesn't match " + title1);
            }

            //click on 'Find a Meeting' button
            /////////click(driver, "//*[@class='find-a-meeting']",logger,"Find a Meeting");

            //timeout to capture the page title 2
            Thread.sleep(3000);
            //verifying the page title 2
            String title2 = driver.getTitle();
            if (title2.equals("Find a Studio & Meeting Near You |")) {
                System.out.println("Title matches");
            } else {
                System.out.println("Title doesn't match " + title2);
            }

            //enter zipcode
            ///////userInput(driver, "//*[@id='meetingSearch']", zipCode, logger, "Enter Location");
            //click on Search button
            //////////click(driver, "//*[@spice='SEARCH_BUTTON']", logger,"Search Button");

            //caputre the first result for meeting location
            String meetLocation = Reusable_Library.captureTextByIndex(driver, "//*[@class='location__name']", 0,  "Meeting Location");
            //click on first element using index
            ////////////clickByIndex(driver, "//*[@class='location__name']", 0, logger,"Location Name Link");

            //caputre the current day operation hour
            String opHour = null;
            //if the operation hour is not present for a zipcode then store the variable inside try catch
            try {
                Thread.sleep(2800);
                opHour = driver.findElements(By.xpath("//*[contains(@class,'currentday')]")).get(0).getText();
            } catch (Exception e) {
                opHour = "Current Operation Hour is not available for this Zip Code";
            }

            //create label and add value to excel for Meeting location
            Label label1 = new Label(1,i,meetLocation);
            wSheet.addCell(label1);

            //create label and add value to excel for Operation Hour
            Label label2 = new Label(2,i,opHour);
            wSheet.addCell(label2);
        } //end of loop
        writableFile.write();
        writableFile.close();
        readableFile.close();
    }//end of test priority 2

}//end of action item