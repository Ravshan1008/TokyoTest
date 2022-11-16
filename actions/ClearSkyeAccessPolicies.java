package com.clearskye.test.actions;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.WebDriverUtil;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.util.SeleniumUtils;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.AccessPolicies;

public class ClearSkyeAccessPolicies extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeAccessPolicies(WebDriverUtil webDriverUtil) {
		super(webDriverUtil);
		this.driver = webDriverUtil.getDriver();
	}
	
	private WebElement findElementByClassName(By by, int index) {
		List<WebElement> elements = driver.findElements(by);
		return elements.get(index);
	}
	
	private WebElement findElementByClassName(By by) {
		List<WebElement> elements = driver.findElements(by);
		return elements.get(elements.size() - 1);
	}
	
	public void navigate(String page) {
		String URL = webDriverUtil.getProperty("clearsky.homepage.url") + "/" + page;
		Reporter.log("Loading Url = " + URL);
		webDriverUtil.loadLocation(URL);
		webDriverUtil.waitAWhile();
	}
	
	public void switchToIframe() {
		driver.switchTo().frame("gsft_main");
	}
	
	public void switchToStream() {
		driver.switchTo().frame("dialog_frame");
	}
	
	public void switchToContent() {
		driver.switchTo().defaultContent();
	}
	
	public void switchToNewWindowAndSelect() {
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
	    for(String windowHandle  : handles) {
	       if(!windowHandle.equals(parentWindow)) {
	         driver.switchTo().window(windowHandle);
	         SeleniumUtils.jsClick(driver, findElementByClassName(Profile.selectIdentity, 0));
	 		 webDriverUtil.waitAWhile();
	         driver.switchTo().window(parentWindow); //cntrl to parent window
	       }
	    }
	    webDriverUtil.waitAWhile();
	    switchToIframe();
	    webDriverUtil.waitAWhile();
	}
	
	public void clickOnAccessPoliciesTab() {
		SeleniumUtils.jsClick(driver, AccessPolicies.tab);
		webDriverUtil.waitAWhile();
	}
	
	public void acceptAnAlert() {
		Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText()); // Get text on alert box
	    javascriptAlert.accept();
	}
	
	public void createAPolicy(TestDataBean tdb) {
		SeleniumUtils.setText(driver, AccessPolicies.title, "Automation Test");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, AccessPolicies.description, "Description");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, AccessPolicies.category, "Department");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, findElementByClassName(AccessPolicies.chooseField, 1), "city");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(AccessPolicies.cityName, 0), tdb.getCity());
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(AccessPolicies.andOrClauses, 3));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsScroll(driver, driver.findElement(AccessPolicies.addActionsBottom));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, findElementByClassName(AccessPolicies.chooseField, 4), "company_raw");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(AccessPolicies.cityName, 1), tdb.getCompanyName() + "e");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(AccessPolicies.andOrClauses, 1));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsScroll(driver, driver.findElement(AccessPolicies.addActionsBottom));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsSelectElementByValue(driver, findElementByClassName(AccessPolicies.chooseField, 7), "department_raw");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, findElementByClassName(AccessPolicies.cityName, 2), "IT");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, AccessPolicies.addActions);
		webDriverUtil.waitAWhile();
	}
	
	public void checkPolicies(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, AccessPolicies.newPolicy);
		webDriverUtil.waitAWhile();
		createAPolicy(tdb);
		webDriverUtil.waitAWhile();
	}

}
