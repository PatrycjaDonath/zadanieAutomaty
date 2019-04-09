import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ScrumOrgPage;
import pages.ScrumOrgResultsPage;
import utils.BaseClassWithUtils;

import static java.lang.Thread.sleep;

public class SecondScenarioTest extends BaseClassWithUtils {

    @Test
    public void verifyIfInstructorHaveTraining() {
        openPageWithUrl("https://www.scrum.org/classes");
        applyFilters("Poland", "Andy Brandt");
        verifyIfInstructorHaveTrainings("2019", "Lublin");
    }

    private void applyFilters(String country, String instructor) {
        ScrumOrgPage scrumOrgPage = new ScrumOrgPage();
        scrumOrgPage.enterValueInCountriesField(country);
        scrumOrgPage.enterValueInInstructorsField(instructor);

        scrumOrgPage.clickApplyButton();
    }

    private void verifyIfInstructorHaveTrainings(String expectedDate, String expectedLocation) {
        ScrumOrgResultsPage scrumOrgResultsPage = new ScrumOrgResultsPage();
        Assert.assertFalse(scrumOrgResultsPage.verifyResults(expectedDate, expectedLocation)
                , "The instructor have a training that he not supposed to have");
    }

}
