package praktikum.stellarburgers.pom.page;

import org.junit.After;
import praktikum.stellarburgers.browser.BrowserType;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import praktikum.stellarburgers.constants.UserState;
import praktikum.stellarburgers.order.GetOrdersOrderData;
import praktikum.stellarburgers.order.GetOrdersSuccessInfo;
import praktikum.stellarburgers.order.OrderClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.stellarburgers.pom.base.BaseTest;
import praktikum.stellarburgers.constants.UserStatus;

import java.util.List;

import static praktikum.stellarburgers.browser.BrowserType.*;
import static java.lang.String.format;
import static praktikum.stellarburgers.constants.UserState.LOGGED_IN_USER;
import static praktikum.stellarburgers.constants.UserStatus.AUTHORIZED_USER;

@RunWith(Parameterized.class)
public class MainPageBurgerAssemblyParamTest extends BaseTest {
    private OrderClient orderClient;

    public MainPageBurgerAssemblyParamTest(BrowserType browserType, UserStatus userStatus, UserState userState) {
        super(browserType, userStatus, userState);
    }

    @Before
    public void startUp() {
        getBrowserAndPrepareUserData();
        orderClient = new OrderClient();
    }

    @After
    public void cleanUp() { closeBrowserAndCleanUpUserData(); }

    @Parameterized.Parameters(name = "Burger assembly test: (browser: {0}, userStatus: {1})")
    public static Object[][] getTestData() {
        return new Object[][]{
                { CHROME, AUTHORIZED_USER, LOGGED_IN_USER },
                { FIREFOX, AUTHORIZED_USER, LOGGED_IN_USER },
                { YANDEX, AUTHORIZED_USER, LOGGED_IN_USER },
                { EDGE, AUTHORIZED_USER, LOGGED_IN_USER },
        };
    }

    @Test
    @DisplayName("Assemble a 'Burger'")
    @Description("Check that burger can be assembled in 'Constructor'")
    public void dragAndDropIngredientsFromMenuToBasketThenCheckThatAllIngredientsToBeDisplayedInBasket() {
        mainPage
                .waitUntilBunsTabToBeActive()
                .addBunToOrder()
                .clickSaucesTab()
                .addSauceToOrder()
                .addSauceToOrder()
                .clickToppingsTab()
                .addToppingToOrder()
                .addToppingToOrder()
                .addToppingToOrder()
        ;
        final int EXPECTED_BUN_NUMBER = 2;
        final int ACTUAL_BUN_NUMBER = mainPage.getBasketBunListSize();

        final int EXPECTED_SAUCE_AND_TOPPING_NUMBER = 5;
        final int ACTUAL_SAUCE_AND_TOPPING_NUMBER = mainPage.getBasketSauceAndToppingListSize();

        softAssertions.assertThat(ACTUAL_BUN_NUMBER).isEqualTo(EXPECTED_BUN_NUMBER);
        softAssertions.assertThat(ACTUAL_SAUCE_AND_TOPPING_NUMBER).isEqualTo(EXPECTED_SAUCE_AND_TOPPING_NUMBER);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("Assemble and checkout a 'Burger'")
    @Description("Check that assembled in 'Constructor' 'Burger' can be added to order")
    public void dragAndDropIngredientsFromMenuToBasketThenCheckoutThenCheckThatOrderToBeMade() {
        Integer ACTUAL_ORDER_TRACK = mainPage
                .waitUntilBunsTabToBeActive()
                .addBunToOrder()
                .clickSaucesTab()
                .addSauceToOrder()
                .addSauceToOrder()
                .clickToppingsTab()
                .addToppingToOrder()
                .addToppingToOrder()
                .addToppingToOrder()
                .clickOrderButton()
                .waitUntilOrderToBeCompleted()
                .getOrderId()
        ;
        mainPage.closeModalWindow();

        ValidatableResponse response = orderClient.getOrders(accessToken);
        GetOrdersSuccessInfo getOrdersSuccessInfo = response.extract().body().as(GetOrdersSuccessInfo.class);
        List<GetOrdersOrderData> orders = getOrdersSuccessInfo.getOrders();
        Integer EXPECTED_ORDER_TRACK = orders.get(0).getNumber();
        String errorMessage = format("Wrong order track: %s. Expected: %s",
                ACTUAL_ORDER_TRACK, EXPECTED_ORDER_TRACK);

        Assert.assertEquals(errorMessage, EXPECTED_ORDER_TRACK, ACTUAL_ORDER_TRACK);
    }
}
