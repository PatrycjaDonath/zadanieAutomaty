package utils;

import core.DriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GeneralUtil extends DriverFactory {

    public String getTextValue (WebElement element) {
        return element.getAttribute("value");
    }

    public void typeAndEnter (WebElement element, String textValue) {
        element.clear();
        element.sendKeys(textValue);
        element.sendKeys(Keys.ENTER);
    }

}
