package restAssuredControllersTest;

public class TestArguments {
    private TestArguments() {}

    public static final String BASE_URL = "http://localhost:8080";
    public static final String PATH = "/images";
    public static final String PATH_ID = PATH + "/{id}";

    public static final String IMAGES_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/images-template.json";
    public static final String SINGLE_IMAGE_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/single-image-template.json";
    public static final String IMAGE_URL = "https://people.com/thmb/fO2beoyTSrvkFMS-rn3CIQJQ46c=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(899x449:901x451):format(webp)/lilo-and-stich-42716eea64ad4d16a01c76f8c1ec30c3.jpg";
    public static final int IMAGE_ID = 8;
    public static final String IMAGE_SERVICE = "Ximilar";

};
