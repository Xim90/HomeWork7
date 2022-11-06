package by.it_academy.page_object;

import com.codeborne.selenide.Configuration;

public abstract class BasePage {
    public BasePage() {
        Configuration.pageLoadTimeout = 200000;
    }
}
