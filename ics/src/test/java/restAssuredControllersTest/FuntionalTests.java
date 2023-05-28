package restAssuredControllersTest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import io.restassured.builder.RequestSpecBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static restAssuredControllersTest.TestArguments.BASE_URL;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FuntionalTests {

    private static RequestSpecBuilder builder;
    private static RequestSpecification requestSpecification;

    @BeforeAll
    static void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath("/images")
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
    }


}
