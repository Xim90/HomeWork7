package by.it_academy.page_object;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class CatalogPage extends BasePage {
    public static final String CATALOG_ONLINER_URL = "https://catalog.onliner.by/";
    public static final String CATALOG_CLASSIFIER_ITEM_LINK_PATTERN = "//li[.//span[contains(text(),'%s')]]";
    public static final String CATALOG_CLASSIFIER_ITEMS_LINK =
            "//li[.//span[contains(@class,'icon_id')]]//span[contains(@class,'wrapper')]";
    public static final String CATALOG_COMPUTER_AND_NETWORK_ASIDE_ITEMS =
            "//div[contains(@style,'block')]//div[contains(@class,'aside-title')]";
    public static final String CATALOG_CURRENT_CATEGORY_ASIDE_ITEM_LINK_PATTERN =
            "//div[contains(@style,'block')]//div[contains(text(),'%s')]";
    public static final String CATALOG_ACCESSORIES_DROPDOWN_ITEMS_TITLE_PATTERN =
            "//div[contains(@style,'block')]//div[./div[contains(text(),'%s')]]" +
                    "//span[contains(@class,'title')]";
    public static final String CATALOG_ACCESSORIES_DROPDOWN_ITEMS_DESCRIPTION_PATTERN =
            "//div[contains(@style,'block')]//div[./div[contains(text(),'%s')]]" +
                    "//span[contains(@class,'description')]";
    public CatalogPage() {
        open(CATALOG_ONLINER_URL);
    }

    public void clickOnCatalogCategory(String categoryName) {
        $x(format(CATALOG_CLASSIFIER_ITEM_LINK_PATTERN, categoryName))
                .shouldBe(visible)
                .click();
    }

    public ElementsCollection getCatalogClassifierItems() {
        return $$x(CATALOG_CLASSIFIER_ITEMS_LINK);
    }

    public ElementsCollection getCurrentCategoryItemsList() {
        return $$x(CATALOG_COMPUTER_AND_NETWORK_ASIDE_ITEMS);
    }

    public void clickOnItemOfCurrentCategory(String categoryName) {
        $x(format(CATALOG_CURRENT_CATEGORY_ASIDE_ITEM_LINK_PATTERN, categoryName))
                .shouldBe(visible)
                .click();
    }

    public ElementsCollection getCategorySideItemsTitle(String categoryName) {
        return $$x(format(CATALOG_ACCESSORIES_DROPDOWN_ITEMS_TITLE_PATTERN, categoryName));
    }

    public ElementsCollection getCategorySideItemsDescription(String categoryName) {
        return $$x(format(CATALOG_ACCESSORIES_DROPDOWN_ITEMS_DESCRIPTION_PATTERN, categoryName));
    }
}
