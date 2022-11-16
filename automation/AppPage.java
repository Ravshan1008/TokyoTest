package com.clearskye.test.automation;

import java.util.List;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.clearskye.test.automation.WebDriverUtil;
import com.clearskye.test.automation.domain.TestDataBean;
import com.clearskye.test.automation.driver.AppTest;

public class AppPage extends AppTest {
	
	protected WebDriverUtil webDriverUtil;
	protected String errorClassName = "error";
	protected String error = "";
	protected boolean testException = false;
	
	public AppPage( WebDriverUtil webDriverUtil){
		PageFactory.initElements(webDriverUtil.getDriver(), this);
		this.webDriverUtil = webDriverUtil;
	}

	@Override
	protected void executeErrorCase(AppPage appPage, TestDataBean tdb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeSuccessCase(AppPage appPage, TestDataBean tdb) {
		// TODO Auto-generated method stub
		
	}
	
	protected void setErrorIfValueNotEqual(String message, WebElement element,
			String value) {
		String test = element.getAttribute("value");
		if( value.equals(element.getAttribute("value"))){
			return;
		}
		error = error + ":" + message;
		testException = true;
	}
	
	protected void setErrorIfValueNotInSelect(String message, WebElement element,
			String value) {
		try {
			webDriverUtil.selectDirectValue(element, value);
		} catch (NoSuchElementException e) {
			error = error + ":" + message;
			testException = true;
		}
	}
	
	public String getError() {
		if ("".equals(error) && webDriverUtil.getError(errorClassName) == null) {
			return null;
		}
		String screenError = webDriverUtil.getError(errorClassName);
		return ( error == null ? "":error) + ":" + (  screenError == null ? "":screenError);
	}
	
	public String getApperror() {
		List<WebElement> errorMsg = webDriverUtil.findAllelements("error");
		for(WebElement we : errorMsg){
			if(! we.getText().trim().isEmpty()){
			return we.getText();
			}
		}
		return null;	
	}
	
	public String getScreenerror() {
		List<WebElement> errorMsg = webDriverUtil.findAllelements("error");
		for(WebElement we : errorMsg){
			if(! we.getText().trim().isEmpty()){
			return we.getText();
			}
		}
		return null;	
	}
	
	public void resetError() {
		testException=false;
		error="";
	}
	
	public void waitForPageLoaded() {

		WebDriver driver = webDriverUtil.getDriver();
	     ExpectedCondition<Boolean> expectation = new
	ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	      };

	      WebDriverWait wait = new WebDriverWait(driver,30);
	      try {
	              wait.until(expectation);
	      } catch(Throwable error) {
	              assertFalse("Timeout waiting for Page Load Request to complete.",true);
	      }
	 }
	
	protected void setErrorIfNotReadOnly(String message, WebElement element) {
		try {
//			webDriverUtil.setText(element, "xx");
			element.clear();
			element.sendKeys("xx");
			error = error + ":" + message;
			testException = true;
		} catch (InvalidElementStateException e) {
			// This is the correct outcome
			return;
		}
	}
	
	protected void setErrorIfNotTrue(String message, boolean equals) {
		if (!equals) {
			error = message;
			testException = true;
		}

	}
}

