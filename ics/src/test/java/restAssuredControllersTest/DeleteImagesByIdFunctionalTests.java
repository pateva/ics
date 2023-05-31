package restAssuredControllersTest;

import com.example.demo.models.Image;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static restAssuredControllersTest.TestService.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteImagesByIdFunctionalTests {

    private static RequestSpecBuilder builder;
    private static RequestSpecification requestSpecification;
    private static Long imageId;

    @BeforeAll
    static void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH_IMAGES)
                .build();

        imageId = createNewImage(IMAGE_URL);
    }

    @Test
    @DisplayName("Delete image, returns 200")
    void testDeleteEndoint_Returns200() {

        given()
                .spec(requestSpecification)
                .when()
                .delete("/" + imageId)
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete non-existent image, returns 404")
    void testDeleteEndointNegative_Returns404() {

        given()
                .spec(requestSpecification)
        .when()
                .delete("images/1")
                .prettyPeek()
        .then()
                .assertThat()
                .statusCode(404);
    }
}
