package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.stellarburgers.pom.base.BasePage;

public class LoginPage extends BasePage {
    protected static final By TEXT_ENTRANCE = By.xpath(".//h2[text()='Вход']");

    protected static final By INPUT_EMAIL = By.xpath(".//label[text()='Email']/parent::div/input");
    protected static final By INPUT_PASSWORD = By.xpath(".//input[@name='Пароль']");
    protected static final By BUTTON_LOGIN = By.xpath(".//button[text()='Войти']");

    protected static final By LINK_PASSWORD_RECOVERY_PAGE = By.xpath(".//a[@href='/forgot-password']");
    protected static final By LINK_REGISTER_PAGE = By.xpath(".//a[@href='/register']");

    public LoginPage(WebDriver driver) { super(driver); }

    @Step("Wait until 'Login' page to be displayed")
    public LoginPage waitUntilLoginPageToBeDisplayed() {
        waitUntilPageDocumentToBeReady();
        waitUntilPageElementToBeDisplayed(TEXT_ENTRANCE);
        return this;
    }

    @Step("Set Email")
    public void setEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    @Step("Set Password")
    public void setPassword(String password) {
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
    }

    @Step("Click Login Button")
    public MainPage clickLoginButton() {
        waitUntilPageElementToBeClickable(BUTTON_LOGIN);
        driver.findElement(BUTTON_LOGIN).click();
        return new MainPage(driver);
    }

    @Step("Fill out Sign in Form")
    public LoginPage fillOutSignInForm(String email, String password) {
        setEmail(email);
        setPassword(password);
        return this;
    }

    @Step("Click Register Page Link")
    public RegisterPage clickRegisterPageLink() {
        waitUntilPageElementToBeClickable(LINK_REGISTER_PAGE);
        driver.findElement(LINK_REGISTER_PAGE).click();
        return new RegisterPage(driver);
    }

    @Step("Click Password Recovery Page Link")
    public PasswordRecoveryPage clickPasswordRecoveryPageLink() {
        waitUntilPageElementToBeClickable(LINK_PASSWORD_RECOVERY_PAGE);
        driver.findElement(LINK_PASSWORD_RECOVERY_PAGE).click();
        return new PasswordRecoveryPage(driver);
    }

    @Step("Check that Login Button is enabled")
    public boolean checkThatLoginButtonIsEnabled() {
        waitUntilPageElementToBeDisplayed(BUTTON_LOGIN);
        waitUntilPageElementToBeClickable(BUTTON_LOGIN);
        return driver.findElement(BUTTON_LOGIN).isEnabled();
    }
}
