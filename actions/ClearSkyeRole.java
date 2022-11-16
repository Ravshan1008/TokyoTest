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
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.Environment;
import com.clearskye.test.util.Locator.Identity;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.Role;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeRole extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeRole(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnRolesTab() {
		SeleniumUtils.jsClick(driver, Role.rolesTab);
		webDriverUtil.waitAWhile();
	}
	
	private void selectUser() {
		SeleniumUtils.click(driver, findElementByClassName(Role.selectables, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Role.approvalUser, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Role.selectUser, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void addApproval() {
		SeleniumUtils.jsClick(driver, findElementByClassName(Role.approvalAdd, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Role.approvalAdd, 0));
		webDriverUtil.waitAWhile();
		selectUser();
		SeleniumUtils.jsClick(driver, Role.okBtn);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.saveApprovals);
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
	
	private void addGroup() {
		SeleniumUtils.jsClick(driver, Role.responsibleGroup);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut();
	}
	
	public void fillOutRoleInfo() {
		addGroup();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		SeleniumUtils.setText(driver, Role.name, webDriverUtil.getProperty("defaultValue") + " " + timeStamp);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Role.type, webDriverUtil.getProperty("roleType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Role.environment, webDriverUtil.getProperty("environment"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Role.risk, webDriverUtil.getProperty("roleRisk"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Role.profile, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.profileLookup);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndSelect();
		SeleniumUtils.setText(driver, Role.description, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		addApproval();
		SeleniumUtils.jsClick(driver, Role.save);
		webDriverUtil.waitAWhile();
	}
	
	public void createARole(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		fillOutRoleInfo();
	}

	public void addEntitlement() {
		SeleniumUtils.setTextAndEnter(driver, Profile.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectProfile, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.addEntitlement);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Role.collectionText, "Admin");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, Role.selectCollection, 0);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.addToCollection);
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Role.collectionText, "Automation");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, Role.selectCollection, 0);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.addToCollection);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.addFilter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.runFilter);
		webDriverUtil.waitAWhile();
	/*	SeleniumUtils.jsSelectElementByIndex(driver, Role.selectOpCollection, 0);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, Role.selectOpCollection, 1);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.removeFromCollection);
		webDriverUtil.waitAWhile(); */
		SeleniumUtils.jsClick(driver, Role.saveEntitlement);
		webDriverUtil.waitAWhile();
	}
	
}

