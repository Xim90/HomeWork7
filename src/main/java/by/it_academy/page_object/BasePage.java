package by.it_academy.page_object;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;

public abstract class BasePage {
    public BasePage() {
        Configuration.pageLoadTimeout = 200000;
        open();
    }
}
