package restAssuredControllersTest;

import com.example.demo.models.Image;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static restAssuredControllersTest.TestService.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostImagesFunctionalTests {

    private static RequestSpecification requestSpecification;
    private static Long idNewImage;


    @BeforeAll
    static void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH_IMAGES)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
    }

    @AfterAll
    static void tearDown() {
        deleteCreatedImages(requestSpecification, idNewImage);
    }

    @Test
    @DisplayName("Post image and get labels, returns 200")
    void testPostImage_Returns200() {
        Image image =
                given()
                        .spec(requestSpecification)
                        .body(getJson(IMAGE_URL))
                .when()
                        .post()
                        .prettyPeek()
                .then()
                        .assertThat()
                        .statusCode(200)
                .and()
                        .body("imageUrl", is(IMAGE_URL))
                        .body("createdAt", not(emptyOrNullString()))
                        .body("width", not(emptyOrNullString()))
                        .body("height", not(emptyOrNullString()))
                        .extract().as(Image.class);

        idNewImage = image.getImageId();
    }

    @Test
    @DisplayName("Post image with invalid url, returns 400")
    void testPostImage_Returns400() {

        given()
                .spec(requestSpecification)
                .body(getJson(IMAGE_INVALID_URL))
        .when()
                .post()
                .prettyPeek()
        .then()
                .assertThat()
                .statusCode(400);

    }

    @Test
    @DisplayName("Post existing image and get labels, returns 202")
    void testPostImage_Returns202() {

        given()
                .spec(requestSpecification)
                .body(getJson(IMAGE_URL_EXISTING))
        .when()
                .post()
                .prettyPeek()
        .then()
                .assertThat()
                .statusCode(202)
        .and()
                .body("imageUrl", is(IMAGE_URL_EXISTING))
                .body("createdAt", not(emptyOrNullString()))
                .body("width", not(emptyOrNullString()))
                .body("height", not(emptyOrNullString()));

    }
}
