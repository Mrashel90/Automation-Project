package Yahoo_Page_Object;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Library_Loggers_POM;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;

public class Yahoo_Homepage extends Abstract_Class {

    ExtentTest logger;
    //create constructor to use driver and logger as a Page Object
    public Yahoo_Homepage(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
        this.logger = super.logger;
    }

    @FindBy(xpath = "//*[@id='uh-search-box']")
    public static WebElement searchKeyLocator;
    @FindBy(xpath = "//*[@id='uh-search-button']")
    public static WebElement searchIconLocator;
    @FindBy(xpath = "//*[contains(@class,'Mstart(21px)')]")
    public static WebElement countTabLocator;


    public Yahoo_Homepage searchKey(String userInput) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver,searchKeyLocator,0,userInput,logger,"Search Field");
        return new Yahoo_Homepage(driver);
    }

    public Yahoo_SearchResultPage clickOnSearchIcon() throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.click(driver,searchIconLocator,0,logger,"Search Icon");
        return new Yahoo_SearchResultPage(driver);
    }

    public Yahoo_Homepage countTabLink(int countNumber) throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,8);
        logger.log(LogStatus.INFO,"Counting the Tab Link on home page");
        try{
            ArrayList<WebElement> countLink = new ArrayList<WebElement>(wait.until(ExpectedConditions.visibilityOfAllElements(countTabLocator)));
            if(countNumber == countLink.size()){
                logger.log(LogStatus.PASS, "Tab counts matches " + countNumber);
            } else {
                logger.log(LogStatus.FAIL, "Tab counts doesn't match.. Actual count is  " + countLink.size());
            }

            return new Yahoo_Homepage(driver);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Element not found for tab count " + e);
            Reusable_Library_Loggers_POM.getScreenshot(driver,logger,"Tab Fail");
        }

        return new Yahoo_Homepage(driver);
    }

}

