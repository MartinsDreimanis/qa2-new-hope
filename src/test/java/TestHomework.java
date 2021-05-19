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
    public boolean checkIfNoCommentsExist(List<WebElement> list, int index){
        boolean value = list.get(index).findElements(COMMENTS_COUNT).isEmpty();
        return value;
    }
    public String articleTitleCleanup(List<WebElement> list, int index){
        String clearExclamation = list.get(index).findElements(ARTICLE_EXCLAMATION).isEmpty() ? list.get(index).getText()
                : list.get(index).getText().substring(list.get(index).findElement(ARTICLE_EXCLAMATION).getText().length() +1);
        String clearComments = checkIfNoCommentsExist(list, index) ? clearExclamation
                : clearExclamation.substring(0, clearExclamation.length() - list.get(index).findElement(COMMENTS_COUNT).getText().length() -1);
        return clearComments;
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
        for (int i = 0; i < articleList.size(); i++) {
            System.out.println((i+1) + " " + articleTitleCleanup(articleList, i));

            //System.out.println(articleList.get(110).findElement(COMMENTS_COUNT).getText().isEmpty());
        }
    }

    @Test
    public void homeworkTaskFive()  {
        initiateTestForWebsite();
        clickCookiesButton();

        List<WebElement> articleList = driver.findElements(ARTICLES);


        for (int i = 0; i < articleList.size(); i++) {
            String articleText = articleTitleCleanup(articleList, i);
            int commentsCount = 0;

            System.out.print( i + 1 + articleText);
            commentsCount = checkIfNoCommentsExist(articleList, i) || articleList.get(i).findElement(COMMENTS_COUNT).getText().length() == 0 ? 0
            :Integer.parseInt(articleList.get(i).findElement(COMMENTS_COUNT).getText().substring(1, articleList.get(i).findElement(COMMENTS_COUNT).getText().length() - 1));
            System.out.println(" --- " + commentsCount);
        }

    }
    @Test
    public void Extra()  {
        initiateTestForWebsite();
        clickCookiesButton();

        List<WebElement> articleList = driver.findElements(ARTICLES);


        for (int i = 0; i < articleList.size(); i++) {
            String articleText = articleTitleCleanup(articleList, i);

            if (checkIfNoCommentsExist(articleList, i)) {
                System.out.println((i +1) + "Article: \"" + articleText + "\" has no comments!");
            }else {
                WebElement childElement = articleList.get(i).findElement(COMMENTS_COUNT);
                String childContent = childElement.getText();
                System.out.println((i +1) + "Article: \"" + articleText + "\" has " + childContent.substring(1, childContent.length() -1) + " comments!");
            }
        }

    }
    //--------------------- TESTS END ----------------------------
    @AfterEach
    public void closeBrowser(){ driver.close(); }
}
//homework - add more methods
//homework - add logs