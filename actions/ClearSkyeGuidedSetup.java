package com.clearskye.test.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.WebDriverUtil;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.util.SeleniumUtils;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.AccessReviews;
import com.clearskye.test.util.Locator.Campaigns;
import com.clearskye.test.util.Locator.GuidedSetup;

public class ClearSkyeGuidedSetup extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeGuidedSetup(WebDriverUtil webDriverUtil) {
		super(webDriverUtil);
		this.driver = webDriverUtil.getDriver();
	}
	
	private WebElement findElementByClassName(By by, int index) {
		List<WebElement> elements = driver.findElements(by);
		return elements.get(index);
	}
	
	private WebElement findElementByClassName(By by) {
		List<WebElement> elements = driver.findElements(by);
		return elements.get(elements.size() - 1);
	}
	
	public void navigate(String page) {
		String URL = webDriverUtil.getProperty("clearsky.homepage.url") + "/" + page;
		Reporter.log("Loading Url = " + URL);
		webDriverUtil.loadLocation(URL);
		webDriverUtil.waitAWhile();
	}
	
	public void switchToIframe() {
		driver.switchTo().frame("gsft_main");
	}
	
	public void switchToStream() {
		driver.switchTo().frame("dialog_frame");
	}
	
	public void switchToContent() {
		driver.switchTo().defaultContent();
	}
	
	public void switchToNewWindowAndSelect() {
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectIdentity, 0));
	 		 webDriverUtil.waitAWhile();
	         driver.switchTo().window(parentWindow); //cntrl to parent window
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
	    webDriverUtil.waitAWhile();
	}
	
	public void clickOnGuidedSetupTab() {
		SeleniumUtils.jsClick(driver, GuidedSetup.tab);
		webDriverUtil.waitAWhile();
	}
	
	public void acceptAnAlert() {
		Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText()); // Get text on alert box
	    javascriptAlert.accept();
	}
	
	public void markAllTasks(By by) {
		List<WebElement> marks = driver.findElements(by);
		for (int i = 0; i < marks.size(); i++) {
			SeleniumUtils.jsClick(driver, findElementByClassName(by, i));
			webDriverUtil.waitAWhile();
		}
	}
	
	public void validate(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, GuidedSetup.getStarted);
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		markAllTasks(GuidedSetup.markAsComplete);
		SeleniumUtils.jsClick(driver, findElementByClassName(GuidedSetup.viewNotes, 0));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, GuidedSetup.comments, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(GuidedSetup.postComment, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, GuidedSetup.close);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, GuidedSetup.assign);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(GuidedSetup.assignName, 0));
		webDriverUtil.waitAWhile();
	/*	WebElement nameText = findElementByClassName(GuidedSetup.nameText, 0);
		nameText.sendKeys(webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		nameText.sendKeys(Keys.TAB);
		webDriverUtil.waitAWhile(); */
		SeleniumUtils.jsClick(driver, GuidedSetup.assign);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, GuidedSetup.backBtn);
		webDriverUtil.waitAWhile();
	}

}
