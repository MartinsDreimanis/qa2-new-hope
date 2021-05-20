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
    private final String WEBSITE_TO_TEST = "http://tvnet.lv";
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode ='primary']");
    private final By ARTICLES = By.className("list-article__headline");
    private final By ARTICLE_COMMENTS_BTN = By.xpath(".//a[6]/span[@class= 'article-share__image-container social-button']");
    private final By COMMENTS_COUNT = By.xpath(".//span [contains (@class, 'list-article__comment')]");
    private final By ARTICLE_EXCLAMATION = By.className("list-article__headline--exclamation");

    WebDriver driver;
    //------------------- METHODS START---------------------------
    public void initiateTestForWebsite(){
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(WEBSITE_TO_TEST);
    }
    public void clickCookiesButton(){
        WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        driver.findElement(ACCEPT_COOKIES_BTN).click();
    }
    public boolean checkIfEmpty(WebElement element, By locator){
        return element.findElements(locator).isEmpty();
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

        if(!checkIfEmpty(element, locator)) {       //determine if the element is at the start or the end(elsewhere) of the string
            subElementText =  element.findElement(locator).getText();
            index = element.getText().indexOf(subElementText);

            if(index != 0 ) {                            //deal with the element according to index
                int commentsLength = subElementText.length();               //remove comments
                title = title.substring(0, title.length() - commentsLength -1);
            } else {
                int exclamationLength = subElementText.length();            //remove exclamation
                title = element.getText().substring(exclamationLength + 1);
            }
        }
//        if (checkIfEmpty(element, locator)){
//            title = element.getText();
//        }else {
//            int exclamationLength = element.findElement(locator).getText().length();
//            title = element.getText().substring(exclamationLength + 1);
//        }
//
//        if (!checkIfEmpty(element, locator)) {
//            int commentsLength = element.findElement(locator).getText().length();
//            title = title.substring(0, title.length() - commentsLength -1);
//        }
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

        driver.findElement(ARTICLES).click();            //Interact with main article
        driver.findElement(ARTICLE_COMMENTS_BTN).click();  //Move to comments section
    }

    @Test
    public void homeworkTaskTwo() {
        initiateTestForWebsite();
        clickCookiesButton();

        System.out.println("Main article title is: " + driver.findElement(ARTICLES).getText());
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

        List<WebElement> articleList = driver.findElements(ARTICLES);
        for (WebElement article:articleList) {
            System.out.println(articleTitleCleanup(article, ARTICLE_EXCLAMATION, COMMENTS_COUNT));
        }
    }

    @Test
    public void homeworkTaskFive()  {
        initiateTestForWebsite();
        clickCookiesButton();

        List<WebElement> articleList = driver.findElements(ARTICLES);
        for (WebElement article:articleList) {
            String articleText = articleTitleCleanup(article, ARTICLE_EXCLAMATION, COMMENTS_COUNT);
            String commentsText = getSubElementText(article, COMMENTS_COUNT);
            int commentsCount;

            if (checkIfEmpty(article, COMMENTS_COUNT) || commentsText.length() == 0){
                commentsCount =  0;
            }else{
                commentsCount = Integer.parseInt(commentsText.substring(1, commentsText.length() - 1));
            }
            System.out.println(articleText + " --- " + commentsCount);
        }
    }

    @Test
    public void Extra()  {
        initiateTestForWebsite();
        clickCookiesButton();

        List<WebElement> articleList = driver.findElements(ARTICLES);
        for (WebElement article:articleList) {
            String articleText = articleTitleCleanup(article, ARTICLE_EXCLAMATION, COMMENTS_COUNT);
            int num = 0;

            if (checkIfEmpty(article, COMMENTS_COUNT)) {
                System.out.println(num + 1 +" Article: \"" + articleText + "\" has no comments!");
            }else {
                String commentsText = article.findElement(COMMENTS_COUNT).getText();
                System.out.println(num + 1 +" Article: \"" + articleText + "\" has " + commentsText.substring(1, commentsText.length() -1) + " comments!");
            }
        }

    }
    //--------------------- TESTS END ----------------------------
    @AfterEach
    public void closeBrowser(){ driver.close(); }
}
//homework - add more methods
//homework - add logs