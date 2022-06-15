package repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_BlazeDemo {
    public WebDriver driver;
    private By welcomeMessage = By.xpath("//h1");
    private By departureCity = By.xpath("//select[@name='fromPort']");
    private By destinationCity = By.xpath("//select[@name='toPort']");
    private By findFlights = By.xpath("//input[@value='Find Flights']");

    public PO_BlazeDemo(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getWelcomeMessage() {
        return driver.findElement(welcomeMessage);
    }

    public WebElement getDepartureCity() {
        return driver.findElement(departureCity);
    }

    public WebElement getDestinationCity() {
        return driver.findElement(destinationCity);
    }

    public WebElement getFindFlights() {
        return driver.findElement(findFlights);
    }
}
