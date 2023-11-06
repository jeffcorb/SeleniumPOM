package saucedemo;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AutomationUtilities;
import utilities.Logs;

import java.time.Duration;

public class CookiesTests extends BaseTest {
    @Test(groups = {regression})
    public void navigatingTest() {
        Logs.info("Navigating to Index");
        driver.get("https://www.saucedemo.com/404");

        Logs.info("Waiting 1 seconds");
        AutomationUtilities.automationSleep(1000);

        driver.manage().addCookie(new Cookie("session-username", "standard_user"));

        Logs.info("Navigating to Saucedemo");
        driver.get("https://www.saucedemo.com/inventory.html");

        //Logs.info("Waiting 3 seconds");
        // AutomationUtilities.automationSleep(3000);

        Logs.info("Waiting Shopping Page to load");
        //AutomationUtilities.automationSleep(3000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        final var labelProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        Assert.assertTrue(labelProduct.isDisplayed());
        //20:07:14 - 20:09:10 (1m 56s)
        //16:53:46 - 16:54:45 (58s 604ms)
    }
}
