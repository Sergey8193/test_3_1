package praktikum.stellarburgers.pom.page;

import org.junit.Assert;
import praktikum.stellarburgers.browser.BrowserType;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.stellarburgers.constants.WayToLoginPage;
import praktikum.stellarburgers.pom.base.BaseTest;
import praktikum.stellarburgers.constants.UserStatus;

import static java.lang.String.format;
import static praktikum.stellarburgers.browser.BrowserType.*;
import static praktikum.stellarburgers.constants.UserState.NO_NAME_USER;
import static praktikum.stellarburgers.constants.UserStatus.AUTHORIZED_USER;
import static praktikum.stellarburgers.constants.WayToLoginPage.*;

@RunWith(Parameterized.class)
public class LoginPageParamTest extends BaseTest {
    private final WayToLoginPage WAY_TO_LOGIN_PAGE;

    public LoginPageParamTest(BrowserType browserType, UserStatus userStatus, WayToLoginPage wayToLoginPage) {
        super(browserType, userStatus, NO_NAME_USER);
        this.WAY_TO_LOGIN_PAGE = wayToLoginPage;
    }

    @Before
    public void startUp() { getBrowserAndPrepareUserData(); }

    @After
    public void cleanUp() { closeBrowserAndCleanUpUserData(); }

    @Parameterized.Parameters(name = "Login user test: (browser: {0}, userStatus: {1}, wayToLoginPage: {2})")
    public static Object[][] getTestData() {
        return new Object[][]{
                { CHROME, AUTHORIZED_USER, PERSONAL_AREA_LINK_ON_MAIN_PAGE },
                { CHROME, AUTHORIZED_USER, LOGIN_TO_ACCOUNT_BUTTON_ON_MAIN_PAGE },
                { CHROME, AUTHORIZED_USER, LOGIN_LINK_ON_REGISTER_PAGE },
                { CHROME, AUTHORIZED_USER, LOGIN_LINK_ON_PASSWORD_RECOVERY_PAGE },

                { FIREFOX, AUTHORIZED_USER, PERSONAL_AREA_LINK_ON_MAIN_PAGE },
                { FIREFOX, AUTHORIZED_USER, LOGIN_TO_ACCOUNT_BUTTON_ON_MAIN_PAGE },
                { FIREFOX, AUTHORIZED_USER, LOGIN_LINK_ON_REGISTER_PAGE },
                { FIREFOX, AUTHORIZED_USER, LOGIN_LINK_ON_PASSWORD_RECOVERY_PAGE },

                { YANDEX, AUTHORIZED_USER, PERSONAL_AREA_LINK_ON_MAIN_PAGE },
                { YANDEX, AUTHORIZED_USER, LOGIN_TO_ACCOUNT_BUTTON_ON_MAIN_PAGE },
                { YANDEX, AUTHORIZED_USER, LOGIN_LINK_ON_REGISTER_PAGE },
                { YANDEX, AUTHORIZED_USER, LOGIN_LINK_ON_PASSWORD_RECOVERY_PAGE },

                { EDGE, AUTHORIZED_USER, PERSONAL_AREA_LINK_ON_MAIN_PAGE },
                { EDGE, AUTHORIZED_USER, LOGIN_TO_ACCOUNT_BUTTON_ON_MAIN_PAGE },
                { EDGE, AUTHORIZED_USER, LOGIN_LINK_ON_REGISTER_PAGE },
                { EDGE, AUTHORIZED_USER, LOGIN_LINK_ON_PASSWORD_RECOVERY_PAGE },
        };
    }

    @Test
    @DisplayName("Login user")
    @Description("Login after opening user authorization page in a certain way")
    public void loginUsingPredefinedWay() {
        LoginPage loginPage = new LoginPage(driver);

        switch (WAY_TO_LOGIN_PAGE) {
            case PERSONAL_AREA_LINK_ON_MAIN_PAGE:
                loginPage = mainPage
                        .clickPersonalAreaLink()
                        .waitUntilLoginPageToBeDisplayed();
                break;
            case LOGIN_TO_ACCOUNT_BUTTON_ON_MAIN_PAGE:
                loginPage = mainPage
                        .clickLoginToAccountButton()
                        .waitUntilLoginPageToBeDisplayed();
                break;
            case LOGIN_LINK_ON_REGISTER_PAGE:
                loginPage = mainPage
                        .clickPersonalAreaLink()
                        .waitUntilLoginPageToBeDisplayed()
                        .clickRegisterPageLink()
                        .waitUntilRegisterPageToBeDisplayed()
                        .clickSignInLink();
                break;
            case LOGIN_LINK_ON_PASSWORD_RECOVERY_PAGE:
                loginPage = mainPage
                        .clickPersonalAreaLink()
                        .waitUntilLoginPageToBeDisplayed()
                        .clickPasswordRecoveryPageLink()
                        .waitUntilPasswordRecoveryPageToBeDisplayed()
                        .clickSignInLink();
                break;
        }

        final boolean IS_USER_LOGGED_IN = loginPage
                .fillOutSignInForm(email, password)
                .clickLoginButton()
                .waitUntilMainPageToBeDisplayed()
                .waitUntilServiceOpensOpportunityToPlaceOrder()
                ;
        Assert.assertTrue( "User is not logged in", IS_USER_LOGGED_IN);
    }
}
