package restAssuredControllersTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static restAssuredControllersTest.TestArguments.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetAllLabelsFunctionalTests {
    private static RequestSpecification requestSpecification;

    @BeforeAll
    static void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH_LABELS)
                .build();
    }

    @Test
    @DisplayName("Get all labels, returns 200")
    void testGetAllImages_Returns200() {

        given()
                .spec(requestSpecification)
                .when()
                .get()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(new File(LABELS_JSON_TEMPLATE_PATH)));
    }

}
