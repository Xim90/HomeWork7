package by.it_academy.selenoid_driver;

import org.openqa.selenium.safari.SafariOptions;

public class SelenoidSafariWebDriver extends SelenoidWebDriver {
    public SelenoidSafariWebDriver(String browserVersion) {
        super.browserVersion = browserVersion;
        super.options = new SafariOptions();
    }
}
