package by.it_academy.selenoid_driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class SelenoidWebDriver {
    private static final Logger logger = LoggerFactory.getLogger(SelenoidWebDriver.class);
    public static final String SELENOID_URL = "http://localhost:4444/wd/hub";
    public static final String TRUE = "true";
    public static final String MANUAL = "manual";
    public static final String TZ_UTC = "TZ=UTC";
    public static final String BROWSER_VERSION = "browserVersion";
    public static final String SELENOID_OPTIONS = "selenoid:options";
    public static final String NAME = "name";
    public static final String TEST_BADGE = "Test badge...";
    public static final String SESSION_TIMEOUT = "sessionTimeout";
    public static final String ENV = "env";
    public static final String LABELS = "labels";
    public static final String SESSION_TIMEOUT_VALUE = "15m";
    public static final String BROWSER_VERSION_INFO = "browser version = {} {}";
    public static final String OPTIONS = "Options";
    public static final String REMOTE_DRIVER_NOT_CREATED = "remote driver not created /n{}";
    public static final String REMOTE_DRIVER_CREATED = "remote driver created";
    protected String browserVersion;
    protected MutableCapabilities options;

    protected void setOptions() {

        options.setCapability(BROWSER_VERSION, browserVersion);
        logger.info(BROWSER_VERSION_INFO,
                options.getClass().getSimpleName().replace(OPTIONS,""),browserVersion);
        options.setCapability(SELENOID_OPTIONS, new HashMap<String, Object>() {{
            put(NAME, TEST_BADGE);
            put(SESSION_TIMEOUT, SESSION_TIMEOUT_VALUE);
            put(ENV, new ArrayList<String>() {{
                add(TZ_UTC);
            }});
            put(LABELS, new HashMap<String, Object>() {{
                put(MANUAL, TRUE);
            }});
        }});
    }


    public RemoteWebDriver getRemoteDriver() {
        setOptions();
        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(SELENOID_URL), options);
        } catch (
                MalformedURLException e) {
            logger.info(REMOTE_DRIVER_NOT_CREATED, e.toString());
        }
        driver.manage().window().maximize();
        logger.info(REMOTE_DRIVER_CREATED);
        return driver;
    }

}
