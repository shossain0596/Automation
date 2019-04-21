package ActionItemTestCases;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.ReusableMethods_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static Reusable_Classes.Abstract_Class.report;

public class Express extends Abstract_Class {


    @Test
    public void tc1_expressCheckout() throws InterruptedException, IOException {

            //Navigation logger
            logger.log(LogStatus.INFO, "Navigating to Express Home Page");

            //Navigate to Express
            driver.navigate().to("http://www.express.com");

            getTitle(driver, logger, "Mens & Women's Clothing");

            //Hover to the Women's Clothing Option
            mouseHover(driver,logger,"//*[@href='/womens-clothing']",0,"Women's Clothing");

            //Hover to Dresses
            mouseHover(driver,logger,"//*[@href='/womens-clothing/dresses/cat550007']",0,"Dresses");

            //Click on Shop by Style
            click(driver,logger, "//*[@href='/womens-clothing/dresses/style/cat4480003']",0,"Shop by Style");

            //Wait a few seconds
            Thread.sleep(2000);

            //Scroll down 400 pixels
            jse.executeScript("scroll(0,400)");

            //Click on first dress
            click(driver, logger,"//*[contains(@alt, 'strapless')]",0,"First Dress");

            //Click on each different size
            click(driver, logger,"//*[@value = '0']",0,"Sizes");

            //Click on Add to Bag
            click(driver,logger, "//*[contains(text(), 'Add to Bag')]",0,"Add to Bag");

            Thread.sleep(2000);

            //Click on Checkout on Pop up
            click(driver,logger,"//*[@href = '/check-out/basket.jsp']",0,"Check out");

            //Select Quantity dropdown
            dropDownByText(driver,logger,"//*[contains(@id, 'quantity')]",0, "2","Dropdown");

            //Click Checkout button
            click(driver, logger, "//*[contains(@id,'checkout')]",0, "Checkout Button");

            Thread.sleep(2000);

            //Click Continue as Guest
            click(driver, logger, "//*[contains(text(), 'Continue as Guest')]",0,"Continue as Guest");

            //Fill out firstName, lastName, email, phoneNumber
            sendKeys(driver,logger,"//*[contains(@id, 'firstname')]",0,"First Name", "Sha");
            sendKeys(driver,logger,"//*[contains(@name, 'lastname')]",0,"Last Name", "Hossain");
            sendKeys(driver,logger,"//*[@type = 'email']",0,"Email", "qwertyuiop@gmail.com");
            sendKeys(driver, logger, "//*[@name = 'confirmEmail']",0,"Confirm Email", "qwertyuiop@gmail.com");
            sendKeys(driver, logger, "//*[@name = 'phone']",0,"Phone Number", "3473456891");

            //Click continue twice
            click(driver, logger, "//*[contains(text(), 'Continue')]",0,"Continue");

            Thread.sleep(2000);

            click(driver, logger, "//*[contains(text(), 'Continue')]",0,"Continue");

            //Fill out address, zipCode, city
            sendKeys(driver, logger, "//*[contains(@name, 'line1')]",0,"Address", "221 Ave F");
            sendKeys(driver, logger, "//*[contains(@name, 'postalCode')]",0,"ZipCode", "11218");
            sendKeys(driver, logger, "//*[contains(@name, 'city')]",0,"City", "Brooklyn");

            //Select state
            dropDownByText(driver, logger, "//*[@name='shipping.state']",0, "New York", "State");

            Thread.sleep(2000);

            //Click continue
            click(driver, logger, "//*[contains(text(), 'Continue')]",0,"Continue Button");

            Thread.sleep(2000);

            //Pop up for Rewards Program
            try {
                if (driver.findElement(By.xpath("//*[contains(text(), 'Congratulations!')]")).isDisplayed()) {
                    click(driver, logger, "//*[contains(text(), 'No, thank you')]", 0, "No thank you");
                }
            } catch (Exception e){
                System.out.println("No Pop Up came up");
            }//End of Try/Catch Pop Up

            //Send Keys to credit card Field
            sendKeys(driver, logger, "//*[@id='creditCardNumberInput']",0,"Credit Card Field", "123456789");

            //Click Place Order Button
            click(driver, logger,"//*[contains(text(), 'Place Order')]",0,"Place Order");

            Thread.sleep(3000);

            //Capture Error Message
            String error = getText(driver, logger, "//*[contains(text(), 'Enter a valid credit card number.')]",0,"Error Message");

            logger.log(LogStatus.INFO,"Error Message is " + error);

            driver.manage().deleteAllCookies();

    }//End of Test Method
}//End of public class
