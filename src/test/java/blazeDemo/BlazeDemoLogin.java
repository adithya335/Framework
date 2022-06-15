package blazeDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.PO_BlazeDemo;
import resources.Base;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlazeDemoLogin extends Base {

    PO_BlazeDemo blazeDemo;
    public WebDriver driver;

    @BeforeTest
    public void beforeTest() throws IOException {
        this.driver = driver_init();
        blazeDemo = new PO_BlazeDemo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void checkWelcomeMessage() {
        List<String> actualDropdownValues = new ArrayList<>();
        driver.get("https://blazedemo.com/");
        String welcomeMessage = blazeDemo.getWelcomeMessage().getText();
        Assert.assertEquals(welcomeMessage, "Welcome to the Simple Travel Agency!");
        Select select = new Select(blazeDemo.getDepartureCity());
        for (WebElement element : select.getOptions()) {
            actualDropdownValues.add(element.getText());
            System.out.println(element.getText());
        }
        List<String> expectedDropdownValue = new ArrayList<>(actualDropdownValues);
        Collections.sort(expectedDropdownValue);
        Assert.assertEquals(actualDropdownValues, expectedDropdownValue);
    }

    @Test(priority = 2)
    public void checkDescendingOrderInDestinationDropdown() {
        List<String> actualDropdownValues = new ArrayList<>();
        driver.get("https://blazedemo.com/");
        Select select = new Select(blazeDemo.getDestinationCity());
        for (WebElement element : select.getOptions()) {
            actualDropdownValues.add(element.getText());
            System.out.println(element.getText());
        }
        List<String> expectedDropdownValue = new ArrayList<>(actualDropdownValues);
        Collections.sort(expectedDropdownValue, Collections.reverseOrder());
        Assert.assertEquals(actualDropdownValues, expectedDropdownValue);
    }

    @Test(priority = 3)
    public void checkFlightButtonDisabledWithoutDestinationAndDepartureFilled() {
        driver.get("https://blazedemo.com/");
        Boolean buttonDisabled = blazeDemo.getFindFlights().isEnabled();
        Assert.assertFalse(buttonDisabled);
    }
}
