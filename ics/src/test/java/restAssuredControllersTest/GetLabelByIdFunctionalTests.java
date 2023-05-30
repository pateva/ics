package restAssuredControllersTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static restAssuredControllersTest.TestArguments.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetLabelByIdFunctionalTests {
    private static RequestSpecification requestSpecification;

    @BeforeAll
    static void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH_LABELS_ID)
                .build();
    }

    @Test
    @DisplayName("Get with path variable, returns 200")
    void testGetWithId_Returns200() {
        given()
                .spec(requestSpecification)
                .pathParam("id", LABEL_ID)
        .when()
                .get()
                .prettyPeek()
        .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(new File(SINGLE_LABEL_JSON_TEMPLATE_PATH)))
        .and()
                .body("labelId", is(LABEL_ID))
                .body("labelDescription", not(emptyOrNullString()));
    }

    @Test
    @DisplayName("Get with path variable, returns 404")
    void testGetWithId_Returns404() {
        given()
                .spec(requestSpecification)
                .pathParam("id", "0")
                .when()
                .get()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(404);
    }

}
