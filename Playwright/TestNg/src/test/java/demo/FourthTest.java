package demo;

import com.microsoft.playwright.Page;
import demo.base.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class FourthTest extends TestBase {
    public FourthTest() throws IOException {
    }

    @Test
    void shouldReturnInnerHTML() {
        page.setContent("<div>hello</div>");
        assertEquals(page.innerHTML("css=div"), "hello");
    }

    @Test
    void shouldClickButton() {
        Page popup = page.waitForPopup(() -> page.evaluate("window.open('about:blank');"));
        assertEquals(popup.url(), "about:blank");
    }
}