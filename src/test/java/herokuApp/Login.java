package herokuApp;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.PO_Login;
import resources.Base;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Login extends Base {
    List<String> myData;
    PO_Login login;

    @Test(priority = 0)
    public void invalidUsernameLogin_TC1() throws IOException {
        driver.get("https://the-internet.herokuapp.com/login");
        boolean loginText = login.getHeader().isDisplayed();
        Assert.assertTrue(loginText);
        String loginText1 = login.getHeader().getText();
        Assert.assertEquals("Login Page", loginText1);
        myData = readExcel("Login");
        login.getUsername().sendKeys(readData(myData, "username"));
        login.getPassword().sendKeys(readData(myData, "password"));
        login.getSubmit().click();
        String expectedErrorMessage = login.getLoginErrorMsg().getText().trim();
        Assert.assertTrue(expectedErrorMessage.contains("Your username is invalid!"));

    }

    @Test(priority = 1)
    public void invalidPasswordLogin_TC2() {
        driver.get("https://the-internet.herokuapp.com/login");
        login.getUsername().sendKeys(readData(myData, "username"));
        login.getPassword().sendKeys(readData(myData, "password"));
        login.getSubmit().click();
        String expectedErrorMessage = login.getLoginErrorMsg().getText().trim();
        Assert.assertTrue(expectedErrorMessage.contains("Your password is invalid!"));
    }

    @Test(priority = 2)
    public void avalidLogin_TC3() {
        driver.get("https://the-internet.herokuapp.com/login");
        login.getUsername().sendKeys(readData(myData, "username"));
        login.getPassword().sendKeys(readData(myData, "password"));
        login.getSubmit().click();
        boolean welcomeMessage = login.getLoginSuccessMsg().isDisplayed();
        Assert.assertTrue(welcomeMessage);
        String BgColor = login.getLoginSuccessBGColor().getCssValue("background-color");
        Assert.assertEquals(BgColor, "rgba(93, 164, 35, 1)");
    }


    @BeforeTest
    public void beforeTest() throws IOException {
        driver_init();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        login = new PO_Login(driver);
    }
}
