package demo.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import demo.helper.PlaywrightHelper;
import org.testng.annotations.*;

import java.io.IOException;

public class TestBase {
    private final PlaywrightHelper playwrightHelper;
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    protected Page page;

    public TestBase() throws IOException {
        playwrightHelper = new PlaywrightHelper();
    }
    @BeforeMethod
    public void beforeMethod() {
        playwright = playwrightHelper.getPlaywrightInstance();
        browser = playwrightHelper.getBrowser(playwright);
        context = playwrightHelper.getBrowserContext(browser);
        page = context.newPage();
    }
    @AfterMethod
    public void afterMethod() {
        playwrightHelper.stopTracing(context);
        context.close();
        playwright.close();
    }
    @BeforeTest
    void beforeTest() {
    }
    @AfterTest
    void afterTest() {
    }
    @AfterClass
    public void afterClass() {
    }
    @BeforeClass
    public void beforeClass() {
    }
    @BeforeSuite
    public void beforeSuite() {
    }
    @AfterSuite
    public void afterSuite() {
    }
}