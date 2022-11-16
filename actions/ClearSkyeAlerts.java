package com.clearskye.test.actions;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import com.clearskye.test.util.Locator.Account;
import com.clearskye.test.util.Locator.Alerts;
import com.clearskye.test.util.Locator.Configurations;
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.Identity;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.Role;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeAlerts extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeAlerts(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnAlertsTab() {
		SeleniumUtils.jsClick(driver, Alerts.tab);
		webDriverUtil.waitAWhile();
	}
	
	private void fillOutNewGroupInfo() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		SeleniumUtils.setText(driver, Role.groupName, webDriverUtil.getProperty("department") + " " + timeStamp);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Role.groupManager, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Role.groupEmail, webDriverUtil.getProperty("email"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Role.groupDescription, webDriverUtil.getProperty("entitlementType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.groupSubmit);
		webDriverUtil.waitAWhile();
	}
	
	public void switchToNewWindowAndFillOut() {
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver, Profile.newOwnerBtn);
	 		 webDriverUtil.waitAWhile();
	 		 fillOutNewGroupInfo();
	        driver.switchTo().window(parentWindow); //cntrl to parent window
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
	    webDriverUtil.waitAWhile();
	}
	
	public void fillOutAlertInfo() {
		SeleniumUtils.jsSelectElementByValue(driver, Alerts.severity, "2");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Alerts.state, "resolved");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Alerts.groupLookup);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut();
		SeleniumUtils.setTextAndTab(driver, Alerts.assignedTo, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Alerts.assignedToLookup);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndSelect();
		SeleniumUtils.setText(driver, Alerts.classification, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Alerts.knowledgeLock);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Alerts.knoweledgeBase, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Alerts.description, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Alerts.message, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Alerts.workNotes, webDriverUtil.getProperty("accountType") + " Notes");
		webDriverUtil.waitAWhile();	
	}
	
	public void createAnAlert(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		fillOutAlertInfo();
		SeleniumUtils.jsClick(driver, Alerts.submit);
		webDriverUtil.waitAWhile();
	}
}
