package restAssuredControllersTest;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static restAssuredControllersTest.TestArguments.BASE_URL;
import static restAssuredControllersTest.TestArguments.PATH;

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
                .statusCode(200);

        //json validation
    }

    @ParameterizedTest
    @CsvSource({"vector"})
    @DisplayName("Get images by labels")
    void testGetImagesWithTags_ReturnSpecified(String firstLabel) {

                given()
                        .spec(requestSpecification)
                        .queryParam("labels", firstLabel)
                        .when()
                        .get()
                        .prettyPeek()
                        .then()
                        .assertThat()
                        .statusCode(200);

                //assert and json validation
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
                        .statusCode(200);

                //negative test -> assert that nothing is returned
                //assert and json validation
    }




}
