package Reusable_Classes;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class Abstract_Class extends Reusable_Library_Loggers_POM{
    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static Workbook readFile = null;
    public static WritableWorkbook writeFile = null;
    public static Sheet readSheet = null;
    public static WritableSheet writeSheet = null;
    public static int rows = 0;


    @BeforeSuite()
    public static void openBrowser() throws IOException, BiffException {
        //path to your report
        report = new ExtentReports("src\\main\\java\\Report_Folder\\AutomationReport" + getDateTime() + ".html", true);
        logger = report.startTest("Yahoo Action");
        readFile = Workbook.getWorkbook(new File("src\\main\\resources\\Action_Item_Yahoo_LogIn.xls"));
        writeFile = Workbook.createWorkbook(new File("src\\main\\resources\\Action_Item_Yahoo_LogIn_results.xls"),readFile);
        writeSheet = writeFile.getSheet(0);
        readSheet = readFile.getSheet(0);
        rows = readSheet.getRows();
        System.out.println("Number of rows in express sheet is: " + rows);


    }//end of before suite

    @Parameters("Browser")
    @BeforeMethod

    public static void captureTestName (Method methodName, String Browser) throws IOException {
        // navigates to website using either firefox or chrome to allow cross browser testing
        if(Browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
            // firefox doesnt have chrome options
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (Browser.equalsIgnoreCase("Chrome")) {
            driver = navigate(driver,"https://www.google.com");
        }
        // this logger captures name of each @Test method and which browser you're using.
        logger = report.startTest(methodName.getName() + "--" + Browser);
        logger.log(LogStatus.INFO, "Automation Test Scenario started.... ");
    } // end of before method


    @AfterMethod
    public static void endTest(){
        report.endTest(logger);
        logger.log(LogStatus.INFO,"Automation Test Scenario ended....");
    }//end of after method

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {

        writeFile.write();
        writeFile.close();
        readFile.close();
        report.flush();
        report.close();
    } // end of after suite

} // end of class

