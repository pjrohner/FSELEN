package de.oio;

import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloSeleniumTest {
	private static final int EXPLICITLY_WAIT_TIME_MS = 5000;

	private static WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		// Load via system property or via PATH settings.
//		System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		driver.get("http://www.google.de");
		// Do agree any popping up agreement dialogs.
		Thread.sleep(EXPLICITLY_WAIT_TIME_MS);

		WebElement searchField = driver.findElement(By.name("q"));
		String searchText = "oio.de";
		searchField.sendKeys(searchText);
		searchField.submit();

		String linkText = "OIO";
		WebElement firstLink = driver.findElement(By.partialLinkText(linkText));
		firstLink.click();

		// Test expected page was loaded.
		String expectedResult = "OIO-Die Java-Experten";
		MatcherAssert.assertThat(driver.getTitle(), CoreMatchers.containsString(expectedResult));
	}
}
