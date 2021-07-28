package packageobject.delfi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import packageobject.BaseFunctions;

import java.util.List;

public class CommentsPage {
        private final By TITLE = By.xpath(".//h1[@class= 'article-title']");
        private final By COMMENTS = By.xpath(".//span [@class='type-cnt']");

        private final Logger LOGGER = LogManager.getLogger(this.getClass());
        private BaseFunctions base;

        public CommentsPage(BaseFunctions base) {
                this.base = base;
        }

        public String getTitle() {
                LOGGER.info("Getting article title");
                return base.getText(TITLE);
        }

        public int getCommentsCount () {
                LOGGER.info("Getting article comments count total");

                List<WebElement> list = base.findElements(COMMENTS);
                String c1 = list.get(0).getText();
                String c2 = list.get(1).getText();

                return base.removeBrackets(c1) + base.removeBrackets(c2);
        }
}
