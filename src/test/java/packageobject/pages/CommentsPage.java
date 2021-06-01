package packageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommentsPage {
        private final By TITLE = By.xpath(".//h1[@class= 'article-title']");
        private final By COMMENTS = By.xpath(".//span [@class='type-cnt']");

        private final Logger LOGGER = LogManager.getLogger(this.getClass());
        private BaseFunc baseFunc;

        public CommentsPage(BaseFunc baseFunc) {
                this.baseFunc = baseFunc;
        }

        public String getCommentsPageTitle() {
                return baseFunc.getElementText(TITLE);
        }

        public void waitForElements() {
                baseFunc.waitForElements(TITLE, COMMENTS);
        }

        public List<WebElement> getComments() {
                return baseFunc.getElementList(COMMENTS);
        }

        public int addCommentsCount (WebElement element1, WebElement element2) {
                return baseFunc.removeBrackets(element1) + baseFunc.removeBrackets(element2);
        }
}
