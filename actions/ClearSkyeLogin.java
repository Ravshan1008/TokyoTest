package com.clearskye.test.actions;

import java.util.List;

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
import com.clearskye.test.util.SeleniumUtils;
import com.clearskye.test.util.Locator.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ClearSkyeLogin extends AppPage {
	
	private WebDriver driver;
	private String emailAddress;
	private String password;
	//Constructor For the Page is defined Here
	public ClearSkyeLogin(WebDriverUtil webDriverUtil) {
		super(webDriverUtil);
		this.driver = webDriverUtil.getDriver();
		emailAddress = System.getProperty("username");
		password = System.getProperty("password");
		System.out.println("Email: " + emailAddress);
		System.out.println("Password: " + password);
		if (emailAddress == null || password == null) {
			emailAddress = webDriverUtil.getProperty("username");
			password = webDriverUtil.getProperty("password");
		}
		System.out.println("Email: " + emailAddress);
		System.out.println("Password: " + password);
	}
	
	private WebElement findElementByClassName(By by, int index) {
		List<WebElement> elements = driver.findElements(by);
		return elements.get(index);
	}
	
	public void saveEmailAndPassword() {
		System.setProperty("emailAddress", emailAddress);
		System.setProperty("password", password);
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void navigate(String page) {
		String URL = webDriverUtil.getProperty("clearsky.homepage.url") + "/" + page;
		Reporter.log("Loading Url = " + URL);
		webDriverUtil.loadLocation(URL);
		webDriverUtil.waitAWhile();
	}
	
	public void login() {
		//driver.switchTo().frame("gsft_main");
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Elements.username, emailAddress);
		webDriverUtil.waitAWhile();
		SeleniumUtils.setText(driver, Elements.password, password);
		webDriverUtil.waitAWhile();
		SeleniumUtils.jsClick(driver, Elements.loginBtn);
		webDriverUtil.waitAWhile();
		webDriverUtil.waitAWhile();
		webDriverUtil.getDriver().navigate().refresh();
		webDriverUtil.waitAWhile();
		//driver.switchTo().defaultContent();
	}

}
