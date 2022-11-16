package com.clearskye.test.actions;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import com.clearskye.test.util.Locator.Deletes;
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.Profile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeProfile extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeProfile(WebDriverUtil webDriverUtil) {
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
	
	private void fillOutNewOwnerInfo() {
	//	SeleniumUtils.setText(driver, Profile.userName, webDriverUtil.getProperty("userName"));
	//	webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.firstName, webDriverUtil.getProperty("firstName"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.lastName, webDriverUtil.getProperty("lastName"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.titleOwner, webDriverUtil.getProperty("title"));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.setText(driver, Profile.department, webDriverUtil.getProperty("department"));
		webDriverUtil.waitAWhile();
	/*	SeleniumUtils.setText(driver, Profile.location, webDriverUtil.getProperty("location"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.costCenter, webDriverUtil.getProperty("costCenter"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.manager, webDriverUtil.getProperty("identity"));
		webDriverUtil.waitAWhile(); */
	//	SeleniumUtils.setText(driver, Profile.password, webDriverUtil.getProperty("password"));
	//	webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.email, webDriverUtil.getProperty("email"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Profile.language, "en");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Profile.calendarIntegration, "1");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.phoneNumber, webDriverUtil.getProperty("phone"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.mobileNumber, webDriverUtil.getProperty("phone"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Profile.submit);
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
	 		 fillOutNewOwnerInfo();
	        driver.switchTo().window(parentWindow); //cntrl to parent window
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
	    webDriverUtil.waitAWhile();
	}
	
	public void fillOutProfileInfo() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		SeleniumUtils.setText(driver, Profile.objectId, "objectID " + timeStamp);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.identity, webDriverUtil.getProperty("identity"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Profile.lookupIdentity);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndSelect();
		SeleniumUtils.jsClick(driver, Profile.lookupOwner);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut();
		SeleniumUtils.setText(driver, Profile.managedBy, webDriverUtil.getProperty("identity"));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.jsClick(driver, Profile.lookupManagedBy);
		webDriverUtil.waitAWhile();
//		switchToNewWindowAndSelect();
	/*	SeleniumUtils.jsSelectElementByValue(driver, Profile.profileType, webDriverUtil.getProperty("profileType"));
		webDriverUtil.waitAWhile(); */
		SeleniumUtils.setText(driver, Profile.startDate, webDriverUtil.getProperty("startDate"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.endDate, webDriverUtil.getProperty("endDate"));
		webDriverUtil.waitAWhile();
	}
	
	public void fillOutInformationForm(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Profile.title, "Title");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.name, tdb.getFirstName());
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.surname, tdb.getLastName());
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.givenName, tdb.getFirstName());
		webDriverUtil.waitAWhile();
		String fullName = tdb.getFirstName() + " " + tdb.getLastName();
		SeleniumUtils.setText(driver, Profile.legalName, fullName);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.middleName, "J");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.preferredName, fullName);
		webDriverUtil.waitAWhile();
	}
	
	public void clickOnProfilesTab() {
		SeleniumUtils.jsClick(driver, Deletes.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Deletes.filter, webDriverUtil.getProperty("profilestab"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Deletes.profiles);
		webDriverUtil.waitAWhile();
		
	}
	
	private String generateRandomNumber(int min, int max) {
		return String.valueOf((int)Math.floor(Math.random()*(max-min+1)+min));
	}
	
	private void fillOutOrganizationInfo(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, findElementByClassName(Profile.tabs, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.employeeId, generateRandomNumber(10000, 100000));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.employeeNumber, tdb.getCardNumber());
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.employeeType, webDriverUtil.getProperty("profileType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.companyRaw, webDriverUtil.getProperty("companyRaw"));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.setText(driver, Profile.departmentRaw, webDriverUtil.getProperty("department"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.costCenterRaw, webDriverUtil.getProperty("costCenter"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.locationRaw, webDriverUtil.getProperty("location"));
		webDriverUtil.waitAWhile();
	}
	
	private void fillOutCommunicationInfo(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, findElementByClassName(Profile.tabs, 2));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.phone, tdb.getPhoneNumber());
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.mobilePhone, tdb.getPhoneNumber());
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.emailAddress, webDriverUtil.getProperty("email"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Profile.personalEmail, webDriverUtil.getProperty("email"));
		webDriverUtil.waitAWhile();
	}
	
	public void createAProfile(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Profile.createNew);
		webDriverUtil.waitAWhile();
		fillOutProfileInfo();
		fillOutInformationForm(tdb);
		fillOutOrganizationInfo(tdb);
		fillOutCommunicationInfo(tdb);
		SeleniumUtils.jsClick(driver, findElementByClassName(Profile.tabs, 3));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Profile.tabs, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Profile.save);
		webDriverUtil.waitAWhile();
	}
	
	private void addTag() {
		SeleniumUtils.jsSelectElementByIndex(driver, Profile.selectAction, 3);
		webDriverUtil.waitAWhile();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		SeleniumUtils.setText(driver, Profile.tagName, webDriverUtil.getProperty("defaultValue") + " Tag " + timeStamp);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Profile.groupsAndUsers);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Profile.addGroup);
		webDriverUtil.waitAWhile();
		WebElement groupName = findElementByClassName(Profile.groupName, 1);
		SeleniumUtils.setTextAndEnter(driver, groupName, "IT");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Profile.addUser);
		webDriverUtil.waitAWhile();
		WebElement userName = findElementByClassName(Profile.groupName, 1);
		SeleniumUtils.setTextAndEnter(driver, userName, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Profile.saveTag);
		webDriverUtil.waitAWhile();
	}
	
	public void validateProfileActions(TestDataBean tdb) {
		SeleniumUtils.setTextAndEnter(driver, Profile.search, webDriverUtil.getProperty("watchList"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectProfile, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Profile.checkProfile);
		webDriverUtil.waitAWhile();
//		addTag();
	}
}
