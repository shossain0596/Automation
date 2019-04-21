package ActionItemTestCases;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.ReusableMethods;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class WeightWatchers extends Abstract_Class {


    @Test
    public void tc3_WeightWatchersSearch() throws InterruptedException, IOException {

            logger.log(LogStatus.INFO, "Navigating to Weight Watchers");

            driver.navigate().to("https://www.weightwatchers.com");

            //Verify Page1 Title
            getTitle(driver, logger, "Weight Loss & Wellness Help");

            //Click on Find a Studio
            click(driver, logger,"//*[@class='find-a-meeting']", 0, "Find a Studio");

            //Verify Page2 Title
            getTitle(driver, logger, "Find a Studio & Meeting Near You");

            //Send zip code to search field
            sendKeys(driver,logger, "//*[@name='meetingSearch']", 0, "Meeting Search", "11218");

            //Click on search button
            click(driver, logger, "//*[@spice='SEARCH_BUTTON']", 0, "Search Button");


            //Get location 1 Text
            getText(driver, logger,"//*[@class='location__container']", 0, "Studio Location");

            //Click on location
            click(driver, logger,"//*[@class='meeting-location']", 0, "Meeting Location");

            Thread.sleep(2000);
            jse.executeScript("scroll(0,1000)");
            Thread.sleep(2000);
            jse.executeScript("scroll(0,-1000)");
            Thread.sleep(2000);
            jse.executeScript("scroll(0,1000)");
            Thread.sleep(2000);

            //Get Operating hours for current day
            String opHours = getText(driver,logger, "//*[contains(@class, 'currentday')]", 0, "Operation Hours");

            logger.log(LogStatus.INFO, "Operating Hours are " + opHours);

            Thread.sleep(1000);

    }//end of test method

}//End of public class

