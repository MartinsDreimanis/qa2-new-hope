import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class TestHomework {
    private final By ACCEPT_COOKIES_BTN = By.xpath(".//button[@mode ='primary']");
    private final By HEADINGS = By.className("list-article__headline");
    private final By STORY_COMMENTS_BTN = By.xpath(".//a[6]/span [@class= 'article-share__image-container social-button']");
    //private final By STORY_COMMENTS_BTN = By.className("article-share__item article-share__item--comments article-share__item-with-count");

    // 3rd homework
    //private final By HEADINGS = By.className("list-article__headline");
    private final By COMMENTS_COUNT = By.className("list-article__comment section-font-color");
    private final By LOGO = By.className("flex header-logo flex--align-items-center");
    private final By LANG_BTN_RUS = By.xpath("//div[3]/div[1]/div[5]/a [@class ='menu-item']");
                                                            //idk if using absolute is good(probably not), but it doesn't appear to change, and I'm too dum do it in another way

    @Test
    public void testHomeworkOne() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("https://tvnet.lv");

        //Accept cookies
        WebDriverWait wait = new WebDriverWait(browserWindow, 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();

        //Interact with main headline
        browserWindow.findElement(HEADINGS).click();
        //Move to comments section
        browserWindow.findElement(STORY_COMMENTS_BTN).click();
    }

    @Test
    public void testHomeworkTwo() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");

        //Accept cookies
        WebDriverWait wait = new WebDriverWait(browserWindow, 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();

        //print text of the element
        //String text = browserWindow.findElement(HEADINGS).getText();
        System.out.println("Main headline title is: " + browserWindow.findElement(HEADINGS).getText());
    }

    @Test
    public void testHomeworkThree() {
       //at variables ^^^
    }

    @Test
    public void testHomeworkFour() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        WebDriver browserWindow = new ChromeDriver();
        browserWindow.manage().window().maximize();
        browserWindow.get("http://tvnet.lv");

        //Accept cookies
        WebDriverWait wait = new WebDriverWait(browserWindow, 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        browserWindow.findElement(ACCEPT_COOKIES_BTN).click();

        //list all headings
        List<WebElement> headingList = browserWindow.findElements(HEADINGS);

        //print all headings
        for (int i = 0; i < headingList.size(); i++) {
            String headingText = headingList.get(i).getText() ;
            System.out.print(headingText);

            //handle "no comments"
            if (headingText.charAt(headingText.length() -1) != ')') {
                System.out.print(" (0)");
            }
            System.out.println();
        }
    }
}

/*  QA2 Homework 1
  #1:
        open http://tvnet.lv
        open first headline                     .//div[1]/div/div[1]/article/div/a[1]/span [@class = 'list-article__headline'] - works by class, how to specify?
        move to commentary page                 .//div[1]/div/div[1]/div[1]/div/a[6] [@class ='article-share__item article-share__item--comments article-share__item-with-count'] works by class,
  #2:                                                                                                                                                           xpath doesn't seem to change, still specify w/o absolute?
        open http://tvnet.lv
        sysout of the name of the 1st headline  sysout smth smth
  #3:
        create and write locators for:
        1: all headlines                        .// [@class = 'list-article__headline']
        2: all locators with commentary count   list-article__comment section-font-color
        3: tvnet logo                           flex header-logo flex--align-items-center
        4: link for language change to RUS      .//div[3]/div[1]/div[5]/a [@class ='menu-item']
  #4:
        sysout all headlines on main page       for...
                                                sout browserWindow.findElement(HEADINGS).getText()
                                                + (0) comments ? (the missing 5th)
*/
