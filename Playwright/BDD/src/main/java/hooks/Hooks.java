package hooks;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import common.PropertiesHelper;
import factories.DriverFactory;
import io.cucumber.java.*;

import java.io.IOException;
import java.nio.file.Paths;

public class Hooks {
    private Page page;
    private PropertiesHelper properties;

    @Before
    public void launchBrowser() throws IOException {
        properties = new PropertiesHelper();
        DriverFactory driverFactory = new DriverFactory();
        page = driverFactory.initDriver(properties);
    }

    //this event runs in reverse order, order=1 will run first
    @After(order = 0)
    public void quitBrowser() {
        page.close();
    }

    @After(order = 1)
    public void takeScreenshotAndTrace(Scenario scenario) {
        boolean tracingEnabled = properties.getBooleanProperty("tracing");
        if (scenario.isFailed() && tracingEnabled) {
            String screenshotName = scenario.getName().replace(" ", "_");
            byte[] sourcePath = page.screenshot();
            scenario.attach(sourcePath, "image/png", screenshotName);
            DriverFactory.getContext().tracing().stop(new Tracing.StopOptions().setPath(Paths.get("target/" + screenshotName + ".zip")));
        }
    }
}
