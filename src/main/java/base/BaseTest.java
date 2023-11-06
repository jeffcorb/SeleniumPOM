package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;
import utilities.Logs;

@Listeners({SuiteListeners.class, TestListeners.class})

public class BaseTest {

    protected static WebDriver driver;
    protected SoftAssert softAssert;
    protected final String regression = "regression";
    protected final String smoke = "smoke";
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUpMaster() {
        Logs.debug("Init Driver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        Logs.debug("Maximize Window");
        driver.manage().window().maximize();

        Logs.debug("Delete Cookies");
        driver.manage().deleteAllCookies();

        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMaster() {
        Logs.debug("Killing Driver");
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
