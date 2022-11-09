package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {


    public static ExtentReports getReport(){
        String path = System.getProperty("user.dir" + "/reports/index.html");
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Practice Test");
        reporter.config().setDocumentTitle("UI Automation Report");

        ExtentReports report = new ExtentReports();
        report.attachReporter(reporter);
        report.setSystemInfo("QA", "Aashima Thakral");
        return report;
    }
    
}
