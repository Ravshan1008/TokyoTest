package com.clearskye.test.automation.tests;

import java.util.ArrayList;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeDeleteRecords;
import com.clearskye.test.actions.ClearSkyeLogin;
import com.clearskye.test.actions.ClearSkyeProfile;
import com.clearskye.test.actions.ClearSkyeTemplates;
import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.automation.driver.AppTest;
import com.google.common.base.Function;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.String;

public class ClearSkyeDeleteRecordsTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Delete Records Functionality");
				
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
		Reporter.log("Testcase:Verify Delete Records functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Delete Records Test!");
	}
	
	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) throws Exception {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeDeleteRecords testObject = new ClearSkyeDeleteRecords(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();

		StringBuilder allExceptions = new StringBuilder();	
		int numberOfDeletes = 12;
		for (int i = 0; i < numberOfDeletes; i++) {
	 		try {
	 			testObject.selectFunction(tdb, i);
			} catch (Exception e) {
				allExceptions.append(e.getMessage());
			}
		}
		
/*		if (allExceptions.length() != 0) {
			String anyExceptions = allExceptions.toString();
			throw new Exception(anyExceptions);
		}*/
		
		webDriverUtil.getDriver().close();
	}
}