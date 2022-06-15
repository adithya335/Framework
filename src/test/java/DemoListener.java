import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;

public class DemoListener extends Base implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test case started " + result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test case success " + result.getMethod().getMethodName());
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test case Failed " + result.getMethod().getMethodName());
        WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        getScreenShot(result.getMethod().getMethodName(),driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
