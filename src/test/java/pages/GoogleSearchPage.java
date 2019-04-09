package pages;

import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.BaseClassWithUtils;

public class GoogleSearchPage extends BaseClassWithUtils {

    WebDriver driver;

    @Getter
    @FindBy(xpath="//img[@alt='Google']")
    private WebElement googleLogo;

    @Getter
    @FindBy(xpath="//input[@type='text']")
    private WebElement searchField;

    @Getter
    @FindBy(xpath="(//div[contains(@class, FPdoLc)]/center/input[@type='submit'])")
    private WebElement searchButton;

    @Getter
    @FindBy(xpath="//div[contains(@class, FPdoLc)]/center/input[@type='submit'][@jsaction='sf.lck']")
    private WebElement luckyButton;

    public GoogleSearchPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public void waitForLogo() {
        waitUtil.waitUntilElementVisible(googleLogo);
    }

    public void populateSearchField(String textValue) {
        waitUtil.waitUntilElementClickable(searchField);
        searchField.sendKeys(textValue);
    }

    public void searchInGoogle(String textValue) {
        waitUtil.waitUntilElementClickable(searchField);
        generalUtil.typeAndEnter(searchField, textValue);
    }

    public void clickButton(String buttonName) {
        switch(buttonName){
            case ("Search") :
            case ("Google Search") :
            case ("Szukaj") :
            case ("Szukaj w Google") :
                waitUtil.clickElement(searchButton);
            case ("I'm Feeling Lucky") :
            case ("Szczęśliwy traf") :
                waitUtil.clickElement(luckyButton);
            default :
                Assert.fail("The button name was not recognized");
        }
    }
}
