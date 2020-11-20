package de.oio.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {

	private static final String SEARCH_BUTTON_NAME = "btnK";
	private static final String SEARCH_BOX_NAME = "q";
	private WebDriver driver;
	
	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public GoogleSearchPage typeIntoSearchBox(String text) {
		WebElement searchBox = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.name(SEARCH_BOX_NAME)));
		searchBox.click();
		searchBox.clear();
		searchBox.sendKeys(text);
		return this;
	}
	
	public GoogleResultPage submitSearch() {
		WebElement searchButton = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.name(SEARCH_BUTTON_NAME)));
		searchButton.submit();
		return new GoogleResultPage(driver);
	}
}