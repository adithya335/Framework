package repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Login {

    public WebDriver driver;
    private By header = By.xpath("//h2");
    private By inputUsername = By.xpath("//input[@id='username']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonSubmit = By.xpath("//button[@type='submit']");
    private By loginErrorMsg = By.xpath("//div[@class='flash error']");
    private By loginSuccessMsg = By.xpath("//h4[text()='Welcome to the Secure Area. When you are done click logout below.']");
    private By loginSuccessBGColor = By.xpath("//div[@class='flash success']");

    public PO_Login(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHeader() {
        return driver.findElement(header);
    }

    public WebElement getUsername() {
        return driver.findElement(inputUsername);
    }

    public WebElement getPassword() {
        return driver.findElement(inputPassword);
    }

    public WebElement getSubmit() {
        return driver.findElement(buttonSubmit);
    }

    public WebElement getLoginErrorMsg() {
        return driver.findElement(loginErrorMsg);
    }

    public WebElement getLoginSuccessMsg() {
        return driver.findElement(loginSuccessMsg);
    }

    public WebElement getLoginSuccessBGColor() {
        return driver.findElement(loginSuccessBGColor);
    }

}
