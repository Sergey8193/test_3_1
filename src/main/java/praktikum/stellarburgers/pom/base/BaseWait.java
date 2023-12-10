package praktikum.stellarburgers.pom.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class BaseWait {

    protected final static int ORDER_WAIT_DURATION = 20;
    private final static int LOADING_WAIT_DURATION = 20;
    protected final static int ELEMENT_WAIT_DURATION = 20;
    private final static int PAGE_WAIT_DURATION = 20;

    protected final WebDriver driver;
    protected final Actions actions;

    BaseWait(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    @Step("Wait until 'Loading animation image' to be invisible")
    protected void waitUntilLoadingToBeFinished(By loadingAnimationImage) {
        new WebDriverWait(driver, Duration.ofSeconds(LOADING_WAIT_DURATION))
                .until(ExpectedConditions.invisibilityOfElementLocated(loadingAnimationImage));
    }

    @Step("Wait until 'Page element' to be displayed")
    protected void waitUntilPageElementToBeDisplayed(By waitElement) {
        WebDriverWait elementWait = new WebDriverWait(driver, Duration.ofSeconds(ELEMENT_WAIT_DURATION));
        elementWait.until(ExpectedConditions.visibilityOfElementLocated(waitElement));
    }

    @Step("Wait until 'Page element' to be clickable")
    protected void waitUntilPageElementToBeClickable(By waitElement) {
        WebDriverWait elementWait = new WebDriverWait(driver, Duration.ofSeconds(ELEMENT_WAIT_DURATION));
        elementWait.until(ExpectedConditions.elementToBeClickable(waitElement));
    }

    @Step("Wait until 'Page' to be displayed")
    public void waitUntilPageToBeDisplayed(By pageElement) {
        waitUntilPageDocumentToBeReady();
        waitUntilPageElementToBeDisplayed(pageElement);
    }

    @Step("Wait until 'Page document' to be ready")
    protected void waitUntilPageDocumentToBeReady() {
        new WebDriverWait(driver, Duration.ofSeconds(PAGE_WAIT_DURATION))
                .until((ExpectedCondition<Boolean>) wd ->
                {
                    assert wd != null;
                    return ((JavascriptExecutor) wd)
                            .executeScript("return document.readyState")
                            .equals("complete");
                });
    }
}
