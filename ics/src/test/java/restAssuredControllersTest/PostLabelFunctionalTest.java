package restAssuredControllersTest;

import com.example.demo.models.Label;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.is;
import static restAssuredControllersTest.TestService.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostLabelFunctionalTest {
    private static RequestSpecification requestSpecification;
    private static Long idNewLabel;

    @BeforeAll
    static void setUp() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH_LABELS)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
    }

    @AfterAll
    static void tearDown() {
        given()
                .spec(requestSpecification)
        .when()
                .delete("/" + idNewLabel);
    }

    @Test
    @DisplayName("Post label, returns 200")
    void testPostLabel_Returns200() {

        Label label =
                given()
                        .spec(requestSpecification)
                        .body(getJson(NEW_LABEL))
                .when()
                        .post()
                        .prettyPeek()
                .then()
                        .assertThat()
                        .statusCode(200)
                .and()
                        .body(matchesJsonSchema(new File(SINGLE_LABEL_JSON_TEMPLATE_PATH)))
                        .body("labelDescription", is(NEW_LABEL))
                .and()
                        .extract().as(Label.class);

        idNewLabel = label.getLabelId();
    }

    private String getJson(String labelDescription) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", labelDescription);

        return jsonObject.toString();
    }

    @Test
    @DisplayName("Post label, returns 200")
    void testPostExistingLabel_Returns200() {

        given()
                .spec(requestSpecification)
                .body(getJson(EXISTING_LABEL))
        .when()
                .post()
                .prettyPeek()
        .then()
                .assertThat()
                .statusCode(200)
        .and()
                .body(matchesJsonSchema(new File(SINGLE_LABEL_JSON_TEMPLATE_PATH)))
                .body("labelDescription", is(EXISTING_LABEL));

    }

}
