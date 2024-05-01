package org.demo;

import com.microsoft.playwright.Page;
import org.demo.base.TestBase;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FourthTest extends TestBase {
    public FourthTest() throws IOException {
    }

    @Test
    void shouldReturnInnerHTML() {
        page.setContent("<div>hello</div>");
        assertEquals("hello", page.innerHTML("css=div"));
    }

    @Test
    void shouldClickButton() {
        Page popup = page.waitForPopup(() -> page.evaluate("window.open('about:blank');"));
        assertEquals("about:blank", popup.url());
    }
}