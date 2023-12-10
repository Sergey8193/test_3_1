package praktikum.stellarburgers.pom.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.stellarburgers.pom.base.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MainPage extends BasePage {
    public static final int BUN = 0;
    public static final int SAUCE = 1;
    public static final int TOPPING = 2;

    public static final By TEXT_ASSEMBLE_THE_BURGER = By.xpath(".//h1[text()='Соберите бургер']");

    public static final By TAB_BUNS = By.xpath(".//div[contains(@class, 'tab__1SPyG')]/span[text()='Булки']/parent::div");
    public static final By TAB_SAUCES = By.xpath(".//div[contains(@class, 'tab__1SPyG')]/span[text()='Соусы']/parent::div");
    public static final By TAB_TOPPINGS = By.xpath(".//div[contains(@class, 'tab__1SPyG')]/span[text()='Начинки']/parent::div");

    public static final By ACTIVE_TAB_BUNS = By.xpath(".//div[contains(@class, 'tab_type_current__2BEPc')]/span[text()='Булки']/parent::div");
    public static final By ACTIVE_TAB_SAUCES = By.xpath(".//div[contains(@class, 'tab_type_current__2BEPc')]/span[text()='Соусы']/parent::div");
    public static final By ACTIVE_TAB_TOPPINGS = By.xpath(".//div[contains(@class, 'tab_type_current__2BEPc')]/span[text()='Начинки']/parent::div");

    public static final By INGREDIENT_LIST = By.xpath(".//div[contains(@class, 'Container__Xu3Mo')]/ul");
    public static final By INGREDIENT = By.xpath(".//a");
    public static final By BASKET = By.xpath(".//section[contains(@class, 'BurgerConstructor_basket__29Cd7 mt-25 ')]");
    public static final By BASKET_BUN_LIST_ITEM = By.xpath(".//section[contains(@class, 'BurgerConstructor_basket__29Cd7 mt-25 ')]/ul//li[contains(@class, 'listItem__aWMu1 mr-4')]");
    public static final By BASKET_SAUCE_AND_TOPPING_LIST_ITEM = By.xpath(".//span[contains(@class, 'BurgerConstructor_basket__listContainer__3P_AM')]/li[contains(@class, 'listItem__3yMU_ mb-4 mr-2')]");

    public static final By BUTTON_MODAL_CLOSE = By.xpath(".//section[@class='Modal_modal_opened__3ISw4 Modal_modal__P3_V5']//button[@class='Modal_modal__close_modified__3V5XS Modal_modal__close__TnseK']");
    public static final By H2_MODAL_ORDER_ID = By.xpath(".//section[@class='Modal_modal_opened__3ISw4 Modal_modal__P3_V5']//h2[@class='Modal_modal__title_shadow__3ikwq Modal_modal__title__2L34m text text_type_digits-large mb-8']");

    public static final By BUTTON_LOGIN_TO_ACCOUNT = By.xpath(".//button[text()='Войти в аккаунт']");
    public static final By BUTTON_ORDER_BURGER = By.xpath(".//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) { super(driver); }

    @Step("Open 'Main' Page")
    public MainPage openMainPage() {
        driver.get(BASE_URL); return this;
    }

    @Step("Wait until 'Main' Page to be displayed")
    public MainPage waitUntilMainPageToBeDisplayed() {
        waitUntilPageToBeDisplayed(TEXT_ASSEMBLE_THE_BURGER);
        return this;
    }

    public Integer getOrderId() {
        String orderId = driver.findElement(H2_MODAL_ORDER_ID).getText();
        if (Objects.equals(orderId, "")) { orderId = "9999"; }
        return Integer.valueOf(orderId);
    }

    public void closeModalWindow() {
        driver.findElement(BUTTON_MODAL_CLOSE).click();
    }

    private List<WebElement> getIngredientList(int ingredientType) {
        List<WebElement> ingredientLists = driver.findElements(INGREDIENT_LIST);
        return ingredientLists.get(ingredientType).findElements(INGREDIENT);
    }

    private List<WebElement> getBuns() { return getIngredientList(BUN); }
    private List<WebElement> getSauces() { return getIngredientList(SAUCE); }
    private List<WebElement> getToppings() { return getIngredientList(TOPPING); }
    private WebElement getBasket() { return driver.findElement(BASKET); }

    private List<WebElement> getBasketBunList() { return driver.findElements(BASKET_BUN_LIST_ITEM); }
    private List<WebElement> getBasketSauceAndToppingList() { return driver.findElements(BASKET_SAUCE_AND_TOPPING_LIST_ITEM ); }

    public int getBasketBunListSize() { return getBasketBunList().size(); }
    public int getBasketSauceAndToppingListSize() { return getBasketSauceAndToppingList().size(); }

    public MainPage addIngredientToOrder(int ingredientType) {
        List<WebElement> ingredients = new ArrayList<>();
        switch(ingredientType) {
            case BUN: ingredients = getBuns(); break;
            case SAUCE: ingredients = getSauces(); break;
            case TOPPING: ingredients = getToppings(); break;
        }

        int ingredientsSize = ingredients.size();
        if (ingredientsSize > 0) {
            Random rn = new Random();
            int randomNumber = rn.nextInt(ingredientsSize);
            WebElement ingredient = ingredients.get(randomNumber);
            WebElement basket = getBasket();
            actions.dragAndDrop(ingredient, basket);
            actions.build().perform();
        }
        return this;
    }

    @Step("Add 'Bun' to order")
    public MainPage addBunToOrder() { return addIngredientToOrder(BUN); }

    @Step("Add 'Sauce' to order")
    public MainPage addSauceToOrder() { return addIngredientToOrder(SAUCE); }

    @Step("Add 'Topping' to order")
    public MainPage addToppingToOrder() { return addIngredientToOrder(TOPPING); }


    @Step("Wait until 'Order' Button to be displayed")
    public MainPage waitUntilOrderBurgerButtonToBeDisplayed() {
        waitUntilPageElementToBeDisplayed(BUTTON_ORDER_BURGER);
        return this;
    }

    @Step("Wait until the service opens the opportunity to place an 'Order'")
    public boolean waitUntilServiceOpensOpportunityToPlaceOrder() {
        waitUntilPageElementToBeDisplayed(BUTTON_ORDER_BURGER);
        return driver.findElement(BUTTON_ORDER_BURGER).isDisplayed();
    }

    @Step("Click 'Sign in' Button")
    public LoginPage clickLoginToAccountButton() {
        driver.findElement(BUTTON_LOGIN_TO_ACCOUNT).click();
        return new LoginPage(driver);
    }

    @Step("Click 'Order' Button")
    public MainPage clickOrderButton() {
        driver.findElement(BUTTON_ORDER_BURGER).click();
        return this;
    }

    @Step("Click 'Buns' Tab")
    public MainPage clickBunsTab() {
        waitUntilPageElementToBeClickable(TAB_BUNS);
        driver.findElement(TAB_BUNS).click();
        return this;
    }

    @Step("Click 'Sauces' Tab")
    public MainPage clickSaucesTab() {
        waitUntilPageElementToBeClickable(TAB_SAUCES);
        driver.findElement(TAB_SAUCES).click();
        return this;
    }

    @Step("Click 'Toppings' Tab")
    public MainPage clickToppingsTab() {
        waitUntilPageElementToBeClickable(TAB_TOPPINGS);
        driver.findElement(TAB_TOPPINGS).click();
        return this;
    }

    @Step("Check that 'Buns' Tab is active")
    public boolean checkThatBunsTabIsActive() {
        waitUntilPageElementToBeDisplayed(ACTIVE_TAB_BUNS);
        waitUntilPageElementToBeClickable(ACTIVE_TAB_BUNS);
        return driver.findElement(ACTIVE_TAB_BUNS).isEnabled();
    }

    @Step("Wait until 'Buns' Tab to be active")
    public MainPage waitUntilBunsTabToBeActive() {
        waitUntilPageElementToBeDisplayed(ACTIVE_TAB_BUNS);
        waitUntilPageElementToBeClickable(ACTIVE_TAB_BUNS);
        return this;
    }

    @Step("Check that 'Sauces' Tab is active")
    public boolean checkThatSaucesTabIsActive() {
        waitUntilPageElementToBeDisplayed(ACTIVE_TAB_SAUCES);
        waitUntilPageElementToBeClickable(ACTIVE_TAB_SAUCES);
        return driver.findElement(ACTIVE_TAB_SAUCES).isEnabled();
    }

    @Step("Check that 'Toppings' Tab is active")
    public boolean checkThatToppingsTabIsActive() {
        waitUntilPageElementToBeDisplayed(ACTIVE_TAB_TOPPINGS);
        waitUntilPageElementToBeClickable(ACTIVE_TAB_TOPPINGS);
        return driver.findElement(ACTIVE_TAB_TOPPINGS).isEnabled();
    }

    @Step("Wait until 'Order' to be completed")
    public MainPage waitUntilOrderToBeCompleted() {
        new WebDriverWait(driver, Duration.ofSeconds(ORDER_WAIT_DURATION))
                .until((ExpectedCondition<Boolean>) wd ->
                {
                    assert wd != null;
                    return (getOrderId() != 9999);
                });
        return this;
    }
}
