package com.test.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractScreen {

	public AndroidDriver driver;

	public AbstractScreen(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void loadPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public abstract AbstractScreen clickOnLink(String linkName);

	public abstract String getScreenName();

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
