package Reusable_Classes;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class Abstract_Class extends  ReusableMethods_Loggers{

    //All the public static variables need to be set to null to be reused
    //on multiple test class

    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;

    //Before Suite is needed to define your ChromeDriver
    @BeforeSuite
    public void openBrowser() {

        //Path to your report
        report = new ExtentReports("src/main/java/Report_Folder/AutomationReport" + shortUUID() + ".html", true);

    }//End of Before Suite

    @Parameters("Browser")
    @BeforeMethod
    public void getTestName(Method methodName, String Browser) throws IOException, InterruptedException {
        if (Browser.equalsIgnoreCase("Chrome")){
            driver = navigate(driver, "https://www.google.com");
        } else if (Browser.equalsIgnoreCase("Firefox")){
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
            Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.navigate().to("https://www.google.com");
        }//End of Browser Set up

        logger = report.startTest(methodName.getName() + " - " + Browser);
        logger.log(LogStatus.INFO, "Automation Test Scenario started...");

        jse = (JavascriptExecutor)driver;

    }//End of Before Method

    @AfterMethod
    public void endLogger() {
        report.endTest(logger);
        logger.log(LogStatus.INFO, "Automation Test Scenario ended...");

    }//End of After Method

    @AfterSuite
    public void closeSuite() {
        report.flush();
        report.close();
        //driver.quit();

    }//End of After Suite
}//End of public class
