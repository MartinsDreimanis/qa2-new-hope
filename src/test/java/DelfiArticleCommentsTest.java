import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DelfiArticleCommentsTest {
    private WebDriver driver;
    private final String WEBSITE_TO_TEST = "http://delfi.lv";
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final Logger LOGGER = LogManager.getLogger(DelfiArticleCommentsTest.class);
    // Home Page
    private final By HOME_PAGE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HOME_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By HOME_PAGE_ARTICLE = By.tagName("article");
    // headline__image__label - remove labels
    // Article Page
    private final By ARTICLE_PAGE_TITLE = By.xpath(".//h1[contains(@Class,'text-size-md-30')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");
    // Article Comments Page
    private final By COMMENTS_PAGE_TITLE = By.xpath(".//h1[@class= 'article-title']");
    private final By COMMENTS_PAGE_COMMENTS = By.xpath(".//span [@class='type-cnt']");
        //˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅ METHODS ˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅
    public void initiateTestForWebsite(){
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        LOGGER.info("Setting driver location");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");

        LOGGER.info("Opening browser window");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOGGER.info("Opening Home Page");
        driver.get(WEBSITE_TO_TEST);
    }
    public void waitToLoad(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void clickCookiesButton(){
        LOGGER.info("Waiting to accept cookies button...");
        waitToLoad(ACCEPT_COOKIE_BTN);
        driver.findElement(ACCEPT_COOKIE_BTN).click();
        LOGGER.info("Done!");

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
    }
    private int getCommentsCount(By locator) {
        int commentsCount = 0;

        if (!driver.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(driver.findElement(locator));
        }
        return commentsCount;
    }
    private int getCommentsCount(WebElement we, By locator) {
        int commentsCount = 0;

        if (!we.findElements(locator).isEmpty()) {
            commentsCount = removeBrackets(we.findElement(locator));
        }
        return commentsCount;
    }
    private String getCommentsCountString(WebElement we, By locator) {
        String commentsCount = "";

        if (!we.findElements(locator).isEmpty()) {
            commentsCount = we.findElement(locator).getText();
        }
        return commentsCount;
    }
    private int removeBrackets(WebElement we) {
            String commentsCountText = we.getText();
            commentsCountText = commentsCountText.substring(1, commentsCountText.length() - 1); // (36) -> 36 (String)
            return Integer.parseInt(commentsCountText); // 36 (String) -> 36 (int)
    }
    private String getElementText(By locator){
        return driver.findElement(locator).getText();
    }
    private String removeComments(WebElement element, By locator){
        String text = element.getText();

        if (!element.findElements(locator).isEmpty()) {
            String comments = getCommentsCountString(element, locator);
            text = text.substring(0, text.length() - comments.length() -1);
        }
        return text;
    }
    private String removeSpace(String text){
        return text.substring(0, text.length() -1);
    }
    private String removeLabels(){
        return "";
    }

        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ METHODS ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    @Test
    public void titleAndCommentsCountCheck() {
        initiateTestForWebsite();
        clickCookiesButton();
        //--------------------------------- HOME PAGE -----------------------------------------

        waitToLoad(HOME_PAGE_ARTICLE);
        waitToLoad(HOME_PAGE_COMMENTS);
        List<WebElement> articles = driver.findElements(HOME_PAGE_ARTICLE);
        WebElement article = articles.get(1);

        String homePageTitle = removeSpace(removeComments(article, HOME_PAGE_COMMENTS));
        int homePageCommentsCount = getCommentsCount(article, HOME_PAGE_COMMENTS);

        article.findElement(HOME_PAGE_TITLE).click();
        //-------------------------------- ARTICLE PAGE ---------------------------------------

        waitToLoad(ARTICLE_PAGE_TITLE);
        waitToLoad(ARTICLE_PAGE_COMMENTS);
        String articlePageTitle = removeSpace(getElementText(ARTICLE_PAGE_TITLE));
        int articlePageCommentsCount = getCommentsCount(ARTICLE_PAGE_COMMENTS);

        LOGGER.info("Comparing article page to home page...");
        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong count!");
        LOGGER.info("Pass!");

        driver.findElement(ARTICLE_PAGE_COMMENTS).click();
        //-------------------------------- COMMENTS PAGE --------------------------------------

        waitToLoad(COMMENTS_PAGE_TITLE);
        waitToLoad(COMMENTS_PAGE_COMMENTS);
        List<WebElement> commentsCount = driver.findElements(COMMENTS_PAGE_COMMENTS);

        String commentsPageTitle = getElementText(COMMENTS_PAGE_TITLE);
        int commentsPageCommentsCount = removeBrackets(commentsCount.get(0)) + removeBrackets(commentsCount.get(1));

        LOGGER.info("Comparing comments page to article page...");
        Assertions.assertEquals(articlePageTitle, commentsPageTitle, "Wrong title!");
        Assertions.assertEquals(articlePageCommentsCount, commentsPageCommentsCount, "Wrong count!");
        LOGGER.info("Pass!");

        LOGGER.info("Test finished successfully!");
    }
    @AfterEach
    public void closeBrowser() {
        driver.close();
    }
}