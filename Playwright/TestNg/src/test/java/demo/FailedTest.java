package demo;

import demo.base.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class FailedTest extends TestBase {
    public FailedTest() throws IOException {
    }

    @Test
    void shouldReturnInnerHTML() {
        page.setContent("<div>hello</div>");
        assertEquals(page.innerHTML("css=div"), "another string");
    }
}
