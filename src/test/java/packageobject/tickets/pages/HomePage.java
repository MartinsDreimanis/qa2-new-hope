package packageobject.tickets.pages;

import org.openqa.selenium.By;
import packageobject.BaseFunctions;

public class HomePage {
    private final By FROM = By.id("afrom");
    private final By TO = By.id("bfrom");
    private final By GO_BTN = By.xpath(".//span[@class = 'gogogo']");

    public HomePage(BaseFunctions baseFunc) {
        this.baseFunc = baseFunc;
    }

    private packageobject.BaseFunctions baseFunc;



    public PassengerInfoPage selectAirports(String from, String to) {
        baseFunc.select(FROM, from);
        baseFunc.select(TO, to);
        baseFunc.click(GO_BTN);

        return new PassengerInfoPage(baseFunc);
    }
}
