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
    private final By ARTICLE_EXCLAMATION = By.className("list-article__headline--exclamation");

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

    public String articleTitleCleanup(WebElement element){
        String title;

        if (base.isEmpty(element, ARTICLE_EXCLAMATION)){
            title = element.getText();
        }else {
            int exclamationLength = element.findElement(ARTICLE_EXCLAMATION).getText().length();
            title = element.getText().substring(exclamationLength + 1);
        }
        if (!base.isEmpty(element, COMMENTS_COUNT)) {
            int commentsLength = element.findElement(COMMENTS_COUNT).getText().length();
            title = title.substring(0, title.length() - commentsLength -1);
        }
        return title;
    }

    public void moveToArticlePage () {
        base.click(ARTICLES);
    }

    public List<WebElement> getArticles(){
        return base.driver.findElements(ARTICLES);
    }

    public boolean hasNoComments(WebElement element){
        return element.findElements(COMMENTS_COUNT).isEmpty();
    }

    public String getCommentsCount(WebElement element){
        String text = "";
        if(!hasNoComments(element)) {
            text = element.findElement(COMMENTS_COUNT).getText();
        }
        return text;
    }
}