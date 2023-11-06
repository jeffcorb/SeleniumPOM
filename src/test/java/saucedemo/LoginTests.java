package saucedemo;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Logs;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navigating to Index");
        driver.get("https://www.saucedemo.com");

        Logs.info("Waiting Index to load");
        //AutomationUtilities.automationSleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
    }

    @Test(groups = regression)
    public void basicTest() {
        final var usernameLocator = By.id("user-name");
        final var buttonLocator = By.id("login-button");
        final var passwordLocator = By.id("password");
        final var messageErrorLocator = By.cssSelector("h3[data-test='error']");

        final var inputUsername = driver.findElement(usernameLocator);
        final var inputPassword = driver.findElement(passwordLocator);
        final var buttonLogin = driver.findElement(buttonLocator);

        Logs.info("Writing text on Username Input");
        inputUsername.sendKeys("User");

        Logs.info("Writing text on Password Input");
        inputPassword.sendKeys("123456");

        Logs.info("Clicking Login Button");
        buttonLogin.click();

        Logs.info("Waiting error message to load");
        //AutomationUtilities.automationSleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));
        //Logs.info("Waiting 1.5 seconds");
        //AutomationUtilities.automationSleep(1500);

        final var errorMessage = driver.findElement(messageErrorLocator);

        Logs.info("Verifying Error Message");
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Test(groups = {regression})
    public void lockedUserTest() {
        final var usernameLocator = By.id("user-name");
        final var buttonLocator = By.id("login-button");
        final var passwordLocator = By.id("password");
        final var messageErrorLocator = By.cssSelector("h3[data-test='error']");

        final var inputUsername = driver.findElement(usernameLocator);
        final var inputPassword = driver.findElement(passwordLocator);
        final var buttonLogin = driver.findElement(buttonLocator);

        Logs.info("Writing text on Username Input");
        inputUsername.sendKeys("locked_out_user");

        Logs.info("Writing text on Password Input");
        inputPassword.sendKeys("secret_sauce");

        Logs.info("Clicking Login Button");
        buttonLogin.click();

        //Logs.info("Waiting 1.5 seconds");
        //AutomationUtilities.automationSleep(1500);
        Logs.info("Waiting error message to load");
        //AutomationUtilities.automationSleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        final var errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorLocator));

        Logs.info("Verifying Error Message");
        Assert.assertEquals(errorMessage.getText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error Message");
    }

    @Test(groups = {regression})
    public void standardUserTest() {
        final var usernameLocator = By.id("user-name");
        final var buttonLocator = By.id("login-button");
        final var passwordLocator = By.id("password");

        final var inputUsername = driver.findElement(usernameLocator);
        final var inputPassword = driver.findElement(passwordLocator);
        final var buttonLogin = driver.findElement(buttonLocator);

        Logs.info("Writing text on Username Input");
        inputUsername.sendKeys("standard_user");

        Logs.info("Writing text on Password Input");
        inputPassword.sendKeys("secret_sauce");

        Logs.info("Clicking Login Button");
        buttonLogin.click();


        //Logs.info("Waiting 3 seconds");
        // AutomationUtilities.automationSleep(3000);

        Logs.info("Waiting Shopping Page to load");
        //AutomationUtilities.automationSleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        final var labelProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        Logs.info("Verifying User has access");
        Assert.assertTrue(labelProduct.isDisplayed());
    }
}
