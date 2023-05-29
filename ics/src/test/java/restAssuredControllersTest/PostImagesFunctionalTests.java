package restAssuredControllersTest;

import com.example.demo.models.Image;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static restAssuredControllersTest.TestArguments.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostImagesFunctionalTests {

    private static RequestSpecification requestSpecification;
    private static Long idNewImage;


    @BeforeAll
    static void setUp() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
    }

    @AfterAll
    static void tearDown() {
        deleteCreatedIssues();
    }

    private static void deleteCreatedIssues() {
        given()
                .spec(requestSpecification)
                .when()
                .delete("/" + idNewImage);
    }

    @Test
    @DisplayName("Post image and get labels, returns 200")
    void testPostImage_Returns200() {

        given()
                .spec(requestSpecification)
                .body(getJson(IMAGE_URL))
                .when()
                .post()
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200);
    }

    private String getJson(String url) {
        JSONObject jsonObject = new JSONObject();
        JSONArray recordsArray = new JSONArray();

        JSONObject recordObject = new JSONObject();
        recordObject.put("_url", url);

        recordsArray.put(recordObject);
        jsonObject.put("records", recordsArray);

        return jsonObject.toString();
    }

}
