package utils;

import core.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
    // wykonanie skryptu Javascript
    public void executeScript(String script){
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        javaScriptExecutor.executeScript(script);
    }

    // czy dany scrypt się już wykonał, czy dana strona się już załadowała
    public String executeScriptAndReturn(String script){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        return javascriptExecutor.executeScript(script).toString();
    }

    // wykonanie Javascript na elemencie przekazanym w argumencie
    public void executeScriptWithArgument(String script, WebElement elem){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverFactory.getDriver();
        javascriptExecutor.executeScript(script, elem);
    }
}
