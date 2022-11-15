package by.it_academy;

import by.it_academy.page_object.CatalogPage;
import by.it_academy.page_object.HomePage;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static by.it_academy.constants.FailTestMessage.*;
import static com.codeborne.selenide.CollectionCondition.*;

public class OnlinerTest {
    public static final String SPACES_PATTERN = "[\s]+";
    public static final int EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS = 14;
    public static final String EXPECTED_ITEMS_DESCRIPTION_GOODS_CONTENT = "товар";
    public static final String EXPECTED_ITEMS_DESCRIPTION_MIN_PRICE_CONTENT = "р.";
    public static final String COMPUTER_AND_NETWORK_CATEGORY_NAME = "Компьютеры";
    public static final String ACCESSORIES_CATEGORY_NAME = "Комплектующие";
    public static final String BROWSER_CHROME = "chrome";
    public final List<String> expectedCatalogMenuItems = Arrays.asList(
            "Onlíner Prime",
            "Электроника",
            "Компьютеры и сети",
            "Бытовая техника", "Стройка и ремонт",
            "Дом и сад", "Авто и мото",
            "Красота и спорт",
            "Детям и мамам",
            "Работа и офис");
    public final List<String> expectedComputerAndNetworksMenuItems = Arrays.asList(
            "Ноутбуки, компьютеры, мониторы",
            "Комплектующие",
            "Хранение данных",
            "Сетевое оборудование");

    @BeforeAll
    public static void setup() {
        ChromeDriverManager.chromedriver().setup();
        Configuration.browser = BROWSER_CHROME;
    }

    @Test
    public void checkCatalogClassifierItemsName() {
        HomePage homePage = new HomePage();
        homePage.clickOnCatalogLink()
                .getCatalogClassifierItems()
                .shouldHave(exactTexts(expectedCatalogMenuItems)
                        .because(MENU_NOT_CONTAIN_EXPECTED_LIST_OF_ITEM_S_NAME));
    }

    @Test
    public void checkComputerAndNetworkAsideItemsName() {
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.clickOnCatalogCategory(COMPUTER_AND_NETWORK_CATEGORY_NAME);
        catalogPage.getCurrentCategoryItemsList()
                .shouldHave(containExactTextsCaseSensitive(expectedComputerAndNetworksMenuItems)
                        .because(MENU_NOT_CONTAIN_EXPECTED_LIST_OF_ITEM_S_NAME));
    }

    @Test
    public void checkAccessoriesDropdownItemsContent() {
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.clickOnCatalogCategory(COMPUTER_AND_NETWORK_CATEGORY_NAME);
        catalogPage.clickOnItemOfCurrentCategory(ACCESSORIES_CATEGORY_NAME);
        catalogPage
                .getCategorySideItemsTitle(ACCESSORIES_CATEGORY_NAME)
                .shouldHave(size(EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS)
                        .because(NUMBER_OF_ITEMS_NOT_EQUAL + EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS))
                .shouldHave(noneMatch(ITEM_TITLE_IS_EMPTY, el -> el.getText().isEmpty()))
                .shouldHave(noneMatch(ITEM_TITLE_MATCHES_SPACES, el -> el.getText().matches(SPACES_PATTERN)));
        catalogPage
                .getCategorySideItemsDescription(ACCESSORIES_CATEGORY_NAME)
                .shouldHave(size(EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS)
                        .because(NUMBER_OF_ITEMS_NOT_EQUAL + EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS))
                .shouldHave(allMatch(ITEM_DESCRIPTION_NOT_CONTAIN +
                                EXPECTED_ITEMS_DESCRIPTION_GOODS_CONTENT,
                        el -> el.getText().contains(EXPECTED_ITEMS_DESCRIPTION_GOODS_CONTENT)))
                .shouldHave(allMatch(ITEM_DESCRIPTION_NOT_CONTAIN +
                                EXPECTED_ITEMS_DESCRIPTION_MIN_PRICE_CONTENT,
                        el -> el.getText().contains(EXPECTED_ITEMS_DESCRIPTION_MIN_PRICE_CONTENT)));
    }
}
