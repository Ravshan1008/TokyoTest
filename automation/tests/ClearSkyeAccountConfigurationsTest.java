package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeAccountConfigurations;
import com.clearskye.test.actions.ClearSkyeLogin;
import com.clearskye.test.actions.ClearSkyeProfile;
import com.clearskye.test.actions.ClearSkyeRole;
import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.automation.driver.AppTest;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.String;

public class ClearSkyeAccountConfigurationsTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Account Configurations Functionality");
				
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
		Reporter.log("Testcase:Verify Account Configurations functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Account Configurations Test!");
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeAccountConfigurations accountConfig = new ClearSkyeAccountConfigurations(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		Reporter.log("Navigating to the 'Account Config' tab.");
		accountConfig.clickOnAccountConfigTab();
		
		accountConfig.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Create a Profile' process.");
		accountConfig.createAConfig(tdb);
		
		webDriverUtil.waitAWhile();
		accountConfig.switchToContent();
		webDriverUtil.getDriver().close();
	}
}