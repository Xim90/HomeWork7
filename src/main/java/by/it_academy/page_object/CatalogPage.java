package by.it_academy.page_object;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class CatalogPage extends BasePage {
    public static final String CATALOG_ONLINER_URL = "https://catalog.onliner.by/";
    public static final String CATALOG_CLASSIFIER_ITEM_LINK_PATTERN = "//li[.//span[contains(text(),'%s')]]";
    public static final String CATALOG_CLASSIFIER_ITEMS_LINK =
            "//li[.//span[contains(@class,'icon_id')]]//span[contains(@class,'wrapper')]";
    public static final String CATALOG_COMPUTER_AND_NETWORK_LIST_ASIDE_ITEMS =
            "//div[@data-id=2]//div[contains(@class,'aside-title')]";
    public static final String CATALOG_COMPUTER_AND_NETWORK_LIST_ASIDE_ITEM_LINK_PATTERN =
            "//div[@data-id=2]//div[contains(text(),'%s')]";
    public static final String CATALOG_ACCESSORIES_DROPDOWN_LIST_ITEMS_TITLE =
            "//div[contains(@style,'block')]//div[./div[contains(text(),'Комплектующие')]]" +
                    "//span[contains(@class,'title')]";
    public static final String CATALOG_ACCESSORIES_DROPDOWN_LIST_ITEMS_DESCRIPTION =
            "//div[contains(@style,'block')]//div[./div[contains(text(),'Комплектующие')]]" +
                    "//span[contains(@class,'description')]";
    public static final String COMPUTER_AND_NETWORK_CATEGORY_NAME = "Компьютеры";
    public static final String ACCESSORIES_CATEGORY_NAME = "Комплектующие";

    public CatalogPage() {
        Selenide.open(CATALOG_ONLINER_URL);
    }

    public void clickOnCatalogCategory(String categoryName) {
        $x(format(CATALOG_CLASSIFIER_ITEM_LINK_PATTERN, categoryName))
                .shouldBe(visible)
                .click();
    }

    public ElementsCollection getCatalogClassifierItems() {
        return $$x(CATALOG_CLASSIFIER_ITEMS_LINK);
    }

    public ElementsCollection getComputerAndNetworksCategoryItemsList() {
        clickOnCatalogCategory(COMPUTER_AND_NETWORK_CATEGORY_NAME);
        return $$x(CATALOG_COMPUTER_AND_NETWORK_LIST_ASIDE_ITEMS);
    }

    public void clickOnComputerAndNetworksCategoryItem(String categoryName) {
        clickOnCatalogCategory(COMPUTER_AND_NETWORK_CATEGORY_NAME);
        $x(format(CATALOG_COMPUTER_AND_NETWORK_LIST_ASIDE_ITEM_LINK_PATTERN, categoryName))
                .shouldBe(visible)
                .click();
    }

    public void clickOnAccessoriesCategoryItem() {
        clickOnComputerAndNetworksCategoryItem(ACCESSORIES_CATEGORY_NAME);
    }

    public ElementsCollection getAccessoriesCategorySideListItemsTitle() {
        return $$x(CATALOG_ACCESSORIES_DROPDOWN_LIST_ITEMS_TITLE);
    }

    public ElementsCollection getAccessoriesCategorySideListItemsDescription() {
        return $$x(CATALOG_ACCESSORIES_DROPDOWN_LIST_ITEMS_DESCRIPTION);
    }

}
