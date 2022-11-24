package com.clearskye.test.actions;

import java.util.Calendar;
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

public class ClearSkyeIdentity extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeIdentity(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnIdentityRequestsTab() {
		SeleniumUtils.jsClick(driver, Identity.identityRequests);
		webDriverUtil.waitAWhile();
	}
	
	private void clickOnSelectIdentity() {
		String idr = System.getProperty("IDR");
		System.out.println("selecting " + idr);
		WebElement identity = driver.findElement(By.xpath("//a[text()='" + idr + "']"));
		System.out.println(identity.getText());
		SeleniumUtils.jsClick(driver, identity);
		webDriverUtil.waitAWhile();
	}
	
	public void switchToNewWindowAndFillOut(TestDataBean tdb) {
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver,findElementByClassName(Identity.showMore, 0));
	 		 webDriverUtil.waitAWhile();
	 		changeAnIdentity(tdb);
	        driver.switchTo().window(parentWindow); //cntrl to parent window
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
	    webDriverUtil.waitAWhile();
	}
	
	public void approveAnIdentity(TestDataBean tdb) {
	//	clickOnSelectIdentity();
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Identity.searchBy, 0), 0);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, Identity.search, "Identity Create : TestFirstName");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(Identity.stageField, 4), "Pending");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.selectIdentity, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Identity.selectAllRequests);
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Identity.selectAction, webDriverUtil.getProperty("approveOption"));
		webDriverUtil.waitAWhile();
	}
	
	public void requestVariable() {
		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.requestVariablesTab, 3));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Identity.newBtn);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Identity.variableDefinition, webDriverUtil.getProperty("requestVariableDefinition"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Identity.setBy, webDriverUtil.getProperty("requestVariableSetBy"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Identity.value, webDriverUtil.getProperty("rvsTable"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Identity.previousValue, webDriverUtil.getProperty("ocname"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Identity.diagnostics, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Identity.warning);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Identity.submitBtn);
		webDriverUtil.waitAWhile();
	}
	
	public void clickOnSubmitIdentity() {
		SeleniumUtils.jsClick(driver, Identity.submit);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Account.confirmAlert);
		webDriverUtil.waitAWhile();
	/*	Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText()); // Get text on alert box
	    javascriptAlert.accept(); */
	    webDriverUtil.waitAWhile();
	}
	
	public void selectManagedBy(int index) {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, index));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(Identity.profileType, index + 4), webDriverUtil.getProperty("testUser"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectProfileType(int index) {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, index - 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(Identity.profileType, index + 4), "vendor");
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectDepartament(int index) {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, index + 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(Identity.profileType, index + 4), webDriverUtil.getProperty("departmentidentity"));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectLocation(int index) {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, index + 3));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(Identity.profileType, index + 4), "California");
		webDriverUtil.waitAWhile();
	//	SeleniumUtils.assertElementExists(driver, Account.selectDropdown, 0);
	//	webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void fillOutIdentityInformation(TestDataBean tdb, int index) {
		SeleniumUtils.setText(driver, Identity.givenName, tdb.getFirstName());
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Identity.preferredName, tdb.getFirstName());
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Identity.middleName, "TestMiddleName");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Identity.surname, tdb.getLastName());
		webDriverUtil.waitAWhile();
		String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		SeleniumUtils.setText(driver, Identity.startDate, todayDate);
		webDriverUtil.waitAWhile();
		selectProfileType(index);
//		selectManagedBy(index);
		SeleniumUtils.setText(driver, Identity.title, tdb.getTestName());
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Identity.phoneNumber, tdb.getPhoneNumber());
		webDriverUtil.waitAWhile();
//		selectDepartament(index);
//		selectLocation(index);
		SeleniumUtils.setText(driver, Identity.mobileNumber, tdb.getPhoneNumber());
		webDriverUtil.waitAWhile();
		clickOnSubmitIdentity();
		webDriverUtil.waitAWhile();
	}
	
	private void getIDRNumber() {
		List<WebElement> allIDs = driver.findElements(Identity.identityID); 
		WebElement idrElement = allIDs.get(allIDs.size() - 1);
		String idr = idrElement.getText().split(" ")[0];
		System.out.println("idr number is " + idr);
		System.setProperty("IDR", idr);
	}
	
	public void createAnIdentity(TestDataBean tdb) {
//		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.serviceCatalogPortal, 2));
//		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.serviceCatalogPortal, 2));
		webDriverUtil.waitAWhile();
	//	SeleniumUtils.jsClick(driver, Identity.cardView);
	//	webDriverUtil.waitAWhile();
		WebElement newRequest = driver.findElements(Identity.createNewRequest).get(4).findElements(Identity.linkTag).get(0);
		SeleniumUtils.jsClick(driver, newRequest);
		webDriverUtil.waitAWhile();
		fillOutIdentityInformation(tdb, 1);
//		getIDRNumber();
	}
	
	public void selectIdentity() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables1, 0));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.setText(driver, findElementByClassName(Identity.profileType, 7), webDriverUtil.getProperty("profileType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void selectProfile() {
		SeleniumUtils.click(driver, findElementByClassName(Account.selectables, 2));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.setText(driver, findElementByClassName(Identity.profiletype, 2), webDriverUtil.getProperty("testUser"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Account.selectDropdown, 1));
		webDriverUtil.waitAWhile();
	}
	
	public void identityrequest() {
		SeleniumUtils.jsClick(driver, Account.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, Account.filter, webDriverUtil.getProperty("requestscatalog"));
		webDriverUtil.waitAWhile();
		
	}
	
	public void changeAnIdentity(TestDataBean tdb) {
 		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.requests, 45));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.jsClick(driver, findElementByClassName(Identity.serviceCatalogPortal, 2));
		webDriverUtil.waitAWhile();
	//	SeleniumUtils.jsClick(driver, Identity.cardView);
	//	webDriverUtil.waitAWhile();
		
/*		WebElement newRequest = driver.findElements(Identity.createNewRequest).get(3).findElements(Identity.linkTag).get(0);
		SeleniumUtils.jsClick(driver, newRequest);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Identity.replace);*/
		
		SeleniumUtils.jsClick(driver, findElementByClassName( Identity.createadditional, 0));
		webDriverUtil.waitAWhile();
		selectIdentity();
		selectProfile();
		fillOutIdentityInformation(tdb, 3);
		webDriverUtil.waitAWhile();
	}
}

