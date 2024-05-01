package org.demo;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.Level;
import org.demo.common.PlaywrightHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class BasicSetup {
    private static final Logger logger = LogManager.getLogger(BasicSetup.class);
    public static void main(String[] args) throws IOException {
        PlaywrightHelper playwrightHelper = new PlaywrightHelper();
        try (Playwright playwright = playwrightHelper.getPlaywrightInstance()) {
            try (Browser browser = playwrightHelper.getBrowser(playwright)) {
                try (BrowserContext context = playwrightHelper.getBrowserContext(browser)) {
                    try (Page page = context.newPage()) {
                        page.navigate("https://playwright.dev");
                        String title = page.title();
                        logger.log(Level.INFO, "**************************************************************************");
                        logger.log(Level.INFO,"The page title is:{}" , title);
                        logger.log(Level.INFO, "**************************************************************************\n");
                    }
                    playwrightHelper.stopTracing(context);
                }
            }
        }
    }
}
