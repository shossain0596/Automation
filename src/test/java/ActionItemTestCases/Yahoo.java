package ActionItemTestCases;

import Reusable_Classes.Abstract_Class;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class Yahoo extends Abstract_Class {


    @Test
    public void tc4_YahooSearch() throws InterruptedException, IOException {

        logger.log(LogStatus.INFO,"Navigating to Yahoo");

        driver.navigate().to("https://www.yahoo.com");

        Thread.sleep(3000);

        //Enter Keyword into Search field
        sendKeys(driver, logger, "//*[@name='p']", 0, "Search Field", "Cars");

        //Click on Search Icon
        click(driver, logger, "//*[@id='uh-search-button']", 0, "Search Icon");

        //Put some time before scrolling
        Thread.sleep(2000);

        //Scroll to the bottom of the page to capture text
        jse.executeScript("scroll(0,10000)");

        //Capture my Search Result
        String result = getText(driver, logger, "//*[@class='compPagination']", 0, "Search Result");

        //Split the Result
        String[] arrayResult = result.split("Next");

        //Print the Search Number
        logger.log(LogStatus.INFO, "Search Number is " + arrayResult[1]);

    }//End of Test

}//End of public class
