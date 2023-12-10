package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.stellarburgers.pom.base.BaseTest;

import static praktikum.stellarburgers.browser.BrowserType.BY_BROWSER_SYSTEM_PROPERTY;
import static praktikum.stellarburgers.constants.UserState.NO_NAME_USER;
import static praktikum.stellarburgers.constants.UserStatus.ANONYMOUS_USER;

public class MainPageConstructorSectionsTest extends BaseTest {
    public MainPageConstructorSectionsTest() { super(BY_BROWSER_SYSTEM_PROPERTY, ANONYMOUS_USER, NO_NAME_USER); }

    @Before
    public void startUp() { getBrowserAndPrepareUserData(); }

    @After
    public void cleanUp() { closeBrowserAndCleanUpUserData(); }

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
