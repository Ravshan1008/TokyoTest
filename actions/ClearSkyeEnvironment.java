package com.clearskye.test.actions;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
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
import com.clearskye.test.util.Locator.Elements;
import com.clearskye.test.util.Locator.Environment;
import com.clearskye.test.util.Locator.Identity;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.Role;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeEnvironment extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeEnvironment(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnEnvironmentsTab() {
		SeleniumUtils.jsClick(driver, Environment.tab);
		webDriverUtil.waitAWhile();
	}
	
	private void selectUser() {
		SeleniumUtils.click(driver, findElementByClassName(Role.selectables, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Role.approvalUser, webDriverUtil.getProperty("watchList"));
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
	
	private void fillOutNewConnector(TestDataBean tdb) {
		ClearSkyeConnector connector = new ClearSkyeConnector(webDriverUtil);
		connector.fillOutInformationForm(tdb);
		SeleniumUtils.jsClick(driver, Environment.connectorSubmit);
		webDriverUtil.waitAWhile();
	}
	
	private void fillOutNewMIDServer() {
		SeleniumUtils.setText(driver, Environment.serverName, webDriverUtil.getProperty("department"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.serverStatus, webDriverUtil.getProperty("rvsCondition"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.serverSubmit);
		webDriverUtil.waitAWhile();
	}
	
	public void switchToNewWindowAndFillOut(TestDataBean tdb, String form) {
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver, Profile.newOwnerBtn);
	 		 webDriverUtil.waitAWhile();
	 		 if (form == "connector") {
	 			 fillOutNewConnector(tdb);
	 		 } else {
	 			 fillOutNewMIDServer();
	 		 }
	        driver.switchTo().window(parentWindow); //cntrl to parent window
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
	    webDriverUtil.waitAWhile();
	}
	
	private void fillOutConnectionSettings() {
		SeleniumUtils.jsSelectElementByValue(driver, Environment.location, "US");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.endpointUnlock);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.endpoint, webDriverUtil.getProperty("defaultTest"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.credentialsTarget, webDriverUtil.getProperty("defaultTest"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.domain, webDriverUtil.getProperty("defaultTest"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Environment.authMethod, webDriverUtil.getProperty("authMethod"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.clientID, webDriverUtil.getProperty("defaultTest"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.clientSecret, webDriverUtil.getProperty("defaultTest"));
		webDriverUtil.waitAWhile();
	}
	
	public void fillOutEnvironmentInfo(TestDataBean tdb) {
		SeleniumUtils.setText(driver, Environment.name, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.description, webDriverUtil.getProperty("accountType"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.connectionSettings);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.midServer);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut(tdb, "midServer");
		SeleniumUtils.setText(driver, Environment.connector, webDriverUtil.getProperty("department"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.lookupConnector);
		webDriverUtil.waitAWhile();
		switchToNewWindowAndFillOut(tdb, "connector");
		SeleniumUtils.jsClick(driver, Environment.preferences);
		webDriverUtil.waitAWhile();
		addApproval();
		SeleniumUtils.jsClick(driver, Environment.attributes);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.attributeName, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.attributeVal, webDriverUtil.getProperty("department"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.connectionSettings);
		webDriverUtil.waitAWhile();
		fillOutConnectionSettings();
		SeleniumUtils.jsClick(driver, Environment.autheritative);
		webDriverUtil.waitAWhile();
	}
	
	private void publishEnvironment() {
		SeleniumUtils.jsClick(driver, Environment.connectionSettings);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, Environment.oathType, webDriverUtil.getProperty("oathType"));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.tokenUnlock);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.tokenEndpoint, webDriverUtil.getProperty("defaultTest"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.publish);
		webDriverUtil.waitAWhile();
	}
	public void environmentLoad(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.connection, webDriverUtil.getProperty("ravshan"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.connector1, webDriverUtil.getProperty("connector"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.description, webDriverUtil.getProperty("descriptionR"));
		webDriverUtil.waitAWhile();
		//Next step is to navigate through connection settings tab.
		SeleniumUtils.jsClick(driver, Environment.connectionSettings);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.midServerR);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.midServerR, webDriverUtil.getProperty("midServerR"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.environmentID);
		webDriverUtil.waitAWhile();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		SeleniumUtils.setText(driver, Environment.environmentID, timeStamp);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.save);
		webDriverUtil.waitAWhile();

		//Next step is to navigate through attributes tab.
		SeleniumUtils.jsClick(driver, Environment.attributes);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.rootBaseR);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Environment.rootBaseR, webDriverUtil.getProperty("rootBaseR"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Environment.publishR);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Environment.importConfigurationsR, 4));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName( Environment.clickAllR,8));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Environment.status, 0));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(Environment.importbutton,0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Environment.importConfigurationsR, 0));
		webDriverUtil.waitAWhile();
		Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText());
	    javascriptAlert.accept();
		
		// Keep checking for statusText to be completed for 10 minutes
		//If all completed, exit code, stop while loop
		// If not completed, keep checking
			
			// Extract text of status of every import and assertEquals completed
			// By.className('formlink')
			
		WebElement status5 = findElementByClassName(Environment.status, 8);
		String status5Text = status5.getText();
	for (int i = 0; i < 6; i++){
			
		if (status5Text != "Compleated") {
			webDriverUtil.getDriver().navigate().refresh();
			webDriverUtil.waitAWhile();
			}
		else {
			webDriverUtil.waitAWhile();
		}
	}
			
			//Assert.assertEquals(statusText, "Completed");
	}
	
	public void createAnEnvironment(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		fillOutEnvironmentInfo(tdb);
		SeleniumUtils.jsClick(driver, Environment.save);
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		publishEnvironment();
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Environment.statuscheck,4));
		webDriverUtil.waitAWhile();
		WebElement status5 = findElementByClassName(Environment.statuscheck, 4);
		String status5Text = status5.getText();
	for (int i = 0; i < 6; i++){
			
		if (status5Text != "Compleated") {
			webDriverUtil.getDriver().navigate().refresh();
			webDriverUtil.waitAWhile();
			}
		else {
			webDriverUtil.waitAWhile();
		}
	}
		
	}
	public void statuscheck(TestDataBean tdb) {
		
		WebElement status5 = findElementByClassName(Environment.status, 8);
		String status5Text = status5.getText();
	for (int i = 0; i < 6; i++){
			
		if (status5Text != "Compleated") {
			webDriverUtil.getDriver().navigate().refresh();
			webDriverUtil.waitAWhile();
			}
		else {
			webDriverUtil.waitAWhile();
		}
	}
	}

}