package de.oio.pageobjects;

import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;

public class OioPage {

	private WebDriver driver;
	
	public OioPage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		MatcherAssert.assertThat(this.driver.getTitle(), CoreMatchers.is("OIO-Die Java-Experten von Trivadis-Schulung Beratung Entwicklung"));
	}
}
