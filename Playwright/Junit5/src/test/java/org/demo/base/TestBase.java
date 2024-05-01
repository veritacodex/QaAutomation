package org.demo.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.demo.helper.PlaywrightHelper;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {
    private final PlaywrightHelper playwrightHelper;
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    protected Page page;

    public TestBase() throws IOException {
        playwrightHelper = new PlaywrightHelper();
    }

    @BeforeAll
    void launchBrowser() {
        playwright = playwrightHelper.getPlaywrightInstance();
        browser = playwrightHelper.getBrowser(playwright);
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = playwrightHelper.getBrowserContext(browser);
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        playwrightHelper.stopTracing(context);
        context.close();
    }
}
