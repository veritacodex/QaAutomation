package demo;

import demo.base.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class FirstTest extends TestBase {
    public FirstTest() throws IOException {
    }

    @Test
    void shouldSearchWiki() {
        page.navigate("https://en.wikipedia.org/wiki/Main_Page");
        page.locator("input[name=\"search\"]").click();
        page.locator("input[name=\"search\"]").fill("playwright");
        page.locator("input[name=\"search\"]").press("Enter");
        assertEquals(page.url(), "https://en.wikipedia.org/wiki/Playwright");
    }
}