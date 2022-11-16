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

public class ClearSkyeAccount extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeAccount(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnHomepageTab() {
		SeleniumUtils.jsClick(driver, Identity.home);
		webDriverUtil.waitAWhile();
	}
	
	public void selectOwnersProfile() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(Identity.profileType, 3), webDriverUtil.getProperty("testUser"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectEnvironment() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(Identity.profileType, 3), webDriverUtil.getProperty("environment"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectPreferredAccount() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 2));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(Identity.profileType, 3), webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void fillOutAccountCreationForm(TestDataBean tdb) {
		selectOwnersProfile();
		selectEnvironment();
		selectPreferredAccount();
		SeleniumUtils.setText(driver, Account.preferredAccountName, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Account.description, tdb.getTestName());
		webDriverUtil.waitAWhile();
		clickOnSubmitAccount();
	}
	
	public void clickOnSubmitAccount() {
		SeleniumUtils.jsClick(driver, Identity.submit);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Account.confirmAlert);
		webDriverUtil.waitAWhile();
	/*	Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText()); // Get text on alert box
	    javascriptAlert.accept(); */
	    webDriverUtil.waitAWhile();
	}
	
	private void getATRNumber() {
		List<WebElement> allIDs = driver.findElements(Identity.identityID); 
		WebElement idrElement = allIDs.get(allIDs.size() - 1);
		String atr = idrElement.getText().split(" ")[0];
		System.out.println("ATR number is " + atr);
		System.setProperty("ATR", atr);
	}
	
	public void clickOnAccountRequestsTab() {
		SeleniumUtils.jsClick(driver, Account.accountRequests);
		webDriverUtil.waitAWhile();
	}
	
	private void clickOnSelectAccount() {
		String atr = System.getProperty("ATR");
		System.out.println("selecting " + atr);
		WebElement account = driver.findElement(By.xpath("//a[text()='" + atr + "']"));
		System.out.println(account.getText());
		SeleniumUtils.jsClick(driver, account);
		webDriverUtil.waitAWhile();
	}
	
	public void approveAnAccount(TestDataBean tdb) {
	//	clickOnSelectAccount();
		SeleniumUtils.jsSelectElementByValue(driver, findElementByClassName(Identity.searchBy, 0), "requester");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, Identity.search, "Automation");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Identity.selectIdentity);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Account.selectAllRequests);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Account.selectAction, webDriverUtil.getProperty("approveAccount"));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
	}
	
	public void createAnAccount(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.serviceCatalogPortal, 2));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.newAccount, 7));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.jsClick(driver, Identity.cardView);
//		webDriverUtil.waitAWhile();
//		WebElement newRequest = driver.findElements(Identity.createNewRequest).get(6).findElements(Identity.linkTag).get(0);
//		SeleniumUtils.jsClick(driver, newRequest);
		webDriverUtil.waitAWhile();
		fillOutAccountCreationForm(tdb);
		webDriverUtil.waitAWhile();
		getATRNumber();
	}
}
