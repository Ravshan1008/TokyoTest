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
import com.clearskye.test.util.Locator.Connectors;
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.ObjectCategory;
import com.clearskye.test.util.Locator.Profile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeObjectCategory extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeObjectCategory(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnOCTab() {
		SeleniumUtils.jsClick(driver, ObjectCategory.tab);
		webDriverUtil.waitAWhile();
	}
	
	public void clickOnOCTypeTab() {
		SeleniumUtils.jsClick(driver, ObjectCategory.typeTab);
		webDriverUtil.waitAWhile();
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
	
	private void fillOutInformationForm(TestDataBean tdb) {
		SeleniumUtils.setText(driver, ObjectCategory.type, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, ObjectCategory.lookupType);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndSelect();
		SeleniumUtils.jsSelectElementByValue(driver, ObjectCategory.octype, webDriverUtil.getProperty("objectCategoryStatus"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, ObjectCategory.displayNameOC, webDriverUtil.getProperty("ocname"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, ObjectCategory.descriptionOC, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, ObjectCategory.accessType, webDriverUtil.getProperty("accessType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, ObjectCategory.selectRole, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, ObjectCategory.lookupRole);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndSelect();
	}
	
	public void createAnObjectCategoryType(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, ObjectCategory.newBtn);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, ObjectCategory.appliesTo, webDriverUtil.getProperty("appliesTo"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, ObjectCategory.status, webDriverUtil.getProperty("objectCategoryStatus"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, ObjectCategory.displayName, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, ObjectCategory.description, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, ObjectCategory.submit);
		webDriverUtil.waitAWhile();
	}
	
	public void createAnObjectCategory(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, ObjectCategory.newBtn);
		webDriverUtil.waitAWhile();
		fillOutInformationForm(tdb);
		SeleniumUtils.jsClick(driver, ObjectCategory.submit);
		webDriverUtil.waitAWhile();
	}

}