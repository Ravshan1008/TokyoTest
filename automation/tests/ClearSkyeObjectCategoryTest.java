package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeConnector;
import com.clearskye.test.actions.ClearSkyeLogin;
import com.clearskye.test.actions.ClearSkyeObjectCategory;
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

public class ClearSkyeObjectCategoryTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Object Category Creation Functionality");
				
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
		Reporter.log("Testcase:Verify Object Category Creation functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Object Category Creation Test!");
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeObjectCategory objectCategory = new ClearSkyeObjectCategory(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		Reporter.log("Navigating to the 'Object Category Type' tab.");
		objectCategory.clickOnOCTypeTab();
		
		objectCategory.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Create an Object Category Type' process.");
		objectCategory.createAnObjectCategoryType(tdb);
		
		webDriverUtil.waitAWhile();
		objectCategory.switchToContent();
		
		Reporter.log("Navigating to the 'Object Category' tab.");
		objectCategory.clickOnOCTab();
		
		objectCategory.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Create an Object Category' process.");
		objectCategory.createAnObjectCategory(tdb);
		
		webDriverUtil.waitAWhile();
		objectCategory.switchToContent();
		webDriverUtil.getDriver().close();
	}
}