package by.it_academy.selenoid_driver;

import org.openqa.selenium.firefox.FirefoxOptions;

public class SelenoidFirefoxWebDriver extends SelenoidWebDriver {
    public SelenoidFirefoxWebDriver(String browserVersion) {
        super.browserVersion = browserVersion;
        super.options = new FirefoxOptions();
    }
}
