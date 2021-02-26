package helper;

import targets.Android;
import targets.Chrome;
import targets.Firefox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static helper.Common.TargetPlatform.Android;

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

    public enum TargetPlatform {
        Android, IOS, Chrome, Firefox, Safari, InternetExplorer, Headless, MicrosoftEdge
    }

    public static void getDesiredTarget(String platform) {
        switch (platform) {
            case "ch":
            case "CH":
            case "Ch":
            case "Chrome":
            case "chrome":
                targets.Chrome.init();
                break;

            case "ie":
            case "Ie":
            case "IE":
            case "internet explorer":
            case "internetexplorer":
            case "InternetExplorer":
            case "internetExplorer":
            case "Internet Explorer":
                // To be implemented
                break;
            case "ff":
            case "Ff":
            case "FF":
            case "Firefox":
            case "firefox":
                targets.Firefox.init();
                break;

            case "Safari":
            case "safari":
                // To be implemented
                break;

            case "H":
            case "Headless":
            case "headless":
                // To be implemented
                break;

            case "Edge":
            case "Microsoft Edge":
            case "MicrosoftEdge":
            case "microsoftEdge":
            case "microsoftedge":
            case "MICROSOFTEDGE":
            case "MICROSOFT EDGE":
                // To be implemented
                break;

            case "IOS":
            case "ios":
            case "Ios":
                targets.IOS.init();
                // To be implemented
                break;

            case "AND":
            case "ANDROID":
            case "Android":
            case "android":
            case "and":
                targets.Android.init();
                break;
        }
    }
}
