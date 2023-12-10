package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import praktikum.stellarburgers.pom.base.AccountBasePage;

import java.util.List;

public class OrderHistoryPage extends AccountBasePage {

    public static final By DIV_HISTORY_CONTAINER = By.xpath(".//div[@class='OrderHistory_orderHistory__qy1VB']");
    public static final By UL_HISTORY_LIST = By.xpath(".//ul[@class='OrderHistory_profileList__374GU OrderHistory_list__KcLDB']");
    public static final By A_HISTORY_LIST_ITEM = By.xpath(".//a[@class='OrderHistory_link__1iNby']");

    public static final By P_HISTORY_LIST_ITEM_TRACK = By.xpath(".//div[@class='OrderHistory_textBox__3lgbs mb-6']/p[@class='text text_type_digits-default']");
    public static final By H2_HISTORY_LIST_ITEM_NAME = By.xpath(".//h2[@class='text text_type_main-medium mb-2']");

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

     @Step("Wait until 'Orders history' Page to be displayed")
    public OrderHistoryPage waitUntilOrdersHistoryPageToBeDisplayed() {
        waitUntilPageDocumentToBeReady();
        waitUntilPageElementToBeDisplayed(TEXT_ACCOUNT_DESCRIPTION);
        waitUntilPageElementToBeDisplayed(ACTIVE_LINK_ORDERS_HISTORY);
        return this;
    }

    @Step("Wait until 'Orders history' List to be displayed")
    public OrderHistoryPage waitUntilOrdersHistoryListToBeDisplayed() {
        waitUntilPageElementToBeDisplayed(DIV_HISTORY_CONTAINER);
        waitUntilPageElementToBeDisplayed(UL_HISTORY_LIST);
        return this;
    }

    @Step("Get 'Orders history' List")
    public List<WebElement> getOrdersHistoryList() {
        return driver.findElement(UL_HISTORY_LIST).findElements(A_HISTORY_LIST_ITEM);
    }

    @Step("Get 'Orders history' 'List item' property value")
    public String getOrderPropertyValue(WebElement order, By property) {
        return order.findElement(property).getText();
    }

    @Step("Get 'Order track'")
    public Integer getOrderTrack(WebElement order) {
        String propertyValue = getOrderPropertyValue(order, P_HISTORY_LIST_ITEM_TRACK);
        return Integer.valueOf(propertyValue.replace( "#", ""));
    }

    @Step("Get 'Order name'")
    public String getOrderName(WebElement order) {
        return getOrderPropertyValue(order, H2_HISTORY_LIST_ITEM_NAME);
    }
}
