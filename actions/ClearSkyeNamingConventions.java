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
import com.clearskye.test.util.Locator.Conventions;
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.Identity;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.Role;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeNamingConventions extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeNamingConventions(WebDriverUtil webDriverUtil) {
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

	public void clickOnConventionsTab() {
		SeleniumUtils.jsClick(driver, Conventions.tab);
		webDriverUtil.waitAWhile();
	}
	
	private void addElement() {
		SeleniumUtils.setText(driver, Conventions.elementDesc, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Conventions.elementVal, "2");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Conventions.elementMaxLen, "3");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Conventions.elementOrder, "1");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Conventions.elementAdd);
		webDriverUtil.waitAWhile();
	}
	
	public void fillOutConventionInfo() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		SeleniumUtils.setText(driver, Conventions.title, webDriverUtil.getProperty("defaultValue") + " " + timeStamp);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Conventions.description, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, Conventions.type, 1);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Conventions.max_length, "2");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Conventions.nextBtn, 1));
		webDriverUtil.waitAWhile();
		addElement();
		webDriverUtil.waitAWhile();
		try {
			SeleniumUtils.jsClick(driver, findElementByClassName(Conventions.nextBtn, 1));
			webDriverUtil.waitAWhile();
			webDriverUtil.waitAWhile();
			SeleniumUtils.jsClick(driver, Conventions.delete);
			webDriverUtil.waitAWhile();
			SeleniumUtils.jsClick(driver, Conventions.okBtn);
			webDriverUtil.waitAWhile();
		} catch(Exception ex) {
			return;
		}
	}
	
	public void createAConvention(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		fillOutConventionInfo();
	}
}
