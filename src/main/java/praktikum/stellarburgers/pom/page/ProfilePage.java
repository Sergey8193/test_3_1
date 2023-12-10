package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.stellarburgers.pom.base.AccountBasePage;

public class ProfilePage extends AccountBasePage {

    private static final By INPUT_NAME = By.xpath(".//label[text()='Имя']/parent::div/input");
    private static final By INPUT_EMAIL = By.xpath(".//label[text()='Email']/parent::div/input");
    private static final By INPUT_PASSWORD = By.xpath(".//input[@type='password' and @name='Пароль']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until 'Profile' page to be displayed")
    public ProfilePage waitUntilProfilePageToBeDisplayed() {
        waitUntilPageDocumentToBeReady();
        waitUntilPageElementToBeDisplayed(TEXT_ACCOUNT_DESCRIPTION);
        waitUntilPageElementToBeDisplayed(ACTIVE_LINK_PROFILE);
        return this;
    }

    @Step("Full out 'Email' Input")
    public ProfilePage setEmail(String email) {
        waitUntilPageElementToBeClickable(INPUT_EMAIL);
        driver.findElement(INPUT_EMAIL).sendKeys(email);
        return this;
    }

    @Step("Get 'Email' from 'User profile'")
    public String getEmail() {
        waitUntilPageElementToBeClickable(INPUT_EMAIL);
        return driver.findElement(INPUT_EMAIL).getText();
    }

    @Step("Full out 'Password' Input")
    public ProfilePage setPassword(String password) {
        waitUntilPageElementToBeClickable(INPUT_PASSWORD);
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
        return this;
    }

    @Step("Get 'Password' from 'User profile'")
    public String getPassword() {
        waitUntilPageElementToBeClickable(INPUT_PASSWORD);
        return driver.findElement(INPUT_PASSWORD).getText();
    }

    @Step("Full out 'Name' Input")
    public ProfilePage setName(String name) {
        waitUntilPageElementToBeClickable(INPUT_NAME);
        driver.findElement(INPUT_NAME).sendKeys(name);
        return this;
    }

    @Step("Get 'Name' from 'User profile'")
    public String getName() {
        waitUntilPageElementToBeClickable(INPUT_PASSWORD);
        return driver.findElement(INPUT_PASSWORD).getText();
    }
}
