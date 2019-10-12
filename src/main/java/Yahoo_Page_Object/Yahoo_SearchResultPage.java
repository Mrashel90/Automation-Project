package Yahoo_Page_Object;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Library_Loggers_POM;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

    public class Yahoo_SearchResultPage extends Abstract_Class {

        ExtentTest logger;
        public Yahoo_SearchResultPage(WebDriver driver) {
            super();
            PageFactory.initElements(driver, this);
            this.logger = super.logger;
        }

        @FindBy(xpath = "//*[@class='compPagination']")
        public static WebElement searchResultLocator;


        public Yahoo_SearchResultPage scrollDown(String pixels) throws InterruptedException {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            Thread.sleep(3000);
            jse.executeScript("scroll(0,"+ pixels +")");

            return new Yahoo_SearchResultPage(driver);
        }

        public Yahoo_SearchResultPage searchNumber() throws IOException, InterruptedException {
            String searchresult = Reusable_Library_Loggers_POM.captureTextByIndex(driver,searchResultLocator,0,logger,"Search Result");
            String[] searchArray = searchresult.split("Next");
            logger.log(LogStatus.INFO,"My search number is " + searchArray[1]);
            return new Yahoo_SearchResultPage(driver);
        }








    }
