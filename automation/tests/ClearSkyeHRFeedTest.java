package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeConnector;
import com.clearskye.test.actions.ClearSkyeHRFeed;
import com.clearskye.test.actions.ClearSkyeLogin;
import com.clearskye.test.actions.ClearSkyeProfile;
import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.automation.driver.AppTest;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.String;

public class ClearSkyeHRFeedTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Connector Creation Functionality");
				
		if (dataFileKey == null || dataFileKey.equals("")) {
			dataFileKey = "input.data.tests";
		}
		
		testData = super.readTestData(TestDataBean.class, dataFileKey);
		
		for(TestDataBean tdb : testData){				
			navigateToClearSkyeHomePage();
			break;
		}
		
		
//		runCase(testData, null);
		super.logTestEnd();
		Reporter.log("Testcase:Verify Connector Creation functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in HRFeed Creation Test!");
	}
	

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeHRFeed HRFeed = new ClearSkyeHRFeed(webDriverUtil);
		
		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		Reporter.log("Navigating to the 'DataSources' tab.");
		HRFeed.clickOnDataSourcesTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Create a DataSources' process.");
		HRFeed.Datatype(tdb);
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'Naming Convention' tab.");
		HRFeed.clickOnConnectorTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Naming Convention' process.");
		HRFeed.ImportingConnector(tdb);
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'AccountType' tab.");
		HRFeed.clickOnAccountTypeTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Account type' process.");
		HRFeed.AccountType(tdb);
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'AccountConfiguration' tab.");
		HRFeed.clickOnAccountConfigurationTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Account Configuration' process.");
		HRFeed.AccountConfiguration(tdb);
		
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'Naming Convention' tab.");
		HRFeed.clickOnEnvironmentsTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Naming Convention' process.");
		HRFeed.DeletingEnvironment(tdb);
		
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'Naming Convention' tab.");
		HRFeed.clickOnConnectorTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Naming Convention' process.");
		HRFeed.ImportingConnector(tdb);
		
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'Naming Convention' tab.");
		HRFeed.clickOnNamingConventionTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Naming Convention' process.");
		HRFeed.NamingConvention(tdb);
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'Naming Convention' tab.");
		HRFeed.clickOnProfileConfigurationTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Naming Convention' process.");
		HRFeed.ProfileConfiguration(tdb);
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'Naming Convention' tab.");
		HRFeed.clickOnEnvironmentsTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Naming Convention' process.");
		HRFeed.DeletingEnvironment(tdb);
		
		HRFeed.switchToContent();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'Naming Convention' tab.");
		HRFeed.clickOnConnectorTab();
		
		HRFeed.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Naming Convention' process.");
		HRFeed.ImportingConnector(tdb);
		
		
		webDriverUtil.waitAWhile();
		webDriverUtil.getDriver().close();
	}
}
		