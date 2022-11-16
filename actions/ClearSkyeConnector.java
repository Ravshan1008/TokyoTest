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
import com.clearskye.test.util.Locator.Profile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeConnector extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeConnector(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnConnectorsTab() {
		SeleniumUtils.jsClick(driver, Connectors.tab);
		webDriverUtil.waitAWhile();
	}
	
	public void fillOutInformationForm(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Connectors.name, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Connectors.version, webDriverUtil.getProperty("version"));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.jsClick(driver, Connectors.activeLabel);
//		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Connectors.description, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Connectors.deploymentNotes, webDriverUtil.getProperty("entitlementType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Connectors.authenticationMethod, webDriverUtil.getProperty("authentication"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.dataTarget);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.endpoint);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.clientID);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.clientSecret);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.dataImport);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.credentialOptions);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.credentialTarget);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.domain);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.location);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.attributes);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Connectors.attributeName, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Connectors.attributeVal, webDriverUtil.getProperty("environment"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Connectors.settings);
		webDriverUtil.waitAWhile();
	}
	
	public void createAConnector(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Connectors.newBtn);
		webDriverUtil.waitAWhile();
		fillOutInformationForm(tdb);
		SeleniumUtils.jsClick(driver, Connectors.submit);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Connectors.selectAll, 0));
		webDriverUtil.waitAWhile();
	}

}
