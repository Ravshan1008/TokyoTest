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
import com.clearskye.test.util.Locator.Configurations;
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.EntitlementConfigs;
import com.clearskye.test.util.Locator.Identity;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.Role;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeEntitlementConfigurations extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeEntitlementConfigurations(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnEntitlementConfigTab() {
		SeleniumUtils.jsClick(driver, EntitlementConfigs.tab);
		webDriverUtil.waitAWhile();
	}
	
	private void selectUser() {
		SeleniumUtils.click(driver, findElementByClassName(Role.selectables, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(Configurations.approvalUser, 4), webDriverUtil.getProperty("watchList"));
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
	
	private void fillOutNewEntitlementTypeInfo() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		SeleniumUtils.setText(driver, EntitlementConfigs.typeName, webDriverUtil.getProperty("defaultValue") + " " + timeStamp);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, EntitlementConfigs.typeDesc, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, EntitlementConfigs.submit);
		webDriverUtil.waitAWhile();
	}
	
	private void fillOutNewNameInfo() {
		SeleniumUtils.setText(driver, Configurations.nameTitle, webDriverUtil.getProperty("defaultValue") + " Name");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Configurations.description, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Configurations.maxLength, "1");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Configurations.conventionType, webDriverUtil.getProperty("conventionType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Configurations.submit);
		webDriverUtil.waitAWhile();
	}
	
	public void switchToNewWindowAndFillOut(boolean createEntitlementType) {
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver, Profile.newOwnerBtn);
	 		 webDriverUtil.waitAWhile();
	 		if (createEntitlementType) fillOutNewEntitlementTypeInfo();
	 		else fillOutNewNameInfo();
	        driver.switchTo().window(parentWindow); //cntrl to parent window
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
	    webDriverUtil.waitAWhile();
	}
	
	public void fillOutConfigInfo() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		SeleniumUtils.setText(driver, EntitlementConfigs.displayName, webDriverUtil.getProperty("defaultValue") + " " + timeStamp);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, EntitlementConfigs.environment, webDriverUtil.getProperty("environment"));
		webDriverUtil.waitAWhile();
	//	SeleniumUtils.jsClick(driver, EntitlementConfigs.environmentLookup);
	//	webDriverUtil.waitAWhile();
	//	switchToNewWindowAndSelect();
		SeleniumUtils.setText(driver, EntitlementConfigs.order, "1");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, EntitlementConfigs.typeLookup);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut(true);
		SeleniumUtils.setTextAndTab(driver, Configurations.filterText, webDriverUtil.getProperty("environment"));
		webDriverUtil.waitAWhile();
	//	SeleniumUtils.jsClick(driver, findElementByClassName(Configurations.filterLookup, 2));
	//	webDriverUtil.waitAWhile();
	//	switchToNewWindowAndSelect();
		SeleniumUtils.jsClick(driver, EntitlementConfigs.nameConventionLookup);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut(false);
		SeleniumUtils.setText(driver, EntitlementConfigs.destination, webDriverUtil.getProperty("roleType"));
		webDriverUtil.waitAWhile();
		addApproval();
		SeleniumUtils.jsClick(driver, findElementByClassName(EntitlementConfigs.navTab, 2));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, EntitlementConfigs.transferAction, 0);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, EntitlementConfigs.submit);
		webDriverUtil.waitAWhile();
	}
	
	public void createAConfig(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		fillOutConfigInfo();
	}
}
