package com.test.github.mobileweb.screens;

import io.appium.java_client.android.AndroidDriver;

import com.test.utils.AbstractScreen;

public class GitHubAppiumScreen extends AbstractScreen {

	public GitHubAppiumScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		loadPage();
	}

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}

	@Override
	public AbstractScreen clickOnLink(String linkName) {
		// TODO Auto-generated method stub
		return null;
	}

}
