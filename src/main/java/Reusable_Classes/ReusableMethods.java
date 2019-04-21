package Reusable_Classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class ReusableMethods {


    //Rename the time for the wait so you can change it
    static int timeOut = 100;


    //Create Navigation and WebDriver
    public static WebDriver navigate(WebDriver driver, String url) throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f /t");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized" /*"incognito"*/, "disable-infobars");

        driver = new ChromeDriver(options);

        //navigate to usps
        driver.navigate().to(url);

        return driver;
    }// End of Webdriver Method


    //Create click element method
    public static void click(WebDriver driver, String locator, int index, String elementName) {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        System.out.println("Clicking on element " + elementName);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.click();
        } catch (Exception e) {
            System.out.println("Unable to click on element " + elementName + " " + e);
        }

    }//End of click method


    //Create sendKeys element method
    public static void sendKeys(WebDriver driver, String locator, int index, String elementName, String userInput) {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        System.out.println("SendKeys to element " + elementName);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            element.clear();
            element.sendKeys(userInput);
        } catch (Exception e) {
            System.out.println("Unable to send Keys to element " + elementName + " " + e);
        }
    }//End of sendKeys method


    // MouseHover Method
    public static void mouseHover(WebDriver driver, String locator, int index, String elementName) {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        Actions mouse = new Actions(driver);

        System.out.println("Hovering on element " + elementName);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            mouse.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("Unable to hover on element " + elementName + " " + e);
        }
    }//End of mouseHover Method


    //Method for get title
    public static void getTitle(WebDriver driver, String expectedTitle) {

        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Yes the title Matches");
        } else {
            System.out.println("Title doesn't match - " + actualTitle);
        }

    }//End of get title Method

    //Create return method for Get Text
    public static String getText(WebDriver driver, String locator, int index, String elementName) {

        String result = null;
        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            result = element.getText();
        } catch (Exception e){
            System.out.println("Unable to locate element " + elementName + " " + e);
        }

        return result;
    }//End of get text method

    //Create Select Method
    public static void dropDownByText(WebDriver driver, String locator, int index, String selection) {

        WebDriverWait wait = new WebDriverWait(driver, timeOut);

        try {
            WebElement elementName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index);
            Select selectName = new Select(elementName);
            selectName.selectByVisibleText(selection);
        } catch (Exception e) {
            System.out.println("Unable to select item " + selection + " from dropdown");
        }
    }// End of Select Method

    /*//Start of Get Screenshot Method
    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {

        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_Folder\\ScreenShots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);
    }*/

}//End of public class
