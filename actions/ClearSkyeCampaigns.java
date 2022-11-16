package com.clearskye.test.actions;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.WebDriverUtil;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.util.SeleniumUtils;
import com.clearskye.test.util.Locator.Profile;
import com.clearskye.test.util.Locator.Campaigns;
import com.clearskye.test.util.Locator.Templates;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

public class ClearSkyeCampaigns extends AppPage {
	
	private WebDriver driver;
	//Constructor For the Page is defined Here
	public ClearSkyeCampaigns(WebDriverUtil webDriverUtil) {
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
	
	public void clickOnReviewCampaignsTab() {
		SeleniumUtils.jsClick(driver, Campaigns.reviewCampaigns);
		webDriverUtil.waitAWhile();
	}
	
	public void acceptAnAlert() {
		Alert javascriptAlert = driver.switchTo().alert();
	    System.out.println(javascriptAlert.getText()); // Get text on alert box
	    javascriptAlert.accept();
	}
	
	public void validateMoreOptions() {
		SeleniumUtils.jsClick(driver, Campaigns.moreOptions);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.addTag);
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.setTextAndTab(driver, Campaigns.tagText, "Tag");
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.moreOptions);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.toggleTemplate);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, findElementByClassName(Campaigns.iconAdd, 0));
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.closeTemplate);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.disableTemplates);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.update);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.cancelReviewEvent);
		acceptAnAlert();
		webDriverUtil.waitAWhile();
	}
	
	public void validateReview() {
		SeleniumUtils.jsClick(driver, Campaigns.notesTab);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Campaigns.notesText, webDriverUtil.getProperty("defaultValue"));
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.activityStream);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.closeActivityStream);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.personalizeForm);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Campaigns.personalizeForm);
		webDriverUtil.waitAWhile();
	}
	
	public void checkReview(TestDataBean tdb) {
		SeleniumUtils.jsClick(driver, findElementByClassName(Campaigns.selectACampaign));
		webDriverUtil.waitAWhile();
		validateReview();
		webDriverUtil.waitAWhile();
		validateMoreOptions();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
	}

}
