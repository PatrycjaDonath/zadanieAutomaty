package pages;

import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseClassWithUtils;

import java.util.List;

public class GoogleResultsPage extends BaseClassWithUtils {

    @Getter
    @FindBy(xpath="//div[@role='tab']")
    private WebElement firstTab;

    @Getter
    @FindBy(xpath="//input[@type='text']")
    private WebElement searchField;

    @Getter
    @FindBy(className="LC20lb")
    private List<WebElement> resultsTitlesList;

    @Getter
    @FindBy(className="iUh30")
    private List<WebElement> resultsUrlsList;

    @Getter
    @FindBy(xpath="//input[contains(@class, 'w7Nvcd')][@type='text']")
    private WebElement microsoftSearchField;

    public GoogleResultsPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public void waitForTabs() {
        waitUtil.waitUntilElementVisible(firstTab);
    }

    public void searchInGoogle(String textValue) {
        waitUtil.waitUntilElementClickable(searchField);
        generalUtil.typeAndEnter(searchField, textValue);
    }

    public boolean verifyIfSearchBarContain(String textValue) {
        waitUtil.waitUntilElementVisible(searchField);
        return generalUtil.getTextValue(searchField).contains(textValue);
    }

    public boolean verifyIfResultsContain(String textValue) {
        boolean resultFound = false;

        for (WebElement element : resultsTitlesList) {
            if (element.getText().contains(textValue)) {
                resultFound = true;
            }
        }
        return resultFound;
    }

    public boolean verifyIfResultLinksContain(String textValue) {
        boolean resultFound = false;

        for (WebElement element : resultsUrlsList) {
            if (element.getText().contains(textValue)) {
                resultFound = true;
            }
        }
        return resultFound;
    }

    public boolean verifyIfMicrosoftSearchFieldFound() {
        try {
            waitUtil.waitUntilElementVisible(microsoftSearchField);
            return true;
        } catch (Exception e) {
            System.out.println("The Microsoft Search bar was not found");
            return false;
        }
    }

    public void searchInMicrosoft(String textValue) {
        waitUtil.waitUntilElementClickable(microsoftSearchField);
        generalUtil.typeAndEnter(microsoftSearchField, textValue);
    }
}
