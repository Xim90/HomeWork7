package by.it_academy;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MyWebDriverFactory {
    public static void create(String driverType) throws MalformedURLException {
        switch (driverType) {
            case "chrome": {
                Configuration.browser="chrome";
                break;
            }
            case "edge": {
                Configuration.browser = "edge";
                Configuration.browserSize = "1600x900";
                break;
            }
            case "remoteSafari":{
                SafariOptions options = new SafariOptions();
                options.setCapability("browserVersion", "15.0");
                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    put("name", "Test badge...");
                    put("sessionTimeout", "15m");
                    put("env", new ArrayList<String>() {{
                        add("TZ=UTC");
                    }});
                    put("labels", new HashMap<String, Object>() {{
                        put("manual", "true");
                    }});
                    put("enableVideo", true);
                }});
                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                WebDriverRunner.setWebDriver(driver);
                break;
            }
        }
    }
}
