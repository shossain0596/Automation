package Reusable_Classes;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.logging.Logger;

public class ReusableMethods_Loggers {


    //Rename the time for the wait so you can change it
    static int timeOut = 100;


    //Create Navigation and WebDriver
    public static WebDriver navigate(WebDriver driver, String url) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f /t");
        Thread.sleep(3000);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "incognito", "disable-infobars");

        driver = new ChromeDriver(options);

        // logger.log(LogStatus.INFO, "Navigating to the url " + url);

        //navigate to usps
        driver.navigate().to(url);

        return driver;
    }// End of Webdriver Method


    //Create click element method
    public static void click(WebDriver driver, ExtentTest logger, String locator, int index, String elementName) throws IOException {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        System.out.println("Clicking on element " + elementName);
        logger.log(LogStatus.INFO, "Clicking on element " + elementName);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.click();
            logger.log(LogStatus.PASS, "Successfully clicked on element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to click on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL, "Unable to click on element " + elementName + " " + e);

            getScreenshot(driver,logger, elementName);
        }

    }//End of click method


    //Create sendKeys element method
    public static void sendKeys(WebDriver driver, ExtentTest logger, String locator, int index, String elementName, String userInput) throws IOException {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        System.out.println("SendKeys to element " + elementName);
        logger.log(LogStatus.INFO, "Sending Keys to element " + elementName);

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.clear();
            element.sendKeys(userInput);
            logger.log(LogStatus.PASS, "Successfully Sent Keys to element " + elementName);
        } catch (Exception e) {
            System.out.println("Unable to send Keys to element " + elementName + " " + e);
            logger.log(LogStatus.FAIL, "Unable to Send Keys to element " + elementName + " " + e);

            getScreenshot(driver,logger,elementName);
        }
    }//End of sendKeys method


    // MouseHover Method
    public static void mouseHover(WebDriver driver, ExtentTest logger, String locator, int index, String elementName) throws IOException {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        Actions mouse = new Actions(driver);

        System.out.println("Hovering on element " + elementName);
        logger.log(LogStatus.INFO, "Hovering to element " + elementName);

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            mouse.moveToElement(element).perform();
            logger.log(LogStatus.PASS, "Successfully Hovered to element " + elementName);

        } catch (Exception e) {
            System.out.println("Unable to hover on element " + elementName + " " + e);
            logger.log(LogStatus.FAIL, "Unable to Hover to element " + elementName + " " + e);

            getScreenshot(driver,logger,elementName);
        }
    }//End of mouseHover Method


    //Method for get title
    public static void getTitle(WebDriver driver, ExtentTest logger, String expectedTitle) throws IOException {

        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Yes the title Matches");
            logger.log(LogStatus.PASS, "Yes, the Title does match - " + actualTitle);
        } else {
            System.out.println("Title doesn't match - " + actualTitle);
            logger.log(LogStatus.FAIL, "The Title does not match - " + actualTitle);
            getScreenshot(driver,logger,"Title");
        }

    }//End of get title Method

    //Create return method for Get Text
    public static String getText(WebDriver driver, ExtentTest logger, String locator, int index, String elementName) throws IOException {

        String result = null;
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        logger.log(LogStatus.INFO, "Capturing text from Element " + elementName);

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            result = element.getText();
            logger.log(LogStatus.PASS, "Successfully captured text from Element " + elementName);
        } catch (Exception e){
            System.out.println("Unable to locate element " + elementName + " " + e);
            logger.log(LogStatus.FAIL, "Unable to capture text from element " + elementName + " " + e);
            getScreenshot(driver,logger,elementName);
        }

        return result;
    }//End of get text method

    //Create Select Method
    public static void dropDownByText(WebDriver driver, ExtentTest logger, String locator, int index, String selection, String element) throws IOException {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        logger.log(LogStatus.INFO, "Selecting " + selection + " from " + element);


        try {
            WebElement elementName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            Select selectName = new Select(elementName);
            selectName.selectByVisibleText(selection);
            logger.log(LogStatus.PASS, "Successfully selected " + selection + " from " + element);
        } catch (Exception e) {
            System.out.println("Unable to select item " + selection + " from dropdown");
            logger.log(LogStatus.FAIL, "Unable to select " + selection + " from " + element + " " + e);
            getScreenshot(driver,logger,element);
        }
    }// End of Select Method

    //Start of Get Screenshot Method
    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {

        // String path
        String path = "src/main/java/Report_Folder/ScreenShots/";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));

        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots/" + fileName);
        logger.log(LogStatus.FAIL, "", image);

    }// End of Screenshot Method

    //Short UUID Method
    public static String shortUUID() {

        UUID uuid = UUID.randomUUID();

        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();

        return Long.toString(l, Character.MAX_RADIX);

    }//End of Short UUID




}//End of public class
