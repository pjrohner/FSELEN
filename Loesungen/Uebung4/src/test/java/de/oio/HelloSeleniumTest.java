package de.oio;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HelloSeleniumTest {
    private static final int EXPLICITLY_WAIT_TIME_MS = 5000;

    private static WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        // Load via system property or via PATH settings.
//		System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");

        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Before
    public void setupMethod() throws InterruptedException {
        driver.get("http://www.google.de");
        // Do agree any popping up agreement dialogs.
        Thread.sleep(EXPLICITLY_WAIT_TIME_MS);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testOioHomepageNavigation() {
        WebDriverWait wait;

        String searchText = "oio.de";
        WebElement searchField;
        {
            wait = new WebDriverWait(driver, 60);
            searchField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        }
        searchField.sendKeys(searchText);
        searchField.submit();

        String linkText = "OIO";
        WebElement firstLink;
        {
            wait = new WebDriverWait(driver, 5);
            firstLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(linkText)));
        }
        firstLink.click();

        String expectedResult = "OIO-Die Java-Experten";
        MatcherAssert.assertThat(driver.getTitle(), CoreMatchers.containsString(expectedResult));
    }

    @Test
    public void testOioHomepageNavigationAlternative() {
        String searchText = "oio.de";
        String linkText = "OIO";
        String expectedResult = "OIO-Die Java-Experten";

        sendKeysAndSubmit(driver, driver.findElement(By.name("q")), 10, searchText);
        clickOn(driver, driver.findElement(By.partialLinkText(linkText)), 10);

        MatcherAssert.assertThat(driver.getTitle(), CoreMatchers.containsString(expectedResult));
    }

    // Send keys method.
    private static void sendKeysAndSubmit(WebDriver driver, WebElement element, int timeoutInSeconds, String value) {
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
        element.submit();
    }

    // Clickable method declared explicitly.
    private static void clickOn(WebDriver driver, WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

}
