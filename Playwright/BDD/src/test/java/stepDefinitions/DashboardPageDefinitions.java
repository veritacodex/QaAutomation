package stepDefinitions;

import factories.DriverFactory;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.DashboardPage;

public class DashboardPageDefinitions {

    DashboardPage dashboardPage = new DashboardPage(DriverFactory.getPage());

    @Then("User should be able to login successfully and new page open")
    public void verifyLogin() {
        String dashboardPageHeading = dashboardPage.getHeading();
        Assert.assertEquals(dashboardPageHeading, "Dashboard");
    }
}
