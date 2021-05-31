package packageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import packageobject.pages.ArticlePage;
import packageobject.pages.BaseFunc;
import packageobject.pages.CommentsPage;
import packageobject.pages.HomePage;

import java.util.List;

public class DelfiArticleCommentsTest {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    WebDriver driver;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");
        BaseFunc baseFunc = new BaseFunc();
        baseFunc.openPage("http://delfi.lv");

        //--------------------------------- HOME PAGE -----------------------------------------
        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCookies();
        homePage.waitForElements();

        List<WebElement> articles = homePage.getArticles();
        WebElement article = articles.get(2);

        String homePageTitle = homePage.getCleanArticleTitle(article);
        int homePageCommentsCount = homePage.getCommentsCount(article);

        homePage.clickTitle(article);
        //-------------------------------- ARTICLE PAGE ---------------------------------------
        ArticlePage articlePage = new ArticlePage(baseFunc);
        articlePage.waitForElements();

        String articlePageTitle = articlePage.getArticleTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount();

        baseFunc.assertionTestCompare(homePageTitle, articlePageTitle, homePageCommentsCount, articlePageCommentsCount);

        articlePage.clickComments();
        //-------------------------------- COMMENTS PAGE --------------------------------------
        CommentsPage commentsPage = new CommentsPage(baseFunc);
        commentsPage.waitForElements();

        List<WebElement> commentsCount = commentsPage.getComments();

        String commentsPageTitle = commentsPage.getCommentsPageTitle();
        int commentsSum = commentsPage.addCommentsCount(commentsCount.get(0), commentsCount.get(1));

        baseFunc.assertionTestCompare(articlePageTitle, commentsPageTitle, articlePageCommentsCount, commentsSum);

        LOGGER.info("Test finished successfully!");
    }
    @AfterEach
    public void closeBrowser() {
        driver.close();
    }
}