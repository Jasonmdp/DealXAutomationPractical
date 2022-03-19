package Tests;

import Objects.AutomationPracticePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Search and verify results for item using an external file as input
public class TestCase3 {
    WebDriver driver;
    public String searchWord;

    @BeforeTest
    public void PageSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
    }

    @Test(priority = 2)
    public void getResult()
    {
        AutomationPracticePage page = new AutomationPracticePage(driver);
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
    @Test (priority = 1)
    public void getSearchCriteria() throws IOException {
        File f = new File("C:\\Users\\Home\\IdeaProjects\\AutomationPractical\\src\\test\\resources\\SearchData");

        //create an object of the file reader class
        FileReader fr = new FileReader(f);

        //create an object of buffered reader
        BufferedReader br = new BufferedReader(fr);

        //read and print out contents
        searchWord = br.readLine();
        System.out.println("I will search for a " + searchWord);

        //close the file
        br.close();
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }

}
