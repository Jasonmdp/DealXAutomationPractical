package Tests;

import Objects.AutomationPracticePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.math.BigDecimal;

public class TestCase5 {
    String username = "jasonmdp@gmail.com";
    String password = "123password";
    String searchWord = "Dress";
    String totalPrice;
    String unitPrice;
    BigDecimal calculatedTotal;
    Integer quantity = 5;
    WebDriver driver;

    @BeforeTest
    public void PageSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
    }

    //logs into account
    @Test (priority = 1)
    public void login()
    {
        AutomationPracticePage page = new AutomationPracticePage(driver);

        page.clickSignInLink();
        page.userName(username);
        page.passWord(password);
        page.clickSignInBtn();
    }

    //search for item, adds it to cart then retreives and compares values
    @Test (priority = 2)
    public void chooseItem() throws InterruptedException {
        AutomationPracticePage page = new AutomationPracticePage(driver);

        //input search and click search button
        page.search(searchWord);
        page.clickSearchButton();
        page.findFirstResult();

        //get unit price and add item to cart and view cart
        unitPrice = page.getUnitPrice();
        System.out.println("unit price of " + searchWord + ": $" + stringToBigDecimal(unitPrice));
        page.clickAddToCart();
        Thread.sleep(5000);
        page.clickProceedToCheckout();
        System.out.println(searchWord + " added to cart");

        //update quantity and get displayed total price
        page.setQuantity(quantity);
        Thread.sleep(5000);
        System.out.println("Quantity changed to " + quantity);
        totalPrice = page.getTotalPrice();
        System.out.println("Displayed total price is $" + stringToBigDecimal(totalPrice));
        calculatedTotal = (getCalculatedTotal(stringToBigDecimal(unitPrice), quantity));

        //compares calculated total and displayed total
        try {
            Assert.assertEquals(stringToBigDecimal(totalPrice), calculatedTotal);
        } catch (AssertionError e) {
            System.out.println("The displayed total is Not equal to calculated total, test failed");
            throw e;
        }
        System.out.println("The displayed total is equal to calculated total, test passed");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    //Multiplies price and quantity
    private BigDecimal getCalculatedTotal(BigDecimal a, Integer b){
        BigDecimal bd = (a).multiply(BigDecimal.valueOf(b)).setScale(2);
        System.out.println("Calculated total is $" +bd);
        return bd;
    }

    //Converts string of numbers to BigDecimal
    private BigDecimal stringToBigDecimal (String a){
        BigDecimal bd = new BigDecimal(a).divide(BigDecimal.valueOf(100));
        return bd.setScale(2);
    }
}
