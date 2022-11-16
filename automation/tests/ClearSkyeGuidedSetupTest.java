package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeAccessReviews;
import com.clearskye.test.actions.ClearSkyeCampaigns;
import com.clearskye.test.actions.ClearSkyeGuidedSetup;
import com.clearskye.test.actions.ClearSkyeLogin;
import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.automation.driver.AppTest;
import java.lang.String;

public class ClearSkyeGuidedSetupTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Guided Setup Functionality");
				
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
		Reporter.log("Testcase:Verify Guided Setup functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Guided Setup Test!");
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeGuidedSetup guidedSetup = new ClearSkyeGuidedSetup(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		Reporter.log("Navigating to the 'Review Campaigns' tab.");
		guidedSetup.clickOnGuidedSetupTab();
		
		guidedSetup.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'campaign review' process.");
		guidedSetup.validate(tdb);
		
		webDriverUtil.waitAWhile();
		guidedSetup.switchToContent();
		webDriverUtil.getDriver().close();
	}
}