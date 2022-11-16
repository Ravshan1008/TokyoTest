package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeLogin;
import com.clearskye.test.actions.ClearSkyeProfile;
import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.automation.driver.AppTest;
import com.clearskye.test.util.ConsoleOutputCapturer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.String;

public class ClearSkyeNewProfileTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Profile Creation Functionality");
				
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
		Reporter.log("Testcase:Verify Profile Creation functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Profile Creation Test!");
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb){		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeProfile profile = new ClearSkyeProfile(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		webDriverUtil.waitAWhile();

		
		Reporter.log("Navigating to the 'Profiles' tab.");
		profile.clickOnProfilesTab();
		
		profile.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Create a Profile' process.");
		profile.createAProfile(tdb);
		
		webDriverUtil.waitAWhile();
		profile.switchToContent();
		
		Reporter.log("Navigating to the 'Profiles' tab.");
		profile.clickOnProfilesTab();
		
		profile.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating Profile Actions.");
		profile.validateProfileActions(tdb);
		
		webDriverUtil.waitAWhile();
		profile.switchToContent();
		webDriverUtil.getDriver().close();
	}
}