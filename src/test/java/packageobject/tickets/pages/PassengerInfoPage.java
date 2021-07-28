package packageobject.tickets.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import packageobject.BaseFunctions;

import java.util.List;

public class PassengerInfoPage {
    private final By AIRPORT_NAME = By.xpath(".//span[@class='bTxt']");
    private BaseFunctions baseFunc;

    public PassengerInfoPage(BaseFunctions baseFunc) {
        this.baseFunc = baseFunc;
    }

    public List<WebElement> getAirports() {
        return baseFunc.findElements(AIRPORT_NAME);
    }
}
