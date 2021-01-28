package helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Common {
    protected static OSType detectedOS;

    /***
     * Returns the operating system type
     * @return: Os name
     */
    public static OSType getOperatingSystemType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                detectedOS = OSType.MacOS;
            } else if (OS.indexOf("win") >= 0) {
                detectedOS = OSType.Windows;
            } else if (OS.indexOf("nux") >= 0) {
                detectedOS = OSType.Linux;
            } else {
                detectedOS = OSType.Other;
            }
        }
        return detectedOS;
    }

    /***
     * Get the current time and date
     * @return: date and time string
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("dd-MMM-yyyy,HH.mm.ss").format(new Date());
    }

    public enum OSType {
        Windows, MacOS, Linux, Other
    }
}
