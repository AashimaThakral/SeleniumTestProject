package commonComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporter;

public class Listeners extends BaseTest implements ITestListener{
    ExtentTest test;
    ExtentReports report = ExtentReporter.getReport();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        test = report.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //assign Unique Thread ID
    }

    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
            String testCaseName = result.getMethod().getMethodName();
            try {
                String filePath = getScreenshot(testCaseName, driver);
                extentTest.get().addScreenCaptureFromPath(filePath, testCaseName);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
        report.flush();
    }
    
}
