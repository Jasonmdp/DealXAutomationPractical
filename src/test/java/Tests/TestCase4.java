package Tests;

import Objects.AutomationPracticePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//Sign in to the website using a username and password stored as a global variable.
public class TestCase4 {
    String username = "jasonmdp@gmail.com";
    String password = "123password";
    WebDriver driver;

    @BeforeTest
    public void PageSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
    }
    @Test
    public void login() throws InterruptedException {
        AutomationPracticePage page = new AutomationPracticePage(driver);

        page.clickSignInLink();
        Thread.sleep(3000);
        page.userName(username);
        page.passWord(password);
        page.clickSignInBtn();
        //System.out.println("Logged in successfully");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
