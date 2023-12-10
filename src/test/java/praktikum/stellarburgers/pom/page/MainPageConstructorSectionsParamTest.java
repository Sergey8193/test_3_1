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
import static praktikum.stellarburgers.constants.UserState.NO_NAME_USER;
import static praktikum.stellarburgers.constants.UserStatus.ANONYMOUS_USER;
import static praktikum.stellarburgers.constants.UserStatus.AUTHORIZED_USER;

@RunWith(Parameterized.class)
public class MainPageConstructorSectionsParamTest extends BaseTest {
    public MainPageConstructorSectionsParamTest(BrowserType browserType, UserStatus userStatus, UserState userState) {
        super(browserType, userStatus, userState);
    }

    @Before
    public void startUp() { getBrowserAndPrepareUserData(); }

    @After
    public void cleanUp() { closeBrowserAndCleanUpUserData(); }

    @Parameterized.Parameters(name = "Navigate between 'Constructor' tabs: (browser: {0}, userState: {2})")
    public static Object[][] getTestData() {
        return new Object[][]{
                { CHROME, AUTHORIZED_USER, LOGGED_IN_USER },
                { FIREFOX, AUTHORIZED_USER, LOGGED_IN_USER },
                { YANDEX, AUTHORIZED_USER, LOGGED_IN_USER },
                { EDGE, AUTHORIZED_USER, LOGGED_IN_USER },

                { CHROME, ANONYMOUS_USER, NO_NAME_USER },
                { FIREFOX, ANONYMOUS_USER, NO_NAME_USER },
                { YANDEX, ANONYMOUS_USER, NO_NAME_USER },
                { EDGE, ANONYMOUS_USER, NO_NAME_USER },
        };
    }

    @Test
    @DisplayName("Go to 'Buns' tab")
    @Description("Check that if click 'Buns' tab then 'Buns' tab to be active")
    public void transitionToBunsInConstructorTest() {
        boolean isBunsTabActive = new MainPage(driver)
                .openMainPage()
                .waitUntilMainPageToBeDisplayed()
                .clickToppingsTab()
                .clickBunsTab()
                .checkThatBunsTabIsActive()
                ;
        Assert.assertTrue(isBunsTabActive);
    }

    @Test
    @DisplayName("Go to 'Sauces' tab")
    @Description("Check that if click 'Sauces' tab then 'Sauces' tab to be active")
    public void transitionToSaucesInConstructorTest() {
        boolean isSaucesTabActive = new MainPage(driver)
                .openMainPage()
                .waitUntilMainPageToBeDisplayed()
                .clickSaucesTab()
                .checkThatSaucesTabIsActive()
                ;
        Assert.assertTrue(isSaucesTabActive);
    }

    @Test
    @DisplayName("Go to 'Toppings' tab")
    @Description("Check that if click 'Toppings' tab then 'Toppings' tab to be active")
    public void clickToppingsTabThenCheckThatToppingsTabIsActive() {
        boolean isToppingsTabActive = new MainPage(driver)
                .openMainPage()
                .waitUntilMainPageToBeDisplayed()
                .clickToppingsTab()
                .checkThatToppingsTabIsActive()
                ;
        Assert.assertTrue(isToppingsTabActive);
    }
}
