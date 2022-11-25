package by.it_academy;

import by.it_academy.constants.FailTestMessage;
import by.it_academy.rest_api.model.Product;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.it_academy.rest_api.endpoints.OnlinerEndpoints.SUSHI_CATALOG;
import static by.it_academy.rest_api.endpoints.OnlinerEndpoints.SUSHI_CATALOG_WITH_FILTER_ROLLS;
import static by.it_academy.rest_api.utils.GetRequestUtils.makeRequestAndGetResponseBody;
import static org.assertj.core.api.Assertions.assertThatList;

public class OnlinerApiTest {

    public static final String ROLLS = "Роллы";
    public static final String PRODUCTS = "products";
    public static final String PRODUCTS_NAME_PREFIX = "products.name_prefix";

    @Test
    public void productsShouldNotContainEmptyNames() {
        ResponseBody responseBody = makeRequestAndGetResponseBody(SUSHI_CATALOG);
        List<Product> list = responseBody.jsonPath().getList(PRODUCTS, Product.class);
        assertThatList(list)
                .withFailMessage(FailTestMessage.ID_EMPTY)
                .noneMatch(el -> el.getId().isEmpty())
                .withFailMessage(FailTestMessage.KEY_EMPTY)
                .noneMatch(el -> el.getKey().isEmpty())
                .withFailMessage(FailTestMessage.NAME_EMPTY)
                .noneMatch(el -> el.getName().isEmpty())
                .withFailMessage(FailTestMessage.FULL_NAME_EMPTY)
                .noneMatch(el -> el.getFull_name().isEmpty());
    }

    @Test
    public void namePrefixShouldContainRolls() {
        ResponseBody responseBody = makeRequestAndGetResponseBody(SUSHI_CATALOG_WITH_FILTER_ROLLS);
        List<String> prefix = responseBody.jsonPath().getList(PRODUCTS_NAME_PREFIX);
        assertThatList(prefix)
                .withFailMessage(FailTestMessage.NAME_PREFIX_DOES_NOT_CONTAIN_FILTER_ROLLS)
                .allMatch(el -> el.equals(ROLLS));
    }
}
