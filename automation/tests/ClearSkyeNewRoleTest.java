package com.clearskye.test.automation.tests;

//added by Tim 9/19/21


import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearskye.test.actions.ClearSkyeLogin;
import com.clearskye.test.actions.ClearSkyeProfile;
import com.clearskye.test.actions.ClearSkyeRole;
import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.automation.driver.AppTest;
import com.clearskye.test.util.Locator.Role;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.String;

public class ClearSkyeNewRoleTest extends AppTest {
	private List<TestDataBean> testData;
    
	@Parameters({ "dataFileKey"})
	@Test
	public void testMain(String dataFileKey) throws Exception{
		super.logTestStart();
		
		Reporter.log("Testcase: Role Creation Functionality");
				
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
		Reporter.log("Testcase:Verify Role Creation functionality ended");
	}
	
	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		Reporter.log("Failure happened in Profile Creation Test!");
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {		
		ClearSkyeLogin user = new ClearSkyeLogin(webDriverUtil);
		ClearSkyeRole role = new ClearSkyeRole(webDriverUtil);

		Reporter.log("Validating the 'User Login' process.");
		user.login();
		
		webDriverUtil.waitAWhile();
		
		Reporter.log("Navigating to the 'Roles' tab.");
		role.clickOnRolesTab();
		
		webDriverUtil.waitAWhile();
		
		role.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Create a Profile' process.");
		role.createARole(tdb);
		
		webDriverUtil.waitAWhile();
		role.switchToContent();
		
		Reporter.log("Navigating to the 'Roles' tab.");
		role.clickOnRolesTab();
		webDriverUtil.waitAWhile();
		
		role.switchToIframe();
		webDriverUtil.waitAWhile();
		
		Reporter.log("Validating the 'Role Entitlement Addition' process.");
		role.addEntitlement();// can not find admin in collection text (driver, Role.collectionText, "Admin")
		
		webDriverUtil.waitAWhile();
		role.switchToContent();
		webDriverUtil.getDriver().close();
	}
}