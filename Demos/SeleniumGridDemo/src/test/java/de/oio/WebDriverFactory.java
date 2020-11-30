package de.oio;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {
	
	static {
		System.setProperty("webdriver.gecko.driver", "c:/tools/selenium/drivers/geckodriver.exe");
		// ChromeDrive is defined via windows PATH variable.
		System.setProperty("webdriver.ie.driver", "C:/IEDriverServer.exe");
	}
	
	private WebDriverFactory() {
	}

	public static WebDriver createDriver(final String seleniumHubURL, final BrowserType browserType) {
		
		try {
			final DesiredCapabilities capabilities = getBrowserSpecificCapabilities(browserType);
			capabilities.setJavascriptEnabled(true);
			capabilities.setPlatform(Platform.WINDOWS);

			return new RemoteWebDriver(new URL(seleniumHubURL), capabilities);
		}
		catch (final MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static DesiredCapabilities getBrowserSpecificCapabilities(final BrowserType browserType) {
		DesiredCapabilities capabilities = null;
		switch (browserType) {
			case FIREFOX:
				capabilities = DesiredCapabilities.firefox();
				break;
			case CHROME:
				capabilities = DesiredCapabilities.chrome();
				break;
			case INTERNET_EXPLORER:
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("ie.ensureCleanSession", true);
				break;
			default:
				throw new IllegalArgumentException("Invalid browser type");
		}
		return capabilities;
	}
}
