package DataDrive_TestNG;

import Reusable_Classes.ReusableMethods;
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

public class WW_ActionItem extends ReusableMethods {

    //Declare all the reusable variables among methods in class level
    WebDriver driver = null;
    Workbook readableFile;
    Sheet readableSheet;
    WritableWorkbook writableFile;
    WritableSheet writableSheet;
    int rows;
    Label label1, label2;
    JavascriptExecutor jse;


    @BeforeMethod
    public void openBrowser() throws IOException, BiffException {

        //Define driver with navigate method
        driver = navigate(driver, "https://www.weightwatchers.com");

        //Define readable workbook path
        readableFile = Workbook.getWorkbook(new File("src/main/resources/weightWatchers_data.xls"));

        //Locating Readable Sheet
        readableSheet = readableFile.getSheet(0);

        //Count the rows
        rows = readableSheet.getRows();

        //Create Writable File
        writableFile = Workbook.createWorkbook(new File("src/main/resources/dataresults.xls"), readableFile);

        //Create Writable Sheet
        writableSheet = writableFile.getSheet(0);

        jse = (JavascriptExecutor)driver;

    }//end of before method

    @Test
    public void testScenario() throws InterruptedException, WriteException {

        //For loop
        for (int i = 1; i < rows; i++) {

            //Get contents of Columns
            String zipCode = readableSheet.getCell(0,i).getContents();

            driver.navigate().to("https://www.weightwatchers.com");

            //Verify Page1 Title
            getTitle(driver, "Weight Loss & Wellness Help");

            //Click on Find a Studio
            click(driver, "//*[@class='find-a-meeting']", 0, "Find a Studio");

            //Verify Page2 Title
            getTitle(driver, "Find a Studio & Meeting Near You");

            //Send zip code to search field
            sendKeys(driver, "//*[@name='meetingSearch']", 0, "Meeting Search", zipCode);

            //Click on search button
            click(driver, "//*[@spice='SEARCH_BUTTON']", 0, "Search Button");


            //Get location 1 Text
            String studioInfo = getText(driver, "//*[@class='location__container']", 0, "Studio Location");

            //Click on location
            click(driver, "//*[@class='meeting-location']", 0, "Meeting Location");

            Thread.sleep(2000);
            jse.executeScript("scroll(0,1000)");
            Thread.sleep(2000);
            jse.executeScript("scroll(0,-1000)");
            Thread.sleep(2000);
            jse.executeScript("scroll(0,1000)");
            Thread.sleep(2000);

            //Get Operating hours for current day
            String opHours = getText(driver, "//*[contains(@class, 'currentday')]", 0, "Operation Hours");
            if (opHours == null){
                opHours = "Operation Hours don't exist";
            }//Created if the opHours don't show up

            Thread.sleep(1000);

            //Send the location and the op hours to the writable sheet
            label1 = new Label(1, i, studioInfo);
            writableSheet.addCell(label1);

            label2 = new Label(2, i, opHours);
            writableSheet.addCell(label2);

            System.out.println("-------------------------------------------------------");

        }//End of for loop

    }//end of test method

    @AfterMethod
    public void closeBrowser() throws IOException, WriteException {

        writableFile.write();
        writableFile.close();
        readableFile.close();
        driver.quit();

    }//end of after method



}//End of public class

