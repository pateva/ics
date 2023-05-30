package restAssuredControllersTest;

public class TestArguments {
    private TestArguments() {}

    public static final String BASE_URL = "http://localhost:8080";
    public static final String PATH = "/images";
    public static final String PATH_ID = PATH + "/{id}";
    public static final String IMAGE_URL = "https://lumiere-a.akamaihd.net/v1/images/ct_snowwhite_upcportalreskin_20694_eb571c22.jpeg?region=0%2C0%2C330%2C330";
    public static final int IMAGE_ID = 8;
    public static final String IMAGE_SERVICE = "Ximilar";
    public static final String IMAGES_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/images-template.json";
    public static final String SINGLE_IMAGE_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/single-image-template.json";

};
