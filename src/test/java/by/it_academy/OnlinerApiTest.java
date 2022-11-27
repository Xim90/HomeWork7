package by.it_academy;

import by.it_academy.rest_api.model.Product;
import io.restassured.response.ResponseBody;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.it_academy.rest_api.endpoints.OnlinerEndpoints.SUSHI_CATALOG;
import static by.it_academy.rest_api.endpoints.OnlinerEndpoints.SUSHI_CATALOG_WITH_FILTER_ROLLS;
import static by.it_academy.rest_api.service.GetOnlinerRequest.makeRequestAndGetResponseBody;
import static org.assertj.core.api.Assertions.assertThatList;

public class OnlinerApiTest {
    public static final String ROLLS = "Роллы";
    public static final String PRODUCTS = "products";
    public static final String PRODUCTS_NAME_PREFIX = "products.name_prefix";

    @Test
    public void productsShouldNotContainEmptyNames() {
        ResponseBody responseBody = makeRequestAndGetResponseBody(SUSHI_CATALOG);
        List<Product> products = responseBody.jsonPath().getList(PRODUCTS, Product.class);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThatList(products).withFailMessage("id is empty")
                    .noneMatch(el -> el.getId().isEmpty());
            softly.assertThatList(products).withFailMessage("key is empty")
                    .noneMatch(el -> el.getKey().isEmpty());
            softly.assertThatList(products).withFailMessage("name is empty")
                    .noneMatch(el -> el.getName().isEmpty());
            softly.assertThatList(products).withFailMessage("full name is empty")
                    .noneMatch(el -> el.getFull_name().isEmpty());
        });
    }

    @Test
    public void namePrefixShouldContainRolls() {
        ResponseBody responseBody = makeRequestAndGetResponseBody(SUSHI_CATALOG_WITH_FILTER_ROLLS);
        List<String> prefix = responseBody.jsonPath().getList(PRODUCTS_NAME_PREFIX);
        assertThatList(prefix)
                .withFailMessage("name prefix does not contain filter 'Rolls'")
                .allMatch(el -> el.equals(ROLLS));
    }
}
