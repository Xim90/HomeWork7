package by.it_academy.page_object;

import com.codeborne.selenide.Configuration;

public abstract class BasePage {
    public static final int WAITING_TIME_SECONDS = 15;

    public BasePage() {
        Configuration.pageLoadTimeout = 200000;
    }
}
