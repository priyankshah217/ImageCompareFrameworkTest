package com.test.github.mobileweb.screens;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;

import com.test.utils.AbstractScreen;

public class GoogleSearchResultScreen extends AbstractScreen {
//
//	@FindBy(linkText = "appium/appium - GitHub")
//	private WebElement appliumGitHubLink;

	public GoogleSearchResultScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		loadPage();
	}

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}

	public AbstractScreen clickOnLink(String linkName) {
		// TODO Auto-generated method stub
		driver.findElement(By.linkText(linkName)).click();
		return this;
	}
}
