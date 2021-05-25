import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TestHomework {
    WebDriver driver;
    private final String WEBSITE_TO_TEST = "http://tvnet.lv";
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode ='primary']");
    private final Logger LOGGER = LogManager.getLogger(TestHomework.class);
    //Main Page
    private final By MAIN_PAGE_ARTICLES = By.className("list-article__headline");
    private final By MAIN_PAGE_COMMENTS_COUNT = By.xpath(".//span [contains (@class, 'list-article__comment')]");
    private final By MAIN_PAGE_ARTICLE_EXCLAMATION = By.className("list-article__headline--exclamation");
    //Article Page
    private final By ARTICLE_PAGE_COMMENTS_BTN = By.xpath(".//a[6]/span[@class= 'article-share__image-container social-button']");

    //------------------- METHODS START---------------------------
    public void initiateTestForWebsite(){
        LOGGER.info("Starting test!");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(WEBSITE_TO_TEST);
    }
    public void clickCookiesButton(){
        LOGGER.info("Handling cookies");
        WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        driver.findElement(ACCEPT_COOKIES_BTN).click();
    }
    public boolean checkIfEmpty(WebElement element, By locator){
        return element.findElements(locator).isEmpty();
    }
    public int removeBrackets (String text){
        return Integer.parseInt(text.substring(1, text.length() - 1));
    }
    public String articleTitleCleanup(WebElement element, By exclamationLocator, By commentsLocator){
        String title;

        if (checkIfEmpty(element, exclamationLocator)){
            title = element.getText();
        }else {
            int exclamationLength = element.findElement(exclamationLocator).getText().length();
            title = element.getText().substring(exclamationLength + 1);
        }
        if (!checkIfEmpty(element, commentsLocator)) {
            int commentsLength = element.findElement(commentsLocator).getText().length();
            title = title.substring(0, title.length() - commentsLength -1);
        }
        return title;
    }
    public String articleTitleCleanup(WebElement element, By locator){    //if only exclamation or comments need to be removed, not both
        String title = element.getText();
        String subElementText;
        int index;

        if(!checkIfEmpty(element, locator)) {
            subElementText =  element.findElement(locator).getText();
            index = element.getText().indexOf(subElementText);

            if(index != 0 ) {
                int commentsLength = subElementText.length();
                title = title.substring(0, title.length() - commentsLength -1);
            } else {
                int exclamationLength = subElementText.length();
                title = element.getText().substring(exclamationLength + 1);
            }
        }
        return title;
    }
    public String getSubElementText(WebElement element, By locator){
        String text = "";
        if(!checkIfEmpty(element, locator)) {
           text = element.findElement(locator).getText();
        }
        return text;
    }
    //--------------- METHODS END/TESTS START --------------------

    @Test
    public void homeworkTaskOne() {
        initiateTestForWebsite();
        clickCookiesButton();

        driver.findElement(MAIN_PAGE_ARTICLES).click();
        driver.findElement(ARTICLE_PAGE_COMMENTS_BTN).click();
    }

    @Test
    public void homeworkTaskTwo() {
        initiateTestForWebsite();
        clickCookiesButton();

        System.out.println("Main article title is: " + driver.findElement(MAIN_PAGE_ARTICLES).getText());
    }

    @Test
    public void homeworkTaskThree() {
        /*   DOWN HERE FOR ORGANIZATION PURPOSE ONLY!
        private final By ARTICLES = By.className("list-article__headline");
        private final By ARTICLE_COMMENT_COUNT = By.className("list-article__comment");
        private final By LOGO = By.className("flex header-logo");
        private final By LANG_BTN_RUS = By.xpath(".//div[5]/a [@class ='menu-item']");
        */
    }

    @Test
    public void homeworkTaskFour() {
        initiateTestForWebsite();
        clickCookiesButton();

        List<WebElement> articleList = driver.findElements(MAIN_PAGE_ARTICLES);
        for (WebElement article:articleList) {
            System.out.println(articleTitleCleanup(article, MAIN_PAGE_ARTICLE_EXCLAMATION, MAIN_PAGE_COMMENTS_COUNT));
        }
    }

    @Test
    public void homeworkTaskFive()  {
        initiateTestForWebsite();
        clickCookiesButton();

        List<WebElement> articleList = driver.findElements(MAIN_PAGE_ARTICLES);
        for (WebElement article:articleList) {
            String articleText = articleTitleCleanup(article, MAIN_PAGE_ARTICLE_EXCLAMATION, MAIN_PAGE_COMMENTS_COUNT);
            String commentsText = getSubElementText(article, MAIN_PAGE_COMMENTS_COUNT);
            int commentsCount = 0;

            if (checkIfEmpty(article, MAIN_PAGE_COMMENTS_COUNT) || commentsText.length() == 0){
                System.out.println(articleText + " --- " + commentsCount);
            }else{
                System.out.println(articleText + " --- " + removeBrackets(commentsText));
            }
        }
    }
    //--------------------- TESTS END ----------------------------

    @AfterEach
    public void closeBrowser(){
        LOGGER.info("End of test, closing browser!");
        driver.close();
    }
}
//homework - add logs