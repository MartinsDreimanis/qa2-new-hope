package homework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseFunctions {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    WebDriver driver;
    WebDriverWait wait;

    public BaseFunctions(){
        LOGGER.info("Starting web browser");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void openPage (String url) {
        driver.get(url);
    }

    public void waitToLoad(By locator){
        wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void click(By locator) {
        LOGGER.info("Clicking on element by: " + locator);
        driver.findElement(locator).click();
    }

    public void click(WebElement element, By locator) {
        LOGGER.info("Clicking on element by: " + locator);
        element.findElement(locator).click();
    }

    public boolean isEmpty(WebElement element, By locator){
        return element.findElements(locator).isEmpty();
    }

    public int removeBrackets (String text){
        return Integer.parseInt(text.substring(1, text.length() - 1));
    }
}
