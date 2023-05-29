package restAssuredControllersTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static restAssuredControllersTest.TestArguments.BASE_URL;
import static restAssuredControllersTest.TestArguments.PATH_ID;

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
                .pathParam("id", "6")
                .when()
                .get()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200);
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
