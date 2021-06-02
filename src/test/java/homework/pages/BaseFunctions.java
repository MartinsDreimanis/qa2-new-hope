package homework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
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

    //General functions

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

    public boolean isEmpty(By locator){
        return driver.findElements(locator).isEmpty();
    }

    public boolean isEmpty(WebElement element, By locator){
        return element.findElements(locator).isEmpty();
    }

    public WebElement getElement (By locator) {
        return driver.findElement(locator);
    }

    //Title related functions
    public String cleanTitle(WebElement element, By excLocator, By comLocator){
        String title;

        if (isEmpty(element, excLocator)){
            title = element.getText();
        }else {
            int exclamationLength = element.findElement(excLocator).getText().length();
            title = element.getText().substring(exclamationLength + 1);
        }
        if (!isEmpty(element, comLocator)) {
            int commentsLength = element.findElement(comLocator).getText().length();
            title = title.substring(0, title.length() - commentsLength -1);
        }
        return title;
    }

    public String cleanTitle(By eleLocator, By excLocator){
        String title;
        WebElement element = getElement(eleLocator);

        if (isEmpty(element, excLocator)){
            title = element.getText();
        }else {
            int exclamationLength = element.findElement(excLocator).getText().length();
            title = element.getText().substring(exclamationLength + 1);
        }
        return title;
    }

    //Comments related functions
    public String getCommentsCount(WebElement element, By locator){
        String text = "";
        if(!isEmpty(element, locator)) {
            text = element.findElement(locator).getText();
        } else {
            LOGGER.info("Title has no comments");
        }
        return text;
    }

    public String getCommentsCount(By locator){
        String text = "";
        if(!isEmpty(locator)) {
            text = driver.findElement(locator).getText();
        }
        return text;
    }

    public int asInt(String text){
        if (text.length() != 0) {
            return Integer.parseInt(text);
        }
        return 0;
    }

    public int removeBrackets (String text){
        if(text.length() != 0) {
            return asInt(text.substring(1, text.length() - 1));
        }
        return 0;
    }

    //Test related
    public void titleCommentTest (String title1, String title2, Integer comments1, Integer comments2){
        if (comments1 != 0 && comments2 != 0) {
            LOGGER.info("Comparing page titles and comment counts...");
            Assertions.assertEquals(title1, title2, "Titles do not match!");
            Assertions.assertEquals(comments1, comments2, "Comment count does not match!");
            LOGGER.info("Pass!");
        } else {
            LOGGER.info("Comparing page titles...");
            Assertions.assertEquals(title1, title2, "Titles do not match!");
            LOGGER.info("Pass!");
        }
    }

    public void endTest(){
        LOGGER.info("End of test, closing browser!");
        driver.quit();
    }
}