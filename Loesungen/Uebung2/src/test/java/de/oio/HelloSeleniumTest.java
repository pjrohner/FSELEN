package de.oio;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class HelloSeleniumTest {
    private static final int EXPLICITLY_WAIT_TIME_MS = 5000;

    @Test
    public void test() throws InterruptedException {
        // Load via system property or via PATH settings.
//		System.setProperty("webdriver.gecko.driver", "C:/tools/selenium/driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.get("http://www.google.de");
        // Do agree any popping up agreements.
        Thread.sleep(EXPLICITLY_WAIT_TIME_MS);

        WebElement searchField = driver.findElement(By.name("q"));
        String searchText = "Selenium IDE";
        searchField.sendKeys(searchText);
        searchField.submit();

        String linkText = "Selenium IDE";
        WebElement firstLink = driver.findElement(By.partialLinkText(linkText));
        firstLink.click();

        // Test expected page was loaded.
        String expectedResult = "Selenium IDE";
        MatcherAssert.assertThat(driver.getTitle(), CoreMatchers.containsString(expectedResult));

        driver.quit();
    }
}
