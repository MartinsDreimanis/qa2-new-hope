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
        driver.get("http://tvnet.lv");
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
        int excEleLength = element.findElement(exclamationLocator).getText().length();
        int comEleLength = element.findElement(commentsLocator).getText().length();

        String title = checkIfEmpty(element, exclamationLocator) ? element.getText()
                : element.getText().substring(excEleLength +1);
        title = checkIfEmpty(element, commentsLocator) ? title
                : title.substring(0, title.length() - comEleLength -1);
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
            System.out.println(articleTitleCleanup(article));
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
            System.out.println(articleText +" --- " + commentsCount);
        }
    }
    @Test
    public void Extra()  {
        initiateTestForWebsite();
        clickCookiesButton();

        List<WebElement> articleList = driver.findElements(ARTICLES);


        for (WebElement article:articleList) {
            String articleText = articleTitleCleanup(article);
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