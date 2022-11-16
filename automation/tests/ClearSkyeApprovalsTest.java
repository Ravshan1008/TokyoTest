package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeAccount;
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

public class ClearSkyeApprovalsTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Requests Approval Functionality");
				
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
		Reporter.log("Testcase:Verify Request Approvals functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Identity Creation Test!");
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeIdentity identity = new ClearSkyeIdentity(webDriverUtil);
		ClearSkyeAccount account = new ClearSkyeAccount(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		Reporter.log("Navigating to the 'Identity Requests' tab.");
		identity.clickOnIdentityRequestsTab();
		
		identity.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Approve an Identity' process.");
		identity.approveAnIdentity(tdb);
		
		Reporter.log("Validating the 'Request Variable' process");
		identity.requestVariable();
		
		webDriverUtil.waitAWhile();
		identity.switchToContent();
		
	/*	Reporter.log("Navigating to the 'Account Requests' tab.");
		account.clickOnAccountRequestsTab();
		
		account.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Approve an Account' process.");
		account.approveAnAccount(tdb);
		
		webDriverUtil.waitAWhile();
		identity.switchToContent(); */
		
		webDriverUtil.getDriver().close();
	}
}