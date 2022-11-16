package com.clearskye.test.actions;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.WebDriverUtil;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.util.SeleniumUtils;
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.Templates;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeTemplates extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeTemplates(WebDriverUtil webDriverUtil) {
		super(webDriverUtil);
		this.driver = webDriverUtil.getDriver();
	}
	
	private WebElement findElementByClassName(By by, int index) {
		List<WebElement> elements = driver.findElements(by);
		return elements.get(index);
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
	
	public void clickOnReviewTemplatesTab() {
		SeleniumUtils.jsClick(driver, Templates.reviewTemplates);
		webDriverUtil.waitAWhile();
	}

	public void fillOutReviewRequest() {
		SeleniumUtils.setText(driver, Templates.title, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Templates.description, webDriverUtil.getProperty("DefaultPayment"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.reviewers);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Templates.selectReviewerType, webDriverUtil.getProperty("reviewerType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Templates.reviewer, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.lookupReviewer);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndSelect();
		SeleniumUtils.jsClick(driver, Templates.schedule);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Templates.deadline, "Yes");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Templates.days, "02");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Templates.hours, "03");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Templates.mins, "20");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Templates.seconds, "30");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.advanced);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.allowConcurrent);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.bypassApproval);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Templates.postReviewWorkflow, webDriverUtil.getProperty("postReviewWorkflow"));
		webDriverUtil.waitAWhile();
	}
	
	public void acceptAnAlert() {
		Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText()); // Get text on alert box
	    javascriptAlert.accept();
	}
	
	public void goOverTheReview() {
		SeleniumUtils.jsClick(driver, Templates.startReview);
		acceptAnAlert();
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Templates.notes, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.advancedView);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.updateReview);
		webDriverUtil.waitAWhile();
	//	SeleniumUtils.jsClick(driver, Templates.cancelReview);
	//	acceptAnAlert();
		webDriverUtil.waitAWhile();
	}
	
	public void createANewReview(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Templates.createNew);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.typeOfReview);
		webDriverUtil.waitAWhile();
		fillOutReviewRequest();
		SeleniumUtils.jsClick(driver, Templates.save);
		webDriverUtil.waitAWhile();
		goOverTheReview();
	}
	
	public void switchToNewWindowAndFillOut() {
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.setTextAndEnter(driver, findElementByClassName(Templates.searchField, 0), webDriverUtil.getProperty("watchList"));
	 		 webDriverUtil.waitAWhile();
	 		 SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectIdentity, 0));
	 		 webDriverUtil.waitAWhile();
	        driver.switchTo().window(parentWindow); //cntrl to parent window
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
	    webDriverUtil.waitAWhile();
	}
	
	private void addReviewerExceptions() {
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsDbClick(driver, findElementByClassName(Templates.addReviewer, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.LookupReviewer);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut();
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.addReviewerOk);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsDbClick(driver, findElementByClassName(Templates.addDelegate, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.LookupDelegate);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut();
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.addReviewerOk);
		webDriverUtil.waitAWhile();
	}
	
	private void removeReviewerAndDelegate() {
		SeleniumUtils.jsClick(driver, findElementByClassName(Templates.checkbox, 2));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Templates.actionOptionList, 0), 1);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Templates.deleteConfirmBtn);
		webDriverUtil.waitAWhile();
	}
	
	public void validateAReview(TestDataBean tdb) {
		SeleniumUtils.setTextAndEnter(driver, Profile.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectProfile, 0));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		addReviewerExceptions();
	//	removeReviewerAndDelegate(); //remove option disapperead
	}

}
