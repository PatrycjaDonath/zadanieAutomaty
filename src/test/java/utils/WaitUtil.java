package utils;

import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import javax.annotation.Nullable;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WaitUtil extends DriverFactory {

    private JavaScriptUtil javaScriptUtils = new JavaScriptUtil();

    private void waitUntilDomIsComplete(){
        WebDriverWait driverWait = new WebDriverWait(DriverFactory.getDriver(), 10);
        ExpectedCondition conditions = new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver){
                String script = "return document.readyState";
                return javaScriptUtils.executeScriptAndReturn(script).equals("complete");
            }
        };

        driverWait.until(conditions);
    }

    private void waitUntilAjaxRequestsAreComplete(){
        WebDriverWait driverWait = new WebDriverWait(DriverFactory.getDriver(), 10);
        ExpectedCondition conditions = new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver){
                String script = "return jQuery.active == 0";
                return javaScriptUtils.executeScriptAndReturn(script).equals("true");
            }
        };

        driverWait.until(conditions);
    }

    public void waitUntilUrlContainsText(String text){
        WebDriverWait driverWait = new WebDriverWait(DriverFactory.getDriver(), 10);
        driverWait.until(ExpectedConditions.urlContains(text));
    }

    public void justWaitSomeTime () {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebElement findElement(By locator) {
        return getDriver().findElement(locator);
    }

    public WebElement findElementWithWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(15, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new com.google.common.base.Function<WebDriver, WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver webDriver) {
                return findElement(locator);
            }
        } );
    }

    public WebElement findElementWithLongWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(60, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new com.google.common.base.Function<WebDriver, WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver webDriver) {
                return getDriver().findElement(locator);
            }
        } );
    }

    public WebElement findElementWithWait(WebElement parent, By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(15, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new com.google.common.base.Function<WebDriver, WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver webDriver) {
                return parent.findElement(locator);
            }
        } );
    }

    public WebElement findElementByLinkTextWithWait(String linkText) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(15, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new com.google.common.base.Function<WebDriver, WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver webDriver) {
                return findElement(By.linkText(linkText));
            }
        } );
    }

    public WebElement findElementByLinkTextWithLongWait(String linkText) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(60, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new com.google.common.base.Function<WebDriver, WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver webDriver) {
                return findElement(By.linkText(linkText));
            }
        } );
    }

    public void waitUntilElementVisible(WebElement element){
        WebDriverWait driverWait = new WebDriverWait(getDriver(), 10);
        driverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementNotVisible(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(10, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        Boolean isVisible = wait.until(new com.google.common.base.Function<WebDriver, Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver webDriver) {
                WebDriverWait driverWait = new WebDriverWait(getDriver(), 1);
                try {
                    driverWait.until(ExpectedConditions.visibilityOf(element));
                    return false;
                } catch (Exception e) {
                    return true;
                }
            }
        });
    }

    public void waitUntilElementClickable(WebElement element){
        WebDriverWait driverWait = new WebDriverWait(getDriver(), 10);
        driverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToElementFacade(WebElement element) {
        javaScriptUtils.executeScriptWithArgument("arguments[0].scrollIntoView();", element);
    }

    public void clickElement(WebElement element) {
        waitUntilElementClickable(element);
        element.click();
    }

    public void clickOnLinkByText(String linkText) {
        clickElement(findElement(By.linkText(linkText)));
    }
}
