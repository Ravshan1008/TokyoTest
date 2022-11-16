package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeAccessPolicies;
import com.clearskye.test.actions.ClearSkyeAccessReviews;
import com.clearskye.test.actions.ClearSkyeCampaigns;
import com.clearskye.test.actions.ClearSkyeLogin;
import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.automation.driver.AppTest;
import java.lang.String;

public class ClearSkyeAccessPoliciesTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Access Policies Functionality");
				
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
		Reporter.log("Testcase:Verify Access Policies functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Access Review Test!");
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeAccessPolicies accessPolicies = new ClearSkyeAccessPolicies(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		Reporter.log("Navigating to the 'Access Policies' tab.");
		accessPolicies.clickOnAccessPoliciesTab();
		
		accessPolicies.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'campaign review' process.");
		accessPolicies.checkPolicies(tdb);
		
		webDriverUtil.waitAWhile();
		accessPolicies.switchToContent();
		webDriverUtil.getDriver().close();
	}
}