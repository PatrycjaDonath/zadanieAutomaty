package pages;

import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseClassWithUtils;

public class ScrumOrgPage extends BaseClassWithUtils {

    @Getter
    @FindBy(xpath="//div[@id='edit_country_chosen']//a[@class='chosen-single']")
    private WebElement allCountriesField;

    @Getter
    @FindBy(xpath="//div[@id='edit_uid_chosen']//a[@class='chosen-single']")
    private WebElement allInstructorsField;

    @FindBy(xpath="//div[@id='edit_country_chosen']//input[@type='text']")
    private WebElement countryTextField;

    @FindBy(xpath="//div[@id='edit_uid_chosen']//input[@type='text']")
    private WebElement instructorTextField;

    @FindBy(xpath="//div[@id='edit_country_chosen']//ul[@class='chosen-results']")
    private WebElement countryChooseList;

    @FindBy(xpath="//div[@id='edit_uid_chosen']//ul[@class='chosen-results']")
    private WebElement instructorChooseList;

    @Getter
    @FindBy(id="edit-submit-courses")
    private WebElement applyButton;


    public ScrumOrgPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public void enterValueInCountriesField(String textValue){
        waitUtil.waitUntilElementVisible(allCountriesField);
        waitUtil.clickElement(allCountriesField);

        waitUtil.waitUntilElementClickable(countryTextField);
        generalUtil.typeAndEnter(countryTextField, textValue);
        waitUtil.waitUntilElementNotVisible(countryChooseList);
    }

    public void enterValueInInstructorsField(String textValue){
        waitUtil.waitUntilElementVisible(allInstructorsField);
        waitUtil.clickElement(allInstructorsField);

        waitUtil.waitUntilElementClickable(instructorTextField);
        generalUtil.typeAndEnter(instructorTextField, textValue);
        waitUtil.waitUntilElementNotVisible(instructorChooseList);
    }

    public void clickApplyButton() {
        waitUtil.scrollToElementFacade(applyButton);
        waitUtil.clickElement(applyButton);
    }
}
