import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static helper.Common.getCurrentTime;

/**
 * To log each step status
 */
public class Listener implements ITestListener {
    protected static String timestamp() {
        return getCurrentTime();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("**********************< INFO >*******************\n" + timestamp() + "\tStart " + getTestMethodName(iTestResult) + "\n");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("**********************< INFO >*******************\n" + timestamp() + "\tINFO: Test result of " + getTestMethodName(iTestResult) + " PASS\n");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("**********************< INFO >*******************\n" + timestamp() + "\tFAILURE: Test result of " + getTestMethodName(iTestResult)
                + " is: FAILED\n");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("**********************< INFO >*******************\n" + timestamp() + "\tSKIP: " + getTestMethodName(iTestResult) + " method has been skipped\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("**********************< INFO >*******************\n" + timestamp() + "\tTest failed but it is in defined success ratio in: "
                + getTestMethodName(iTestResult) + "\n");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        // On start from XML file
        System.out.println("**********************< INFO >*******************\n" + timestamp() + "\tINFO: Start from XML file; " + iTestContext.getName() + "\n");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        // On finish from the XML file // the whole suite
        System.out.println("**********************< INFO >*******************\n" + timestamp() + "\tINFO: End testing " + iTestContext.getName() + "\n");
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
