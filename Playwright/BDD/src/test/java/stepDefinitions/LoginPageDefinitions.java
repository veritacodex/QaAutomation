package stepDefinitions;

import factories.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class LoginPageDefinitions {

    LoginPage loginPage = new LoginPage(DriverFactory.getPage());

    @Given("User is on HRMLogin page {string}")
    public void loginTest(String url) {
        loginPage.navigateToUrl(url);
    }

    @When("User enters username as {string} and password as {string}")
    public void userEntersData(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("User should be able to see error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getAlertBoxText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}