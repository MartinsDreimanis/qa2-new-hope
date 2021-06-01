package homework;

import homework.pages.ArticlePage;
import homework.pages.BaseFunctions;
import homework.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestHomework {
    WebDriver driver;
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Test
    public void homeworkTaskOne() {
        BaseFunctions base = new BaseFunctions();
        HomePage home = new HomePage(base);
        base.openPage("http://tvnet.lv");
        home.clickCookiesButton();

        home.moveToArticlePage();
        //-----------------------------------------
        ArticlePage article = new ArticlePage(base);

        article.moveToCommentsPage();
    }

    @Test
    public void homeworkTaskTwo() {
        BaseFunctions base = new BaseFunctions();
        HomePage home = new HomePage(base);
        base.openPage("http://tvnet.lv");
        home.clickCookiesButton();

        System.out.println("Main article title is: " + home.getArticleName());
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
        BaseFunctions base = new BaseFunctions();
        HomePage home = new HomePage(base);
        base.openPage("http://tvnet.lv");
        home.clickCookiesButton();

        List<WebElement> articleList = home.getArticles();
        for (WebElement article:articleList) {
            System.out.println(home.articleTitleCleanup(article));
        }
    }

    @Test
    public void homeworkTaskFive()  {
        BaseFunctions base = new BaseFunctions();
        HomePage home = new HomePage(base);
        base.openPage("http://tvnet.lv");
        home.clickCookiesButton();

        List<WebElement> articleList = home.getArticles();
        for (WebElement article:articleList) {
            String articleText = home.articleTitleCleanup(article);
            String commentsText = home.getCommentsCount(article);
            int commentsCount = 0;

            if (home.hasNoComments(article) || commentsText.length() == 0){
                System.out.println(articleText + " --- " + commentsCount);
            }else{
                System.out.println(articleText + " --- " + base.removeBrackets(commentsText));
            }
        }
    }

    @Test
    public void homeworkTaskSix(){
    BaseFunctions base = new BaseFunctions();
    HomePage home = new HomePage(base);
    base.openPage("http://tvnet.lv");

        //1 List all article names on main page
        List<WebElement> articleList = home.getArticles();

        //2 Pick an article
        WebElement article = articleList.get(3);

        //3 get selected article name
        //4 get selected article comments count(if present)
        //5 move to selected article page
        //6 get article name on article page
        //7 get comments count on article page
        //8 compare article name and comments count found in main page and article page
        //9 move to comments page
        //10get article name on comments page
        //11get comments count on comments page
        //12compare article name and comments count found in article page and comments page



    }

    @AfterEach
    public void closeBrowser(){

        LOGGER.info("End of test, closing browser!");
        driver.close();
    }
}