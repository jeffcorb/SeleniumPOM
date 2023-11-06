package saucedemo;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.AutomationUtilities;
import utilities.Logs;

import java.time.Duration;

public class ShoppingTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        AutomationUtilities.saucedemoLogin(driver);
    }

    @Test(groups = {regression})
    public void buttonClickTest() {
        final var buttonsLocatorsList = By.className("btn_inventory");

        final var buttonsList = driver.findElements(buttonsLocatorsList);

        Logs.info("Clicking buttons to buy");
        for (var element : buttonsList) {
            element.click();
        }

        final var shoppingCartNumberLocator = By.className("shopping_cart_badge");

        final var shoppingCartNumber = driver.findElement(shoppingCartNumberLocator);

        final var shoppingNumberCartInt = Integer.parseInt(shoppingCartNumber.getText());

        Logs.info("Verifying Number Cart");
        Assert.assertEquals(shoppingNumberCartInt, buttonsList.size());
    }

    @Test(groups = {regression})
    public void verifyItemsPage() {
        final var imageBackpackLocator = By.cssSelector("img[alt='Sauce Labs Backpack']");
        final var imgBackpack = driver.findElement(imageBackpackLocator);

        Logs.info("Clicking Image");
        imgBackpack.click();

        // Logs.info("Waiting 0.5 seconds");
        // AutomationUtilities.automationSleep(500);

        final var inventoryDetail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_details_name")));
        Logs.info("Verifying Details Page");
        softAssert.assertTrue(inventoryDetail.isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_desc")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_price")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_img")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("btn_primary")).isDisplayed());

        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void verifySelectPriceTest() {
        final var selectLocator = By.cssSelector("select[data-test='product_sort_container']");
        final var selectWebElement = driver.findElement(selectLocator);

        final var select = new Select(selectWebElement);

        Logs.info("Sorting by Price L-H");
        select.selectByValue("lohi");

        Logs.info("Waiting 1 second");
        AutomationUtilities.automationSleep(1000);

        final var itemPriceLocator = By.className("inventory_item_price");
        final var itemPriceList = driver.findElements(itemPriceLocator);

        final var lowPriceWebElement = itemPriceList.get(0);
        final var highPriceWebElement = itemPriceList.get(itemPriceList.size() - 1);

        final var lowPrice = Double.parseDouble(lowPriceWebElement.getText().substring(1));
        final var highPrice = Double.parseDouble(highPriceWebElement.getText().substring(1));

        Logs.info("Verifying Prices");
        softAssert.assertEquals(lowPrice, 7.99);
        softAssert.assertEquals(highPrice, 49.99);
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void verifyingSortByAlphabetic() {
        final var selectLocator = By.cssSelector("select[data-test='product_sort_container']");
        final var selectWebElement = driver.findElement(selectLocator);

        final var select = new Select(selectWebElement);

        Logs.info("Sorting by Z-A");
        select.selectByValue("za");

        Logs.info("Waiting 1 second");
        AutomationUtilities.automationSleep(1000);

        final var itemNameLocator = By.className("inventory_item_name");
        final var itemNameList = driver.findElements(itemNameLocator);

        final var firstItemWebElement = itemNameList.get(0);
        final var lastItemWebElement = itemNameList.get(itemNameList.size() - 1);

        final var firstItem = firstItemWebElement.getText();
        final var lastItem = lastItemWebElement.getText();

        Logs.info("Verifying Items");
        softAssert.assertEquals(firstItem, "Test.allTheThings() T-Shirt (Red)");
        softAssert.assertEquals(lastItem, "Sauce Labs Backpack");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void verifyTwitter() {
        final var twitterButtonLocator = By.xpath("//a[text()='Twitter']");
        final var twitterButton = driver.findElement(twitterButtonLocator);

        Logs.info("Verifying Twitter button");
        softAssert.assertTrue(twitterButton.isDisplayed());
        softAssert.assertTrue(twitterButton.isEnabled());
        softAssert.assertEquals(twitterButton.getAttribute("href"), "https://twitter.com/saucelabs");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void verifyLinkedin() {
        final var linkedinButtonLocator = By.xpath("//a[text()='LinkedIn']");
        final var linkedinButton = driver.findElement(linkedinButtonLocator);

        Logs.info("Verifying Linkedin button");
        softAssert.assertTrue(linkedinButton.isDisplayed());
        softAssert.assertTrue(linkedinButton.isEnabled());
        softAssert.assertEquals(linkedinButton.getAttribute("href"), "https://www.linkedin.com/company/sauce-labs/");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void verifyingAboutButton() {
        final var burgerLocator = By.id("react-burger-menu-btn");
        final var burgerOption = driver.findElement(burgerLocator);
        burgerOption.click();

        Logs.info("Waiting 4 seconds");
        //AutomationUtilities.automationSleep(2500);

        final var aboutLocator = By.id("about_sidebar_link");
        final var aboutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(aboutLocator));

        Logs.info("Verifying About button");
        softAssert.assertTrue(aboutButton.isDisplayed(), "is not displayed");
        softAssert.assertTrue(aboutButton.isEnabled(), "is not enabled");
        softAssert.assertEquals(aboutButton.getAttribute("href"), "https://saucelabs.com/", "Not href");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void logOutTest() {
        final var burgerLocator = By.id("react-burger-menu-btn");
        final var burgerOption = driver.findElement(burgerLocator);
        burgerOption.click();

        // Logs.info("Waiting 2.5 seconds");
        // AutomationUtilities.automationSleep(2500);

        Logs.info("Waiting Slide bar to load");
        //AutomationUtilities.automationSleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        final var logOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        logOutButton.click();

        ///Logs.info("Waiting 2 seconds");
        // AutomationUtilities.automationSleep(2000);

        Logs.info("Waiting Index to load");
        //AutomationUtilities.automationSleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        final var inputUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        Logs.info("Verifying Return to login page");
        Assert.assertTrue(inputUsername.isDisplayed());
    }

    @Test(groups = {regression})
    public void verifyCookies() {
        final var listCookies = driver.manage().getCookies();
        Logs.info("List of Cookies");

        for (var element : listCookies) {
            final var mensaje = """
                    name: %s
                    value: %s
                    """;
            Logs.debug(mensaje, element.getName(), element.getValue());
        }
    }
}
