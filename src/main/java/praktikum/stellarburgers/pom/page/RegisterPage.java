package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.stellarburgers.pom.base.BasePage;

import java.time.Duration;

public class RegisterPage extends BasePage {
    private static final By TEXT_REGISTER = By.xpath(".//h2[text()='Регистрация']");

    private static final By INPUT_NAME = By.xpath(".//label[text()='Имя']/parent::div/input");
    private static final By INPUT_EMAIL = By.xpath(".//label[text()='Email']/parent::div/input");
    private static final By INPUT_PASSWORD = By.xpath(".//input[@type='password' and @name='Пароль']");

    private static final By BUTTON_REGISTER = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By LINK_LOGIN_PAGE = By.xpath(".//a[@href='/login']");

    private static final By TEXT_INCORRECT_PASSWORD = By.xpath(".//p[text()='Некорректный пароль']");
    private static final By TEXT_ALREADY_EXISTS = By.xpath(".//p[text()='Такой пользователь уже существует']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until 'Main' Page to be displayed")
    public RegisterPage waitUntilRegisterPageToBeDisplayed() {
        waitUntilPageToBeDisplayed(TEXT_REGISTER);
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

    @Step("Set Name")
    public void setName(String name) {
        driver.findElement(INPUT_NAME).sendKeys(name);
    }

    @Step("Click Registration Button")
    public void clickRegistrationButton() {
        driver.findElement(BUTTON_REGISTER).click();
    }

    @Step("Click Sign in Link")
    public LoginPage clickSignInLink() {
        driver.findElement(LINK_LOGIN_PAGE).click();
        return new LoginPage(driver);
    }

    @Step("Check that Password Error Message is displayed")
    public boolean checkThatPasswordErrorMessageIsDisplayed() {
        waitUntilElementToBeDisplayed(TEXT_INCORRECT_PASSWORD);
        return driver.findElement(TEXT_INCORRECT_PASSWORD).isDisplayed();
    }

    @Step("Check that Email Error Message is displayed")
    public boolean checkThatEmailErrorMessageIsDisplayed() {
        waitUntilElementToBeDisplayed(TEXT_ALREADY_EXISTS);
        return driver.findElement(TEXT_ALREADY_EXISTS).isDisplayed();
    }

    @Step("Wait until Element to be displayed")
    public void waitUntilElementToBeDisplayed(By waitElement) {
        WebDriverWait elementWait = new WebDriverWait(driver, Duration.ofSeconds(ELEMENT_WAIT_DURATION));
        elementWait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
    }

    @Step("Fill out Register Form")
    public RegisterPage fillOutRegisterForm(String name, String email, String password) {
        setEmail(email);
        setName(name);
        setPassword(password);
        return this;
    }

    @Step("Click Email Input")
    public RegisterPage  clickEmailInput() {
        clickPageElement(INPUT_EMAIL);
        return this;
    }

    @Step("Click Name Input")
    public RegisterPage  clickNameInput() {
        clickPageElement(INPUT_NAME);
        return this;
    }

    @Step("Click Register Button")
    public LoginPage clickRegisterButton() {
        clickRegistrationButton();
        return new LoginPage(driver);
    }

    @Step("Click Register Button After Entering Incorrect Data")
    public RegisterPage clickRegisterButtonAfterEnteringIncorrectData() {
        waitUntilPageElementToBeClickable(BUTTON_REGISTER);
        clickRegistrationButton();
        return this;
    }
}
