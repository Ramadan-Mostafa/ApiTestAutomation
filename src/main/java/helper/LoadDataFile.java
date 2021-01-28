package helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadDataFile {

    // Read data from the properties file
    public static Properties userdata = loadProperties(Constants.PROPERTIES_FILE_PATH);

    private static Properties loadProperties(String path) {
        Properties prop = new Properties();
        try {
            FileInputStream streamFile = new FileInputStream(path);
            try {
                prop.load(streamFile);
            } catch (IOException e) {
                System.out.println("File Cannot be streamed!:" + e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!" + e);
        }
        return prop;
    }
}