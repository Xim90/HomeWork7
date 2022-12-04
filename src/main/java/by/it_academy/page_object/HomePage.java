package by.it_academy.page_object;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class HomePage extends BasePage {
    private static final String ONLINER_URL = "https://www.onliner.by/";
    private static final SelenideElement OnlinerCatalogLink =
            $x("//*[@class='b-top-menu']//a[contains(@href,'catalog')]");
    private static final SelenideElement OnlinerFastSearchBar =
            $x("//input[contains(@class,'fast-search')]");
    private static final SelenideElement OnlinerSearchFrame =
            $x("//iframe[@class='modal-iframe']");
    private static final ElementsCollection OnlinerSearchResults =
            $$x("//a[contains(@class,'title-link')]");

    public HomePage() {
        open(ONLINER_URL);
    }

    public CatalogPage clickOnCatalogLink() {
        OnlinerCatalogLink.shouldBe(visible, ofSeconds(WAITING_TIME_SECONDS)).click();
        return new CatalogPage();
    }

    public HomePage clearSearchBarAndSendKeys(String value) {
        OnlinerFastSearchBar.clear();
        OnlinerFastSearchBar.sendKeys(value);
        return this;
    }

    public HomePage switchToSearchFrame() {
        switchTo().frame(OnlinerSearchFrame);
        return this;
    }
    public ElementsCollection getSearchResultNames(){
        return OnlinerSearchResults;
    }
}
