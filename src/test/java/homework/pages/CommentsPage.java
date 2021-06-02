package homework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommentsPage {
    private final By TITLE = By.xpath(".//h1 [@class='article-headline' and @itemprop='headline name']");
    private final By COMMENTS_COUNT = By.xpath(".//span [contains (@class, 'article-comments-heading__count')]");
    private final By EXCLAMATION = By.className(".//span [@class='article-headline--exclamation']");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    BaseFunctions base;

    public CommentsPage (BaseFunctions base) {
        this.base = base;
    }

    public String getCleanTitle(){
        String title;
        WebElement element = base.getElement(TITLE);

        if (base.isEmpty(element, EXCLAMATION)){
            title = element.getText();
        }else {
            int exclamationLength = element.findElement(EXCLAMATION).getText().length();
            title = element.getText().substring(exclamationLength + 1);
        }
        if (!base.isEmpty(element, COMMENTS_COUNT)) {
            int commentsLength = element.findElement(COMMENTS_COUNT).getText().length();
            title = title.substring(0, title.length() - commentsLength -1);
        }
        return title;
    }

    public String getCommentsCount(){
        return base.getCommentsCount(COMMENTS_COUNT);
    }
}