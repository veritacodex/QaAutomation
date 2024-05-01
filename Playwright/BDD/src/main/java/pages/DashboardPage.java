package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {
    private final Locator heading;
    public DashboardPage(Page page) {
        this.heading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Dashboard"));
    }
    public String getHeading() {
        return heading.textContent();
    }
}
