package restAssuredControllersTest;

import com.example.demo.controllers.dto.RecognitionRequestBody;
import com.example.demo.controllers.dto.URL;
import com.example.demo.models.Image;
import com.example.demo.models.Label;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestService {
    private TestService() {}

    public static final String BASE_URL = "http://localhost:8080";
    public static final String PATH_IMAGES = "/images";
    public static final String PATH_LABELS = "/labels";
    public static final String PATH_IMAGES_ID = PATH_IMAGES + "/{id}";
    public static final String PATH_LABELS_ID = PATH_LABELS + "/{id}";
    public static final String IMAGE_URL = "https://www.justgoholidays.com/images/Itinerary/17-lake-garda-verona-express.jpg";
    public static final String IMAGE_URL_EXISTING = "https://static.wikia.nocookie.net/disneyfanon/images/0/0e/Happy1.png";
    public static final String IMAGE_INVALID_URL = "not-valid";
    public static final int IMAGE_ID = 8;
    public static final int LABEL_ID = 7;
    public static final String NEW_LABEL = "testHappyPath";
    public static final String EXISTING_LABEL = "vector";
    public static final String IMAGE_SERVICE = "Ximilar";
    public static final String IMAGES_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/images-template.json";
    public static final String SINGLE_IMAGE_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/single-image-template.json";
    public static final String LABELS_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/labels-template.json";
    public static final String SINGLE_LABEL_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/single-label-template.json";

    public static String getJson(String url) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<URL> requestBody = new ArrayList<>();
            requestBody.add(new URL(url));
            RecognitionRequestBody recordsWrapper = new RecognitionRequestBody(requestBody);

            return objectMapper.writeValueAsString(recordsWrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteCreatedImages(RequestSpecification requestSpecification, Long idNewImage) {
        given()
                .spec(requestSpecification)
                .when()
                .delete("/" + idNewImage);
    }

    public static Long createNewImage(String url) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH_IMAGES)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        Image image =
                given()
                        .spec(requestSpecification)
                        .body(getJson(url))
                        .when()
                        .post()
                        .prettyPeek()
                        .then()
                        .assertThat()
                        .statusCode(anyOf(is(200), is(202)))
                        .and()
                        .extract().as(Image.class);

        return image.getImageId();
    }
};
