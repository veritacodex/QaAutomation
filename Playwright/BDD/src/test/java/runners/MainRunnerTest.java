package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        //tags = "@ValidCredentials",
        plugin = {"pretty", "json:target/cucumber-reports/reports.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "junit:target/cucumber-reports/Cucumber.xml",
                "html:target/cucumber-reports/index.html"},
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions", "hooks"}
)

public class MainRunnerTest extends AbstractTestNGCucumberTests {

}