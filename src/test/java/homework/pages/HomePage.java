package homework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final By COOKIES_BTN = By.xpath(".//button[@mode ='primary']");
    private final By ARTICLE = By.xpath(".//article [@class='list-article']");
    private final By TITLE = By.className("list-article__headline");
    private final By COMMENTS = By.xpath(".//span [contains (@class, 'list-article__comment')]");
    private final By EXCLAMATION = By.className("list-article__headline--exclamation");
    private final By ARTICLE_ADDITION =  By.className("list-article__additional-headline");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunctions base;

    public HomePage (BaseFunctions base) {
        this.base = base;
    }

    public void acceptCookies(){
        LOGGER.info("Accepting cookies");
        base.click(COOKIES_BTN);
        base.scroll(0, 250);
    }

    public ArticlePage getFirstArticle (){
        LOGGER.info("Getting first article");
        base.click(base.findElement(ARTICLE));
        return new ArticlePage(base);
    }

    public String getFirstTitle () {
        return base.getText(base.findElement(ARTICLE), TITLE);
    }

    public WebElement getArticleById(int id){
        LOGGER.info("Getting article Nr." + (id + 1));
        List<WebElement> articles = base.findElements(ARTICLE);

        Assertions.assertFalse(articles.isEmpty(), "There are no articles");
        Assertions.assertTrue(articles.size() > id, "Article amount is less than id");

        return base.findElements(ARTICLE).get(id);
    }

    public List<WebElement> getTitles() {
        return base.findElements(TITLE);
    }

    public String getTitleText(int id){
        LOGGER.info("Getting title for article Nr: " + (id +1));
        WebElement titleElement = getArticleById(id).findElement(TITLE);
        String title = titleElement.getText();

        title = base.removeChildText(title, titleElement, EXCLAMATION);
        title = base.removeChildText(title, titleElement, ARTICLE_ADDITION);
        title = base.removeChildText(title, titleElement, COMMENTS);

        return title;
    }
    public String getTitleText(WebElement element){
        LOGGER.info("Cleaning up the article");
        String title = element.getText();

        title = base.removeChildText(title, element, EXCLAMATION);
        title = base.removeChildText(title, element, ARTICLE_ADDITION);
        title = base.removeChildText(title, element, COMMENTS);

        return title;
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

    public String getCommentsCount(WebElement element) {
        LOGGER.info("Getting comments count");

        if(!base.findElements(element, COMMENTS).isEmpty()) {
            return base.getText(element, COMMENTS);
        }
        return "";
    }

    public boolean hasNoComments(WebElement element) {
        return element.findElements(COMMENTS).isEmpty();
    }
}