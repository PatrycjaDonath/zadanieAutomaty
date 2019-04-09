import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleResultsPage;
import pages.GoogleSearchPage;
import utils.BaseClassWithUtils;

import static java.lang.Thread.sleep;

public class FirstScenarioTest extends BaseClassWithUtils {

    private String url = "https://www.google.com";

    @Test
    public void microsoftSearchBarVisibilityTest() {
        openPageWithUrl(url);
        waitForPageAndSearch("microsoft");
        verifyIfResultFound("Microsoft - Official Home Page");
        verifyIfMicrosoftSearchFieldFound();
    }

    @Test
    public void microsoftSearchBarFunctionalityTest() {
        openPageWithUrl(url);
        waitForPageAndSearch("microsoft");
        researchIfSearchBarNotAvailable();
        searchInMicrosoftAndVerifyResults();
    }


    private void waitForPageAndSearch(String textValue) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        googleSearchPage.waitForLogo();

        googleSearchPage.searchInGoogle(textValue);
    }

    private void verifyIfResultFound(String textValue) {
        GoogleResultsPage googleResultsPage = new GoogleResultsPage();
        googleResultsPage.waitForTabs();
        Assert.assertTrue(googleResultsPage.verifyIfResultsContain(textValue), "The results were not found");
    }

    private void verifyIfMicrosoftSearchFieldFound() {
        GoogleResultsPage googleResultsPage = new GoogleResultsPage();
        Assert.assertTrue(googleResultsPage.verifyIfMicrosoftSearchFieldFound(), "The Microsoft Search bar was not found");
    }

    private void researchIfSearchBarNotAvailable(){
        GoogleResultsPage googleResultsPage = new GoogleResultsPage();
        googleResultsPage.waitForTabs();

        if (!googleResultsPage.verifyIfMicrosoftSearchFieldFound()) {
            googleResultsPage.searchInGoogle("microsoft.com");
        }
        Assert.assertTrue(googleResultsPage.verifyIfMicrosoftSearchFieldFound(), "The Microsoft Search bar was not found");
    }

    private void searchInMicrosoftAndVerifyResults(){
        GoogleResultsPage googleResultsPage = new GoogleResultsPage();
        googleResultsPage.searchInMicrosoft("surface");

        Assert.assertTrue(googleResultsPage.verifyIfSearchBarContain("'surface site:microsoft.com")
                , "The SearchBar did not contain expected value");

        Assert.assertTrue(googleResultsPage.verifyIfResultLinksContain("'https://www.microsoft.com/en-us/surface")
                , "The results were not found");
    }
}
