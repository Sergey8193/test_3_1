package praktikum.stellarburgers.pom.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.stellarburgers.pom.page.LoginPage;
import praktikum.stellarburgers.pom.page.OrderHistoryPage;
import praktikum.stellarburgers.pom.page.ProfilePage;

public class AccountBasePage extends BasePage {
    protected final By LINK_PROFILE = By.xpath(".//a[@href='/account/profile']");
    protected final By LINK_ORDERS_HISTORY = By.xpath(".//a[@href='/account/order-history']");

    protected final By ACTIVE_LINK_PROFILE = By.xpath(".//a[contains(@class, 'active__2opc9') and @href='/account/profile']");
    protected final By ACTIVE_LINK_ORDERS_HISTORY = By.xpath(".//a[contains(@class, 'active__2opc9') and@href='/account/order-history']");

    protected final By BUTTON_EXIT = By.xpath(".//li/button[text()='Выход']");

    protected final By TEXT_ACCOUNT_DESCRIPTION = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");

    public AccountBasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Profile' Link")
    public ProfilePage clickProfileLink() {
        waitUntilPageElementToBeClickable(LINK_PROFILE);
        driver.findElement(LINK_PROFILE).click();
        // waitUntilLoadingAnimationImageToBeInvisible();
        return new ProfilePage(driver);
    }

    @Step("Check that 'Profile' Link to be active")
    public boolean checkThatProfileLinkToBeActive() {
        waitUntilPageElementToBeClickable(ACTIVE_LINK_PROFILE);
        return driver.findElement((ACTIVE_LINK_PROFILE)).isEnabled();
    }

    @Step("Click 'Order history' Link")
    public OrderHistoryPage clickOrderHistoryLink() {
        waitUntilPageElementToBeClickable(LINK_ORDERS_HISTORY);
        driver.findElement(LINK_ORDERS_HISTORY).click();
        // waitUntilLoadingAnimationImageToBeInvisible();
        return new OrderHistoryPage(driver);
    }

    @Step("Check that 'Profile' Link to be active")
    public boolean checkThatOrderHistoryLinkToBeActive() {
        waitUntilPageElementToBeClickable(ACTIVE_LINK_ORDERS_HISTORY);
        return driver.findElement((ACTIVE_LINK_ORDERS_HISTORY)).isEnabled();
    }

    @Step("Click 'Exit' Button")
    public LoginPage clickExitButton() {
        waitUntilPageElementToBeClickable(BUTTON_EXIT);
        driver.findElement(BUTTON_EXIT).click();
        //waitUntilLoadingAnimationImageToBeInvisible();
        return new LoginPage(driver);
    }
}
