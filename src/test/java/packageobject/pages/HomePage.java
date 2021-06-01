package packageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final By ACCEPT_COOKIE_BTN = By.xpath(".//button[@mode = 'primary']");
    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By LABEL = By.xpath(".//div[contains(@class, 'headline__image__label')]");
    private final By SUB_TITLE = By.xpath(".//h1[contains(@class, 'text-size-14')]");
    private final By COMMENTS = By.xpath(".//a[contains(@class, 'comment-count')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc){
        this.baseFunc = baseFunc;
    }

    public void acceptCookies(){
        LOGGER.info("Waiting to accept cookies button...");
        baseFunc.waitToLoad(ACCEPT_COOKIE_BTN);
        baseFunc.click(ACCEPT_COOKIE_BTN);
        LOGGER.info("Done!");

//        JavascriptExecutor jse = (JavascriptExecutor)baseFunc.driver;
//        jse.executeScript("window.scrollBy(0,250)");
    }

    public void waitForElements(){
        baseFunc.waitForElements(ARTICLE, COMMENTS);
    }

    public List<WebElement> getArticles(){
        return baseFunc.getElementList(ARTICLE);
    }

    public String getCleanArticleTitle(WebElement element) {
        String text = element.getText();
        //remove label if present
        if(!element.findElements(LABEL).isEmpty()){
            String label = element.findElement(LABEL).getText();
            text = text.substring(label.length() + 1);
        }
        //remove comments if present
        if (!element.findElements(COMMENTS).isEmpty()) {
            String comments = element.findElement(COMMENTS).getText();
            text = text.substring(0, text.length() - comments.length() -1);
        }
        //remove sub-title if present
        if(!element.findElements(SUB_TITLE).isEmpty()){
            String subTitle = element.findElement(SUB_TITLE).getText();
            text = text.substring(0, text.length() - subTitle.length() -1);
        }
        //remove last ' ' if present and return
        return baseFunc.removeSpace(text);
    }

    public void clickTitle(WebElement element){
        baseFunc.click(element, TITLE);
    }

    public int getCommentsCount(WebElement element) {
        int commentsCount = 0;

        if (!element.findElements(COMMENTS).isEmpty()) {
            commentsCount = baseFunc.removeBrackets(element.findElement(COMMENTS).getText());
        }
        return commentsCount;
    }

}
