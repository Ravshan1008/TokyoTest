package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeAccount;
import com.clearskye.test.actions.ClearSkyeEntitlements;
import com.clearskye.test.actions.ClearSkyeIdentity;
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

public class ClearSkyeNewEntitlementTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Entitlement Creation Functionality");
				
		if (dataFileKey == null || dataFileKey.equals("")) {
			dataFileKey = "input.data.tests";
		}
		
		testData = super.readTestData(TestDataBean.class, dataFileKey);
		
		for(TestDataBean tdb : testData){				
			navigateToClearSkyeHomePage();
			break;
		}
		
		runCase(testData, null);
		super.logTestEnd();
		Reporter.log("Testcase:Verify Entitlement Creation functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Entitlement Creation Test!");
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeEntitlements entitlement = new ClearSkyeEntitlements(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		Reporter.log("Navigating to the 'Homepage' tab.");
		entitlement.identityrequest();
		
		entitlement.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Create an Entitlement' process.");
		entitlement.switchToNewWindowAndFillOut(tdb);
		
		webDriverUtil.waitAWhile();
		entitlement.switchToContent();
		webDriverUtil.getDriver().close();
	}
}