package InClassAI;

import Reusable_Classes.ReusableMethods;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionItem extends ReusableMethods {

    WebDriver driver = null;
    JavascriptExecutor jse;

    @BeforeMethod
    public void openBrowser() throws IOException {

        driver = navigate(driver, "https://www.yahoo.com");

        jse = (JavascriptExecutor)driver;
    }

    @Test
    public void testScenario() throws InterruptedException {

        driver.navigate().to("https://www.yahoo.com");

        Assert.assertEquals("Yahoo", driver.getTitle());

        List<WebElement> tabsCount = driver.findElements(By.xpath("//span[contains(@class, 'Mstart(2')]"));
        System.out.println("Tab Count is " + tabsCount.size());

        sendKeys(driver,"//*[@id='uh-search-box']",0,"Yahoo Search","Nutrition");

        click(driver, "//*[@id='uh-search-button']",0,"Search Button");

        jse.executeScript("scroll(0,100000)");

        Thread.sleep(2000);

        String results = driver.findElement(By.xpath("//*[@class='compPagination']")).getText();
        String [] resultArray = results.split("Next");

        System.out.println("Result Number is " + resultArray[1]);

        jse.executeScript("scroll(100000,0)");

        click(driver,"//*[contains(text(), 'Images')]",0,"Images");

        List<WebElement> images = driver.findElements(By.xpath("//*[contains(@class, 'ld')]"));
        System.out.println("Image count is " + images.size());

        click(driver,"//*[contains(text(), 'Sign in')]",0, "Sign in");

        boolean elementState = driver.findElement(By.xpath("//*[@id = 'persistent']")).isSelected();
        System.out.println("Is element selected? " + elementState);

        sendKeys(driver, "//*[@name= 'username']",0, "Username", "dkafdgkf");

        Thread.sleep(2000);

        click(driver, "//*[@id='login-signin']",0,"Next");

        String errMsg = getText(driver,"//*[@class='row error']",0,"Error Message");

        Assert.assertEquals(errMsg, "Sorry, we don't recognize this email.");

    }

    @AfterMethod
    public void closeBrowser(){

        driver.quit();
    }

}//End of public class
