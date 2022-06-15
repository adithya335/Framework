import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent {
    ExtentReports extentReports;

    public static void main(String[] args) {
        Extent e = new Extent();
        e.config();
    }

    public void config() {
        String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setReportName("Test Extent");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester", "Adithya");
        ExtentTest test = extentReports.createTest("My test1");
        test.addScreenCaptureFromPath("Screenshot (13).jpg", "screenshotTest");
        extentReports.flush();

    }

}
