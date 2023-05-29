package restAssuredControllersTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;
import static restAssuredControllersTest.TestArguments.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetImagesByIdFunctionalTests {
    private static RequestSpecification requestSpecification;

    @BeforeAll
    static void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH_ID)
                .build();
    }

    @Test
    @DisplayName("Get with path variable, returns 200")
    void testGetWithId_Returns200() {
        given()
                .spec(requestSpecification)
                .pathParam("id", IMAGE_ID)
                .when()
                .get()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(new File(SINGLE_IMAGE_JSON_TEMPLATE_PATH)))
                .and()
                .body("imageId", is(IMAGE_ID))
                .body("createdAt", not(emptyOrNullString()))
                .body("updatedAt", not(emptyOrNullString()))
                .body("width", not(emptyOrNullString()))
                .body("height", not(emptyOrNullString()))
                .body("imageService", is(IMAGE_SERVICE));
    }

    @Test
    @DisplayName("Get with path variable, returns 404")
    void testGetWithId_Returns404() {
        given()
                .spec(requestSpecification)
                .pathParam("id", "5")
                .when()
                .get()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(404);
    }
}
