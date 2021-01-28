package reportgenerator;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import helper.Constants;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static helper.Common.getCurrentTime;

@SuppressWarnings("deprecation")
public class ReportManager {

    protected static ExtentReports extent = new ExtentReports();
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest logger;
    private static String fileName;
    static String reportFilePath;
    protected static InetAddress ip;
    protected static String hostname;

    /**
     * return the current class name
     *
     * @param testSuiteName: current test suite
     */
    public static void getTestClassName(String testSuiteName) {
        fileName = testSuiteName;
    }

    /**
     * log the report in .html file
     */
    public static void generateReport() {
        reportFilePath = Constants.REPORT_DIR + getCurrentTime() + ".html";
        htmlReporter = new ExtentHtmlReporter(reportFilePath);
        extent.attachReporter(htmlReporter);
    }

    /**
     * get current environment data to be shown in the log file
     */
    public static void setEnvironmentData() {
        htmlReporter.config().setDocumentTitle("Test Suite");
        htmlReporter.config().setReportName("MBRHE Test Suite");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent.setSystemInfo("Os name: ", System.getProperty("os.name"));
        extent.setSystemInfo("Java version: ", System.getProperty("java.version"));
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            extent.setSystemInfo("Your current IP address: ", ip.toString());
            extent.setSystemInfo("Your current Hostname: ", hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * To add current step in the log report
     *
     * @param methodName: caller method name
     */
    public static void stepLogGeneration(String methodName) {
        hostname = ip.getHostName();
        logger = extent.createTest(methodName);
        logger.assignAuthor("@author: " + hostname);
        logger.log(Status.INFO, "Start testing for: " + methodName);
    }

    /**
     * Log the info in the report
     *
     * @param info:             string sequence to be shown in the report
     * @param caller:           the current method which called LogInfo method
     * @param callerLineNumber: the line in which the method called
     */
    protected void logInfo(String info, String caller, String callerLineNumber) {
        logger.log(Status.INFO, info + " " + caller + ": " + callerLineNumber);
    }

    /**
     * Log the failure status
     *
     * @param failure: cause of the failure
     */
    protected void logFailure(String failure) {
        logger.fail(MarkupHelper.createLabel(failure, ExtentColor.RED));
    }

    /**
     * Return the status of the test method
     *
     * @param result: Failed, Passed or ignored
     */
    public static void getTestStatus(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.pass(MarkupHelper.createLabel(Status.PASS + " " + result.getName(), ExtentColor.GREEN));

        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.fail(MarkupHelper.createLabel(Status.FAIL + " " + result.getThrowable() + " " + result.getName(),
                    ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.skip(MarkupHelper.createLabel(Status.SKIP + " " + result.getName(), ExtentColor.GREY));
            throw new SkipException("Skipping - This is not ready for testing ");

        } else if (result.getStatus() == ITestResult.CREATED) {
            logger.log(Status.INFO, "Test Case has been created and ready for test");
        }
    }

    /**
     * This method is desired to open the report after the execution
     *
     * @throws IOException
     */
    public static void openReport() throws IOException {
        // Text file, should be opening in default text editor
        File file = new File(reportFilePath);
        // First check if Desktop is supported by Platform or not
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) desktop.open(file);
    }

    /**
     * add screenshot to the report
     *
     * @param info:      a string to be shown in the html report
     * @param imagePath: the path of the screenshot to be shown in the report
     */
    public static void addScreenshotToReport(String info, String imagePath) {
        try {
            logger.info(info, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
        } catch (IOException e) {
            logger.error(MarkupHelper.createLabel(Status.FAIL + " " + e.getMessage(), ExtentColor.PINK));
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * end report and flush resources
     *
     * @throws IOException
     */
    public static void endReport() {
        try {
            extent.flush();
            System.out.println(getCurrentTime() + "\tReport has been generated refer to 'reports' to open it");
            openReport();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
