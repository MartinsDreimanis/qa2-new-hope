package homework;

import homework.pages.ArticlePage;
import homework.pages.BaseFunctions;
import homework.pages.CommentsPage;
import homework.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestHomework {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final String URL = "http://tvnet.lv";
    BaseFunctions base = new BaseFunctions();
    HomePage home = new HomePage(base);
    ArticlePage articlePage = new ArticlePage(base);
    CommentsPage comments = new CommentsPage(base);

    @Test
    public void homeworkTaskOne() {
        base.openPage(URL);
        home.clickCookiesButton();

        home.clickArticle();
        //-----------------------------------------
        ArticlePage article = new ArticlePage(base);

        article.clickComments();
    }

    @Test
    public void homeworkTaskTwo() {
        base.openPage(URL);
        home.clickCookiesButton();

        System.out.println("Main article title is: " + home.getArticleName());
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
        base.openPage(URL);
        home.clickCookiesButton();

        List<WebElement> articleList = home.getArticles();
        for (WebElement article:articleList) {
            System.out.println(home.getCleanTitle(article));
        }
    }

    @Test
    public void homeworkTaskFive()  {
        base.openPage(URL);
        home.clickCookiesButton();

        List<WebElement> articleList = home.getArticles();
        for (WebElement article:articleList) {
            String articleText = home.getCleanTitle(article);
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
        base.openPage(URL);
        home.clickCookiesButton();

        //Home Page
        List<WebElement> articleList = home.getArticles();
        WebElement article = articleList.get(2);

        String homeArticleName = home.getCleanTitle(article);
        int homeCommentsCount = base.removeBrackets(home.getCommentsCount(article));

        article.click();
        //Article Page

        String articleArticleName = articlePage.getCleanTitle();
        int articleCommentsCount = base.asInt(articlePage.getCommentsCount());

        base.titleCommentTest(homeArticleName, articleArticleName, homeCommentsCount, articleCommentsCount);

        articlePage.clickComments();
        //Comments Page

        String commentsArticleName = comments.getCleanTitle();
        int commentsCommentCount = base.asInt(comments.getCommentsCount());

        base.titleCommentTest(articleArticleName, commentsArticleName, articleCommentsCount, commentsCommentCount);
    }

    @AfterEach
    public void endTest(){
        base.endTest();
    }
}