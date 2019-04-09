package core;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class DriverFactory {
    public static ThreadLocal<DriverManager> driverThread;


    @BeforeTest
    public static void initialiseDriver(){
        driverThread = new ThreadLocal<DriverManager>(){
            @Override
            protected DriverManager initialValue(){
                DriverManager driverThread = new DriverManager();
                driverThread.initialiseDriver();
                return driverThread;
            }
        };
    }

    @AfterTest
    public static void closeDriver(){
        driverThread.get().clearCookies();
        driverThread.get().closeDriver();
    }

    public static WebDriver getDriver(){
        return driverThread.get().getDriver();
    }

    public static void openPageWithUrl (String url) {
        driverThread.get().goToUrl(url);
    }

}
