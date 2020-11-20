package de.oio;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloSelenium {
    private static final int EXPLICITLY_WAIT_TIME_MS = 5000;

    public static void main(String[] args) throws InterruptedException {
        // Load via system property or via PATH settings.
//		System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
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

        // Check the loaded page is what you expect.
        Thread.sleep(EXPLICITLY_WAIT_TIME_MS);

        driver.close();
    }
}
