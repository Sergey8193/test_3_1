package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import praktikum.stellarburgers.order.GetOrdersOrderData;
import praktikum.stellarburgers.order.GetOrdersSuccessInfo;
import praktikum.stellarburgers.order.OrderClient;
import praktikum.stellarburgers.order.OrderData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import praktikum.stellarburgers.pom.base.BaseTest;

import java.util.List;
import java.util.Random;

import static praktikum.stellarburgers.browser.BrowserType.BY_BROWSER_SYSTEM_PROPERTY;
import static praktikum.stellarburgers.constants.UserState.NO_NAME_USER;
import static praktikum.stellarburgers.order.OrderDataGenerator.getRandomOrderData;
import static praktikum.stellarburgers.constants.UserStatus.AUTHORIZED_USER;

public class OrderHistoryPageTest extends BaseTest {
    private int ordersNumber;
    private GetOrdersSuccessInfo getOrdersSuccessInfo;

    private final static int ORDERS_COUNT = 7;
    private final static boolean FROM_REAL_DATA = true;

    public OrderHistoryPageTest() { super(BY_BROWSER_SYSTEM_PROPERTY, AUTHORIZED_USER, NO_NAME_USER); }

    @Before
    public void startUp() {
        getBrowserAndPrepareUserData();

        OrderClient orderClient = new OrderClient();
        ordersNumber = new Random().nextInt(ORDERS_COUNT - 1) + 1;
        for (int i = 0; i < ordersNumber; i++) {
            OrderData orderData = getRandomOrderData(FROM_REAL_DATA);
            orderClient.createOrder(orderData, accessToken);
        }

        ValidatableResponse ordersResponse = orderClient.getOrders(accessToken);
        getOrdersSuccessInfo = ordersResponse.extract().body().as(GetOrdersSuccessInfo.class);

        mainPage = new MainPage(driver)
                .openMainPage()
                .waitUntilMainPageToBeDisplayed()
                .clickPersonalAreaLink()
                .fillOutSignInForm(email, password)
                .clickLoginButton()
                .waitUntilMainPageToBeDisplayed()
                .waitUntilOrderBurgerButtonToBeDisplayed();
    }

    @After
    public void cleanUp() { closeBrowserAndCleanUpUserData(); }

    @Test
    @DisplayName("check 'Order History' List is valid")
    @Description("Check that 'Orders history' List contains 'Logged in user' orders")
    public void checkThatExistentUserOrderHistoryListContainsSpecificOrder() {
        OrderHistoryPage page = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .clickOrderHistoryLink()
                .waitUntilOrdersHistoryPageToBeDisplayed()
                .waitUntilOrdersHistoryListToBeDisplayed()
                ;
        List<WebElement> orders = page.getOrdersHistoryList();
        boolean isOrdersNotEmpty = !orders.isEmpty();

        final Integer ACTUAL_ORDER_TRACK = isOrdersNotEmpty ? page.getOrderTrack(orders.get(0)) : null;
        final String  ACTUAL_ORDER_NAME = isOrdersNotEmpty ? page.getOrderName(orders.get(0)) : null;

        GetOrdersOrderData order = getOrdersSuccessInfo.getOrders().get(0);

        final Integer EXPECTED_ORDER_TRACK = order.getNumber();
        final String EXPECTED_ORDER_NAME = order.getName();

        softAssertions.assertThat(isOrdersNotEmpty).isEqualTo(true);
        softAssertions.assertThat(orders.size()).isEqualTo(ordersNumber);
        softAssertions.assertThat(ACTUAL_ORDER_TRACK).isEqualTo(EXPECTED_ORDER_TRACK);
        softAssertions.assertThat(ACTUAL_ORDER_NAME).isEqualTo(EXPECTED_ORDER_NAME);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Go to 'Orders history' Page")
    @Description("Check that if click 'Orders history' Link on 'Profile' Page then 'Orders history' Page to be active")
    public void clickOrdersHistoryLinkThenCheckThatOrdersHistoryPageToBeActive() {
        boolean isOrderHistoryLinkActive = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .checkThatProfileLinkToBeActive()
                ;
        Assert.assertTrue(isOrderHistoryLinkActive);
    }

    @Test
    @DisplayName("Go to 'Constructor' from 'Orders history' Page with 'Constructor' Link")
    @Description("Check that 'Logged in user' can navigate to burger 'Constructor' from 'Orders history' Page")
    public void clickConstructorLinkOnOrderHistoryPageThenCheckThatConstructorToBeDisplayed() {
        boolean isBunsTabActive = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .clickOrderHistoryLink()
                .waitUntilOrdersHistoryPageToBeDisplayed()
                .clickConstructorLink()
                .waitUntilMainPageToBeDisplayed()
                .checkThatBunsTabIsActive()
                ;
        Assert.assertTrue(isBunsTabActive);
    }

    @Test
    @DisplayName("Go to 'Constructor' from 'Orders history' Page with 'Logo' Link")
    @Description("Check that 'Logged in user' can navigate to burger 'Constructor' from 'Orders history' Page")
    public void clickLogoLinkOnOrderHistoryPageThenCheckThatConstructorToBeDisplayed() {
        boolean isBunsTabActive = mainPage
                .clickLoggedInUserPersonalAreaLink()
                .waitUntilProfilePageToBeDisplayed()
                .clickOrderHistoryLink()
                .waitUntilOrdersHistoryPageToBeDisplayed()
                .clickLogoLink()
                .waitUntilMainPageToBeDisplayed()
                .checkThatBunsTabIsActive()
                ;
        Assert.assertTrue(isBunsTabActive);
    }

    @Test
    @DisplayName("Check that log out 'Account' is available on 'Orders history' Page")
    @Description("Check that 'Logged in user' can log out his 'Account' on 'Orders history' Page")
    public void clickExitButtonOnOrderHistoryPageThenCheckLoginPageToBeDisplayed()  {
        boolean isUserLoggedOut =
                mainPage.clickLoggedInUserPersonalAreaLink()
                        .waitUntilProfilePageToBeDisplayed()
                        .clickOrderHistoryLink()
                        .waitUntilOrdersHistoryPageToBeDisplayed()
                        .clickExitButton()
                        .waitUntilLoginPageToBeDisplayed()
                        .checkThatLoginButtonIsEnabled()
                ;
        Assert.assertTrue(isUserLoggedOut);
    }
}
