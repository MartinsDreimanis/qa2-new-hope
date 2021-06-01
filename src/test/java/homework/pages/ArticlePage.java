package homework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticlePage {
    private final By ARTICLE_PAGE_COMMENTS_BTN = By.xpath(".//a[6]/span[@class= 'article-share__image-container social-button']");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunctions base;

    public ArticlePage (BaseFunctions base) {
        this.base=base;
    }
    public void moveToCommentsPage(){
        base.click(ARTICLE_PAGE_COMMENTS_BTN);
    }
}
