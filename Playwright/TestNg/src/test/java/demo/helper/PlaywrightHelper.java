package demo.helper;

import com.microsoft.playwright.*;
import common.PropertiesHelper;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PlaywrightHelper {
    private final PropertiesHelper properties;

    public PlaywrightHelper() throws IOException {
        properties = new PropertiesHelper();
    }

    public Playwright getPlaywrightInstance() {
        boolean downloadDrivers = properties.getBooleanProperty("downloadDrivers");
        if (!downloadDrivers) {
            Map<String, String> env = new HashMap<>();
            env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");
            return Playwright.create(new Playwright.CreateOptions().setEnv(env));
        } else return Playwright.create();
    }

    public Browser getBrowser(Playwright playwright) {
        boolean headless = properties.getBooleanProperty("headless");
        int humanizeDelay = properties.getIntProperty("humanizeDelay");
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setSlowMo(humanizeDelay));
    }

    public BrowserContext getBrowserContext(Browser browser) {
        BrowserContext context = browser.newContext();
        boolean tracing = properties.getBooleanProperty("tracing");
        if (tracing) {
            context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }
        return context;
    }

    public void stopTracing(BrowserContext context) {
        boolean tracing = properties.getBooleanProperty("tracing");
        if (tracing) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("./target/traces/" + timeStamp + ".trace.zip")));
        }
    }
}
