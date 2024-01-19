package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static synchronized ExtentReports createInstance() {
        String fileName = Util.getReportName();
        String reportsDirectory = Constants.REPORTS_DIRECTORY;
        new File(reportsDirectory).mkdirs();
        String path = reportsDirectory + fileName;
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Run");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.setSystemInfo("Organization", "Dummy Assessment");
        extent.setSystemInfo("Automation Framework", "Selenium WebDriver");
        extent.attachReporter(htmlReporter);

        return extent;
    }

}


