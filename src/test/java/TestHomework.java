import org.junit.jupiter.api.AfterEach;
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
    private final By ARTICLES = By.className("list-article__headline");
    private final By ARTICLE_COMMENTS_BTN = By.xpath(".//a[6]/span[@class= 'article-share__image-container social-button']");

    WebDriver driver;

    //------------------- METHODS START---------------------------
    public void initiateTestForWebsite(){
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://tvnet.lv");
    }
    public void clickCookiesButton(){
        WebDriverWait wait = new WebDriverWait(driver, 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCEPT_COOKIES_BTN));
        driver.findElement(ACCEPT_COOKIES_BTN).click();
    }
    //------------------- METHODS END ----------------------------

    @Test
    public void homeworkTaskOne() {
        initiateTestForWebsite();
        clickCookiesButton();

        driver.findElement(ARTICLES).click();            //Interact with main headline
        driver.findElement(ARTICLE_COMMENTS_BTN).click();  //Move to comments section
    }

    @Test
    public void homeworkTaskTwo() {
        initiateTestForWebsite();
        clickCookiesButton();

        System.out.println("Main headline title is: " + driver.findElement(ARTICLES).getText());
    }

    @Test
    public void homeworkTaskThree() {
        /*
        private final By HEADINGS = By.className("list-article__headline");
        private final By COMMENTS_COUNT = By.className("list-article__comment");
        private final By LOGO = By.className("flex header-logo");
        private final By LANG_BTN_RUS = By.xpath(".//div[5]/a [@class ='menu-item']");
        */
    }

    @Test
    public void homeworkTaskFour() {
        initiateTestForWebsite();
        clickCookiesButton();

        List<WebElement> headingList = driver.findElements(ARTICLES);                                                                                    //gather(list) all headings

        /*
        //----------------- PRINT WITH COMMENTS (plain) ------------------
        for (int i = 0; i < headingList.size(); i++) {                                                                                                   //print all headings
            String headingText = headingList.get(i).getText() ;
            System.out.print(headingText);

            if (headingText.charAt(headingText.length() -1) != ')') {                                                                                    //handle strings with no comments
                System.out.print(" (0)");
            }
            System.out.println();
        }*/
        /*//---------------- PRINT WITHOUT COMMENTS ----------------
        for (int i = 0; i < headingList.size(); i++) {
            String headingText = headingList.get(i).getText() ;

            if (headingList.get(i).findElements(By.xpath(".//span [contains (@class, 'list-article__comment')]")).isEmpty) {
                System.out.println(headingText);                                                                                                        //if no comments - print as is
            } else {
                WebElement childElement = headingList.get(i).findElement(By.xpath(".//span [contains (@class, 'list-article__comment')]"));             //check if comments exist
                String childContent = childElement.getText();                                                                                           //get content of the current child (comments number)
                StringBuilder sb = new StringBuilder(headingText);
                sb.setLength (sb.length() - childContent.length());                                                                                     //cut off comments
                System.out.println(sb);
            }
            }

        *///---------------- PRINT WITH COMMENTS (formated)----------------
        for (int i = 0; i < headingList.size(); i++) {
            String headingText = headingList.get(i).getText();                                                                                          //get text contained inside current element
            //Boolean childIsPresent = headingList.get(i).findElements(By.xpath(".//span [contains (@class, 'list-article__comment')]")).size() > 0;    //boolean for checking if heading has comments (got integrated in to if, leaving for reference)

            if (headingList.get(i).findElements(By.xpath(".//span [contains (@class, 'list-article__comment')]")).isEmpty()) {
                System.out.println("Heading: \"" + headingText + "\" has no comments!");                                                                //if no comments - print as is
            } else {
                WebElement childElement = headingList.get(i).findElement(By.xpath(".//span [contains(@class, 'list-article__comment')]"));              //check if comments exist
                String childContent = childElement.getText();                                                                                           //"single out" the count of comments and brackets
                StringBuilder sb = new StringBuilder(headingText);
                sb.setLength (sb.length() - childContent.length() -1);                                                                                  //cut off comments and the space before comments
                System.out.println("Heading: \"" + sb + "\" has " + childContent.replace("(", "").replace(")", "") + " comments!");
            }
        }
    }

    @Test
    public void homeworkTaskFive()  {

    }
    @AfterEach
    public void closeBrowser(){
        driver.close();
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
