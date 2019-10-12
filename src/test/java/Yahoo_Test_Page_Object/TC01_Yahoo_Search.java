package Yahoo_Test_Page_Object;

        import Reusable_Classes.Abstract_Class;
        import org.testng.annotations.Test;

        import java.io.IOException;

        import static Yahoo_Page_Object.Yahoo_Base_Class.yahoo_homepage;
        import static Yahoo_Page_Object.Yahoo_Base_Class.yahoo_searchResultPage;


public class TC01_Yahoo_Search extends Abstract_Class {


    @Test
    public static void Yahoo_SearchResultPage() throws IOException, InterruptedException {

        driver.navigate().to("https://www.yahoo.com");

        //get tab count
        yahoo_homepage().countTabLink(8);

        yahoo_homepage().searchKey("Cars");
        yahoo_homepage().clickOnSearchIcon();

        yahoo_searchResultPage().scrollDown("5000");
        yahoo_searchResultPage().searchNumber();



    }



}
