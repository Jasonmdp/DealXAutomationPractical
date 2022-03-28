package Tests;


import Objects.AutomationPracticePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//perform a search and verify the first result matches your search criteria.
public class TestCase1 {
    WebDriver driver;

    @BeforeTest
    public void PageSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void getResult()
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AutomationPracticePage page = new AutomationPracticePage(driver);
        //declare search word and result variable
        String searchWord = "dress";
        String searchResult;

        //input search and click search button
        page.search(searchWord);
        page.clickSearchButton();
        page.findFirstResult();
        searchResult = page.resultName();

        System.out.println("first Search Result: " + searchResult);

        //verify result
        if (searchResult.toLowerCase().contains(searchWord.toLowerCase()))
        {
            System.out.println("the first result matches your search");
        }
        else System.out.println("No matches");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
