package by.it_academy.util;

import by.it_academy.selenoid_driver.SelenoidChromeWebDriver;
import by.it_academy.selenoid_driver.SelenoidFirefoxWebDriver;
import by.it_academy.selenoid_driver.SelenoidSafariWebDriver;
import by.it_academy.selenoid_driver.SelenoidWebDriver;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public class WebDriverSetter {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverSetter.class);
    public static final String CHROME_VERSION_106 = "106.0";
    public static final String FIREFOX_VERSION_105 = "105.0";
    public static final String CHROME_VERSION_107 = "107.0";
    public static final String FIREFOX_VERSION_106 = "106.0";
    public static final String SAFARI_VERSION_15 = "15.0";
    public static final String BROWSER_SIZE = "1600x900";
    public static final String REMOTE_SAFARI = "remoteSafari";
    public static final String REMOTE_CHROME_106 = "remoteChrome106";
    public static final String REMOTE_CHROME_107 = "remoteChrome107";
    public static final String REMOTE_FIREFOX_105 = "remoteFirefox105";
    public static final String REMOTE_FIREFOX_106 = "remoteFirefox106";
    public static final String DRIVER_WAS_SET = "driver was set";

    public static void setWebDriver(String driverType) {
        switch (driverType) {
            case CHROME: {
                Configuration.browser = CHROME;
                break;
            }
            case FIREFOX: {
                Configuration.browser = FIREFOX;
                Configuration.browserSize = BROWSER_SIZE;
                break;
            }
            case REMOTE_SAFARI: {
                SelenoidSafariWebDriver selenoidSafariWebDriver = new SelenoidSafariWebDriver(SAFARI_VERSION_15);
                WebDriverRunner.setWebDriver(selenoidSafariWebDriver.getRemoteDriver());
                break;
            }
            case REMOTE_CHROME_106: {
                SelenoidWebDriver selenoidChromeWebDriver = new SelenoidChromeWebDriver(CHROME_VERSION_106);
                WebDriverRunner.setWebDriver(selenoidChromeWebDriver.getRemoteDriver());
                break;
            }
            case REMOTE_CHROME_107: {
                SelenoidWebDriver selenoidChromeWebDriver = new SelenoidChromeWebDriver(CHROME_VERSION_107);
                WebDriverRunner.setWebDriver(selenoidChromeWebDriver.getRemoteDriver());
                break;
            }
            case REMOTE_FIREFOX_105: {
                SelenoidWebDriver selenoidChromeWebDriver = new SelenoidFirefoxWebDriver(FIREFOX_VERSION_105);
                WebDriverRunner.setWebDriver(selenoidChromeWebDriver.getRemoteDriver());
                break;
            }
            case REMOTE_FIREFOX_106: {
                SelenoidWebDriver selenoidChromeWebDriver = new SelenoidFirefoxWebDriver(FIREFOX_VERSION_106);
                WebDriverRunner.setWebDriver(selenoidChromeWebDriver.getRemoteDriver());
                break;
            }
        }
        logger.info(DRIVER_WAS_SET);
    }
}
