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
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.RVS;
import com.clearskye.test.util.Locator.Role;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeRequestVariableSettings extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeRequestVariableSettings(WebDriverUtil webDriverUtil) {
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

	public void fillOutNewRequestForm(TestDataBean tdb) {
		selectTable();
		SeleniumUtils.setText(driver, RVS.order, "200");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, RVS.variable, "Name");
		webDriverUtil.waitAWhile();
		selectCondition();
		SeleniumUtils.jsClick(driver, RVS.active);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, RVS.andBtn);
		webDriverUtil.waitAWhile();
		selectCondition2();
		SeleniumUtils.jsClick(driver, RVS.dynamic);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, RVS.advanced);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, RVS.advanced);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, RVS.settingsValue, webDriverUtil.getProperty("location"));
		webDriverUtil.waitAWhile();
	}
	
	private void selectCondition() {
		SeleniumUtils.click(driver, findElementByClassName(RVS.arrowDown, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(RVS.dropdownText, 1), webDriverUtil.getProperty("rvsCondition"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Role.selectUser, 0));
		webDriverUtil.waitAWhile();
	}
	
	private void selectCondition2() {
		SeleniumUtils.click(driver, findElementByClassName(RVS.arrowDown, 2));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(RVS.dropdownText, 2), webDriverUtil.getProperty("rvsCondition2"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Role.selectUser, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(RVS.departmentText, 0), webDriverUtil.getProperty("department"));
		webDriverUtil.waitAWhile();
	}
	
	private void selectTable() {
		SeleniumUtils.click(driver, findElementByClassName(RVS.arrowDown, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(RVS.dropdownText, 0), webDriverUtil.getProperty("rvsTable"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(Role.selectUser, 0));
		webDriverUtil.waitAWhile();
	}
	
	public void clickOnReviewVariableSettingsTab() {
		SeleniumUtils.jsClick(driver, RVS.rvsTab);
		webDriverUtil.waitAWhile();
	}
	
	public void createANewRequest(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, RVS.newRequest);
		webDriverUtil.waitAWhile();
		fillOutNewRequestForm(tdb);
		SeleniumUtils.jsClick(driver, RVS.submit);
		webDriverUtil.waitAWhile();
	}

}
