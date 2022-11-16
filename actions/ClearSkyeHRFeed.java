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
import org.testng.Assert;
import org.testng.Reporter;

import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.WebDriverUtil;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.util.SeleniumUtils;
import com.clearskye.test.util.Locator.Account;
import com.clearskye.test.util.Locator.DataSources;
import com.clearskye.test.util.Locator.Deletes;
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

public class ClearSkyeHRFeed extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeHRFeed(WebDriverUtil webDriverUtil) {
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
	public void clickOnDataSourcesTab() {
		SeleniumUtils.jsClick(driver, Deletes.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Deletes.filter, webDriverUtil.getProperty("datasource"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.datasources);
		webDriverUtil.waitAWhile();
		
		
	}
	public void clickOnConnectorTab() {
		SeleniumUtils.jsClick(driver, Deletes.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Deletes.filter, webDriverUtil.getProperty("connectorR"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.connectors);
		webDriverUtil.waitAWhile();
		
		
	}
	public void clickOnAccountConfigurationTab() {
		SeleniumUtils.jsClick(driver, Deletes.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, Deletes.filter, webDriverUtil.getProperty("accountconfigurations"));
		webDriverUtil.waitAWhile();
		
	}
	
	public void clickOnAccountTypeTab() {
		SeleniumUtils.jsClick(driver, Deletes.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, Deletes.filter, webDriverUtil.getProperty("accounttype"));
		webDriverUtil.waitAWhile();
	}
	
	public void clickOnNamingConventionTab() {
		SeleniumUtils.jsClick(driver, Deletes.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, Deletes.filter, webDriverUtil.getProperty("namingconvention"));
		webDriverUtil.waitAWhile();
	}
	public void clickOnProfileConfigurationTab() {
		SeleniumUtils.jsClick(driver, Deletes.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, Deletes.filter, webDriverUtil.getProperty("profileconfigurations"));
		webDriverUtil.waitAWhile();
	}
	public void clickOnEnvironmentsTab() {
		SeleniumUtils.jsClick(driver, Deletes.filter);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, Deletes.filter, webDriverUtil.getProperty("environmentHRFeed"));
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
	
	
	public void Datatype(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.datasourcesname);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.datasourcesname, webDriverUtil.getProperty("datasourcename"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.datasourcesimport);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.datasourcesimport, webDriverUtil.getProperty("datasourcesimport"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.datasourcesimportset);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.datasourcesimportset, webDriverUtil.getProperty("datasourcesimportset"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.type, 3), 1);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.midserver);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.midserver, webDriverUtil.getProperty("midserver"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, DataSources.format, 1);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.port);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.port, webDriverUtil.getProperty("port"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.username);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.username, webDriverUtil.getProperty("username1"));
		webDriverUtil.waitAWhile();		
		SeleniumUtils.jsClick(driver, DataSources.password);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.password, webDriverUtil.getProperty("passwordR"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.server);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.server, webDriverUtil.getProperty("serverR"));
		webDriverUtil.waitAWhile();		
		SeleniumUtils.jsClick(driver, DataSources.name);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.name, webDriverUtil.getProperty("nameR"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.database);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.database, webDriverUtil.getProperty("databaseR"));
		webDriverUtil.waitAWhile();		
		SeleniumUtils.click(driver, DataSources.bottom);
		webDriverUtil.waitAWhile();
		
		/*SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.type, 0), 4);
		webDriverUtil.waitAWhile();*/
		/*SeleniumUtils.click(driver, findElementByClassName(DataSources.header, 2));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(DataSources.header, 3));
		webDriverUtil.waitAWhile();	
		SeleniumUtils.click(driver, findElementByClassName(DataSources.select, 0));
		webDriverUtil.waitAWhile();	*/
		
	}
	public void Connector(TestDataBean tdb) {
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.type, 0), 1);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.type, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.type, 1), webDriverUtil.getProperty("hrdatabases"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.select, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.select, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.caption, 4));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.select, 15));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.def, 1));
		webDriverUtil.waitAWhile();
		Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText());
	    javascriptAlert.accept();
		
		
		
		
/*		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.identity);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.identity, webDriverUtil.getProperty("identityprovider"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, DataSources.authentication, 5);
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(DataSources.btn , 0));
		webDriverUtil.waitAWhile();
		// Finder of the last saved record is missing
		
/*		SeleniumUtils.click(driver, findElementByClassName(DataSources.btn , 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, DataSources.classifier);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, DataSources.classifier, 3);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, DataSources.description, 0);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.query);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.query, webDriverUtil.getProperty("query"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(DataSources.caption, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.row, 1));
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.row, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.row, 1), webDriverUtil.getProperty("rowR"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.vt, 11));
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.vt, 11));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.vt, 11), webDriverUtil.getProperty("vtR"));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.row, 1));
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.row, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(DataSources.row, 1), webDriverUtil.getProperty("row1"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.row, 1), 1);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.bt, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.vt, 18));
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.vt, 18));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.vt, 18), webDriverUtil.getProperty("vt1"));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.row, 1));
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.row, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.row, 1), webDriverUtil.getProperty("row2"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.vt, 25));
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.vt, 25));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.vt, 25), webDriverUtil.getProperty("vt2"));
		webDriverUtil.waitAWhile();*/
		
		
		
	}
	public void ImportingConnector(TestDataBean tdb) {
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.type, 1), webDriverUtil.getProperty("importconnectorsHR"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formlink, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.captiontab, 3));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formlink, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formlink, 12));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.defaultbtn, 5));
		webDriverUtil.waitAWhile();
		Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText());
	    javascriptAlert.accept();
	    
		
		
	}
	
	public void AccountType(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.naming);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.naming, webDriverUtil.getProperty("naming"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.descriptioning);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.descriptioning, webDriverUtil.getProperty("naming"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.primary);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.naming);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.naming, webDriverUtil.getProperty("naming1"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.descriptioning);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.descriptioning, webDriverUtil.getProperty("naming1"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.primary);
		webDriverUtil.waitAWhile();
		
		
		
	}
	public void AccountConfiguration(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.display, webDriverUtil.getProperty("displayname"));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.jsClick(driver, DataSources.accountconfig);
//		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.accounttype);
		webDriverUtil.waitAWhile();
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectIdentity, 8));
	 		 webDriverUtil.waitAWhile();
	         driver.switchTo().window(parentWindow);
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
//		SeleniumUtils.setTextAndTab(driver, DataSources.accountconfig, webDriverUtil.getProperty("HRContractor"));
//		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.environment);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, DataSources.environment, webDriverUtil.getProperty("HRDatabase"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.order);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.order, webDriverUtil.getProperty("orderaccount"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.reference, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.reference, 0), webDriverUtil.getProperty("referenceaccount"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.tableaction, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(DataSources.chosen, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.selectinput, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.selectinput, 1), webDriverUtil.getProperty("nativetype"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.condoperator, 1), 4);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.tableinput, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(DataSources.tableinput, 1), webDriverUtil.getProperty("tableinput"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formaction, 1));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.display, webDriverUtil.getProperty("displayname"));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.jsClick(driver, DataSources.accountconfig);
//		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.accounttype);
		webDriverUtil.waitAWhile();
		String parentWindoww = driver.getWindowHandle();
		Set<String> handless =  driver.getWindowHandles();
	    for(String windowHandle  : handless) {
	       if(!windowHandle.equals(parentWindoww)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectIdentity, 9));
	 		 webDriverUtil.waitAWhile();
	         driver.switchTo().window(parentWindoww);
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
//		SeleniumUtils.setTextAndTab(driver, DataSources.accountconfig, webDriverUtil.getProperty("naming1"));
//		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.environment);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, DataSources.environment, webDriverUtil.getProperty("HRDatabase"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.order);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.order, webDriverUtil.getProperty("orderaccount"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.reference, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.reference, 0), webDriverUtil.getProperty("referenceaccount"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.tableaction, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(DataSources.chosen, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.selectinput, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.selectinput, 1), webDriverUtil.getProperty("nativetype"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.condoperator, 1), 4);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.tableinput, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(DataSources.tableinput, 1), webDriverUtil.getProperty("tableinput"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formaction, 1));
		webDriverUtil.waitAWhile();
		
		
	}
	public void DeletingEnvironment(TestDataBean tdb) {
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.type, 0), 1);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formcontrol, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.formcontrol, 1), webDriverUtil.getProperty("referenceaccount"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formlink, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.addactionbtn, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.addaction, 2));
		webDriverUtil.waitAWhile();
		
		
		
	}
	
	
	public void NamingConvention(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.namingconvention);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.namingconvention, webDriverUtil.getProperty("namingconventions"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.descriptionnaming);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.descriptionnaming, webDriverUtil.getProperty("descriptionnaming"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, DataSources.typenaming, 3);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.lengthnaming);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.lengthnaming, webDriverUtil.getProperty("lengthnaming"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.defaultbtn, 2));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.conventionelement);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.conventionelement, webDriverUtil.getProperty("conventionelement"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.conventionvalue);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.conventionvalue, webDriverUtil.getProperty("conventionvalue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.primarybtn, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.conventionelement);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.conventionelement, webDriverUtil.getProperty("conventionElement1"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, DataSources.elementtype, 0);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, DataSources.conventionfield, 13);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.primarybtn, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.conventiondescription);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.conventiondescription, webDriverUtil.getProperty("conventiondescription"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, DataSources.conventionelements, 2);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.primarybtn, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formbtn, 5));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formbtn, 6));
		webDriverUtil.waitAWhile(); 
		
		
		
		
		
		
		
	}
	public void ProfileConfiguration(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.active);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, DataSources.environmentprofile, webDriverUtil.getProperty("HRDatabase"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(DataSources.selectchosen, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.selectinput, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.selectinput, 0), webDriverUtil.getProperty("nativetype"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.descriptionprofile);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.descriptionprofile, webDriverUtil.getProperty("descriptionprofile"));
		webDriverUtil.waitAWhile();
//		SeleniumUtils.jsClick(driver, DataSources.profilesettings);
		SeleniumUtils.jsClick(driver, DataSources.namingconventions);
		webDriverUtil.waitAWhile();
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectIdentity, 0));
	 		 webDriverUtil.waitAWhile();
	         driver.switchTo().window(parentWindow);
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
//	    webDriverUtil.waitAWhile();;
//		SeleniumUtils.setTextAndEnter(driver, DataSources.profilesettings, webDriverUtil.getProperty("contractorprofile"));
//		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.profiletype);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, DataSources.profiletype, webDriverUtil.getProperty("contractorprofile"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.condoperator, 0), 4);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formbtncontrol, 6));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.formbtncontrol, 6), webDriverUtil.getProperty("tableinput"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.primarybtn, 1));
		webDriverUtil.waitAWhile();
		
		SeleniumUtils.jsClick(driver, Role.newRole);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.active);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, DataSources.environmentprofile, webDriverUtil.getProperty("HRDatabase"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.click(driver, findElementByClassName(DataSources.selectchosen, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.selectinput, 0));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.selectinput, 0), webDriverUtil.getProperty("nativetype"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.descriptionprofile);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, DataSources.descriptionprofile, webDriverUtil.getProperty("descriptionprofile"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.profilesettings);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, DataSources.profilesettings, webDriverUtil.getProperty("employeeprofiledefault"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, DataSources.profiletype);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, DataSources.profiletype, webDriverUtil.getProperty("employeeprof"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByIndex(driver, findElementByClassName(DataSources.condoperator, 0), 4);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.formbtncontrol, 6));
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndEnter(driver, findElementByClassName(DataSources.formbtncontrol, 6), webDriverUtil.getProperty("tableinput1"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(DataSources.primarybtn, 1));
		webDriverUtil.waitAWhile();
		
		
		
		
	}
	
	
	
}