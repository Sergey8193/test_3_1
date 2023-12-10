package praktikum.stellarburgers.pom.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import praktikum.stellarburgers.pom.page.LoginPage;
import praktikum.stellarburgers.pom.page.MainPage;
import praktikum.stellarburgers.pom.page.ProfilePage;

public class BasePage extends BaseWait {
    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    protected static final By LINK_CONSTRUCTOR = By.xpath(".//nav//li/a[@href='/']");
    protected static final By LINK_LOGO = By.xpath(".//nav/div/a[@href='/']");
    protected static final By LINK_PERSONAL_AREA = By.xpath(".//nav/a[@href='/account']");

    protected static final By IMAGE_LOADING_ANIMATION = By.xpath(".//img[@src='./static/media/loading.89540200.svg']");

    protected BasePage(WebDriver driver){ super(driver); }

    @Step("Click 'Personal area' link")
    public LoginPage clickPersonalAreaLink() {
        waitUntilPageElementToBeClickable(LINK_PERSONAL_AREA);
        driver.findElement(LINK_PERSONAL_AREA).click();
        return new LoginPage(driver);
    }

    @Step("Click logged in user 'Personal area' link")
    public ProfilePage clickLoggedInUserPersonalAreaLink() {
        waitUntilPageElementToBeClickable(LINK_PERSONAL_AREA);
        driver.findElement(LINK_PERSONAL_AREA).click();
        return new ProfilePage(driver);
    }

    @Step("Click 'Logo' link")
    public MainPage clickLogoLink() {
        waitUntilPageElementToBeClickable(LINK_LOGO);
        driver.findElement(LINK_LOGO).click();
        return new MainPage(driver);
    }

    @Step("Click 'Constructor' link")
    public MainPage clickConstructorLink() {
        waitUntilPageElementToBeClickable(LINK_CONSTRUCTOR);
        driver.findElement(LINK_CONSTRUCTOR).click();
        return new MainPage(driver);
    }

    @Step("Click 'Page element'")
    public void clickPageElement(By pageElement) {
        waitUntilPageElementToBeClickable(pageElement);
        driver.findElement(pageElement).click();
    }

    @Step("Wait until 'loading animation image' to be invisible")
    protected void waitUntilLoadingAnimationImageToBeInvisible() {
        waitUntilLoadingToBeFinished(IMAGE_LOADING_ANIMATION);
    }
}
