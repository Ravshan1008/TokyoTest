package com.clearskye.test.automation;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.awt.Toolkit;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;
import org.testng.Reporter;

public class WebDriverUtil {

	private Properties properties;
	private WebDriver driver;
	private long defaultTimeOutSecs;
	private final Log log = LogFactory.getLog(WebDriverUtil.class);

	public void setupDriver() {
		// Chrome Browser Launch
		if (getProperty("Platforms").equalsIgnoreCase("web")) {
			System.setProperty("webdriver.chrome.driver", getProperty("chromedriver"));

			ChromeOptions options = new ChromeOptions();
		//	options.addArguments("--headless"); // only if you are ACTUALLY running headless
			options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
			options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
			options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
			options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
			options.addArguments("--start-maximized");
			options.addArguments("enable-automation");
			driver = new ChromeDriver(options);
		//	maximizeBrowserWithToolkitapi();
			// driver.manage().window().maximize();
			WebElement html = driver.findElement(By.tagName("html"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		}
		// Android Mobile Chrome Emulator launch
		else if (getProperty("Platforms").equalsIgnoreCase("android")) {
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Google Nexus 5");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);
		}
	}

	public void setDefaults() {
		defaultTimeOutSecs = Long.parseLong(getProperty("default.wait.secs"));
		System.out.println("Default timeout is " + defaultTimeOutSecs);
	}

	public void loadProperties() {
		PropertyFileUtil propertyFileUtil = new PropertyFileUtil();
		properties = propertyFileUtil.getProperties();
	}

	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	public WebElement enterTextForElement(String elementIdKey, String valueKey) {
		String text = getProperty(valueKey);
		return enterDirectTextForElement(elementIdKey, text);
	}

	public WebElement enterDirectTextForElement(String elementIdKey, String value) {
		WebElement element = waitForElement(elementIdKey);
		element.clear();
		element.sendKeys(value);
		return element;
	}

	public WebElement selectDirectValue(String elementIdKey, String value) {
		WebElement element = waitForElement(elementIdKey);
		Select select = new Select(element);
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeOutSecs);
		element = wait.until(ExpectedConditions.visibilityOf(element));
		select.selectByVisibleText(value);
		return element;
	}

	public WebElement selectDirectValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		return element;
	}

	public WebElement selectDirectValueIfNotBlank(WebElement element, String value) {
		if (null != value && !value.trim().equals("")) {
			Select select = new Select(element);
			select.selectByVisibleText(value);
		}
		return element;
	}

	public int getSelectListLength(WebElement element) {
		Select select = new Select(element);
		return select.getOptions().size();
	}

	public WebElement selectDirectIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		return element;
	}

	public WebElement selectValue(String elementIdKey, String valueKey) {
		String text = getProperty(valueKey);
		WebElement element = selectDirectValue(elementIdKey, text);
		return element;
	}

	public void waitForPage(String pageTitleKey) {
		// String pageTitle = getProperty( pageTitleKey );
		waitForElement(pageTitleKey);
	}

	public WebElement waitForElementByXpath(String elementIdKey) {
		String elementId = getProperty(elementIdKey);
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, defaultTimeOutSecs);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementId)));
		} catch (Exception e) {
			fail("Failed to get element [" + elementId + "] specified by key [" + elementIdKey + "]");
		}
		return element;
	}

	public WebElement waitForElement(String elementIdKey) {
		String elementId = getProperty(elementIdKey);
		return driver.findElement(By.id(elementIdKey));
	}

	public WebElement waitTillElementVisible(WebElement element) throws InterruptedException {
		int start = 0;
		int time = (int) defaultTimeOutSecs;
		Thread.sleep(3000);
		if (!element.isDisplayed()) {
			fail("Timed out.. [" + element + "] is visible");
		}
		return element;
	}

	public WebElement waitForElement(WebElement element) {

		try {
			System.out.println("waiting for " + defaultTimeOutSecs);
			WebDriverWait wait = new WebDriverWait(driver, defaultTimeOutSecs);
			element = wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("element found");
		} catch (Exception e) {
			fail("Failed to get element [" + element + "] ");
		}
		return element;
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeOutSecs);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id(element.getAttribute("id"))));
		return element;
	}

	public WebElement waitForElementToBeClickableByText(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeOutSecs);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(element.getText())));
		return element;
	}

	public WebElement verifyClassAtrributeValue(WebElement webElement, String value) {
		String actualValue = webElement.getAttribute("class");
		assertEquals(value, actualValue.trim());
		return webElement;
	}

	public WebElement loadLocationAndWaitForElement(String locationKey, String elementIdKey) {
		loadLocation(locationKey);
		return waitForElement(elementIdKey);
	}

	public void loadLocation(String location) {

		// String location = getProperty(locationKey);
		int retry = 0;
		while (retry < 1) {
			try {
				driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
				driver.get(location);

			} catch (Exception e) {

			}
			retry++;
		}

	}

	public void loadLocationWithCacheClear(String locationKey) {

		String location = getProperty(locationKey);
		int retry = 0;
		while (retry < 1) {
			try {
				// ClearBrowserCache();
				driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
				driver.get(location);

			} catch (Exception e) {
				Reporter.log("Login page is Displayed as a Blank page");

			}
			retry++;
		}
	}

	public void ClearBrowserCache() throws InterruptedException {
		driver.manage().deleteAllCookies(); // delete all cookies
		Thread.sleep(5000); // wait 5 seconds to clear cookies.
	}

	protected WebElement clickRadioButton(String elementIdKey) {
		WebElement rButton = waitForElement(elementIdKey);
		rButton.click();
		return rButton;
	}

	protected WebElement clickButton(String elementIdKey) {
		return clickRadioButton(elementIdKey);
	}

	public String getProperty(String key) {
		assertNotNull("Key is not null", key);
		Object rawProperty = properties.get(key);
		assertNotNull("[" + key + "] is not null", rawProperty);
		String property = rawProperty.toString();
		return property;
	}

	public void assertPageTitle(String pageTitleKey) {
		String pageTitle = getProperty(pageTitleKey);
		assertEquals("Page title as expected ", pageTitle, driver.getTitle());
	}

	public void quit() {
		driver.quit();
	}

	public void loadDirectLocation(String url) {
		int retry = 0;
		while (retry < 2) {
			try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(url);

			} catch (Exception e) {

			}
			retry++;
		}

	}

	public void gotoURL(String url) {
		driver.navigate().to(url);
	}

	@SuppressWarnings("static-access")
	public void waitAWhile() {
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	@SuppressWarnings("unused")
	public int length(List<WebElement> listElement) {
		int length = 0;
		for (WebElement element : listElement) {
			length += 1;
		}
		return length;
	}

	@SuppressWarnings("deprecation")
	public void waitForText(String string) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(
					ExpectedConditions.textToBePresentInElement((WebElement) By.className("bio-content-head"), string));
		} catch (Exception e) {
			fail("Failed to get text");
		}
		return;

	}

	public void setDate(WebElement dobElement, String dateString) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "$( '.datePicker' ).datepicker( 'setDate', '" + dateString + "' )";
		js.executeScript(script);
	}

	public void setDateOnPicker(String dob, DateTime minDate) {
		// This function is an elaborate way to enter date using the picker
		// however you should prefer just entering the date as text
		String[] tokens = dob.split("\\/");
		int year = Integer.parseInt(tokens[2]);
		int month = Integer.parseInt(tokens[1]);
		int day = Integer.parseInt(tokens[0]);
		DateTime x = new DateTime().withDate(year, month, day);
		Months d = Months.monthsBetween(x, minDate);
		int monthsDiff = d.getMonths();

		for (int i = 0; i < monthsDiff; i++) {
			driver.findElement(By.cssSelector("span.ui-icon.ui-icon-circle-triangle-w")).click();
		}
		driver.findElement(By.linkText(tokens[0])).click();

	}

	public void clearDeviceCookie() {
		driver.manage().deleteCookieNamed(getProperty("app.registered.device.cookie.name"));
	}

	public String getError(WebElement errorMessage) {
		// waitAWhile();
		try {
			if (errorMessage == null) {
				return null;
			}
			String errorMsg = errorMessage.getText().trim();
			if (errorMsg.equals("")) {
				return null;
			}
			return errorMsg;
		} catch (Exception e) {
			// Didn't find error return null
			return null;
		}
	}

	public void setText(WebElement webElement, String value) {
		webElement.clear();
		webElement.sendKeys(value);
	}

	public void clickRadioButtonByXPath(String elementName, String value) {
		if (null != value)
			driver.findElement(By.xpath("(//input[@name='" + elementName + "'])[" + value + "]")).click();
	}

	public void clickRadioButtonByXPathandValue(String elementName, String value) {
		driver.findElement(By.xpath("//input[@name='" + elementName + "'][@value='" + value + "']")).click();
	}

	public void clickRadiobutton(String elementId) {
		WebElement we = driver.findElement(By.id(elementId));
		we.click();
	}

	public void clickRadiobuttonByxpath(String elementId) {
		WebElement we = driver.findElement(By.xpath(elementId));
		we.click();
	}

	public void clickRadiobutton(WebElement element) {
		element.click();
	}

	public boolean assertTextPresent(String text) {
		return (driver.getPageSource().contains(text));
	}

	public List<WebElement> findAllelements(String classname) {
		return driver.findElements(By.className(classname));
	}

	public WebElement findElementById(String id) {
		return driver.findElement(By.id(id));
	}

	public WebElement findElementByClassName(String classname) {
		return driver.findElement(By.className(classname));
	}

	public List<WebElement> findAllelementsByXpath(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	public WebElement findElementByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public List<WebElement> findAllelementsByClass(String className) {
		return driver.findElements(By.className(className));
	}

	public List<WebElement> findAllelementsByCss(String css) {
		return driver.findElements(By.cssSelector(css));
	}

	public String getError(String errorClassName) {
		try {
			List<WebElement> errorList = findAllelementsByClass(errorClassName);
			if (errorList.size() == 0) {
				return null;
			}

			StringBuilder outString = new StringBuilder();
			for (WebElement error : errorList) {
				if (!"".equals(error.getText())) {
					outString.append(error.getText()).append(":");
				}
			}
			if ("".equals(outString.toString())) {
				return null;
			}
			return outString.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public void waitTillScreenReady() {
		try {
			List<WebElement> waitList = findAllelementsByClass("waiting-resp");
			while (waitList.size() > 0) {
				waitList = findAllelementsByClass("waiting-resp");
			}
		} catch (Exception e) {
			return;
		}
	}

	public List<WebElement> readTable(WebElement table) {
		List<WebElement> tableData = table.findElements(By.xpath("//tr"));
		return tableData;
	}

	public void pressTab() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
	}

	public void pressEnter() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public void pressEnterafterlandingonpartcularelement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).sendKeys(Keys.ENTER).build().perform();
	}

	public void doubleclick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().perform();
	}

	public WebElement getSubElementinElementbyXPath(String xpath, WebElement parentElement) {
		try {
			WebElement element = parentElement.findElement(By.xpath(xpath));
			return element;
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	public void scrollUp() {
		((JavascriptExecutor) driver).executeScript("scroll(0,-1250);");

	}

	public void handleAlert() {
		try {
			String parentWindow = driver.getWindowHandle(); // save the window handle to switch back later
			Set<String> winIds = driver.getWindowHandles();
			Iterator<String> it = winIds.iterator();
			WebDriver popup = null;
			while (it.hasNext()) {
				popup = driver.switchTo().window(it.next());
				if (popup != null)
					popup.switchTo().alert().accept();

			}
			// return control to the main window
			driver.switchTo().window(parentWindow);
		} catch (Exception NoSuchWindowException) {
			NoSuchWindowException.getLocalizedMessage();
		}
	}

	public void selectDate(WebElement dob, String dob2) {
		waitingForAllElementsinaPage();
		String mmddyy[] = dob2.split("\\/");
		dob.click(); // click on the dob field to open up the date picker
		waitingForAllElementsinaPage();
		WebElement month = driver.findElement(By.className("ui-datepicker-month"));
		Select selectmonth = new Select(month);
		selectmonth.selectByValue(mmddyy[0]);
		waitingForAllElementsinaPage();
		WebElement year = driver.findElement(By.className("ui-datepicker-year"));
		waitingForAllElementsinaPage();
		Select selectyear = new Select(year);
		waitingForAllElementsinaPage();
		selectyear.selectByValue("19" + mmddyy[2]);
		// selectyear.selectByValue(mmddyy[2]);
		WebElement table = driver.findElement(By.id("ui-datepicker-div"));

		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		List<WebElement> allRows2 = allRows.subList(1, allRows.size()); // omit the days row
		List<WebElement> dateCellrow;
		for (WebElement tableRow : allRows2) {
			dateCellrow = tableRow.findElements(By.tagName("td"));
			for (WebElement dateCell : dateCellrow) {
				if (dateCell.getText().equals(mmddyy[1]))
					dateCell.click();
				break;
			}
		}
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public boolean checkifElementexists(WebElement we) {
		boolean flag = false;
		if (null != we) {
			flag = we.isDisplayed() ? true : false;
		}
		return flag;
	}

	public WebElement clickTextByxpath(int position) {
		WebElement we = null;
		try {
			we = driver.findElement(By.xpath("//div[@id='sub_actions_load']/div/div[3]/div[2]/div[1]/div[3]/ul/li["
					+ position + "]/a[contains('Dairy')]"));
		} catch (NoSuchElementException ne) {
			return we;
		}
		return we;
	}

	public void waitingForAllElementsinaPage() {
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(expectation);
		} catch (Throwable error) {

		}
	}

	public void resizeBrowserWindow(int x, int y) {
		Dimension dimension = new Dimension(x, y);
		driver.manage().window().setSize(dimension);
	}

	public void scrollingpage(String x) {
		((JavascriptExecutor) driver).executeScript(x);

	}

	public void dragAndDropBy(WebElement source, int x, int y) {
		Actions move = new Actions(driver);
		Action action = (Action) move.dragAndDropBy(source, x, y).build();
		action.perform();
	}

	public boolean isDisplayed(WebElement element) {
		try {

			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isEnabled(WebElement element) {
		return element.isEnabled();
	}

//New Functions

	public void rightClick(WebElement element) {
		Actions action = new Actions(driver);
		WebElement el = waitForElementToBeClickable(element);
		sleepMillis(200);
		try {
			action.contextClick(el).build().perform();
		} catch (StaleElementReferenceException ex) {
			log.warn("stale element " + element + ": " + ex);
			sleepSecs(3);
			el = waitForElementToBeClickable(element);
			action.contextClick(el).build().perform();
		}
	}

	public void sleepMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
		}
	}

	/**
	 * Sleep for the given number of seconds, and ignore an interrupted exception
	 * 
	 * @param secs
	 */
	public void sleepSecs(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException ex) {
		}
	}

	public WebElement clickUsingJavascript(WebDriver driver, WebElement button) {
		String script = "arguments[0].click();";
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object result = executor.executeScript(script, button);
		log.info("executed script=" + script + ", button=" + button + ", WebElement=" + button + ", result=" + result
				+ " on " + driver.getCurrentUrl() + " " + "[" + driver.getTitle() + "]");
		return button;
	}

	public void moveMouse(WebElement element) {
		log.info("moving mouse to element " + ": " + element);
		new Actions(driver).moveToElement(element).build().perform();
	}

	public void moveMouseWithWait(WebElement element) {
		WebElement e = waitForElement(element);
		log.info("about to move to " + element);
		moveMouse(element);
	}

	public void maximizeBrowserWithToolkitapi() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenResolution = new Dimension((int) toolkit.getScreenSize().getWidth(),
				(int) toolkit.getScreenSize().getHeight());
		driver.manage().window().setSize(screenResolution);
	}

	public String generateRandomNumber() {
		Calendar c = Calendar.getInstance();
		String str = String.valueOf(c.get(Calendar.YEAR)) + String.valueOf(c.get(Calendar.MONTH))
				+ String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + String.valueOf(c.get(Calendar.HOUR_OF_DAY))
				+ String.valueOf(c.get(Calendar.MINUTE)) + String.valueOf(c.get(Calendar.SECOND));

		return str;
	}

	public String generateRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		return generatedString;
	}

}
