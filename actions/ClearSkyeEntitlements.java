package com.clearskye.test.actions;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
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
import com.clearskye.test.util.Locator.Identity;
import com.clearskye.test.util.Locator.Profile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeEntitlements extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeEntitlements(WebDriverUtil webDriverUtil) {
		super(webDriverUtil);
		this.driver = webDriverUtil.getDriver();
	}
	
	private WebElement findElementByClassName(By by, int index) {
		List<WebElement> elements = driver.findElements(by);
		return elements.get(index);
	}
	
	private WebElement getLastElementByClassName(By by) {
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
	
	public void switchToContent() {
		driver.switchTo().defaultContent();
	}
	
	public void clickOnHomepageTab() {
		SeleniumUtils.jsClick(driver, Identity.home);
		webDriverUtil.waitAWhile();
	}
	
	public void clickOnSubmitEntitlement() {
		SeleniumUtils.jsClick(driver, Identity.submit);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Account.confirmAlert);
		webDriverUtil.waitAWhile();
	/*	Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText()); // Get text on alert box
	    javascriptAlert.accept(); */
	    webDriverUtil.waitAWhile();
	}
	
	public void selectEnvironment() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 2));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, getLastElementByClassName(Identity.profileType), webDriverUtil.getProperty("environment"));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectRequest() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 0));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, getLastElementByClassName(Identity.profileType), webDriverUtil.getProperty("entitlement_request"));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectSystemType() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 1));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, getLastElementByClassName(Identity.profileType), webDriverUtil.getProperty("entitlement_system_type"));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectEntitlemenType() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 5));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, getLastElementByClassName(Identity.profileType), webDriverUtil.getProperty("entitlementType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectAccount() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 6));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, getLastElementByClassName(Identity.profileType), webDriverUtil.getProperty("firstName"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	private void fillOutNewEntitlementForm() {
		selectRequest();
		selectSystemType();
		selectEnvironment();
		SeleniumUtils.setText(driver, Account.entitleName, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		selectEntitlemenType();
		SeleniumUtils.setText(driver, Account.entitleDesc, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
	}
	
	public void createAnEntitlement(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.serviceCatalogPortal, 2));
		webDriverUtil.waitAWhile();
	//	SeleniumUtils.jsClick(driver, Identity.cardView);
	//	webDriverUtil.waitAWhile();
		WebElement newRequest = driver.findElements(Identity.createNewRequest).get(0).findElements(Identity.linkTag).get(0);
		SeleniumUtils.jsClick(driver, newRequest);
		webDriverUtil.waitAWhile();
		fillOutNewEntitlementForm();
		clickOnSubmitEntitlement();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
	}
}
