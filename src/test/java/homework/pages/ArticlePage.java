package homework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticlePage {
    private final By TITLE = By.xpath(".//h1 [@class='article-headline' and @itemprop='headline name']");
    private final By EXCLAMATION = By.className(".//span [@class='article-headline--exclamation']");
    private final By COMMENTS_ICON = By.xpath(".//a[6]/span[@class= 'article-share__image-container social-button']");
    private final By COMMENTS_COUNT = By.xpath(".//span [contains (@class, 'article-share__item--comments')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunctions base;

    public ArticlePage (BaseFunctions base) {
        this.base=base;
    }

    public String getCleanTitle(){
        return base.cleanTitle(TITLE, EXCLAMATION);
    }

    public String getCommentsCount(){
        return base.getCommentsCount(COMMENTS_COUNT);
    }

    public void clickComments(){
        base.click(COMMENTS_ICON);
    }
}