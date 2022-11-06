package by.it_academy;

import by.it_academy.page_object.CatalogPage;
import by.it_academy.page_object.HomePage;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatList;

public class OnlinerTest {
    public static final String SPACES_PATTERN = "[\s]+";
    public static final int EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS = 14;
    public static final String EXPECTED_ACCESSORIES_DROPDOWN_ITEMS_DESCRIPTION_GOODS_CONTENT = "товар";
    public static final String EXPECTED_ACCESSORIES_DROPDOWN_ITEMS_DESCRIPTION_MIN_PRICE_CONTENT = "р.";
    public static final String COMPUTER_AND_NETWORK_CATEGORY_NAME = "Компьютеры";
    public static final String ACCESSORIES_CATEGORY_NAME = "Комплектующие";
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
        Configuration.browser = "chrome";
    }

    @Test
    public void checkCatalogClassifierItemsName() {
        HomePage homePage = new HomePage();
        List<String> catalogElements = homePage
                .clickOnCatalogLink()
                .getCatalogClassifierItems()
                .texts();
        assertThatList(catalogElements)
                .containsExactlyElementsOf(expectedCatalogMenuItems);
    }

    @Test
    public void checkComputerAndNetworkAsideItemsName() {
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.clickOnCatalogCategory(COMPUTER_AND_NETWORK_CATEGORY_NAME);
        assertThatList(catalogPage.getCurrentCategoryItemsList().texts())
                .containsAll(expectedComputerAndNetworksMenuItems);
    }

    @Test
    public void checkAccessoriesDropdownItemsContent() {
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.clickOnCatalogCategory(COMPUTER_AND_NETWORK_CATEGORY_NAME);
        catalogPage.clickOnItemOfCurrentCategory(ACCESSORIES_CATEGORY_NAME);
        List<String> dropDownItemsTitle = catalogPage
                .getCategorySideItemsTitle(ACCESSORIES_CATEGORY_NAME)
                .texts();
        List<String> dropDownItemsDescription = catalogPage
                .getCategorySideItemsDescription(ACCESSORIES_CATEGORY_NAME)
                .texts();
        assertThatList(dropDownItemsTitle)
                .hasSize(EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS)
                .noneMatch(String::isEmpty)
                .noneMatch(el -> el.matches(SPACES_PATTERN));
        assertThatList(dropDownItemsDescription)
                .hasSize(EXPECTED_NUMBER_ACCESSORIES_DROPDOWN_ITEMS)
                .allMatch(el -> el.contains(EXPECTED_ACCESSORIES_DROPDOWN_ITEMS_DESCRIPTION_GOODS_CONTENT))
                .allMatch(el -> el.contains(EXPECTED_ACCESSORIES_DROPDOWN_ITEMS_DESCRIPTION_MIN_PRICE_CONTENT));
    }
}
