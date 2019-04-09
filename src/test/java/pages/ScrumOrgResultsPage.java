package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseClassWithUtils;

import java.util.List;

public class ScrumOrgResultsPage extends BaseClassWithUtils {

    @Getter
    @FindBy(className="views-row")
    private List<WebElement> coursesList;


    public ScrumOrgResultsPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifyResults(String expectedDate, String expectedLocation) {
        boolean containsBoth = false;

        for (WebElement element : coursesList) {
            WebElement elementsDate = waitUtil.findElementWithWait(element, By.xpath("//article/div[@class='coursefinder-course-row-item'][1]"));
            WebElement elementsLocation = waitUtil.findElementWithWait(element, By.xpath("//article/div[@class='coursefinder-course-row-item'][2]"));

            if(elementsDate.getText().contains(expectedDate) && elementsLocation.getText().contains(expectedLocation)) {
                containsBoth = true;
            }
        }

        return containsBoth;
    }

}
