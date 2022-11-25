package by.it_academy.selenoid_driver;

import org.openqa.selenium.chrome.ChromeOptions;

public class SelenoidChromeWebDriver extends SelenoidWebDriver {
    public SelenoidChromeWebDriver(String browserVersion) {
        super.browserVersion = browserVersion;
        super.options = new ChromeOptions();
    }
}
