package by.it_academy;

import com.codeborne.selenide.Configuration;

public class MyWebDriverFactory{
    public static synchronized void create(){
        String diverType = System.getProperty("driverType");
        switch (diverType) {
            case "chrome" -> Configuration.browser = "chrome";
            case "remoteChrome" -> Configuration.browser = "chrome";
            case "edge" -> Configuration.browser = "edge";
            case "remoteEdge" -> Configuration.browser = "edge";
        }
    }
}
