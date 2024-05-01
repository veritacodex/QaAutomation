package factories;

import com.microsoft.playwright.*;
import common.PropertiesHelper;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static final ThreadLocal<Page> threadLocalDriver = new ThreadLocal<>();
    public static final ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();

    public Page initDriver(PropertiesHelper properties) {
        boolean headless = properties.getBooleanProperty("headless");
        String browserName = properties.getStringProperty("browser");
        boolean downloadDrivers = properties.getBooleanProperty("downloadDrivers");
        int humanizeDelay = properties.getIntProperty("humanizeDelay");

        Map<String, String> environment = new HashMap<>();
        if (!downloadDrivers) {
            environment.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");
        }
        Playwright.CreateOptions createOptions = new Playwright.CreateOptions().setEnv(environment);
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(humanizeDelay);

        BrowserType browserType;
        Browser browser = switch (browserName) {
            case "firefox" -> {
                browserType = Playwright.create(createOptions).firefox();
                yield browserType.launch(launchOptions);
            }
            case "chrome" -> {
                browserType = Playwright.create(createOptions).chromium();
                yield browserType.launch(launchOptions.setChannel("chrome"));
            }
            case "webkit" -> {
                browserType = Playwright.create(createOptions).webkit();
                yield browserType.launch(launchOptions);
            }
            default -> throw new IllegalArgumentException("Could not Launch Browser for type:" + browserName);
        };

        BrowserContext context = browser.newContext();
        boolean tracingEnabled = properties.getBooleanProperty("tracing");
        if (tracingEnabled)
            context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(false));
        Page page = context.newPage();
        threadLocalDriver.set(page);
        threadLocalContext.set(context);
        return page;
    }

    public static synchronized Page getPage() {
        return threadLocalDriver.get();
    }
    public static synchronized BrowserContext getContext() {
        return threadLocalContext.get();
    }
}

