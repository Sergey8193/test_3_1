package praktikum.stellarburgers.pom.base;

import praktikum.stellarburgers.browser.BrowserType;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import praktikum.stellarburgers.constants.UserState;
import praktikum.stellarburgers.constants.UserStatus;
import praktikum.stellarburgers.pom.page.MainPage;
import praktikum.stellarburgers.user.UserClient;
import praktikum.stellarburgers.user.UserRegistrationData;

import java.util.Objects;

import static praktikum.stellarburgers.browser.Browser.getDriver;
import static praktikum.stellarburgers.browser.BrowserType.BY_BROWSER_SYSTEM_PROPERTY;
import static praktikum.stellarburgers.constants.UserState.LOGGED_IN_USER;
import static praktikum.stellarburgers.constants.UserState.NO_NAME_USER;
import static praktikum.stellarburgers.constants.UserStatus.ANONYMOUS_USER;
import static praktikum.stellarburgers.constants.UserStatus.AUTHORIZED_USER;
import static praktikum.stellarburgers.user.UserDataGenerator.*;

public class BaseTest {
    protected WebDriver driver;

    protected final SoftAssertions softAssertions = new SoftAssertions();

    protected String email = null;
    protected String password = null;
    protected String name = null;
    protected BrowserType browserType = BY_BROWSER_SYSTEM_PROPERTY;
    protected UserStatus userStatus = ANONYMOUS_USER;
    protected UserState userState = NO_NAME_USER;

    protected UserClient userClient = null;
    protected UserRegistrationData  userRegistrationData = null;
    protected String accessToken = null;
    protected MainPage mainPage = null;

    public BaseTest() { }
    public BaseTest(BrowserType browserType, UserStatus userStatus, UserState userState) {
        this.email = Objects.equals(userStatus, AUTHORIZED_USER) ? getRandomEmail() : null;
        this.password = Objects.equals(userStatus, AUTHORIZED_USER) ? getRandomPassword() : null;
        this.name = Objects.equals(userStatus, AUTHORIZED_USER) ? getRandomName() : null;
        this.browserType = browserType;
        this.userStatus = userStatus;
        this.userState = userState;
    }

    protected void getBrowserAndPrepareUserData() {
        driver = getDriver(browserType);

        if (Objects.equals(userState, NO_NAME_USER)) {
            mainPage = new MainPage(driver)
                    .openMainPage()
                    .waitUntilMainPageToBeDisplayed();
        }
        if (Objects.equals(userStatus, AUTHORIZED_USER)) {
            userClient = new UserClient();
            userRegistrationData = new UserRegistrationData(email, password, name);
            accessToken = userClient.createUser(userRegistrationData)
                    .extract()
                    .body()
                    .jsonPath()
                    .getString("accessToken");

            if (Objects.equals(userState, LOGGED_IN_USER)) {
                mainPage = new MainPage(driver)
                        .openMainPage()
                        .waitUntilMainPageToBeDisplayed()
                        .clickPersonalAreaLink()
                        .fillOutSignInForm(email, password)
                        .clickLoginButton()
                        .waitUntilMainPageToBeDisplayed()
                        .waitUntilOrderBurgerButtonToBeDisplayed();
            }
        }
    }

    protected void closeBrowserAndCleanUpUserData() {
        if (!Objects.equals(accessToken, null) && !accessToken.isBlank()) {
            userClient.deleteUser(accessToken);
        }
        if (!Objects.equals(driver, null)) { driver.quit(); }
    }
}
