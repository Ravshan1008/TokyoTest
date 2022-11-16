package com.clearskye.test.actions;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

public class ClearSkyeAccessReviews extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeAccessReviews(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnAccessReviewsTab() {
		SeleniumUtils.jsClick(driver, AccessReviews.tab);
		webDriverUtil.waitAWhile();
	}
	
	public void acceptAnAlert() {
		Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText()); // Get text on alert box
	    javascriptAlert.accept();
	}
	
	public void goOverSLATabs() {
		List<WebElement> slaConditions = driver.findElements(AccessReviews.slaConditions);
		for (WebElement slaCondition : slaConditions) {
			SeleniumUtils.jsClick(driver, slaCondition);
			webDriverUtil.waitAWhile();
		}
	}
	
	public void validateReview() {
		SeleniumUtils.setText(driver, findElementByClassName(AccessReviews.inputField,  0), webDriverUtil.getProperty("reviewUser"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(AccessReviews.inputField,  1), webDriverUtil.getProperty("environment"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(AccessReviews.inputField,  2), webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
	//	SeleniumUtils.setText(driver, findElementByClassName(AccessReviews.inputField,  3), webDriverUtil.getProperty("owner"));
	//	webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.unlockWatchList);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, AccessReviews.watchListName, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, AccessReviews.watchListEmail, webDriverUtil.getProperty("email"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.addEmail);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.addMe);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.listLock);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.follow);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(AccessReviews.stopWatch));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.closeWindow);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.showTimeline);
		acceptAnAlert();
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsScroll(driver, findElementByClassName(AccessReviews.scrollDown, 0));
		webDriverUtil.waitAWhile();
		goOverSLATabs();
		SeleniumUtils.jsClick(driver, AccessReviews.slaRightIcon);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.zoomIn);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.zoomOut);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.refresh);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(AccessReviews.settings, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(AccessReviews.switchSLA, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsScroll(driver, findElementByClassName(AccessReviews.scrollDown, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessReviews.slaCloseTab);
		webDriverUtil.waitAWhile();
	}
	
	public void checkReview(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, findElementByClassName(AccessReviews.selectAReview));
		webDriverUtil.waitAWhile();
		validateReview();
		webDriverUtil.waitAWhile();
	}

}
