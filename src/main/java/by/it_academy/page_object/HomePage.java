package by.it_academy.page_object;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;

public class HomePage extends BasePage {
    public static final String ONLINER_URL = "https://www.onliner.by/";
    public static final SelenideElement OnlinerCatalogLink =
            $x("//*[@class='b-top-menu']//a[contains(@href,'catalog')]");

    public HomePage() {
        open(ONLINER_URL);
    }

    public CatalogPage clickOnCatalogLink() {
        OnlinerCatalogLink.shouldBe(visible, ofSeconds(WAITING_TIME_SECONDS)).click();
        return new CatalogPage();
    }
}
