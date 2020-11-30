package de.oio;

import java.net.MalformedURLException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RunWith(Parallelized.class)
public class SeleniumGridTest {

	static {
		System.setProperty("webdriver.gecko.driver", "c:/tools/selenium/drivers/geckodriver.exe");
		// ChromeDrive is defined via windows PATH variable.
		System.setProperty("webdriver.ie.driver", "C:/IEDriverServer.exe");
	}
	
	
	@Parameters(name = "Browser={0}")
	public static Iterable<Object> data() {
		return Arrays.asList(new Object[] { BrowserType.CHROME, BrowserType.FIREFOX });
//		return Arrays.asList(new Object[] { BrowserType.INTERNET_EXPLORER, BrowserType.FIREFOX, BrowserType.CHROME });
	}
	
	@Parameter(0)
	public BrowserType browserType;
	
	private WebDriver driver;
	private static final String HUB_URL = "http://localhost:4444/wd/hub";
//	private static final String HUB_URL = "http://172.20.10.4:4444/grid/register/";
	private static final String START_URL = "https://www.google.de/";
	
	@Before
	public void setUp() throws MalformedURLException {
		System.out.println("HUB_URL: " + HUB_URL);
		System.out.println("browserType: " + browserType);
		driver = WebDriverFactory.createDriver(HUB_URL, browserType);
		System.out.println("setUp>driver=" + driver);
	}

	@Test
	public void testGoogleSearch() {
		getDriver().get(START_URL);
//		getDriver().manage().window().maximize();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		By item = By.name("q");
		WebElement googleSearchBar = getDriver().findElement(item);
//		WebElement googleSearchBar = getDriver().findElement(By.id("lst-ib"));
		for (int i = 0; i < 50; i++) {
			googleSearchBar.click();
			googleSearchBar.clear();
			googleSearchBar.sendKeys("Test: " + i);
		}
	}
	
	@After
	public void destroy() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
	
	private WebDriver getDriver() {
		return driver;
	}
}
