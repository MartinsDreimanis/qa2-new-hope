package homework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final By COOKIES_BTN = By.xpath(".//button[@mode ='primary']");
    private final By ARTICLES = By.className("list-article__headline");
    private final By COMMENTS_COUNT = By.xpath(".//span [contains (@class, 'list-article__comment')]");
    private final By EXCLAMATION = By.className("list-article__headline--exclamation");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunctions base;

    public HomePage (BaseFunctions base) {
        this.base = base;
    }

    public void clickCookiesButton(){
        LOGGER.info("Handling cookies");
        base.waitToLoad(COOKIES_BTN);
        base.click(COOKIES_BTN);
    }

    public String getArticleName (){
        return base.driver.findElement(ARTICLES).getText();
    }

    public String getCleanTitle(WebElement article) {
        return base.cleanTitle(article ,EXCLAMATION, COMMENTS_COUNT);
    }

    public void clickArticle() {
        base.click(ARTICLES);
    }

    public List<WebElement> getArticles(){
        return base.driver.findElements(ARTICLES);
    }

    public String getCommentsCount(WebElement element){
        return base.getCommentsCount(element, COMMENTS_COUNT);
    }

    public boolean hasNoComments(WebElement element) {
        return base.isEmpty(element, COMMENTS_COUNT);
    }


}