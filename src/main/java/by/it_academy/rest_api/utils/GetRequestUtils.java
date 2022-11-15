package by.it_academy.rest_api.utils;

import com.google.common.collect.ImmutableMap;
import io.restassured.response.ResponseBody;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestUtils {

    public static final int STATUS_CODE_OK = 200;
    public static final String HOST = "Host";
    public static final String CATALOG_ONLINER_BY = "catalog.onliner.by";

    private GetRequestUtils() {
    }

    public static ResponseBody makeRequestAndGetResponseBody(String endpoint) {
        return given()
                .headers(configureHeaders())
                .when()
                .get(endpoint)
                .then()
                .statusCode(STATUS_CODE_OK)
                .extract()
                .response()
                .getBody();
    }

    public static Map<String, String> configureHeaders() {
        return ImmutableMap.of(HOST, CATALOG_ONLINER_BY);
    }
}
