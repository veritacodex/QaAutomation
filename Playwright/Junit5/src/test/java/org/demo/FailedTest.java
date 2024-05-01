package org.demo;

import org.demo.base.TestBase;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FailedTest extends TestBase {

    public FailedTest() throws IOException {
    }

    @Test
    void shouldReturnInnerHTML() {
        page.setContent("<div>hello</div>");
        assertEquals("failString", page.innerHTML("css=div"));
    }
}
