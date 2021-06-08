package packageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final By COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunctions base;

    public HomePage(BaseFunctions base){
        this.base = base;
    }

    public void acceptCookies(){
        LOGGER.info("Accepting cookies");
        base.click(COOKIE_BTN);
        base.scroll(0, 250);
    }

    public WebElement getArticleById(int id){
        LOGGER.info("Getting article Nr." + (id + 1));
        List<WebElement> articles = base.findElements(ARTICLE);

        Assertions.assertFalse(articles.isEmpty(), "There are no articles");
        Assertions.assertTrue(articles.size() > id, "Article amount is less than id");

        return base.findElements(ARTICLE).get(id);
    }

    public String getTitle(int id){
        LOGGER.info("Getting title for article Nr: " + (id +1));
        return base.getText(getArticleById(id), TITLE);
    }

    public ArticlePage openArticle(int id){
        LOGGER.info("Opening article Nr. " + (id +1));
        base.click(getArticleById(id));
        return new ArticlePage(base);
    }

    public int getCommentsCount(int id) {
        LOGGER.info("Getting comments count for article Nr: " + (id +1));

        if(!base.findElements(getArticleById(id), COMMENTS).isEmpty()) {
            String commentsCountToParse = base.getText(getArticleById(id), COMMENTS);
            return base.removeBrackets(commentsCountToParse);
        }
        return 0;
    }
}