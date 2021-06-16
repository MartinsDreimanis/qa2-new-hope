package homework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommentsPage {
    private final By TITLE = By.xpath(".//h1 [@class='article-headline' and @itemprop='headline name']");
    private final By COMMENTS = By.xpath(".//span [contains (@class, 'article-comments-heading__count')]");
    private final By EXCLAMATION = By.className(".//span [@class='article-headline--exclamation']");
    private final By ARTICLE_ADDITION =  By.className("article-headline--additional");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    BaseFunctions base;

    public CommentsPage (BaseFunctions base) {
        this.base = base;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        WebElement element = base.driver.findElement(TITLE);
        String title =  element.getText();

        title = base.removeChildText(title, element, EXCLAMATION);
        title = base.removeChildText(title, element, ARTICLE_ADDITION);

        return title;
    }

    public int getCommentsCount () {
        LOGGER.info("Getting article comments count");

        if(!base.findElements(COMMENTS).isEmpty()){
            return Integer.parseInt(base.getText(COMMENTS));
        }
        return 0;
    }
}