package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {
    private final Page page;
    private final Locator usernameTextBox;
    private final Locator passwordTextBox;
    private final Locator loginButton;
    private final Locator alertBox;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameTextBox = page.locator("[name='username']");
        this.passwordTextBox = page.locator("[name='password']");
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        this.alertBox = page.getByRole(AriaRole.ALERT);
    }

    public void navigateToUrl(String url) {
        this.page.navigate(url);
    }
    public void enterUsername(String username) {
        usernameTextBox.fill(username);
    }
    public void enterPassword(String password) {
        passwordTextBox.fill(password);
    }
    public void clickLogin() {
        loginButton.click();
    }
    public String getAlertBoxText(){
        return alertBox.textContent();
    }
}
