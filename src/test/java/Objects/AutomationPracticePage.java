package Objects;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class AutomationPracticePage {

    WebDriver driver;

    By searchBox = By.id("search_query_top");
    By firstResultLink = By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[1]/div/a[1]/img");
    By firstResultName = By.xpath("//*[@id=\"center_column\"]/div/div/div[3]/h1");
    By homePageLink = By.xpath("//*[@id=\"header_logo\"]/a/img");
    By signInLink = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
    By userNameBox = By.id("email");
    By passwordBox = By.id("passwd");
    By signInBtn = By.xpath("//*[@id=\"SubmitLogin\"]/span");
    By addToCartBtn = By.xpath("//*[@id=\"add_to_cart\"]/button/span");
    By proceedToCheckout = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span");
    By totalPrice = By.id("total_product");
    By unitPrice = By.xpath("//*[@id=\"our_price_display\"]");
    By itemQuantity = By.xpath("//*[@id=\"product_5_19_0_651958\"]/td[5]/input[2]");
    By WomenMenu = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]");
    By DressesMenu = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]");
    By TshirtsMenu = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]");

    public AutomationPracticePage(WebDriver localDriver) {
        this.driver = localDriver;
    }

    public void PageSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
    }

    //enter search criteria in search box
    public void search(String SearchInput) {
        driver.findElement(searchBox).sendKeys(SearchInput);
    }

    //click search button
    public void clickSearchButton() {
        driver.findElement(By.name("submit_search")).click();
    }

    //click on first result
    public void findFirstResult() {
        driver.findElement(firstResultLink).click();

    }

    //returns title of the first result clicked on
    public String resultName() {
        return driver.findElement(firstResultName).getText();
    }

    //returns to homepage
    public void returnHome() {
        driver.findElement(homePageLink).click();
    }

    //clicks sign in button
    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }

    //enters username
    public void userName(String email) {
        driver.findElement(userNameBox).sendKeys(email);
    }

    //enters password
    public void passWord(String yourPassword) {
        driver.findElement(passwordBox).sendKeys(yourPassword);
    }

    //clicks search button
    public void clickSignInBtn() {
        driver.findElement(signInBtn).click();
        System.out.println(driver.getTitle() + " page loaded, logged in successfully");
    }

    //clicks add to cart
    public void clickAddToCart() {
        driver.findElement(addToCartBtn).click();
    }

    //Clicks proceed after adding to cart
    public void clickProceedToCheckout() {
        driver.findElement(proceedToCheckout).click();
    }

    //returns displayed total
    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText().replaceAll("\\D", "");
    }

    //returns the displayed unit price
    public String getUnitPrice() {
        return driver.findElement(unitPrice).getText().replaceAll("\\D", "");
    }

    //updates quantity im cart
    public void setQuantity(Integer numberOfItems) {
        driver.findElement(itemQuantity).clear();
        driver.findElement(itemQuantity).sendKeys(String.valueOf(numberOfItems));
    }


    public void HoverAndClick(String whichMenu, String subMenuItem) throws InterruptedException {
        Actions act = new Actions(driver);

        switch (whichMenu.toLowerCase()) {
            case "women" -> {
                act.moveToElement((driver.findElement(WomenMenu))).build().perform();
                Thread.sleep(2000);
                List<WebElement> links = driver.findElements((By.tagName("a")));
                int totalCount = links.size();

                for (WebElement element : links) {
                    String menuItem = element.getText();
                    if (subMenuItem.equalsIgnoreCase(menuItem)) {
                        element.click();
                        Thread.sleep(2000);
                        String pageTitle = driver.getTitle();

                        if (pageTitle.toLowerCase().contains(subMenuItem)){
                        System.out.println(pageTitle + " page was loaded, test passed.");}
                        else {
                            System.out.println("The incorrect page was loaded");
                        }
                        break;
                    }
                }
            }
            case "dresses" -> {
                act.moveToElement((driver.findElement(DressesMenu))).build().perform();
                Thread.sleep(2000);
                List<WebElement> dressesLinks = driver.findElements((By.tagName("a")));
                int dressesLinkCount = dressesLinks.size();
                for (WebElement element : dressesLinks) {
                    String menuItem = element.getText();

                    if (subMenuItem.equalsIgnoreCase(menuItem)) {
                        element.click();
                        Thread.sleep(2000);
                        System.out.println(driver.getTitle() + " page was loaded.");
                        break;
                    }
                }break;
            }
            case "t-shirts" -> {
                act.moveToElement((driver.findElement(TshirtsMenu))).build().perform();
                Thread.sleep(2000);
                    if (subMenuItem.equalsIgnoreCase("t-shirts")) {
                        driver.findElement(TshirtsMenu).click();
                        Thread.sleep(2000);
                        System.out.println(driver.getTitle() + " page was loaded.");
                    } else System.out.println("Invalid Menu");
                break;
                }

            default -> System.out.println("Invalid Menu");
        }


    }
}










