package com.clearskye.test.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SeleniumUtils {

    private static final Log log = LogFactory.getLog(SeleniumUtils.class);

    ////////////////////////////////
    ///// assert helpers
    //////////////////////////////

    /**
     * Assert an element exists
     * @param driver
     * @param by
     */
    public static void assertElementExists(WebDriver driver, By by){
        Assert.assertNotNull(driver.findElement(by), "not found: " + by);
    }
        
    public static void assertElementExists(WebDriver driver, WebElement element){
        Assert.assertNotNull(element, "not found: " + element.getText());
    }

    /**
     * Assert an element exists
     * @param driver
     * @param by
     */
    public static void assertElementExists(WebDriver driver, By by, String message){
        Assert.assertNotNull(driver.findElement(by), "not found: " + by + (message==null?"":": "+ message));
    }
    
    public static void mouseOverElement(WebDriver driver, List<WebElement> we) {
    	Actions action = new Actions(driver);
    	for (WebElement element : we) {
    		action.moveToElement(element);
    	}
    	action.build().perform();
    }
    
    public static void mouseOverElement(WebDriver driver, By by) {
    	Actions action = new Actions(driver);
    	WebElement we = driver.findElement(by);
    	action.moveToElement(we).moveToElement(driver.findElement(by)).build().perform();
    }
    /**
     * Assert an element exists and count
     * A locator can match zero or more elements.
     * @param driver
     * @param by The locator
     * @param count The number expected
     */
    public static void assertElementCount(WebDriver driver, By by, int count){
        List<WebElement> els = driver.findElements(by);
        Assert.assertEquals(els.size(), count, "incorrect count of elements for " + by + ": " + els);
    }

    /**
     * Assert an element exists and there is only one
     * @param driver
     * @param by
     */
    public static void assertElementCountIsOne(WebDriver driver, By by){
    	assertElementCount(driver, by, 1);
    }

    /**
     * Assert an element is clickable
     * @param driver
     * @param by
     */
    public static void assertElementIsClickable(WebDriver driver, By by) {
    	WebElement el = waitForClickable(driver, by);
    	Assert.assertNotNull(el, "did not find clickable element by " + by);
    }
    
    /**
     * Check an element has the given class (eg <element class="myclassname otherclassname">)
     * @param driver
     * @param by The element locator
     * @param classname The class name
     */
    public static void assertElementHasClass(WebDriver driver, By by, String classname) {
    	assertElementHasClass(waitForElement(driver, by), classname);
    }

    /**
     * Check an element has the given class (eg <element class="myclassname otherclassname">)
     * @param el The web element
     * @param classname The class name
     */
    public static void assertElementHasClass(WebElement el, String classname) {
    	Assert.assertTrue(elementHasClass(el, classname), "classname " + classname + " not found in " + el.getAttribute("class") + " for element: tag=" + el.getTagName() + ", id=" + el.getAttribute("id") + ", class=" + el.getAttribute("class") + ": " + el);
    }
    
    public static void validateElementHasText(WebDriver driver, By by, String text) {
    	WebElement element = driver.findElement(by);
    	Assert.assertTrue(element.getText().toLowerCase().contains(text.toLowerCase()));
    }
    
    public static void assertElementIsClickable(WebDriver driver, WebElement element) {
    	WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS, DEFAULT_POLLING_MILLISECONDS);
    	WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
    	Assert.assertNotNull(el, "did not find clickable element " + element.getText().trim());
    }
    
    /**
     * Check an element has an expected tag name
     * @param el
     * @param tagName
     */
    public static void assertElementHasTagName(WebElement el, String tagName) {
    	Assert.assertNotNull(el);
        Assert.assertEquals(el.getTagName(), tagName, "unvalid tag name for id=" + el.getAttribute("id"));
    }
    
    public static boolean elementHasClass(WebElement el, String classname) {
    	String c = el.getAttribute("class");
    	if(c == null) return false;
    	
    	HashSet<String> tokens = new HashSet<String>();
    	for(StringTokenizer st = new StringTokenizer(c); st.hasMoreTokens(); ) tokens.add(st.nextToken());
    	
    	return tokens.contains(classname);
    }

    public static void assertElementExistsWithWait(WebDriver driver, By by){
    	assertElementExistsWithWait(driver, by, DEFAULT_TIMEOUT_SECONDS, null);
    }

    public static void assertElementExistsWithWait(WebDriver driver, By by, String message){
    	assertElementExistsWithWait(driver, by, DEFAULT_TIMEOUT_SECONDS, message);
    }

    /**
     * Assert an element exists, wait until there is an element
     * @param driver
     * @param by
     */
    public static void assertElementExistsWithWait(WebDriver driver, By by, long secondsTimeout){
    	assertElementExistsWithWait(driver, by, secondsTimeout, null);
    }
    
    public static String getInputElementValue(WebDriver driver, By locator) {
    	WebElement element = waitForElement(driver, locator);
    	return element.getAttribute("value");
    }
    
    /**
     * Assert an element exists, wait until there is an element
     * @param driver
     * @param by
     */
    public static void assertElementExistsWithWait(WebDriver driver, By by, long secondsTimeout, String message){
        waitForElement(driver, by, secondsTimeout);
        assertElementExists(driver, by, message);
    }

    /**
     * Assert a label exists and has the mandatory asterix
     * @param driver
     * @param labelName the label name
     */
    public static void assertLabelIsMandatory(WebDriver driver, String labelName){
        By by = By.xpath("//label/span[text()[contains(.,'" + labelName + "')]]/span[text()[.='*']]");
        assertElementExists(driver, by);
    }

    /**
     * Assert an element text value is the given value
     * @param driver
     * @param by
     */
    public static void assertElementInnerHtmlWithWait(WebDriver driver, By by, String expectedTextValue, long seconds){
        WebElement el = waitForVisible(driver, by, seconds);
        String text = el.getText(); // element must be visible
        Assert.assertEquals(text, expectedTextValue, "element text is invalid by '" + by + "' in element '" + el.getTagName() + "' position '" + el.getLocation() + "' id '" + el.getAttribute("id") + ", el = " + el + "', displayed " + el.isDisplayed() + ", on url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
    }

    public static void assertElementInnerHtmlWithWait(WebDriver driver, By by, String expectedTextValue){
        assertElementInnerHtmlWithWait(driver, by, expectedTextValue, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * Assert an element text value is the given value
     * @param driver
     * @param by
     */
    public static void assertElementInnerHtmlStartsWithWithWait(WebDriver driver, By by, String expectedTextValueStartsWith){
        WebElement el = waitForElement(driver, by);
        String text = el.getText();
        Assert.assertNotNull(text, "element text is invalid by=" + by + ", element=" + el);
        Assert.assertTrue(text.startsWith(expectedTextValueStartsWith), "element text is invalid by=" + by + ", element=" + el + ", got '" + text + "', expected starts with '" + expectedTextValueStartsWith + "'" + "' on url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
    }

    /**
     * Assert an element text value is the given value
     * @param driver
     * @param by
     */
    public static void assertElementInnerHtmlContainsWithWait(WebDriver driver, By by, String expectedTextValueContains){
        WebElement el = waitForElement(driver, by);
        String text = el.getText();
        Assert.assertNotNull(text, "element text is invalid by=" + by + ", element=" + el);
        Assert.assertTrue(text.contains(expectedTextValueContains), "element text is invalid by=" + by + ", element=" + el + ", got '" + text + "', expected starts with '" + expectedTextValueContains + "' on url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
    }

    /**
     * Assert a page title is the given value
     * @param driver
     * @param expectedTitle
     */
    public static void assertPageTitle(WebDriver driver, String expectedTitle, long seconds) {

        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.titleIs(expectedTitle));

        String title = driver.getTitle();
        Assert.assertEquals(title, expectedTitle, "we are on the wrong page: title is '" + driver.getTitle() + "' url '" + driver.getCurrentUrl() + "'");
    }

    public static void assertPageTitle(WebDriver driver, String expectedTitle) {
        assertPageTitle(driver, expectedTitle, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * Assert an element does not exist
     * @param driver
     * @param by
     */
    public static void assertElementNotExists(WebDriver driver, By by){
        try {
            WebElement el = driver.findElement(by);
            if(el != null) Assert.assertTrue(false, "should not have found '" + by + "' on url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
        } catch(NoSuchElementException ex){
            // good!
        }
    }

    /**
     * Assert an element does not exist
     * @param driver
     * @param by
     */
    public static void assertElementInVisibleWithWait(WebDriver driver, By by){
        try {
            Boolean el = waitForInVisible(driver, by);
            if(el == null || !el.booleanValue()) Assert.assertTrue(false, "should not have found '" + by + "' on url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
        } catch(NoSuchElementException ex){
            // good!
        }
    }

    //////////////////////////////
    //// waiting for stuff helpers
    //////////////////////////////
    public static WebElement waitForClickable(WebDriver driver, By by, long timeoutSeconds){
        long start = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds, DEFAULT_POLLING_MILLISECONDS);
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
            long end = System.currentTimeMillis();
            if(end-start > 5000) log.warn("had to wait " + (end-start) + " ms for " + by + " in url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'");
            return el;
        } catch(TimeoutException ex){
            String msg = "error waiting for element to be clickable by " + by + " in url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'";
            log.error(msg);
            throw new TimeoutException(msg, ex);
        }
    }

    public static WebElement waitForClickable(WebDriver driver, By by){
        return waitForClickable(driver, by, DEFAULT_TIMEOUT_SECONDS);
    }

    public static WebElement waitForClickable(WebDriver driver, WebElement element, long timeoutSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds, DEFAULT_POLLING_MILLISECONDS);
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
            return el;
        } catch(TimeoutException ex){
            String msg = "error waiting for element to be clickable by " + element + " in url '" + driver.getCurrentUrl() + "' title '" + driver.getTitle() + "'";
            log.error(msg);
            throw new TimeoutException(msg, ex);
        }
    }
    
    public static void jsSelectElementByValue(WebDriver driver, By locator, String value) {
    	waitForElement(driver, locator);
    	WebElement element = driver.findElement(locator);
    	System.out.println(element.getTagName() + " " + element.getAttribute("class"));
    	Select dropdown = new Select(element);
    	dropdown.selectByValue(value);
    }
    
    public static void jsSelectElementByText(WebDriver driver, By locator, String value) {
    	waitForElement(driver, locator);
    	WebElement element = driver.findElement(locator);
    	System.out.println(element.getTagName() + " " + element.getAttribute("class"));
    	Select dropdown = new Select(element);
    	dropdown.selectByVisibleText(value);
    }
    
    public static void jsSelectElementByIndex(WebDriver driver, By locator, int index) {
    	waitForElement(driver, locator);
    	WebElement element = driver.findElement(locator);
    	System.out.println(element.getTagName() + " " + element.getAttribute("class"));
    	Select dropdown = new Select(element);
    	dropdown.selectByIndex(index);
    }
    
    public static void jsSelectElementByIndex(WebDriver driver, WebElement element, int index) {
    	System.out.println(element.getTagName() + " " + element.getAttribute("class"));
    	Select dropdown = new Select(element);
    	dropdown.selectByIndex(index);
    }
    
    public static void jsSelectElementByValue(WebDriver driver, WebElement element, String value) {
    	System.out.println(element.getTagName() + " " + element.getAttribute("class"));
    	Select dropdown = new Select(element);
    	dropdown.selectByValue(value);
    }
    
    public static void selectElementByValue(WebDriver driver, By locator, String value) {
    	waitForElement(driver, locator);
    	Actions actions = new Actions(driver);
    	WebElement element = waitForVisible(driver, locator);
    	actions.moveToElement(element).click().perform();
    	Select dropdown = new Select(element);
    	dropdown.selectByValue(value);
    }

    public static WebElement waitForClickable(WebDriver driver, WebElement element){
        return waitForClickable(driver, element, DEFAULT_TIMEOUT_SECONDS);
    }

    public static WebElement waitForVisible(WebDriver driver, By by, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds, DEFAULT_POLLING_MILLISECONDS);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch(TimeoutException ex){
            log.error("error waiting for " + by + " in " + timeoutSeconds + " s...trying to scroll.." + ex);

            try {
                // maybe it is off the page?
                scrollToElement(driver, by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch(TimeoutException ex2){
                throw new TimeoutException("error waiting for element to be visible by " + by + " in url " + driver.getCurrentUrl(), ex2);
            }
        }
    }

    public static WebElement waitForVisible(WebDriver driver, By by) {
        return waitForVisible(driver, by, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * A helper to wait for all elements found for the given locator.
     * The existing waitForInVisible just waited for the first element found.
     * This was added to wait for any 'Loading...' box on the page to be invisible, since there
     * could be multiple on the page, and only one will be visible. The existing method would
     * just return immediately if the first one it found was already invisible.
     */
    public static void waitForAllInVisible(WebDriver driver, By by) {
    	waitForAllInVisible(driver, by, DEFAULT_TIMEOUT_SECONDS);
    }

    /**
     * A helper to wait for all elements found for the given locator, with a sleep time before and after
     * The existing waitForInVisible just waited for the first element found.
     * This was added to wait for any 'Loading...' box on the page to be invisible, since there
     * could be multiple on the page, and only one will be visible. The existing method would
     * just return immediately if the first one it found was already invisible.
     */
    public static void waitForAllInVisibleWithSleeps(WebDriver driver, By by, int sleepSecondsBeforeAndAfter) {
    	sleepSecs(sleepSecondsBeforeAndAfter);
    	waitForAllInVisible(driver, by, DEFAULT_TIMEOUT_SECONDS);
    	sleepSecs(sleepSecondsBeforeAndAfter);
    }

    /**
     * A helper method to wait for the Ext loading mask
     * @param driver
     */
    public static void waitForLoading(WebDriver driver) {
    	sleepMillis(500);
    	waitForAllInVisible(driver, Locator.divText("Loading..."), DEFAULT_TIMEOUT_SECONDS);
    	sleepMillis(500);
    }

    /**
     * A helper to wait for all elements found for the given locator.
     * The existing waitForInVisible just waited for the first element found.
     * This was added to wait for any 'Loading...' box on the page to be invisible, since there
     * could be multiple on the page, and only one will be visible. The existing method would
     * just return immediately if the first one it found was already invisible.
     */
    public static void waitForAllInVisible(WebDriver driver, By by, long timeoutSeconds) {
    	List<WebElement> es = driver.findElements(by);
    	if(es == null || es.isEmpty()) {
    		log.warn("no elements found by " + by + " in " + driver.getCurrentUrl());
    		return;
    	}
    	for(final WebElement e: driver.findElements(by)) {
            String id = e.getAttribute("id");
            waitForInVisible(driver, By.id(id));
    	}
    }
    
    public static Boolean waitForInVisible(WebDriver driver, By by, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds, DEFAULT_POLLING_MILLISECONDS);
        try {
            long start = System.currentTimeMillis();
            Boolean result = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            log.info("waitForInVisible waited " + (System.currentTimeMillis()-start) + " ms for " + by + " with result " + result + " at " + driver.getCurrentUrl());
            return result;
        } catch(TimeoutException ex){
            throw new TimeoutException("error waiting for element to be invisible by " + by + " with timeout " + timeoutSeconds + "s in url " + driver.getCurrentUrl(), ex);
        }
    }

    public static Boolean waitForInVisible(WebDriver driver, By by) {
        return waitForInVisible(driver, by, DEFAULT_TIMEOUT_SECONDS);
    }


    static int DEFAULT_TIMEOUT_SECONDS = 5;
    static int DEFAULT_POLLING_MILLISECONDS = 5;
    


    public static WebElement waitForElement(WebDriver driver, final By by, long seconds) {
        List<WebElement> matches = driver.findElements(by);
        if (matches.size() == 0) {
            throw new NoSuchElementException("cannot find by=" + by + ", url=" + driver.getCurrentUrl() + ", title=" + driver.getTitle());
        }
		return matches.get(0);
    }

    public static List<WebElement> waitForElements(WebDriver driver, final By by, long seconds) {
    	List<WebElement> matches = driver.findElements(by);
        if (matches.size() == 0) {
            throw new NoSuchElementException("cannot find by=" + by + ", url=" + driver.getCurrentUrl() + ", title=" + driver.getTitle());
        }
		return matches;
    }

    public static List<WebElement> waitForElements(WebDriver driver, final By by) {
        return waitForElements(driver, by, DEFAULT_TIMEOUT_SECONDS);
    }

        /**
         * The locator can be a list of elements (such as elements that get hidden and shown).
         * It will pass when at least one of the elements matching the pattern are visible and enabled.
         * @param driver
         * @param by
         * @param seconds
         * @return
         */

    public static WebElement waitForElement(WebDriver driver, final By by) {
        return waitForElement(driver, by, DEFAULT_TIMEOUT_SECONDS);
    }

    public static WebElement checkForElement(WebDriver driver, final By by) {
        try {
            return waitForElement(driver, by, DEFAULT_TIMEOUT_SECONDS);
        } catch(Exception ex){
            log.error("did not find element " + by + " on " + driver.getCurrentUrl() + " in " + DEFAULT_TIMEOUT_SECONDS + " seconds");
            return null;
        }
    }


    public static boolean checkErrorMsgVisible(WebDriver driver) {
        WebElement el = driver.findElement(Locator.divText("Server Error"));
        if(el != null){
            WebElement errEl = driver.findElement(By.xpath("//div[contains(@class, 'x-window-text')]/div/div"));
            String msg = errEl.getText();
            log.info("got error msg: " + msg);

            return true;
        }
        return false;
    }

    public static void assertElementExistsOrCloseErrorMsg(WebDriver driver, By by) {
        WebElement el = checkForElement(driver, by);
        if(el == null) {
            boolean err = checkErrorMsgVisible(driver);
            if (err) {
                log.info("Found Error Msg Box and closed it.");
            }
            Assert.assertNotNull(el, "not found: " + by + " on " + driver.getCurrentUrl() + ", error box was present ? " + err + " (title " + driver.getTitle());
        }
    }
    
    public static void clickOnSVGObject(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		builder.click(element).build().perform();
	}

    public static void setFormPropertyText (WebDriver driver, String propertyName, String propertyValue) throws Exception {
        SeleniumUtils.sleepMillis(300);
        click(driver, By.xpath("//div[text()[.='" + propertyName + "']]/../../td[2]/div"));

        SeleniumUtils.sleepMillis(300);
        WebElement el = driver.switchTo().activeElement();
        SeleniumUtils.sleepMillis(100);
        el.clear();
        el.sendKeys(propertyValue);
        SeleniumUtils.sleepMillis(100);
        
        // this is a workaround saving form properties, just entering test and hitting enter no longer works to set the value
        el.sendKeys(Keys.ARROW_DOWN);
        SeleniumUtils.sleepMillis(100);
        el.sendKeys(Keys.RETURN);
        SeleniumUtils.sleepSecs(1);
    }

    /////////////////////////////
    /// standard actions helpers
    /////////////////////////////

    public static String strip(String text) {
    	String updated = "";
    	int i = 0;
    	while (i < text.length() && text.charAt(i) == ' ') {
            i++;
        }
    	int j = text.length() - 1;
    	while (j >= 0 && text.charAt(j) == ' ') {
    		j--;
    	}
    	for(int k = i; k <= j; k++) {
    		updated += text.charAt(k);
    	}
    	return updated;
    }
    
    public static void jsSetText(WebDriver driver, By by, String text) {
    	WebElement input = driver.findElement(by);
    	jsSetText(driver, input, text);
    }
    
    public static void jsSetText(WebDriver driver, WebElement input, String text) {
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
        log.info("set text '" + text + "' into tag=" + input.getTagName());
		executor.executeScript("arguments[0].setAttribute('value', '" + text + "');", input);
    }
    
    public static void jsClick(WebDriver driver, By by) {
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	WebElement button = driver.findElement(by);
        log.info("clicking " + by + " on " + driver.getCurrentUrl() + " [" + driver.getTitle() + "]");
		jsClick(driver, button);
    }
    
    public static WebElement getShadowRootNavBarElement(WebDriver driver, By by) {
    	JavascriptExecutor js = (JavascriptExecutor) driver; 
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	WebElement body = driver.findElement(By.tagName("body"));
    	WebElement parentElement = (WebElement) js.executeScript("return arguments[0].children[0].shadowRoot.children[0].children[0].children[0].children[0].shadowRoot.children[2].getElementsByClassName('header-bar')[0].children[0].shadowRoot.children[0].children[0].children[1].shadowRoot.children[0].children[1].getElementsByClassName('sn-tree-menu')[0].children[0].children[0].children[1].shadowRoot.children[0]", body);
    	return parentElement.findElement(by);
    }
    
    public static void jsClick(WebDriver driver, WebElement button) {
    	System.out.println("clicking on " + button.getText());
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", button);
    }
    
    public static void jsDbClick(WebDriver driver, WebElement button) {
    	System.out.println("clicking on " + button.getText());
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("var clickEvent  = document.createEvent ('MouseEvents'); clickEvent.initEvent ('dblclick', true, true); arguments[0].dispatchEvent (clickEvent);", button);
    }
    
    
    public static void jsRightClick(WebDriver driver, By by) {
    	WebElement button = driver.findElement(by);
        log.info("clicking " + by + " on " + driver.getCurrentUrl() + " [" + driver.getTitle() + "]");
        rightClick(driver, button);
    }
    
    public static void rightClick(WebDriver driver, WebElement button) {
    	Actions a = new Actions(driver);
        a.moveToElement(button).
        contextClick().
        build().perform();
    }
    
    public static void escape(WebDriver driver) {
    	Actions a = new Actions(driver);
    	a.sendKeys(Keys.ESCAPE).perform();
    }
    
    public static void jsScroll(WebDriver driver, WebElement element) {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    	log.info("scrolling to " + element.getText());
    }
    
    public static WebElement click(WebDriver driver, By by, long waitSeconds) {
    	WebElement el = null;
       // Assert.assertTrue(driver.findElements(by).size() > 0);
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        el = driver.findElement(by);
        //el = waitForElementHasNotClass(driver, by, "x-btn-disabled");
        //if(el.isDisplayed()) throw new RuntimeException("button is not visible, cannot be clicked. by " + by + ", el " + el);
        if(el.getSize().getHeight() == 0 && el.getSize().getWidth() == 0) throw new RuntimeException("button has 0 size, cannot be clicked. by " + by + ", button " + el);
        log.info("clicking " + by + " on " + driver.getCurrentUrl() + " [" + driver.getTitle() + "]");
        el.click();
        return el;
    }

    public static void scrollToElement(WebDriver driver, By by) {
        try {
            scrollToElement(driver, waitForElement(driver, by));
        } catch(StaleElementReferenceException ex) {
            log.warn("stale element " + by + ": " + ex);
            sleepSecs(3);
            scrollToElement(driver, waitForElement(driver, by));
        }

    }

    private static void scrollToElement(WebDriver driver, WebElement el) {
        log.info("scrolling to element "  + ": " + el);
        // possible that this is because the button is off screen....try scrolling and try clicking again
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(" + el.getLocation().getX() + "," + el.getLocation().getY() + ");");
        //((JavascriptExecutor)driver).executeScript("window.scrollBy(" + el.getLocation().getX() + "," + el.getLocation().getY() + ");");
        //((JavascriptExecutor) driver).executeScript("document.getElementById('xxxxx').scrollIntoView(true);");
        try { Thread.sleep(250); } catch(Exception t){}
    }

    public static WebElement click(WebDriver driver, By by) {
        return click(driver, by, DEFAULT_TIMEOUT_SECONDS);
    }

    public static void click(WebDriver driver, WebElement el) {
    	log.info("clicking " + el.getText() + " on " + driver.getCurrentUrl() + " [" + driver.getTitle() + "]");
        el.click();
    }
    
    public static String debugElement(WebElement found) {

        try {
            return "tagName=" + found.getTagName() + ", id " + found.getAttribute("id") + ", class=" + found.getAttribute("class") + ", style=" + found.getAttribute("style") + ", location=" + found.getLocation().getX() + "," + found.getLocation().getY() + ", size=" + found.getSize().getWidth() + "x" + found.getSize().getHeight() + ", displayed=" + found.isDisplayed() + ", enabled=" + found.isEnabled() + ", selected=" + found.isSelected();
        } catch (Exception e) {
            log.info("Error in debug statement. " + e);
            return "" + e;
        }
    }
    /**
     * Click an element using javascript (click()) given the locator
     * @param driver
     * @param by
     */
    public static WebElement clickUsingJavascript(WebDriver driver, By by){
        String script = "arguments[0].click();";
        WebElement button = waitForClickable(driver, by);
        log.info("about to execute script=" + script + ", by=" + by + " on " + driver.getCurrentUrl() + " [" + driver.getTitle() + "]");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Object result = null;

        try {
            result = executor.executeScript(script, button);
        } catch(StaleElementReferenceException ex){
            log.warn("stale element " + by + ": " + ex);
            sleepSecs(2);
            button = waitForClickable(driver, by);
            result = executor.executeScript(script, button);
        }
        log.info("executed script, result is " + result);

        return button;
    }

    /**
     * Click on a split button. Have to click close to the arrow
     * @param driver
     * @param by
     */
    public static void clickSplitButton(WebDriver driver, By by) {

        WebElement el = waitForClickable(driver, by);

        int width = el.getSize().getWidth();
        int height = el.getSize().getHeight();

        int xOffset = (width *90)/100;
        int yOffset = (height * 60)/100;

        Actions action = new Actions(driver);
        action.moveToElement(el, xOffset, yOffset);
        action.perform();
        sleepMillis(400);
        action = new Actions(driver);
        action.click();
        action.perform();
        sleepMillis(400);
        //new Actions(driver).moveToElement(el, xOffset, yOffset).click().perform();
        //new Actions(driver).moveToElement(el, xOffset, yOffset).clickAndHold().release().perform();
    }

    public static WebElement setText(WebDriver driver, By locator, String text) {
    //	Actions actions = new Actions(driver);
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	WebElement textField = driver.findElement(locator);
    //	waitForVisible(driver, locator);
    //	actions.moveToElement(textField).click().perform();
   //   WebElement textField = click(driver, locator);
        textField.sendKeys(text);
        SeleniumUtils.sleepMillis(300);
        
        String value = textField.getAttribute("value");
        log.info("set text '" + text + "' into tag=" + textField.getTagName() + ", id=" + textField.getAttribute("id") + ", value=" + value + ": " + textField);
        System.out.println("Settext res is " + value);
        if(!text.equals(value)) {
        	log.warn("sendKeys seems not to have worked... trying again");
        	textField.clear();
        	textField.click();
            textField.sendKeys(text);
            SeleniumUtils.sleepMillis(300);
            log.info("set text '" + text + "' into tag=" + textField.getTagName() + ", id=" + textField.getAttribute("id") + ", value=" + value + ": " + textField);
        } 
        
        return textField;
    }
    
    public static WebElement setText(WebDriver driver, WebElement textField, String text) {
        //	Actions actions = new Actions(driver);
            textField.sendKeys(text);
            SeleniumUtils.sleepMillis(300);
            
            String value = textField.getAttribute("value");
            log.info("set text '" + text + "' into tag=" + textField.getTagName() + ", id=" + textField.getAttribute("id") + ", value=" + value + ": " + textField);
            System.out.println("Settext res is " + value);
            if(!text.equals(value)) {
            	log.warn("sendKeys seems not to have worked... trying again");
            	textField.clear();
            	textField.click();
                textField.sendKeys(text);
                SeleniumUtils.sleepMillis(300);
                log.info("set text '" + text + "' into tag=" + textField.getTagName() + ", id=" + textField.getAttribute("id") + ", value=" + value + ": " + textField);
            } 
            
            return textField;
        }
    
    public static WebElement enterText(WebDriver driver, By locator, String text) {
    //	Actions actions = new Actions(driver);
    	waitForElement(driver, locator);
    //	actions.moveToElement(textField).click().perform();;
    	waitForVisible(driver, locator);
    	WebElement textField = click(driver, locator);
        textField.sendKeys(text);
        SeleniumUtils.sleepMillis(300);
        
        String value = textField.getAttribute("value");
        log.info("set text '" + text + "' into tag=" + textField.getTagName() + ", id=" + textField.getAttribute("id") + ", value=" + value + ": " + textField);
        
        if(!text.equals(value)) {
        	log.warn("sendKeys seems not to have worked... trying again");
        	textField.clear();
        	textField.click();
            textField.sendKeys(text);
            SeleniumUtils.sleepMillis(300);
            log.info("set text '" + text + "' into tag=" + textField.getTagName() + ", id=" + textField.getAttribute("id") + ", value=" + value + ": " + textField);
        }
        
        return textField;
    }
    
    public static WebElement setTextAndEnter(WebDriver driver, By locator, String text) {
        WebElement el = setText(driver, locator, text);
        sleepMillis(250);
        el.sendKeys(Keys.ENTER);
        return el;
    }
    
    public static WebElement setTextAndEnter(WebDriver driver, WebElement el, String text) {
        setText(driver, el, text);
        sleepMillis(250);
        el.sendKeys(Keys.ENTER);
        return el;
    }
    
    public static WebElement setTextAndTab(WebDriver driver, By locator, String text) {
        WebElement el = setText(driver, locator, text);
        sleepMillis(250);
        el.sendKeys(Keys.TAB);
        return el;
    }
    
    public static WebElement setTextAndTab(WebDriver driver, WebElement el, String text) {
        setText(driver, el, text);
        el.sendKeys(Keys.TAB);
        return el;
    }

    public static WebElement sendEnter(WebDriver driver, By locator) {
        WebElement e = waitForElement(driver, locator);
        e.sendKeys(Keys.ENTER);
        return e;
    }

    public static WebElement clearThenSetTextAndEnter(WebDriver driver, By locator, String text) throws Exception {
        clear(driver, locator);
        return setTextAndEnter(driver, locator, text);
    }

    public static WebElement clearThenSetText(WebDriver driver, By locator, String text) throws Exception {
        clear(driver, locator);
        sleepMillis(250);
        return setText(driver, locator, text);
    }

    public static String getText(WebDriver driver, By locator) {
        WebElement text_field = waitForClickable(driver, locator);
        return text_field.getText();
    }

    public static void clear(WebDriver driver, By locator) {
        WebElement text_field = waitForClickable(driver, locator);
        text_field.clear();
        assertEquals(text_field.getAttribute("value"), "");
    }



    public static void moveMouseWithWait(WebDriver driver, By by) {
        WebElement e = waitForElement(driver, by);
        log.info("about to move to " + by);
        moveMouse(driver, e);
    }
    public static void moveMouseWithWaitForClickable(WebDriver driver, By by) {
        WebElement e = waitForClickable(driver, by);
        log.info("about to move to " + by );
        moveMouse(driver, e);
    }
    public static void moveMouse(WebDriver driver, WebElement e){
        log.info("moving mouse to element " + ": " + e);
        new Actions(driver).moveToElement(e).build().perform();
    }

    /**
     * to do a hover hover mouse click
     * move the mouse over an item, then click something
     * @param driver
     * @param moveTo
     */
    public static void moveThenClick(WebDriver driver, By moveTo, By toClick) throws Exception{
        moveMouseWithWait(driver, moveTo);
        waitForElement(driver, toClick);
        click(driver, toClick);
    }


    /**
     * perform drag and drop action
     */
    public static void dragAndDrop(WebDriver driver, By source, By target){
        WebElement element = waitForElement(driver, source);
        WebElement where = waitForElement(driver, target);

        new Actions(driver).dragAndDrop(element, where).build().perform();
    }

    /**
     *
     * @param driver
     * @param source
     * @param target
     * @param xOffsetPercent will drop the element at the target top corner + width of target * xOffsetPercent
     * @param yOffsetPercent
     */
    public static void dragAndDrop(WebDriver driver, By source, By target, int xOffsetPercent, int yOffsetPercent) {

        WebElement sourceElement = waitForClickable(driver, source);
        WebElement targetElement = waitForClickable(driver, target);

        int width = targetElement.getSize().getWidth();
        int height = targetElement.getSize().getHeight();

        int xOffset = (width *xOffsetPercent)/100;
        int yOffset = (height * yOffsetPercent)/100;


        new Actions(driver).clickAndHold(sourceElement).perform();
        sleepMillis(200);
        new Actions(driver).moveToElement(targetElement, xOffset, yOffset).perform();
        sleepMillis(200);
        new Actions(driver).click().perform();
        new Actions(driver).release().perform();
        sleepMillis(200);

    }

    /**
     * perform double-click
     */
    public static void doubleClickOnce(WebDriver driver, By locator){

        WebElement el = waitForClickable(driver, locator);
        moveMouseWithWait(driver, locator);
        Actions action = new Actions(driver);
        action.moveToElement(el).doubleClick().build().perform();
        log.info("double clicked " + locator + " on " + driver.getCurrentUrl());
    }

    /**
     * default implementation of double click is to try again if a StateElementReferenceException is thrown
     */
    public static void doubleClick(WebDriver driver, By locator){
    	try {
    		doubleClickOnce(driver, locator);
        }catch(StaleElementReferenceException ex) {
            log.warn("stale element " + locator + ": " + ex);
            sleepSecs(3);
    		doubleClickOnce(driver, locator);
    	}
    }
    
    /**
     * find a specified element and click it
     */
    public static void clickElement(WebDriver driver, By selector, int num){

        boolean found = false;

        List<WebElement> elements = driver.findElements(selector);

        if(num <= elements.size() && num >= 0){
            for(int i = 0; !found && (i < elements.size()); i++){
                if(i == (num-1)) {
                    WebElement el = elements.get(i);
                    moveMouse(driver, el);
                    el.click();
                    found = true;
                }
            }
        }

    }


    /**
     * Sleep for the given number of seconds, and ignore an interrupted exception
     * @param secs
     */
    public static void sleepSecs(int secs){
        try{
            Thread.sleep(secs * 1000);
        }catch (InterruptedException ex){}
    }

    /**
     * Sleep for the given number of seconds, and ignore an interrupted exception
     * @param millis
     */
    public static void sleepMillis(long millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException ex){}
    }


	public static void rightClick(WebDriver driver, By locator){
		Actions action = new Actions(driver);
        WebElement el = waitForClickable(driver, locator);
        sleepMillis(200);
        try {
            action.contextClick(el).build().perform();
        } catch(StaleElementReferenceException ex) {
            log.warn("stale element " + locator + ": " + ex);
            sleepSecs(3);
            el = waitForClickable(driver, locator);
            action.contextClick(el).build().perform();
        }
	}

	public static void dragAndDropBy(WebDriver driver,  By source, int xOffSet, int yOffSet){
		WebElement element = waitForElement(driver, source);

		new Actions(driver).dragAndDropBy(element, xOffSet, yOffSet).build().perform();
	}


	public static WebElement clickUsingJavascript(WebDriver driver, WebElement button){
		String script = "arguments[0].click();";
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object result = executor.executeScript(script, button);
		log.info("executed script=" + script + ", button=" + button + ", WebElement=" + button + ", result=" + result + " on " + driver.getCurrentUrl() + " " +
				"[" + driver
				.getTitle() + "]");
		return button;
	}


    public static void handleConfirmMsg(WebDriver driver, String title, String msg, String buttonTxt) {
    	handleConfirmMsg(driver, title, msg, buttonTxt, DEFAULT_TIMEOUT_SECONDS);
    }
    
    /**
     * Making sure a popup with a certain msg is present. Click on the ok button and make sure popup disappears.
     * @param driver
     * @param title
     * @param msg
     * @param buttonTxt
     */
    public static void handleConfirmMsg(WebDriver driver, String title, String msg, String buttonTxt, long timeoutSeconds) {
        // check the confirm box pops up
        By confirmBox = Locator.divText(title);
        SeleniumUtils.assertElementExistsWithWait(driver, confirmBox, timeoutSeconds);

        if(msg != null && !msg.equals("")) {
        	By message = Locator.divText(msg);
        	SeleniumUtils.assertElementExistsWithWait(driver, message);
        }
        // click the ok button
        By okButton = Locator.spanText(buttonTxt);
        SeleniumUtils.clickUsingJavascript(driver, okButton);

        // check the box goes away
        SeleniumUtils.waitForInVisible(driver, confirmBox);
    }

    public static void goToTab(WebDriver driver, By tab, String pageTitle) {
        SeleniumUtils.waitForElement(driver, tab, 30);
        SeleniumUtils.clickUsingJavascript(driver, tab);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.assertPageTitle(driver, pageTitle);
    }

    public static void goToSubTab(WebDriver driver, By tab, By subTab, String pageTitle) throws Exception{
        SeleniumUtils.waitForElement(driver, tab, 30);
        SeleniumUtils.moveThenClick(driver, tab, subTab);
        SeleniumUtils.waitForInVisible(driver, Locator.divText("Loading..."));
        SeleniumUtils.assertPageTitle(driver, pageTitle);
    }

    public static void waitOnProgressBar(WebDriver driver, int seconds) {
        SeleniumUtils.waitForElement(driver, By.xpath("//div[contains(@class, 'x-message-box') and contains(@class, 'x-hidden-offsets')]"), seconds);
    }

    public static String getAttributeValue(WebDriver driver, By element, String attr){
        WebElement webElement = checkForElement(driver, element);
        String attrValue = webElement.getAttribute(attr).toString();
        return attrValue;
    }

    public static void clickonfusionel(WebDriver driver){
        Actions action = new Actions(driver);
        WebElement chart1 = driver.findElement(By.xpath("//div[substring-before(substring-after(@id, 'window'), 'body')]"));
        List<WebElement> elements = chart1.findElements(By.cssSelector("g.fusioncharts-legend > text"));
        WebElement el = null;
        for (int i = (elements.size()-1); i >= 0; i--){
            el = elements.get(i);
            action.click(el).build().perform();
        }
    }

    public static void clickonfusionel(WebDriver driver, By chartLocator){
        Actions performAction = new Actions(driver);
        WebElement chart = driver.findElement(chartLocator);
        List<WebElement> elements = chart.findElements(By.cssSelector("g.fusioncharts-legend > text"));
        WebElement el = null;
        for (int i = (elements.size()-1); i >= 0; i--){
            el = elements.get(i);
            performAction.click(el).build().perform();
        }
    }

    // Skip test
    public static void skipTest(String message){
        throw new SkipException(message);
    }

}

