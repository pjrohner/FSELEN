package de.oio;

import de.oio.pageobjects.GoogleSearchPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class HelloSeleniumTest {
	private static final int EXPLICITLY_WAIT_TIME_MS = 5000;

	private static WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		// Load via system property or via PATH settings.
//		System.setProperty("webdriver.gecko.driver", "C:/tools/selenium/driver/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Before
	public void setupMethod() {
		driver.get("http://www.google.de");
		// Do agree any popping up agreement dialogs.
		try {
			Thread.sleep(EXPLICITLY_WAIT_TIME_MS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void testOioHomepageNavigation() {
		new GoogleSearchPage(driver)
				.typeIntoSearchBox("oio.de")
				.submitSearch()
				.clickOnFirstLink();
	}
}
