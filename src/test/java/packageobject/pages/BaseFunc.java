package packageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.List;

public class BaseFunc {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    WebDriver driver;
    WebDriverWait wait;

    public BaseFunc() {
        LOGGER.info("Starting web browser");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void openPage(String url) {
        LOGGER.info("Opening page by URL " + url);

        if(!url.startsWith("http://") && !url.startsWith("https://")){
            url = "http://" + url;
        }
        driver.get(url);
    }

    public void waitToLoad(By locator){
        wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    //makes sure elements have loaded so the test doesn't choke
    public void waitForElements(By articleNameLocator, By commentsCountLocator){
        LOGGER.info("Waiting for related elements to load...");
        waitToLoad(articleNameLocator);
        waitToLoad(commentsCountLocator);
        LOGGER.info("Loaded!");
    }

    public void click(By locator) {
        LOGGER.info("Clicking on element by: " + locator);
        driver.findElement(locator).click();
    }

    public void click(WebElement element, By locator) {
        LOGGER.info("Clicking on element by: " + locator);
        element.findElement(locator).click();
    }

    public int removeBrackets(WebElement element) {
        String text = element.getText();
        return Integer.parseInt(text.substring(1, text.length() - 1));
    }

    public int removeBrackets (String text) {
        return Integer.parseInt(text.substring(1, text.length() - 1));
    }

    public String getElementText(By locator){
        return driver.findElement(locator).getText();
    }

    public List<WebElement> getElementList (By locator) {
            return driver.findElements(locator);
    }

    public String removeSpace(String text) {
        if(text.charAt((text.length() -1)) == ' '){
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    public void assertionTestCompare(String string1, String string2, Integer int1, Integer int2) {
        LOGGER.info("Comparing page titles and comment counts...");
        Assertions.assertEquals(string1, string2, "Wrong title!");
        Assertions.assertEquals(int1, int2, "Wrong count!");
        LOGGER.info("Pass!");
    }
}
