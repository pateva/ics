package restAssuredControllersTest;

import com.example.demo.models.Image;
import com.example.demo.models.Label;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static restAssuredControllersTest.TestArguments.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GetAllImagesFunctionalTests {

    private static RequestSpecBuilder builder;
    private static RequestSpecification requestSpecification;

    @BeforeAll
    static void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH)
                .build();
    }

    @Test
    @DisplayName("Get all images, returns 200")
    void testGetAllImages_Returns200() {

        given()
                .spec(requestSpecification)
                .when()
                .get()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(new File(IMAGES_JSON_TEMPLATE_PATH)));

    }

    @ParameterizedTest
    @CsvSource({"vector"})
    @DisplayName("Get images by labels")
    void testGetImagesWithTags_ReturnSpecified(String label) {

        List<Image> images = given()
                .spec(requestSpecification)
                .queryParam("labels", label)
                .when()
                .get()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(new File(IMAGES_JSON_TEMPLATE_PATH)))
                .and()
                .extract().jsonPath()
                .getList(".", Image.class);


        assertTrue(hasLabel(images, label));
    }

    private boolean hasLabel(List<Image> receivedImages, String label) {
        boolean isExisting = false;

        for (Image image : receivedImages) {
            for(Label lbl : image.getLabels()) {
                if(lbl.getLabelDescription().equals(label)) isExisting = true;
            }
        }

        return isExisting;
    }

    @ParameterizedTest
    @CsvSource({"nothing"})
    @DisplayName("Get images by labels")
    void testGetImagesWithTags_ReturnNothing(String firstLabel) {

        given()
                .spec(requestSpecification)
                .queryParam("labels", firstLabel)
                .when()
                .get()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("$", empty());
    }


}
