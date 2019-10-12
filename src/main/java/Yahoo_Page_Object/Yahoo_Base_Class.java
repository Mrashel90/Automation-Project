package Yahoo_Page_Object;

import Reusable_Classes.Abstract_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Yahoo_Base_Class extends Abstract_Class {

    public Yahoo_Base_Class(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    //// Object for yahoo home page
    public static Yahoo_Homepage yahoo_homepage() {
        Yahoo_Homepage yahoo_homepage = new Yahoo_Homepage(driver);
        return yahoo_homepage;
    }

    //// Object for yahoo home page
    public static Yahoo_SearchResultPage yahoo_searchResultPage() {
        Yahoo_SearchResultPage yahoo_searchResultPage = new Yahoo_SearchResultPage(driver);
        return yahoo_searchResultPage;
    }
    //// Object for yahoo home page
    public static Registration_Page registration_page() {
        Registration_Page registration_page = new Registration_Page(driver);
        return registration_page;
    }



}//end of class
