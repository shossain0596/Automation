package Google_Cucumber;

import Reusable_Classes.ReusableMethods;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class googleTestScenarios extends ReusableMethods {

    WebDriver driver = null;

    @Given("^I navigate to google.com$")
    public void openBrowser() throws IOException {
        driver = navigate(driver,"https://www.google.com");
    }

    @When("^I verify my Google Home title states as Google$")
    public void verifyHomeTitle(){
        getTitle(driver, "GOOGLE");
    }

    @Then("^I enter a keyword in my Search field$")
    public void enterKeyword (){
        sendKeys(driver, "//*[@name = 'q']",0,"Search Field","Cars");
    }

    @And("^I click on search Icon$")
    public void clickOnSearch (){
        click(driver,"//*[@name='btnK']",0,"Search Button");
    }

    @When("^I verify the Google Search$")
    public void verifySearch(){
        getTitle(driver,"Google Search");
    }

    @Then("^I capture the Search Result$")
    public void captureSearchResult () {
        String result = getText(driver,"//*[@id='resultStats']",0, "Search Result");
        String [] arrayResult = result.split(" ");
        System.out.println("Search Number is " + arrayResult[1]);
    }

}//End of Public Class
