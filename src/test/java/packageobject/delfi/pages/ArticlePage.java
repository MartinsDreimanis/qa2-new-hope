package packageobject.delfi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import packageobject.BaseFunctions;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1[contains(@Class,'text-size-md-30')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'text-size-md-28')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunctions base;

    public ArticlePage (BaseFunctions base) {
        this.base = base;
    }

    public String getTitle() {
        LOGGER.info("Getting article title");
        return base.getText(TITLE);
    }

    public int getCommentsCount() {
        LOGGER.info("Getting article comments count");

        if(!base.findElements(COMMENTS).isEmpty()){
        String commentsCountToParse = base.getText(COMMENTS);
        return base.removeBrackets(commentsCountToParse);
        }
        return 0;
    }

    public CommentsPage openCommentsPage(){
        LOGGER.info("Opening article comments page");
        base.click(COMMENTS);
        return new CommentsPage(base);
    }
}

