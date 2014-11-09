package com.test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.im4java.core.IM4JavaException;

import com.test.github.mobileweb.screens.GitHubAppiumScreen;
import com.test.github.mobileweb.screens.GitHubSelendroidScreen;
import com.test.github.mobileweb.screens.GoogleHomeScreen;
import com.test.github.mobileweb.screens.GoogleSearchResultScreen;
import com.test.utils.AbstractScreen;
import com.test.utils.AppUtils;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MobileWebAutomationStepDefs {
	private AndroidDriver driver;

	private GoogleHomeScreen googleHomeScreen;
	private GitHubAppiumScreen githubAppiumScreen;
	private GitHubSelendroidScreen githubSelendroidScreen;
	private GoogleSearchResultScreen googleSearchResultScreen;
	private AbstractScreen screen;
	private String diffImage;
	private Scenario scenario;

	@Before
	public void Setup(Scenario scenario) throws IOException {
		this.scenario = scenario;
		AppUtils.loadConfigProp("config_githubsearch_mobile_web.properties");
		AppUtils.setCapabilities();
		driver = AppUtils.getDriver();
	}

	@Given("^I launch \"(.*?)\" in chrome$")
	public void i_launch_in_chrome(String url) throws IOException {
		// Write code here that turns the phrase above into concrete actions

		githubAppiumScreen = new GitHubAppiumScreen(driver);
		githubSelendroidScreen = new GitHubSelendroidScreen(driver);
		googleHomeScreen = new GoogleHomeScreen(driver);
		googleHomeScreen.openWebPage(url);
	}

	@When("^I enter \"(.*?)\" in \"(.*?)\" field on \"(.*?)\" screen$")
	public void i_enter_in_field_on_screen(String textToBeSearched,
			String fieldName, String screenName) {
		// Write code here that turns the phrase above into concrete actions
		switch (screenName) {
		case "Google Home":
			googleHomeScreen = googleHomeScreen
					.enterSearchText(textToBeSearched);
			break;
		}
	}

	@When("^I click on \"(.*?)\" button on \"(.*?)\" screen$")
	public void i_click_on_button_on_screen(String buttonName, String screenName) {
		// Write code here that turns the phrase above into concrete actions
		switch (screenName) {
		case "Google Home":
			googleSearchResultScreen = googleHomeScreen.clickOnButton();
			break;
		}
	}

	@Then("^I take a screenshot of \"(.*?)\" screen$")
	public void i_take_a_screenshot_of_screen(String screenName)
			throws IOException, InterruptedException, IM4JavaException {
		// Write code here that turns the phrase above into concrete actions
		switch (screenName) {
		case "Google Home":
			screen = googleHomeScreen;
			break;
		case "Google Search Result":
			screen = googleSearchResultScreen;
			break;
		case "Appium GitHub":
			screen = githubAppiumScreen;
			break;
		case "Selendroid GitHub":
			screen = githubSelendroidScreen;
			break;
		}
		String screenshotName = screen.getScreenName() + ".png";
		String actualImage = AppUtils.ACTUAL_IMAGE_DIR + "/" + screenshotName;
		String maskImage = AppUtils.MASKED_IMAGE_DIR + "/" + screenshotName;
		String referenceImage = AppUtils.REFERENCE_IMAGE_DIR + "/"
				+ screenshotName;
		File maskImageFile = new File(maskImage);
		AppUtils.takeScreenShot(actualImage);
		if (maskImageFile.exists()) {
			String maskedActualImage = AppUtils.DIFF_IMAGE_DIR + "/"
					+ "MaskedActual" + screenshotName;
			String maskedReferenceImage = AppUtils.DIFF_IMAGE_DIR + "/"
					+ "MaskedReference" + screenshotName;
			AppUtils.maskImage(actualImage, maskImage, maskedActualImage);
			AppUtils.maskImage(referenceImage, maskImage, maskedReferenceImage);
			actualImage = maskedActualImage;
			referenceImage = maskedReferenceImage;

		}
		diffImage = AppUtils.DIFF_IMAGE_DIR + "/" + "Diff" + screenshotName;
		// Assert.assertSame(true,
		// AppUtils.compareImages(actualImage, referenceImage, diffImage));
	}

	@When("^I click on \"(.*?)\" link on \"(.*?)\" screen$")
	public void i_click_on_link_on_screen(String searchText, String screenName) {
		String linkText = "";
		// Write code here that turns the phrase above into concrete actions
		switch (screenName) {
		case "Google Search Result":
			if (searchText.contains("Appium")) {
				linkText = "appium/appium - GitHub";
			} else if (searchText.contains("Selendroid")) {
				linkText = "selendroid/selendroid - GitHub";
			}
			screen = googleSearchResultScreen.clickOnLink(linkText);
			break;
		}
	}

	@When("^I tap on \"(.*?)\"$")
	public void i_tap_on(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@After
	public void tearDown() throws IOException {
		if (this.scenario.isFailed()) {
			byte[] fileContent = Files.readAllBytes(new File(diffImage)
					.toPath());
			this.scenario.embed(fileContent, "image/png");
			this.scenario.write("Image Comparision Failed");
		}
		driver.quit();
	}
}