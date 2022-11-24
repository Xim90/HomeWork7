package by.it_academy;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyWebDriverFactory {
    public static void create(String driverType) {
        switch (driverType) {
            case "chrome": {
                Configuration.browser="chrome";
                break;
            }
            case "edge": {
                Configuration.browser = "edge";
                Configuration.browserSize = "1024x768";
                break;
            }
        }
    }
}
