package de.oio.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleResultPage {

	private static final String FIRST_LINK_XPATH = "//cite";
	private WebDriver driver;
	
	public GoogleResultPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public OioPage clickOnFirstLink() {
		WebElement firstLink = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_LINK_XPATH)));
		firstLink.click();
		return new OioPage(driver);
	}
}