package packageobject.delfi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import packageobject.delfi.pages.ArticlePage;
import packageobject.BaseFunctions;
import packageobject.delfi.pages.CommentsPage;
import packageobject.delfi.pages.HomePage;

public class DelfiArticleCommentsTest {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final int ARTICLE_ID = 5;
    BaseFunctions base;

    @Test
    public void titleAndCommentsCountCheck() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        base = new BaseFunctions();
        base.openPage("http://delfi.lv");

        //--------------------------------- HOME PAGE -----------------------------------------
        HomePage home = new HomePage(base);
        home.acceptCookies();

        String homePageTitle = home.getTitle(ARTICLE_ID);
        int homePageCommentsCount = home.getCommentsCount(ARTICLE_ID);

        ArticlePage article = home.openArticle(ARTICLE_ID);

        //-------------------------------- ARTICLE PAGE ---------------------------------------
        String articlePageTitle = article.getTitle();
        int articlePageCommentsCount = article.getCommentsCount();

        Assertions.assertEquals(homePageTitle, articlePageTitle, "Wrong title!");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong count!");

        CommentsPage comments = article.openCommentsPage();

        //-------------------------------- COMMENTS PAGE --------------------------------------
        String commentsPageTitle = comments.getTitle();
        int commentsPageCommentsCount = comments.getCommentsCount();

        Assertions.assertEquals(articlePageTitle, commentsPageTitle, "Wrong title!");
        Assertions.assertEquals(articlePageCommentsCount, commentsPageCommentsCount, "Wrong count!");
    }
    @AfterEach
    public void endTest() {
        base.endTest();
    }
}