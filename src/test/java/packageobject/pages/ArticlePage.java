package packageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[contains(@Class,'text-size-md-30')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc bf;

    public ArticlePage (BaseFunc baseFunc) {
        this.bf = baseFunc;
    }

    public void waitForElements(){
        bf.waitForElements(TITLE, COMMENTS);
    }

    public String getArticleTitle() {
        return bf.removeSpace(bf.getElementText(TITLE));
    }

    public int getCommentsCount() {
        int commentsCount = 0;

        if (!bf.driver.findElements(COMMENTS).isEmpty()) {
            commentsCount = bf.removeBrackets(bf.driver.findElement(COMMENTS).getText());
        }
        return commentsCount;
    }

    public void clickComments(){
        bf.click(COMMENTS);
    }
}
