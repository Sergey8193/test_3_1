package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.stellarburgers.pom.base.BasePage;

public class PasswordRecoveryPage extends BasePage {
    private final By TEXT_PASSWORD_RECOVERY = By.xpath(".//main/div/h2[text()='Восстановление пароля']");
    private final By INPUT_EMAIL = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    private final By LINK_LOGIN_PAGE = By.xpath(".//div/p/a[@href='/login' and text()='Войти']");

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until 'Password recovery' Page to be displayed")
    public PasswordRecoveryPage waitUntilPasswordRecoveryPageToBeDisplayed() {
        waitUntilPageDocumentToBeReady();
        waitUntilPageElementToBeDisplayed(TEXT_PASSWORD_RECOVERY);
        return this;
    }

    @Step("Set 'Email'")
    public void setEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    @Step("Click 'Login' Link")
    public LoginPage clickSignInLink() {
        driver.findElement(LINK_LOGIN_PAGE).click();
        waitUntilLoadingAnimationImageToBeInvisible();
        return new LoginPage(driver);
    }
}
