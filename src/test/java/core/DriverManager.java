package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class DriverManager {

    public WebDriver driver;

    private String selectBrowser = "firefox";

    public void initialiseDriver() {
        startDriver(determineDriverType());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    public void closeDriver() {
        driver.close();
    }

    private BrowserTypes determineDriverType() {
        try {
            return BrowserTypes.valueOf(selectBrowser.toUpperCase());
        } catch (Exception e) {
            Assert.fail("Not recognize browser: please use chrome or firefox");
            return null;
        }
    }

    private void startDriver(BrowserTypes type) {
        switch (type){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "C:/Sources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "C:/Sources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                Assert.fail("Browser type not supported");
        }
    }

}