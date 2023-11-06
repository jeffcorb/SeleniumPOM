package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutomationUtilities {
    public static void automationSleep(long milliMs) {
        try {
            Thread.sleep(milliMs);
        } catch (InterruptedException interruptedException) {
            Logs.error("Error: %s", interruptedException.getLocalizedMessage());
        }
    }

    public static void saucedemoLogin(WebDriver driver) {
        Logs.info("Navigating to Index");
        driver.get("https://www.saucedemo.com/404");

        Logs.info("Waiting 0.5 seconds");
        AutomationUtilities.automationSleep(500);

        driver.manage().addCookie(new Cookie("session-username", "standard_user"));

        Logs.info("Navigating to Saucedemo");
        driver.get("https://www.saucedemo.com/inventory.html");

        // Logs.info("Waiting 2 seconds");
        // AutomationUtilities.automationSleep(2000);
        Logs.info("Waiting Shopping Page to load");
        //AutomationUtilities.automationSleep(3000);
        final var wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
    }
}
