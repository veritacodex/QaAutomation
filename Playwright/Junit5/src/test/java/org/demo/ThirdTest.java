package org.demo;

import org.demo.base.TestBase;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThirdTest extends TestBase {
    public ThirdTest() throws IOException {
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