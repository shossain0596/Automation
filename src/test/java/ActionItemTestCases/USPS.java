package ActionItemTestCases;

import Reusable_Classes.Abstract_Class;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class USPS extends Abstract_Class {

        @Test
        public void tc2_USPSTrack () throws IOException, InterruptedException {

        logger.log(LogStatus.INFO, "Navigating to USPS");

        driver.navigate().to("https://www.usps.com");

        Thread.sleep(3000);

        //Hover over Quick Tools
        mouseHover(driver, logger,"//*[text()='Quick Tools']", 0, "Quick Tools");

        //Click Track a Package
        click(driver, logger, "//*[text()='Track a Package']", 0, "Track a Package");

        //Send Keys to Track a Package Input Field
        sendKeys(driver, logger, "//*[@id = 'tracking-input']", 0, "Track a Package Input Field", "1Z 999 AA1 01 2345 6784");

        //Click Track Button
        click(driver, logger, "//*[@class='button tracking-btn']", 0, "Track Search Button");

        }//End of Test Method
}//End of public class
