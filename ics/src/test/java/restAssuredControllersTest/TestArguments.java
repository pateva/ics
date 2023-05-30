package restAssuredControllersTest;

public class TestArguments {
    private TestArguments() {}

    public static final String BASE_URL = "http://localhost:8080";
    public static final String PATH_IMAGES = "/images";
    public static final String PATH_LABELS = "/labels";
    public static final String PATH_IMAGES_ID = PATH_IMAGES + "/{id}";
    public static final String PATH_LABELS_ID = PATH_LABELS + "/{id}";
    public static final String IMAGE_URL = "https://media1.popsugar-assets.com/files/thumbor/UOPRdnbZ1s6XYh2LIz818gMT8CE/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2018/10/03/826/n/1922283/8e56ee3c5bb50f849f97e5.03708514_/i/Disney-Lilo-Stitch-Live-Action-Reboot-Details.jpg";
    public static final int IMAGE_ID = 8;
    public static final int LABEL_ID = 7;
    public static final String IMAGE_SERVICE = "Ximilar";
    public static final String IMAGES_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/images-template.json";
    public static final String SINGLE_IMAGE_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/single-image-template.json";
    public static final String LABELS_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/labels-template.json";
    public static final String SINGLE_LABEL_JSON_TEMPLATE_PATH = "src/test/resources/json-templates/single-label-template.json";


};
