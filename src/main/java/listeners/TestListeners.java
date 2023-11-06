package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.FileManager;
import utilities.Logs;
import utilities.PrintTestUtils;

public class TestListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Logs.debug("on test start");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println(context.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.debug("on test success");
        PrintTestUtils.printSuccess(result.getInstanceName(), result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.debug("on test failure");
        FileManager.getScreenshot(result.getName());
        FileManager.getPageSourceXML(result.getName());
        PrintTestUtils.printFailed(result.getInstanceName(), result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.debug("on test skipped");
        PrintTestUtils.printSkipped(result.getInstanceName(), result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println();
    }
}
