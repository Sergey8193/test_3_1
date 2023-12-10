package praktikum.stellarburgers.pom.page;

import praktikum.stellarburgers.browser.BrowserType;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.stellarburgers.constants.UserState;
import praktikum.stellarburgers.pom.base.BaseTest;
import praktikum.stellarburgers.constants.UserStatus;

import static praktikum.stellarburgers.browser.BrowserType.*;
import static praktikum.stellarburgers.constants.UserState.LOGGED_IN_USER;
import static praktikum.stellarburgers.constants.UserStatus.AUTHORIZED_USER;

@RunWith(Parameterized.class)
public class ProfilePageParamTest extends BaseTest {
    public ProfilePageParamTest(BrowserType browserType, UserStatus userStatus, UserState userState) {
        super(browserType, userStatus, userState);
    }

    @Before
    public void startUp() { getBrowserAndPrepareUserData(); }

    @After
    public void cleanUp() { closeBrowserAndCleanUpUserData(); }

    @Parameterized.Parameters(name = "Navigation between 'Profile' Page and 'Constructor': (browser: {0}, userStatus: {1})")
    public static Object[][] getTestData() {
        return new Object[][]{
                { CHROME, AUTHORIZED_USER, LOGGED_IN_USER },
                { FIREFOX, AUTHORIZED_USER, LOGGED_IN_USER },
                { YANDEX, AUTHORIZED_USER, LOGGED_IN_USER },
                { EDGE, AUTHORIZED_USER, LOGGED_IN_USER },
        };
    }

    @Test
    @DisplayName("Go to 'Personal area'")
    @Description("Check that logged in user can navigate to 'Personal area'")
    public void clickPersonalAreaLinkAndCheckThatProfilePageToBeDisplayed() {
        boolean isProfileLinkActive = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .checkThatProfileLinkToBeActive()
                ;
        Assert.assertTrue(isProfileLinkActive);
    }

    @Test
    @DisplayName("Go to 'Profile' Page")
    @Description("Check that if click 'Profile' Link then 'Profile' Page to be active")
    public void clickProfileLinkThenCheckThatProfilePageToBeActive() {
        boolean isProfileLinkActive = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .clickOrderHistoryLink()
                .clickProfileLink()
                .checkThatProfileLinkToBeActive()
                ;
        Assert.assertTrue(isProfileLinkActive);
    }

    @Test
    @DisplayName("Go to 'Orders history' Page")
    @Description("Check that if click 'Orders history' Link then 'Orders history' Page to be active")
    public void clickOrdersHistoryLinkThenCheckThatOrdersHistoryPageToBeActive() {
        boolean isOrderHistoryLinkActive = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .checkThatProfileLinkToBeActive()
                ;
        Assert.assertTrue(isOrderHistoryLinkActive);
    }

    @Test
    @DisplayName("Go to 'Constructor' from 'Account' with 'Constructor' Link")
    @Description("Check of 'Logged in user' can navigate to burger 'Constructor' from 'Account'")
    public void clickConstructorLinkOnProfilePageThenCheckThatConstructorToBeDisplayed() {
        boolean isBunsTabActive = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .clickConstructorLink()
                .waitUntilMainPageToBeDisplayed()
                .checkThatBunsTabIsActive()
                ;
        Assert.assertTrue(isBunsTabActive);
    }

    @Test
    @DisplayName("Go to 'Constructor' from 'Account' with 'Logo' Link")
    @Description("Check of 'Logged in user' can navigate to burger 'Constructor' from 'Account'")
    public void clickLogoLinkOnProfilePageThenCheckThatConstructorToBeDisplayed() {
        boolean isBunsTabActive = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .clickLogoLink()
                .waitUntilMainPageToBeDisplayed()
                .checkThatBunsTabIsActive()
                ;
        Assert.assertTrue(isBunsTabActive);
    }

    @Test
    @DisplayName("Check that log out 'Account' is available on 'Profile' Page")
    @Description("Check that 'Logged in user' can log out his 'Account' on 'Profile' Page")
    public void clickExitButtonOnProfilePageThenCheckLoginPageToBeDisplayed()  {
        boolean isUserLoggedOut =
                mainPage.clickLoggedInUserPersonalAreaLink()
                        .waitUntilProfilePageToBeDisplayed()
                        .clickExitButton()
                        .waitUntilLoginPageToBeDisplayed()
                        .checkThatLoginButtonIsEnabled()
                ;
        Assert.assertTrue(isUserLoggedOut);
    }
}
