package homework;

import homework.pages.ArticlePage;
import homework.pages.BaseFunctions;
import homework.pages.CommentsPage;
import homework.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestHomework {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final String URL = "http://tvnet.lv";
    private final int ARTICLE_ID = 1;

    BaseFunctions base;

    @Test
    public void homeworkTaskOne() {
        base = new BaseFunctions();
        base.openPage(URL);
        HomePage home = new HomePage(base);
        home.acceptCookies();

        ArticlePage article = home.getFirstArticle();
        article.openCommentsPage();
    }

    @Test
    public void homeworkTaskTwo() {
        base = new BaseFunctions();
        base.openPage(URL);
        HomePage home = new HomePage(base);
        home.acceptCookies();

        System.out.println("Main article title is: " + home.getFirstTitle());
    }

    @Test
    public void homeworkTaskThree() {
        /*   HERE FOR ORGANIZATION PURPOSE ONLY!
        private final By ARTICLES = By.className("list-article__headline");
        private final By ARTICLE_COMMENT_COUNT = By.className("list-article__comment");
        private final By LOGO = By.className("flex header-logo");
        private final By LANG_BTN_RUS = By.xpath(".//div[5]/a [@class ='menu-item']");
        */
    }

    @Test
    public void homeworkTaskFour() {
        base = new BaseFunctions();
        base.openPage(URL);
        HomePage home = new HomePage(base);
        home.acceptCookies();

        List<WebElement> articleList = home.getTitles();
        for (WebElement article:articleList) {
            System.out.println(home.getTitleText(article));
        }
    }

    @Test
    public void homeworkTaskFive()  {
        base.openPage(URL);
        HomePage home = new HomePage(base);
        home.acceptCookies();

        List<WebElement> articleList = home.getTitles();
        for (WebElement article:articleList) {
            String articleText = home.getTitleText(article);
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
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        base = new BaseFunctions();
        base.openPage(URL);

        //Home Page
        HomePage home = new HomePage(base);
        home.acceptCookies();

        String homePageTitle = home.getTitleText(ARTICLE_ID);
        int homePageCommentsCount = home.getCommentsCount(ARTICLE_ID);

        ArticlePage article = home.openArticle(ARTICLE_ID);

        //Article Page
        String articlePageTitle = article.getTitle();
        int articlePageCommentsCount = article.getCommentsCount();

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong count!");

        CommentsPage comments = article.openCommentsPage();

        //Comments Page
        String commentsPageTitle = comments.getTitle();
        int commentsPageCommentsCount = comments.getCommentsCount();

        Assertions.assertEquals(articlePageTitle, commentsPageTitle, "Wrong title!");
        Assertions.assertEquals(articlePageCommentsCount, commentsPageCommentsCount, "Wrong count!");
    }

    @AfterEach
    public void endTest(){
        base.endTest();
    }
}