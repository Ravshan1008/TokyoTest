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
import com.clearskye.test.util.Locator.Connectors;
import com.clearskye.test.util.Locator.Deletes;
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.Profile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeDeleteRecords extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeDeleteRecords(WebDriverUtil webDriverUtil) {
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
	
	public void selectFunction(TestDataBean tdb, int function) {
		switch(function) {
/*			case 0: 
				deleteUser(tdb);
				break;*/
			case 1:
				deleteProfile(tdb);
				break;
			case 2:
				deleteIdentity(tdb);
				break;
/*			case 3:
				deleteConnectors(tdb);
				break;*/
			case 4:
				deleteRVS(tdb);
				break;
			case 5:
				deleteRoles(tdb);
				break;
			case 6:
				deleteReviewTemplates(tdb);
				break;
			case 7:
				deleteObjectCategoryType(tdb);
				break;
			case 8:
				deleteAlerts(tdb);
				break;
/*			case 9:
				deleteNamingConvention(tdb);
				break;
/*			case 10:
				deleteAccountConfigurations(tdb);
				break;
	/*		case 11:
				deleteEntitlementConfigurations(tdb);
				break;*/
			case 12:
				deleteObjectCategory(tdb);
				break;
			case 13:
				deleteEnvironments(tdb);
				break;
				
			default:
				System.out.println("No function selected!");
				break;
		}
	}
	
	private void selectElementAndDelete() {
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectProfile, 0));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, Deletes.deleteUser);
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.ok, 0));
		webDriverUtil.waitAWhile();
	}
	
	
	private void deleteReviewTemplates(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "reviewtemplates");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectReviewTemplates, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteNamingConvention(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Naming Conventions");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectNamingConventions, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 1);
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteConnectors(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Connectors");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectConnectors, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteAccountConfigurations(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Account Configurations");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectAccountConfig, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 0);
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("environment"));
		webDriverUtil.waitAWhile();
		
//		selectElementAndDelete(); no delete button
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteAlerts(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Alerts");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectAlerts, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 3);
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteEntitlementConfigurations(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Entitlement Configurations");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectEC, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 2);
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteObjectCategoryType(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Object Category Type");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectOCT, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteObjectCategory(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Object Category");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectObjectCategory, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteIdentity(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Identities");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectIdentities, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 0);;
		webDriverUtil.waitAWhile();
//		SeleniumUtils.setTextAndEnter(driver, Deletes.search, tdb.getFirstName());
//		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteAccounts(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Account Requests");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectAccountRequests, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 2);;
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(Deletes.search, 5), "Pending");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectProfile, 0));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, Deletes.selectAll);
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByValue(driver, Deletes.selectAction, webDriverUtil.getProperty("deleteOption"));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, Deletes.ok);
		webDriverUtil.waitAWhile();
		
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteRVS(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Request Variable Settings");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectRVS, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 0);;
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, "Name");
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteUser(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Users");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectUsers, 1));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.user, 0));
		webDriverUtil.waitAWhile();
		
//		SeleniumUtils.jsClick(driver, Deletes.selectUser);
//		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, Deletes.deleteUser);
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, Deletes.ok);
		webDriverUtil.waitAWhile();
		
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteProfile(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Profiles");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectProfiles, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 2);;
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, tdb.getFirstName());
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	
	private void deleteRoles(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Deletes.filter, "Roles");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectRoles, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 1);;
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
	}
	private void deleteEnvironments (TestDataBean tdb) {
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, Deletes.filter );
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.filter, "Environments");
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.formcontrol, 1));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setText(driver, findElementByClassName(Deletes.formcontrol, 1), webDriverUtil.getProperty("ravshan"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent();
		
		
		
	/*	SeleniumUtils.jsClick(driver, findElementByClassName(Deletes.selectNamingConventions, 0));
		webDriverUtil.waitAWhile();
		
		switchToIframe();
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(Deletes.searchBy, 0), 1);
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.setTextAndEnter(driver, Deletes.search, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		
		selectElementAndDelete();
		webDriverUtil.waitAWhile();
		switchToContent(); */
		
	}
}