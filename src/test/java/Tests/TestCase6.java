package Tests;

import Objects.AutomationPracticePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//hovering over specified menu and searching dropdown for submenu item to click on
public class TestCase6 {
    static WebDriver driver;
    static String menu = "women";
    static String subMenu = "evening dresses";

    @BeforeTest
    public void PageSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
    }

    @Test
    public static void hoverAndSelect() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AutomationPracticePage page = new AutomationPracticePage(driver);

        page.HoverAndClick(menu, subMenu);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }


}