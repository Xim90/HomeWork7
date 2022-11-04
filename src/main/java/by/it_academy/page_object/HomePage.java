package by.it_academy.page_object;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class HomePage extends BasePage {
    public static final String ONLINER_URL = "https://www.onliner.by/";
    public static final String ONLINER_CATALOG_LINK_PATTERN = "//*[@class='b-top-menu']//a[contains(@href,'catalog')]";

    public HomePage() {
        open(ONLINER_URL);
    }

    public CatalogPage clickOnCatalogLink() {
        $x(ONLINER_CATALOG_LINK_PATTERN).shouldBe(visible).click();
        return new CatalogPage();
    }
}
