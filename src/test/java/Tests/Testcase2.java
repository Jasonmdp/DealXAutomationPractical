package Tests;

import Objects.AutomationPracticePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
//search and verify results for a string of 3 items
public class Testcase2 {
    WebDriver driver;

    @BeforeTest
    public void PageSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void getResultFromSearchList() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AutomationPracticePage page = new AutomationPracticePage(driver);
        //declare search word and result variable
        String searchCriteria = "dress,t-shirt, belt";
        String searchResult;

        //split list and convert to array
        String[] searchArray = searchCriteria.split(",");

        //loop through the search array
        int numberOfItems = searchArray.length;
        for (int i = 0; i < numberOfItems;i++) {
            String searchWord = searchArray[i];
            page.search(searchWord);
            page.clickSearchButton();
            page.findFirstResult();
            searchResult = page.resultName();
            System.out.println("seaching for " + searchWord);
            System.out.println("first Search Result: " + searchResult);

            if (searchResult.toLowerCase().contains(searchWord.toLowerCase()))
            {
                System.out.println("this matches your search");
            }
            else System.out.println("No match for your search");

            page.returnHome();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            }
        //close browser
        driver.close();
        }

    }

