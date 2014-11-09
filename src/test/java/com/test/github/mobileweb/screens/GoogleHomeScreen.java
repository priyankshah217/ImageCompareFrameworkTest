package com.test.github.mobileweb.screens;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.test.utils.AbstractScreen;
import com.test.utils.AppUtils;

public class GoogleHomeScreen extends AbstractScreen {

	@FindBy(id = "lst-ib")
	private WebElement searchTextField;

	@FindBy(id = "tsbb")
	private WebElement searchButton;

	public GoogleHomeScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		loadPage();
	}

	public void openWebPage(String url) {
		driver.get(AppUtils.APP_URL);
	}

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}

	public GoogleHomeScreen enterSearchText(String textToBeSearched) {
		// TODO Auto-generated method stub
		searchTextField.clear();
		searchTextField.sendKeys(textToBeSearched);
		return this;
	}

	public GoogleSearchResultScreen clickOnButton() {
		searchButton.click();
		return new GoogleSearchResultScreen(driver);
	}

	@Override
	public AbstractScreen clickOnLink(String linkName) {
		// TODO Auto-generated method stub
		return null;
	}
}
