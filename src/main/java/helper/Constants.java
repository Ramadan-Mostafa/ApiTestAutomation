package helper;

public class Constants {
    public static final String WORKING_DIR = System.getProperty("user.dir");

    public static String SCREENSHOT_DIR = WORKING_DIR + "/screenshots"; // Screenshots folder path
    public static String REPORT_DIR = WORKING_DIR + "/reports/"; // Reports folder path

    public static final String OBJECT_REPO_PATH = WORKING_DIR + "/resources/objectRepo.xlsx";

    public static final String CHROME_DRIVER_PATH_WIN = WORKING_DIR + "/drivers/win/chromedriver.exe";
    public static final String FIREFOX_DRIVER_PATH_WIN = WORKING_DIR + "/drivers/win/geckodriver.exe";

    public static final String CHROME_DRIVER_PATH_MAC = WORKING_DIR + "/drivers/mac/chromedriver";
    public static final String FIREFOX_DRIVER_PATH_MAC = WORKING_DIR + "/drivers/mac/geckodriver";

    public static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    public static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";

    public static final String PROPERTIES_FILE_PATH = WORKING_DIR + "/resources/config.properties";
    public static final String MAP_ENDPOINT = LoadDataFile.userdata.getProperty("mapEndpoint");
    public static final String API_KEY = LoadDataFile.userdata.getProperty("apiKey");
    public static final String CONVERT_ENDPOINT = LoadDataFile.userdata.getProperty("convertEndpoint");
    public static final String EHTEREUM_ENDPOINT = LoadDataFile.userdata.getProperty("ethereumEndpoint");

    public static final String APPIUM_SERVER = LoadDataFile.userdata.getProperty("appiumServerUrl");


}
