package by.it_academy;

import by.it_academy.page_object.CatalogPage;
import by.it_academy.page_object.HomePage;
import by.it_academy.util.WebDriverSetter;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;

public class OnlinerTest {
    public static final String SPACES_PATTERN = "[\s]+";
    public static final int EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS = 14;
    public static final String EXPECTED_ITEMS_DESCRIPTION_GOODS_CONTENT = "товар";
    public static final String EXPECTED_ITEMS_DESCRIPTION_MIN_PRICE_CONTENT = "р.";
    public static final String COMPUTER_AND_NETWORK_CATEGORY_NAME = "Компьютеры";
    public static final String ACCESSORIES_CATEGORY_NAME = "Комплектующие";
    public static final String DRIVER_TYPE = "driverType";
    public static final String ALLURE_SELENIDE = "AllureSelenide";
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
    public static void setUp() {
        WebDriverSetter.setWebDriver(System.getProperty(DRIVER_TYPE));
        SelenideLogger.addListener(ALLURE_SELENIDE,
                new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @Test
    public void checkCatalogClassifierItemsName() {
        HomePage homePage = new HomePage();
        homePage.clickOnCatalogLink()
                .getCatalogClassifierItems()
                .shouldHave(exactTexts(expectedCatalogMenuItems)
                        .because("Menu not contain expected list of item's name"));
    }

    @Test
    public void checkComputerAndNetworkAsideItemsName() {
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.clickOnCatalogCategory(COMPUTER_AND_NETWORK_CATEGORY_NAME);
        catalogPage.getCurrentCategoryItemsList()
                .shouldHave(containExactTextsCaseSensitive(expectedComputerAndNetworksMenuItems)
                        .because("Menu not contain expected list of item's name"));
    }

    @Test
    public void checkAccessoriesDropdownItemsContent() {
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.clickOnCatalogCategory(COMPUTER_AND_NETWORK_CATEGORY_NAME);
        catalogPage.clickOnItemOfCurrentCategory(ACCESSORIES_CATEGORY_NAME);
        catalogPage
                .getCategorySideItemsTitle(ACCESSORIES_CATEGORY_NAME)
                .shouldHave(size(EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS)
                        .because("Number of items not equal" + EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS))
                .shouldHave(noneMatch("Item's title is empty",
                        el -> el.getText().isEmpty()))
                .shouldHave(noneMatch("Item's title matches spaces",
                        el -> el.getText().matches(SPACES_PATTERN)));
        catalogPage
                .getCategorySideItemsDescription(ACCESSORIES_CATEGORY_NAME)
                .shouldHave(size(EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS)
                        .because("Number of items not equal" + EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS))
                .shouldHave(allMatch("Item's description not contain" +
                                EXPECTED_ITEMS_DESCRIPTION_GOODS_CONTENT,
                        el -> el.getText().contains(EXPECTED_ITEMS_DESCRIPTION_GOODS_CONTENT)))
                .shouldHave(allMatch("Item's description not contain" +
                                EXPECTED_ITEMS_DESCRIPTION_MIN_PRICE_CONTENT,
                        el -> el.getText().contains(EXPECTED_ITEMS_DESCRIPTION_MIN_PRICE_CONTENT)));
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
