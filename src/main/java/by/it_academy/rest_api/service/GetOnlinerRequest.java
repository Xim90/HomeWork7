package by.it_academy.rest_api.service;

import com.google.common.collect.ImmutableMap;
import io.restassured.response.ResponseBody;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class GetOnlinerRequest {
    public static final String HOST = "Host";
    public static final String CATALOG_ONLINER_BY = "catalog.onliner.by";

    private GetOnlinerRequest() {
    }

    public static ResponseBody makeRequestAndGetResponseBody(String endpoint) {
        return given()
                .headers(configureHeaders())
                .when()
                .get(endpoint)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response()
                .getBody();
    }

    public static Map<String, String> configureHeaders() {
        return ImmutableMap.of(HOST, CATALOG_ONLINER_BY);
    }
}
