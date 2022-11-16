package com.clearskye.test.util;

import org.openqa.selenium.By;

public class Locator {
	
	/**
	 * Provider of a string xpath value
	 * This has a little interface to support string values and string
	 * values created by functions like concat.
	 */
	public static interface XpathTextValue {
		String getString();
	}

	/**
	 * A raw string value, rendered as 'value'
	 */
	public static class XpathText implements XpathTextValue {
		private String v;
		
		public XpathText(String v){
			this.v = v;
		}
		
		@Override public String getString() {
			return "'" + (v==null?"":v) + "'";
		}
	}

	public static class XpathTextViaFunction implements XpathTextValue {
		private String function;
		
		public XpathTextViaFunction(String function){
			this.function = function;
		}
		
		@Override public String getString() {
			return function;
		}
	}
	
	/**
	 * This can be used to escape single quotes using concat	
	 */
	public static XpathTextValue escapeTextValueOfXpath(String text) {
		if(text == null) return new XpathText(null);
		
		if(!text.contains("'")) return new XpathText(text);
		
		// use concat
		StringBuilder sb = new StringBuilder("concat(");
		int i = 0;
		int previ = 0;
		for(i = text.indexOf('\'', i); i != -1; i = text.indexOf('\'', i)) {
			if(previ != 0) sb.append(",");
			if(previ != i) {
				sb.append("'").append(text.substring(previ, i)).append("'");
				sb.append(",");
			}
			sb.append("\"'\"");
			i++;
			previ = i;
		}
		if(previ != 0 && previ < text.length()) {
			sb.append(",");
			sb.append("'").append(text.substring(previ)).append("'");
		}
		sb.append(")");
		return new XpathTextViaFunction(sb.toString());
	}

    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By spanText(String text){
        return By.xpath("//span[text()[.='" + text + "']]");
    }

    /**
     * Select a span with the given text inside a div that has the given id
     * //div[@id='divid']//span[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By spanTextWithinDivId(String divId, String text){
        return By.xpath("//div[@id='" + divId + "']//span[text()[.='" + text + "']]");
    }

    /**
     * Select a span with the given text inside an element that has the given id
     * //*[@id='id']//span[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By spanTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//span[text()[.='" + text + "']]");
    }
	
    	/**
        * Select a div with the given text inside an element that has the given id
        * //*[@id='id']//div[text()[.='mytext']]
        * @param text
        * @return
        */
       public static By divTextWithinId(String elementId, String text){
           return By.xpath("//*[@id='" + elementId + "']//div[text()[.='" + text + "']]");
       }

    public static By h1Text(String text){
        return By.xpath("//h1[text()[.='" + text + "']]");
    }

    public static By h1TextContains(String text){
        return By.xpath("//h1[text()[contains(.,'" + text + "')]]");
    }

    public static By spanId(String id){
        return By.cssSelector("[id='"+id+"']");
    }
    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By h2Text(String text){
        return By.xpath("//h2[text()[.='" + text + "']]");
    }

    public static By h2TextContains(String text){
        return By.xpath("//h2[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a span with the given text
     * @param text
     * @return
     */
    public static By spanTextContains(String text){
        return By.xpath("//span[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a span containing a class with the given name
     * @param className
     * @return
     */
    public static By spanContainsClass(String className){
        return By.xpath("//span[contains(@class, '" + className + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By divText(String text){
        return By.xpath("//div[text()[.='" + text + "']]");
    }

    /**
     * Select any element with the given text
     * @param text
     * @return
     */
    public static By anyText(String text){
        return By.xpath("//*[text()[.='" + text + "']]");
    }

    /**
     * Select the element with the given text value within the element with the given id
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By anyTextWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[text()[.='" + text + "']]");
    }

    /**
     * A selector for a single element with the given id and the given title
     * @param id
     * @param title
     * @return
     */
    public static By byIdAndTitle(String id, String title){
        return By.xpath("//*[@id='" + id + "' and @title='" + title + "']");
    }


    /**
     * Select any element that contains the given text
     * @param text
     * @return
     */
    public static By anyTextContains(String text){
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select a div containing a class with the given name
     * @param className
     * @return
     */
    public static By divContainsClass(String className){
        return By.xpath("//div[contains(@class, '" + className + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By idText(String id, String text){
        return By.xpath("//*[@id='" + id + "']/text()[.='" + text + "']]");
    }

    /**
     * Select any element within another element id that contains the given text
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By containsTextWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select any element within another element id that contains the given text
     * @param id The element id to look within
     * @param text The text to look for
     * @return By The locator
     */
    public static By textWithinId(String id, String text){
        return By.xpath("//*[@id='" + id + "']//*[text()[.='" + text + "']]");
      
    }

    public static By anyContainsText(String text){
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By divTextContains(String text){
        return By.xpath("//div[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a div with the given text
     * @param text
     * @return
     */
    public static By liTextContains(String text){
        return By.xpath("//li[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select a label with the given text
     * @param text
     * @return
     */
    public static By labelText(String text){
        return By.xpath("//label[text()[.='" + text + "']]");
    }

    /**
     * Select a label with the given text
     * //label[text()[contains(.,'mytext')]]
     * @param text
     * @return
     */
    public static By labelTextContains(String text){
        return By.xpath("//label[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select an anchor with the given text
     * //a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorText(String text){
        return By.xpath("//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given class
     * //div[contains(@class, 'myclass')]//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given class
     * //*[contains(@class, 'myclass')]//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinAnyWithClass(String c, String text){
        return By.xpath("//*[contains(@class, '" + c + "')]//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given class
     * //*[contains(@class, 'myclass')]//a[contains(text(),'mytext')]
     * @param text
     * @return
     */
    public static By anchorTextContainsWithinAnyWithClass(String c, String text){
        return By.xpath("//*[contains(@class, '" + c + "')]//a[contains(text(),'" + text + "')]");
    }

    /**
     * Select an anchor with the given text inside any element that has the given id
     * //*[@id='myid']//a[contains(text(),'mytext')]
     * @param text
     * @return
     */
    public static By anchorTextContainsWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a[contains(text(),'" + text + "')]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given id
     * //div[@id='myid']//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinDivId(String divId, String text){
        return By.xpath("//div[@id='" + divId + "']//a[text()[.='" + text + "']]");
    }

    /**
     * Select an anchor with the given text inside a div that has the given id
     * //*[@id='myid']//a[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By anchorTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a[text()[.='" + text + "']]");
    }

    /**
     * FInd the element with the given text residing within an element that has the given css class
     * //*[contains(@class, 'myclass')]//*[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By textWithinElementWithClass(String classname, String text){
        return By.xpath("//*[contains(@class, '" + classname + "')]//*[text()[.='" + text + "']]");
    }

    /**
     * Select an div with the given text inside a div with a given class
     * //div[contains(@class, 'myclass')]//div[text()[.='mytext']]
     * @param text
     * @return
     */
    public static By divTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//div[text()[.='" + text + "']]");
    }

    
    /**
     * Select an anchor that contains the given text
     * @param text
     * @return
     */
    public static By anchorTextContains(String text){
        return By.xpath("//a[text()[contains(.,'" + text + "')]]");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     *  //a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanText(String text){
        return By.xpath("//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     * //div[contains(@class, 'myclass')]//a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanTextWithinDivWithClass(String divClass, String text){
        return By.xpath("//div[contains(@class, '" + divClass + "')]//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Select an anchor that as a child span with the given text (EXT style link)
     * //*[@id='myid')]//a/span[text()[.='mytext']]/..
     * @param text
     * @return
     */
    public static By anchorWithSpanTextWithinId(String elementId, String text){
        return By.xpath("//*[@id='" + elementId + "']//a/span[text()[.='" + text + "']]/..");
    }

    /**
     * Locate a main tab
     * @param name
     * @return
     */
    public static By tab(String name) {
       // return By.linkText(name);
        return By.xpath("//div/ul/li/a[text()[starts-with(.,'" + name + "')]]");
    }

    public static By buttonWithSpanText(String text) {
        //return By.xpath("//button/span[text()[.='" + text + "']]/..");
        return By.xpath("(//button/span[text()[.='" + text + "']])[last()]");
    }
	
	
    /**
        * html button element within an id
        * //*[@id='myid']//button[text()[.='mytext']]
        * @param id The element id
        * @param text The text of the button element
        * @return
        */
       public static By buttonWithTextWithinId(String id, String text) {
           return By.xpath("//*[@id='" + id + "']//button[text()[.='" + text + "']]");
       }

    public static By inputWithNameOnPopupTitle(String popupTitle, String inputName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@name='" + inputName + "']");
    }

    public static By inputWithNameOnPopupTitleParent(String popupTitle, String inputName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@name='" + inputName + "']/..");
    }

    /**
     * Match on an html input element that has the value attribute set
     * We specify a popup title so we only look within a popup (since there may be other elements on the page)
     * ie. <input value='aaa'>...</input>
     * @param popupTitle The popup title
     * @param inputValue The value attribute of the input element
     * @return By The locator 
     */
    public static By inputWithValueOnPopupTitle(String popupTitle, String inputValue) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//input[@value='" + inputValue + "']");
    }

    public static By divTextWithinPopupTitle(String popupTitle, String divText) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//div[text()[.='" + divText + "']]");
    }

    /**
     * A helper to find any text string within a popup title
     * The popup title is a way to locate the popup without needing the internal ids
     * This is EXT specific helper
     * @param popupTitle The popup title
     * @param text The text to find
     * @return By The Locator
     */
    public static By anyTextWithinPopupTitle(String popupTitle, String text) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//*[text()[.='" + text + "']]");
    }

    public static By spanTextWithinPopupTitle(String popupTitle, String spanText) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//span[text()[.='" + spanText + "']]");
    }

    public static By textWithinPopupTitle(String popupTitle, String text) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//*[text()[.='" + text + "']]");
    }

    public static By textareaWithNameOnPopupTitle(String popupTitle, String textAreaName) {
        return By.xpath("//div[text()[.='" + popupTitle + "']]/../../../../..//textarea[@name='" + textAreaName + "']");
    }

    public static By inputWithinLabel(String label, String text) {
        return By.xpath("//span[text()[.='" + label + "']]/../..//input[@name='" + text + "']");
    }

    /**
     * Get an input given the name attribute (<input name='myname' ...)
     * @param name The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithName(String name) {
    	return By.xpath("//input[@name='" + name + "']");
    }


    /**
     * Get an input given the name attribute (<input value='Apply' ...)
     * @param value The value of the input element
     * @return By The locator
     */
    public static By inputWithValue(String value) {
    	return By.xpath("//input[@value='" + value + "']");
    }

    /**
     * Locator an input given a name, but only with an element with the given element id
     * eg to find elements within a section of the page (like a popup box)
     * @param id The element id to look within
     * @param name The input element name attribute value
     * @return The Locator
     */
    public static By inputWithNameWithinElementId(String id, String name) {
    	return By.xpath("//*[@id='" + id + "']//input[@name='" + name + "']");
    }
		
    /**
        * Locator an input given a name, but only with an element with the given element id
        * eg to find elements within a section of the page (like a popup box)
        * @param id The element id to look within
        * @param name The input element name attribute value
        * @return The Locator
        */
       public static By inputWithNameAndTypeWithinElementId(String id, String name, String type) {
       	return By.xpath("//*[@id='" + id + "']//input[@name='" + name + "' and @type='" + type + "']");
       }	

    public static By inputWithType(String type) {
        return By.xpath("//input[@type='" + type + "']");
    }

    /**
     * Get the parent element of input element given the name attribute (parent of <input name='myname' ...)
     * @param name The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithNameParent(String name) {
    	return By.xpath("//input[@name='" + name + "']/..");
    }

    /**
     * Get an input given the name attribute (<input name='myname' ...)
     * @param id The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithId(String id) {
    	return By.xpath("//input[@id='" + id + "']");
    }

    public static By inputWithRole(String role) {
        return By.xpath("//input[@role='" + role + "']");
    }

    /**
     * Get the parent element of input element given the name attribute (parent of <input name='myname' ...)
     * @param id The value of the name attribute of the input element
     * @return By The locator
     */
    public static By inputWithIdParent(String id) {
    	return By.xpath("//input[@id='" + id + "']/..");
    }

    /**
     * Find a textarea anywhere with the given name
     * Careful with a page that may contain multiple textareas with the same name attribute, the locator could match multiple web elements
     * @param name The name attribute value of the textarea element
     * @return By The Locator
     */
    public static By textareaWithName(String name) {
    	return By.xpath("//textarea[@name='" + name + "']");
    }

   
    public interface Elements {
    	public static final By username = By.id("user_name");
    	public static final By password = By.id("user_password");
    	public static final By loginBtn = By.id("sysverb_login");
    }
    
    public interface Profile {
    	public static final By createNew = By.id("sysverb_new");
    	public static final By objectId = By.id("x_cls_clear_skye_i_profile.object_id");
    	public static final By identity = By.id("sys_display.x_cls_clear_skye_i_profile.identity");
    	public static final By profileType = By.id("x_cls_clear_skye_i_profile.profile_type");
    	public static final By ownerUser = By.id("sys_display.x_cls_clear_skye_i_profile.owner");
    	public static final By startDate = By.id("x_cls_clear_skye_i_profile.start_date_base");
    	public static final By endDate = By.id("x_cls_clear_skye_i_profile.end_date");
    	public static final By managedBy = By.id("sys_display.x_cls_clear_skye_i_profile.managed_by");
    	public static final By title = By.id("x_cls_clear_skye_i_profile.title");
    	public static final By name = By.id("x_cls_clear_skye_i_profile.name");
    	public static final By givenName = By.id("x_cls_clear_skye_i_profile.first_name");
    	public static final By legalName = By.id("x_cls_clear_skye_i_profile.full_name");
    	public static final By middleName = By.id("x_cls_clear_skye_i_profile.middle_name");
    	public static final By preferredName = By.id("x_cls_clear_skye_i_profile.preferred_name");
    	public static final By surname = By.id("x_cls_clear_skye_i_profile.last_name");
    	public static final By organizationTab = By.xpath("//span[text()='Organization']");
    	public static final By save = By.id("sysverb_insert_bottom");
    	public static final By profileTab = By.xpath("//span[text()='Profiles']");
    	public static final By lookupIdentity = By.id("lookup.x_cls_clear_skye_i_profile.identity");
    	public static final By selectIdentity = By.className("glide_ref_item_link");
    	public static final By lookupManagedBy = By.id("lookup.x_cls_clear_skye_i_profile.managed_by");
    	public static final By lookupOwner = By.id("lookup.x_cls_clear_skye_i_profile.owner");
    	public static final By firstName = By.id("sys_user.first_name");
    	public static final By newOwnerBtn = By.id("sysverb_new"); 
    	public static final By lastName = By.id("sys_user.last_name");
    	public static final By titleOwner = By.id("sys_user.title");
    	public static final By department = By.id("sys_display.sys_user.department");
    	public static final By location = By.id("sys_display.sys_user.location");
    	public static final By costCenter = By.id("sys_display.sys_user.cost_center");
    	public static final By userName = By.id("sys_user.user_name");
    	public static final By manager = By.id("sys_display.sys_user.manager");
    	public static final By email = By.id("sys_user.email");
    	public static final By language = By.id("sys_user.preferred_language");
    	public static final By calendarIntegration = By.id("sys_user.calendar_integration");
    	public static final By phoneNumber = By.id("sys_user.phone");
    	public static final By mobileNumber = By.id("sys_user.mobile_phone");
    	public static final By password = By.id("sys_user.user_password");
    	public static final By submit = By.id("sysverb_insert_bottom");
 	   	public static final By employeeId = By.id("x_cls_clear_skye_i_profile.employee_id_base");
 	   	public static final By employeeNumber = By.id("x_cls_clear_skye_i_profile.employee_number"); 
	   	public static final By employeeType = By.id("x_cls_clear_skye_i_profile.employee_type");
	   	public static final By companyRaw = By.id("sys_display.x_cls_clear_skye_i_profile.company_cmn"); 
 	   	public static final By departmentRaw = By.id("sys_display.x_cls_clear_skye_i_profile.department_cmn");
 	   	public static final By costCenterRaw = By.id("sys_display.x_cls_clear_skye_i_profile.cost_center_cmn"); 
	   	public static final By locationRaw = By.id("sys_display.x_cls_clear_skye_i_profile.location_cmn");
	   	public static final By tabs = By.className("default-focus-outline"); 
	   	public static final By phone = By.id("x_cls_clear_skye_i_profile.phone");
	   	public static final By emailAddress = By.id("x_cls_clear_skye_i_profile.email_address"); 
	   	public static final By mobilePhone = By.id("x_cls_clear_skye_i_profile.mobile_phone");
	   	public static final By personalEmail = By.id("x_cls_clear_skye_i_profile.personal_email");
	   	public static final By notes = By.xpath("//span[text()='Notes']"); 
	   	public static final By information = By.xpath("//span[text()='Information']");
	   	public static final By search = By.xpath("//input[@placeholder=\"Search\"]");
	   	public static final By selectProfile = By.className("formlink"); 
	   	public static final By checkProfile = By.xpath("//label[@for='allcheck_x_cls_clear_skye_i_profile.x_cls_clear_skye_i_account.profile']");
	   	public static final By checkBox = By.className("checkbox");
	   	public static final By selectAction = By.xpath("//*[@id=\"x_cls_clear_skye_i_profile.x_cls_clear_skye_i_account.profile_choice_actions\"]/select"); 
	   	public static final By actionOption = By.xpath("//*[@id=\"x_cls_clear_skye_i_profile.x_cls_clear_skye_i_account.profile_choice_actions\"]/select/optgroup[@label=' Assign Tag:']/option"); 
	   	public static final By tagName = By.id("name_input");
		public static final By groupsAndUsers = By.id("groups_users_button"); 
	   	public static final By addGroup = By.id("add_group_button");
	   	public static final By groupName = By.className("select2-input"); 
	   	public static final By addUser = By.id("add_user_button");
		public static final By saveTag = By.id("save_button"); 
		public static final By selectOption = By.className("select2-result-label");
    }
    
    public interface Identity {
		public static final By sortByCreated = By.className("column_head");
    	public static final By firstDate = By.className("datex");
		public static final By stageField = By.className("form-control-search");
    	public static final By home = By.xpath("//div[text()='Homepage']"); 
    	public static final By cardView = By.id("tab-card");
    	public static final By identityRequests = By.xpath("//span[text()='Identity Requests']");
    	public static final By searchBy = By.className("form-control");
    	public static final By selectIdentity = By.className("formlink");
    	public static final By identityID = By.className("timeline-body");
    	public static final By selectAllRequests = By.id("allcheck_x_cls_clear_skye_i_identity_operations.sysapproval_approver.sysapproval");
    	public static final By serviceCatalogPortal = By.className("ilmdroplink"); 
    	public static final By selectAction = By.xpath("//*[@id=\"x_cls_clear_skye_i_identity_operations.sysapproval_approver.sysapproval_choice_actions\"]/select");
    	public static final By createNewRequest = By.className("item-card-column");
    	public static final By linkTag = By.className("panel-body");
    	public static final By givenName = By.id("sp_formfield_first_name"); 
    	public static final By middleName = By.id("sp_formfield_middle_name"); 
    	public static final By preferredName = By.id("sp_formfield_preferred_name"); 
    	public static final By surname = By.id("sp_formfield_last_name"); 
    	public static final By title = By.id("sp_formfield_title"); 
    	public static final By phoneNumber = By.id("sp_formfield_phone"); 
    	public static final By startDate = By.id("sp_formfield_start_date"); 
    	public static final By department = By.id("sp_formfield_department"); 
    	public static final By location = By.id("sp_formfield_location"); 
    	public static final By mobileNumber = By.id("sp_formfield_mobile_phone"); 
    	public static final By comments = By.id("sp_formfield_comments"); 
    	public static final By managedBy = By.id("s2id_autogen2_search"); 
    	public static final By profileType = By.className("select2-input");
    	public static final By profile = By.id("s2id_autogen3_search");
    	public static final By submit = By.id("submit-btn"); 
    	public static final By attachment = By.className("sp-attachments-input"); 
    	public static final By replace = By.xpath("//input[@value='replace']");
    	public static final By requestVariablesTab = By.className("tab_caption_text");
	   	public static final By newBtn = By.id("sysverb_new"); 
	   	public static final By variableDefinition = By.id("sys_display.x_cls_clear_skye_i_reqvar_variable.variable");
	   	public static final By value = By.id("x_cls_clear_skye_i_reqvar_variable.value");
	   	public static final By previousValue = By.id("x_cls_clear_skye_i_reqvar_variable.previous_value"); 
	   	public static final By setBy = By.id("sys_display.x_cls_clear_skye_i_reqvar_variable.set_by");
	   	public static final By warning = By.id("ni.x_cls_clear_skye_i_reqvar_variable.warning");
	   	public static final By diagnostics = By.id("x_cls_clear_skye_i_reqvar_variable.diagnostics"); 
	   	public static final By submitBtn = By.id("sysverb_insert_bottom");
	   	public static final By search = By.xpath("//input[@placeholder=\"Search\"]");
	   	public static final By newAccount = By.className("catalog-text-wrap");
	   	public static final By request = By.className("list_action");
    }
    
    public interface Account {
		public static final By confirmAlert = By.xpath("//button[text()='OK']");
    	public static final By selectDropdown = By.className("select2-result-label"); 
    	public static final By accountRequests = By.xpath("//span[text()='Account Requests']");
    	public static final By selectAllRequests = By.id("allcheck_x_cls_clear_skye_i_account_operations.sysapproval_approver.sysapproval");
    	public static final By selectables = By.className("select2-choice"); 
    	public static final By selectAction = By.xpath("//*[@id=\"x_cls_clear_skye_i_account_operations.sysapproval_approver.sysapproval_choice_actions\"]/select");
    	public static final By preferredAccountName = By.id("sp_formfield_unformattedName"); 
    	public static final By description = By.id("sp_formfield_description"); 
    	public static final By outsideSpace = By.xpath("//span[text()='Account Provisioning']"); 
    	public static final By entitleName = By.id("sp_formfield_name");
    	public static final By entitleDesc = By.id("sp_formfield_description");
    	public static final By filter = By.id("filter");
    }
    
    public interface Role {
    	public static final By newRole = By.id("sysverb_new");
    	public static final By rolesTab = By.xpath("//span[text()='Roles']");
    	public static final By name = By.id("x_cls_clear_skye_i_role_v2.name");
    	public static final By type = By.id("sys_display.x_cls_clear_skye_i_role_v2.type");
    	public static final By environment = By.id("sys_display.x_cls_clear_skye_i_role_v2.environment");
    	public static final By risk = By.id("x_cls_clear_skye_i_role_v2.risk");
    	public static final By profile = By.id("sys_display.x_cls_clear_skye_i_role_v2.profile");
    	public static final By description = By.id("x_cls_clear_skye_i_role_v2.description");
    	public static final By approvalAdd = By.className("cs-add");
    	public static final By selectables = By.className("select2-choice");
    	public static final By approvalUser = By.id("s2id_autogen2_search");
    	public static final By selectUser = By.className("select2-result-label");
    	public static final By okBtn = By.xpath("//button[text()='OK']");
    	public static final By save = By.id("sysverb_insert_bottom");
    	public static final By saveApprovals = By.xpath("//span[text()='Save Approvals']");
    	public static final By responsibleGroup = By.id("lookup.x_cls_clear_skye_i_role_v2.responsible_group");
    	public static final By groupName = By.id("sys_user_group.name");
    	public static final By groupManager = By.id("sys_display.sys_user_group.manager");
    	public static final By groupEmail = By.id("sys_user_group.email");
    	public static final By groupParent = By.id("sys_display.sys_user_group.parent");
    	public static final By groupDescription = By.id("sys_user_group.description");
    	public static final By groupSubmit = By.id("sysverb_insert_bottom");
    	public static final By addEntitlement = By.id("sysverb_edit_m2m"); 
	    public static final By collectionText = By.id("_x_cls_clear_skye_i_group");
	   	public static final By selectCollection = By.id("select_0");
	   	public static final By addToCollection = By.id("add_to_collection_button"); 
	   	public static final By addFilter = By.id("_add");
	   	public static final By runFilter = By.id("_run"); 
	   	public static final By selectCondition = By.id("select2-chosen-8");
	   	public static final By condition = By.id("s2id_autogen8_search");
	   	public static final By saveEntitlement = By.id("select_0_sysverb_save"); 
	   	public static final By selectOpCollection = By.id("select_1");
	   	public static final By removeFromCollection = By.id("remove_from_collection_button"); 
	   	public static final By profileLookup = By.id("lookup.x_cls_clear_skye_i_role_v2.profile");
    }
    
    public interface Templates {
    	public static final By reviewTemplates = By.xpath("//span[text()='Review Templates']");
    	public static final By createNew = By.id("sysverb_new");
    	public static final By typeOfReview = By.xpath("//a[contains(text(), 'Only Accounts: review of accounts — not including access')]");
    	public static final By title = By.id("x_cls_clear_skye_i_review_configuration.title");
    	public static final By description = By.id("x_cls_clear_skye_i_review_configuration.description");
    	public static final By reviewers = By.xpath("//span[text()='Reviewers']");
    	public static final By selectReviewerType = By.id("x_cls_clear_skye_i_review_configuration.reviewer_selection");
    	public static final By reviewer = By.id("sys_display.x_cls_clear_skye_i_review_configuration.person");
    	public static final By schedule = By.xpath("//span[text()='Schedule']");
    	public static final By deadline = By.id("x_cls_clear_skye_i_review_configuration.due_selection");
    	public static final By days = By.id("ni.x_cls_clear_skye_i_review_configuration.review_durationdur_day");
    	public static final By hours = By.id("ni.x_cls_clear_skye_i_review_configuration.review_durationdur_hour");
    	public static final By mins = By.id("ni.x_cls_clear_skye_i_review_configuration.review_durationdur_min");
    	public static final By seconds = By.id("ni.x_cls_clear_skye_i_review_configuration.review_durationdur_sec");
    	public static final By advanced = By.xpath("//span[text()='Advanced']");
    	public static final By allowConcurrent = By.id("label.ni.x_cls_clear_skye_i_review_configuration.allow_concurrent");
    	public static final By bypassApproval = By.id("label.ni.x_cls_clear_skye_i_review_configuration.bypass_approval");
    	public static final By postReviewWorkflow = By.id("sys_display.x_cls_clear_skye_i_review_configuration.workflow");
    	public static final By save = By.id("sysverb_insert_bottom");
    	public static final By startReview = By.id("start.review");
    	public static final By advancedView = By.xpath("//button[text()='Advanced View']");
    	public static final By notes = By.id("x_cls_clear_skye_i_review_event.work_notes");
    	public static final By updateReview = By.id("sysverb_update");
    	public static final By cancelReview = By.id("cancel.review.event");
    	public static final By lookupReviewer = By.id("lookup.x_cls_clear_skye_i_review_configuration.person");
    	public static final By addReviewer = By.className("list_edit_new_row"); 
	   	public static final By addDelegate = By.className("vt");
	   	public static final By reviewerText = By.id("sys_display.LIST_EDIT_x_cls_clear_skye_i_reviewer_exceptions.reviewer"); 
	   	public static final By addReviewerOk = By.id("cell_edit_ok");
	   	public static final By delegateText = By.id("sys_display.LIST_EDIT_x_cls_clear_skye_i_reviewer_exceptions.delegate"); 
	    public static final By checkbox = By.className("checkbox-label");
	    public static final By actionOptionList = By.className("list_action_option"); 
	    public static final By deleteConfirmBtn = By.id("ok_button");
	    public static final By searchField = By.xpath("//input[@placeholder=\"Search\"]");
	    public static final By LookupReviewer = By.id("ref_list.LIST_EDIT_x_cls_clear_skye_i_reviewer_exceptions.reviewer");
	    public static final By LookupDelegate = By.id("ref_list.LIST_EDIT_x_cls_clear_skye_i_reviewer_exceptions.delegate");
	    public static final By configuration = By.id("sys_display.×_cls_clear_skye_i_review_configuration.configured_by");
	    
    }
    
    public interface Campaigns {
    	public static final By reviewCampaigns = By.xpath("//span[text()='Review Campaigns']");
    	public static final By selectACampaign = By.className("formlink");
    	public static final By notesTab = By.xpath("//span[text()='Notes']");
    	public static final By notesText = By.id("x_cls_clear_skye_i_review_event.work_notes");
    	public static final By activityStream = By.xpath("//span[text()='Activity Stream']");
    	public static final By workNotes = By.id("activity-stream-textarea");
    	public static final By post = By.xpath("//button[contains(text(), 'Post')]");
    	public static final By closeActivityStream = By.id("list_stream_reader_close_button");
    	public static final By personalizeForm = By.id("togglePersonalizeForm");
    	public static final By moreOptions = By.id("toggleMoreOptions");
    	public static final By addTag = By.id("tags_menu");
    	public static final By tagText = By.xpath("//input[@placeholder='Add tag...']");
    	public static final By toggleTemplate = By.id("template-toggle-button");
    	public static final By iconAdd = By.className("icon-add");
    	public static final By global = By.id("label.ni.sys_template.global");
    	public static final By shortDescription = By.id("sys_template.short_description");
    	public static final By submit = By.id("sysverb_insert_bottom");
    	public static final By disableTemplates = By.xpath("//button[@aria-label='Disable Template Bar']");
    	public static final By update = By.id("sysverb_update");
    	public static final By cancelReviewEvent = By.id("cancel.review.event");
    	public static final By closeTemplate = By.id("sys_template_closemodal");
    }
    
    public interface AccessReviews {
    	public static final By tab = By.xpath("//span[text()='Access Reviews']");
    	public static final By selectAReview = By.className("formlink");
    	public static final By inputField = By.className("ui-grid-filter-input");
    	public static final By unlockWatchList = By.id("x_cls_clear_skye_i_ilm_review.watch_list_unlock");
    	public static final By watchListName = By.id("sys_display.x_cls_clear_skye_i_ilm_review.watch_list");
    	public static final By watchListEmail = By.id("text.value.x_cls_clear_skye_i_ilm_review.watch_list");
    	public static final By addMe = By.id("add_me.x_cls_clear_skye_i_ilm_review.watch_list");
    	public static final By listLock = By.id("x_cls_clear_skye_i_ilm_review.watch_list_lock");
    	public static final By follow = By.id("connectFollowWidgetAction");
    	public static final By stopWatch = By.className("icon-stop-watch");
    	public static final By closeWindow = By.id("_closemodal");
    	public static final By showTimeline = By.xpath("//a[text()='Show SLA Timeline']");
    	public static final By zoomIn = By.xpath("//button[@title='Zoom in']");
    	public static final By zoomOut = By.xpath("//button[@title='Zoom out']");
    	public static final By slaConditions = By.className("sla_condition_tab");
    	public static final By slaRightIcon = By.className("icon-chevron-right");
    	public static final By slaCloseTab = By.xpath("//button[@title='Close detail']");
    	public static final By settings = By.className("settings_button");
    	public static final By switchSLA = By.className("switch");
    	public static final By slaLeftIcon = By.className("icon-chevron-left");
    	public static final By refresh = By.xpath("//button[@title='Refresh']");
    	public static final By scrollDown = By.className("section_sub_header");
    	public static final By addEmail = By.xpath("//*[@id=\"x_cls_clear_skye_i_ilm_review.watch_list_edit\"]/div[3]/span/button");
    }
    
    public interface GuidedSetup {
    	public static final By tab = By.xpath("//span[text()='Guided Setup']");
    	public static final By getStarted = By.xpath("//button[@ng-attr-id='button{{content.sys_id}}']");
    	public static final By markAsComplete = By.xpath("//button[contains(@ng-attr-id, 'omplete{{c.sys_id}}')]");
    	public static final By viewNotes = By.className("status-add-notes-text-gap");
    	public static final By comments = By.id("activity-stream-textarea");
    	public static final By postComment = By.className("activity-submit");
    	public static final By close = By.xpath("//button[text()='Close']");
    	public static final By assign = By.id("assignedToPopover");
    	public static final By backBtn = By.className("icon-chevron-left");
    	public static final By assignName = By.className("supperimpose");
    	public static final By nameText = By.className("select2-input");
    }
    
   public interface AccessPolicies {
	   public static final By tab = By.xpath("//span[text()='Access Policies']");
	   public static final By newPolicy = By.id("sysverb_new");
	   public static final By title = By.id("x_cls_clear_skye_i_access_policies.title");
	   public static final By description = By.id("x_cls_clear_skye_i_access_policies.description");
	   public static final By category = By.id("sys_display.x_cls_clear_skye_i_access_policies.category");
	   public static final By chooseField = By.className("filerTableSelect");
	   public static final By cityName = By.className("filerTableInput");
	   public static final By andOrClauses = By.className("filerTableAction");
	   public static final By addActions = By.id("sysverb_insert");
	   public static final By departmentName = By.className("filter-reference-input");
	   public static final By addActionsBottom = By.id("sysverb_insert_bottom");
   }
   
   public interface RVS {
	   public static final By rvsTab = By.xpath("//span[text()='Request Variable Settings']");
	   public static final By newRequest = By.id("sysverb_new");
	   public static final By arrowDown = By.className("select2-choice");
	   public static final By dropdownText = By.className("select2-input");
	   public static final By variable = By.id("sys_display.x_cls_clear_skye_i_reqvar_setting.variable");
	   public static final By order = By.id("x_cls_clear_skye_i_reqvar_setting.order");
	   public static final By active = By.id("label.ni.x_cls_clear_skye_i_reqvar_setting.active");
	   public static final By andBtn = By.xpath("//button[@alt=\"Add AND condition\"]");
	   public static final By departmentText = By.className("filter-reference-input");
	   public static final By dynamic = By.id("label.ni.x_cls_clear_skye_i_reqvar_setting.dynamic");
	   public static final By advanced = By.id("label.ni.x_cls_clear_skye_i_reqvar_setting.advanced");
	   public static final By settingsValue = By.id("x_cls_clear_skye_i_reqvar_setting.value");
	   public static final By submit = By.id("sysverb_insert_bottom");
   }
   
   public interface Connectors {
	   public static final By tab = By.xpath("//span[text()='Connectors']"); 
	   public static final By newBtn = By.id("sysverb_new"); 
	   public static final By name = By.id("x_cls_clear_skye_i_identity_provider.name"); 
	   public static final By version = By.id("x_cls_clear_skye_i_identity_provider.version"); 
	   public static final By description = By.id("x_cls_clear_skye_i_identity_provider.description"); 
	   public static final By deploymentNotes = By.id("x_cls_clear_skye_i_identity_provider.deployment_notes"); 
	   public static final By activeLabel = By.id("label.ni.x_cls_clear_skye_i_identity_provider.active"); 
	   public static final By authenticationMethod = By.id("x_cls_clear_skye_i_identity_provider.authentication_method"); 
	   public static final By dataTarget = By.id("label.ni.x_cls_clear_skye_i_identity_provider.data_import_credential_target"); 
	   public static final By endpoint = By.id("label.ni.x_cls_clear_skye_i_identity_provider.endpoint"); 
	   public static final By clientID = By.id("label.ni.x_cls_clear_skye_i_identity_provider.client_id"); 
	   public static final By clientSecret = By.id("label.ni.x_cls_clear_skye_i_identity_provider.client_secret"); 
	   public static final By dataImport = By.id("label.ni.x_cls_clear_skye_i_identity_provider.delta_import_compatible"); 
	   public static final By credentialOptions = By.id("label.ni.x_cls_clear_skye_i_identity_provider.identity_provider_credential_options"); 
	   public static final By credentialTarget = By.id("label.ni.x_cls_clear_skye_i_identity_provider.identity_provider_credential_target"); 
	   public static final By domain = By.id("label.ni.x_cls_clear_skye_i_identity_provider.use_domain"); 
	   public static final By location = By.id("label.ni.x_cls_clear_skye_i_identity_provider.location"); 
	   public static final By attributes = By.xpath("//span[text()='Attributes']"); 
	   public static final By attributeName = By.id("name-11"); 
	   public static final By attributeVal = By.id("value-11"); 
	   public static final By settings = By.xpath("//span[text()='Settings']"); 
	   public static final By submit = By.id("sysverb_insert_bottom"); 
       public static final By selectAll = By.className("breadcrumb_link"); 
   }
   
   public interface ObjectCategory {
	   public static final By tab = By.xpath("//span[text()='Object Category']"); 
	   public static final By newBtn = By.id("sysverb_new");
	   public static final By submit = By.id("sysverb_insert_bottom"); 
	   public static final By typeTab = By.xpath("//span[text()='Object Category Types']");
	   public static final By appliesTo = By.id("x_cls_clear_skye_i_resource_type.applies_to"); 
	   public static final By status = By.id("x_cls_clear_skye_i_resource_type.status");
	   public static final By displayName = By.id("x_cls_clear_skye_i_resource_type.display_name"); 
	   public static final By description = By.id("x_cls_clear_skye_i_resource_type.description");
	   public static final By type = By.id("sys_display.x_cls_clear_skye_i_resource.resource_type"); 
	   public static final By lookupType = By.id("lookup.x_cls_clear_skye_i_resource.resource_type");
	   public static final By octype = By.id("x_cls_clear_skye_i_resource.status"); 
	   public static final By displayNameOC = By.id("x_cls_clear_skye_i_resource.display_name");
	   public static final By descriptionOC = By.id("x_cls_clear_skye_i_resource.description"); 
	   public static final By accessType = By.id("x_cls_clear_skye_i_resource.access_type");
	   public static final By selectRole = By.id("sys_display.x_cls_clear_skye_i_resource.entitlement_groups"); 
	   public static final By lookupRole = By.id("lookup.x_cls_clear_skye_i_resource.entitlement_groups");
   }  
   
      public interface Environment {
	    public static final By tab = By.xpath("//span[text()='Environments']");
		public static final By name = By.id("x_cls_clear_skye_i_environment.name");
		public static final By connector = By.id("sys_display.x_cls_clear_skye_i_environment.id_provider");
		public static final By lookupConnector = By.id("lookup.x_cls_clear_skye_i_environment.id_provider");
		public static final By description = By.id("x_cls_clear_skye_i_environment.description");
		public static final By autheritative = By.id("label.ni.x_cls_clear_skye_i_environment.primary_environment");
		public static final By connectorSubmit = By.id("sysverb_insert");
		public static final By preferences = By.xpath("//span[text()='Preferences']");
		public static final By connectionSettings = By.xpath("//span[text()='Connection Settings']");
		public static final By midServer = By.id("lookup.x_cls_clear_skye_i_environment.mid_server");
		public static final By serverName = By.id("ecc_agent.name");
		public static final By serverStatus = By.id("ecc_agent.status");
		public static final By serverSubmit = By.id("sysverb_insert_bottom");
		public static final By attributes = By.xpath("//span[text()='Attributes']");
		public static final By attributeName = By.id("name-17");
		public static final By attributeVal = By.id("value-17");
		public static final By save = By.id("sysverb_insert_bottom");
		public static final By location = By.id("x_cls_clear_skye_i_environment.location");
		public static final By endpointUnlock = By.id("x_cls_clear_skye_i_environment.endpoint_unlock");
		public static final By endpoint = By.id("x_cls_clear_skye_i_environment.endpoint");
		public static final By credentialsTarget = By.id("x_cls_clear_skye_i_environment.identity_provider_credential_target");
		public static final By domain = By.id("x_cls_clear_skye_i_environment.domain");
		public static final By clientID = By.id("x_cls_clear_skye_i_environment.client_id");
		public static final By clientSecret = By.id("x_cls_clear_skye_i_environment.client_secret");
		public static final By authMethod = By.id("x_cls_clear_skye_i_environment.authentication_method");
		public static final By oathType = By.id("x_cls_clear_skye_i_environment.oauth_type");
		public static final By tokenUnlock = By.id("x_cls_clear_skye_i_environment.token_endpoint_unlock");
		public static final By tokenEndpoint = By.id("x_cls_clear_skye_i_environment.token_endpoint");
		public static final By publish = By.id("cs.env.publish");
		public static final By connection = By.id("x_cls_clear_skye_i_environment.name");
		public static final By connector1 = By.id("sys_display.x_cls_clear_skye_i_environment.id_provider");
		public static final By environmentId = By.id("x_cls_clear_skye_i_environment.environment_id");
		public static final By saveR = By.id("ssysverb_update_bottom");
		public static final By ccAlias = By.id("sys_display.x_cls_clear_skye_i_environment.cc_alias");
		public static final By publishR = By.id("cs.env.publish_bottom");
		public static final By rootHostR = By.id("value-37");
		public static final By rootBaseR = By.id("value-39");
		public static final By environmentID = By.id("x_cls_clear_skye_i_environment.environment_id");
		public static final By importConfigurationsR = By.className("action_context");
		public static final By clickAllR = By.className("checkbox-label");
		public static final By midServerR = By.id("sys_display.x_cls_clear_skye_i_environment.mid_server");
//		public static final By selectAction = By.xpath("//*[@id=\"x_cls_clear_skye_i_environment.x_cls_clear_skye_i_import_configuration.environment_choice_actions\"]/select");
		public static final By status = By.className("vt");
		public static final By statuscheck = By.className("tab_caption_text");
		public static final By selectAction = By.id("listv2_35ed31b71bee5d14a2c12023b24bcb4e_labelAction");
		public static final By importbutton = By.className("formlink");
		
   }
   
   public interface	Configurations {
	   public static final By tab = By.xpath("//span[text()='Account Configurations']");
	   public static final By displayName = By.id("x_cls_clear_skye_i_account_settings.display_name");
	   public static final By accountType = By.id("sys_display.x_cls_clear_skye_i_account_settings.account_type");
	   public static final By environment = By.id("sys_display.x_cls_clear_skye_i_account_settings.environment");
	   public static final By order = By.id("x_cls_clear_skye_i_account_settings.order");
	   public static final By filterText = By.className("filter-reference-input");
	   public static final By nameConventionLookup = By.id("lookup.x_cls_clear_skye_i_account_settings.default_naming_convention");
	   public static final By destination = By.id("x_cls_clear_skye_i_account_settings.source");
	   public static final By navTab = By.className("tab_caption_text");
	   public static final By transferAction = By.id("x_cls_clear_skye_i_account_settings.transfer_action");
	   public static final By offboardingOptions = By.id("x_cls_clear_skye_i_account_settings.offboarding_action");
	   public static final By submit = By.id("sysverb_insert_bottom");
	   public static final By nameTitle = By.id("x_cls_clear_skye_i_naming_convention.title");
	   public static final By description = By.id("x_cls_clear_skye_i_naming_convention.description");
	   public static final By maxLength = By.id("x_cls_clear_skye_i_naming_convention.max_length");
	   public static final By conventionType = By.id("x_cls_clear_skye_i_naming_convention.type");
   	   public static final By approvalUser = By.className("select2-input");
   	   public static final By environmentLookup = By.id("lookup.x_cls_clear_skye_i_account_settings.environment");
	   public static final By filterLookup = By.className("filerTableAction");
   }
   
   public interface Alerts {
	   public static final By severity = By.id("x_cls_clear_skye_i_ilm_alert.alert.severity");
	   public static final By state = By.id("x_cls_clear_skye_i_ilm_alert.state");
	   public static final By groupLookup = By.id("lookup.x_cls_clear_skye_i_ilm_alert.assignment_group");
	   public static final By assignedToLookup = By.id("lookup.x_cls_clear_skye_i_ilm_alert.assigned_to");
	   public static final By classification = By.id("x_cls_clear_skye_i_ilm_alert.alert.classification");
	   public static final By knowledgeLock = By.id("x_cls_clear_skye_i_ilm_alert.alert.knowledgebase_unlock");
	   public static final By knoweledgeBase = By.id("x_cls_clear_skye_i_ilm_alert.alert.knowledgebase");
	   public static final By description = By.id("x_cls_clear_skye_i_ilm_alert.alert.description");
	   public static final By message = By.id("x_cls_clear_skye_i_ilm_alert.message");
	   public static final By workNotes = By.id("x_cls_clear_skye_i_ilm_alert.work_notes");
	   public static final By submit = By.id("sysverb_insert_bottom");
	   public static final By assignedTo = By.id("sys_display.x_cls_clear_skye_i_ilm_alert.assigned_to");
	   public static final By tab = By.xpath("//span[text()='Alerts']");
   }
   
   public interface Conventions {
	   public static final By tab = By.xpath("//span[text()='Naming Conventions']");
	   public static final By title = By.id("x_cls_clear_skye_i_naming_convention.title");
	   public static final By delete = By.id("sysverb_delete");
	   public static final By description = By.id("x_cls_clear_skye_i_naming_convention.description");
	   public static final By type = By.id("x_cls_clear_skye_i_naming_convention.type");
	   public static final By max_length = By.id("x_cls_clear_skye_i_naming_convention.max_length");
	   public static final By nextBtn = By.className("form_action_button");
	   public static final By elementDesc = By.id("x_cls_clear_skye_i_naming_convention.element_description");
	   public static final By elementVal = By.id("x_cls_clear_skye_i_naming_convention.value");
	   public static final By elementMaxLen = By.id("x_cls_clear_skye_i_naming_convention.inp_max_length");
	   public static final By elementOrder = By.id("x_cls_clear_skye_i_naming_convention.order");
	   public static final By elementAdd = By.id("add_element_bottom");
	   public static final By okBtn = By.id("ok_button");
   }
   
   public interface EntitlementConfigs {
	   public static final By displayName = By.id("x_cls_clear_skye_i_group_settings.display_name");
	   public static final By tab = By.xpath("//span[text()='Entitlement Configurations']");
	   public static final By environment = By.id("sys_display.x_cls_clear_skye_i_group_settings.environment");
	   public static final By environmentLookup = By.id("lookup.x_cls_clear_skye_i_group_settings.environment");
	   public static final By order = By.id("x_cls_clear_skye_i_group_settings.order");
	   public static final By typeLookup = By.id("lookup.x_cls_clear_skye_i_group_settings.group_type");
	   public static final By typeName = By.id("x_cls_clear_skye_i_group_type.name");
	   public static final By typeDesc = By.id("x_cls_clear_skye_i_group_type.description");
	   public static final By submit = By.id("sysverb_insert_bottom");
	   public static final By nameConventionLookup = By.id("lookup.x_cls_clear_skye_i_group_settings.default_naming_convention");
	   public static final By destination = By.id("x_cls_clear_skye_i_group_settings.source");
	   public static final By transferAction = By.id("x_cls_clear_skye_i_group_settings.transfer_action");
	   public static final By navTab = By.className("tab_caption_text");
   }
   
   public interface Deletes {
	   public static final By filter = By.id("filter");
	   public static final By selectUsers = By.xpath("//div[text()='Users']");
	   public static final By selectProfiles = By.xpath("//div[text()='Profiles']");
	   public static final By selectUser = By.xpath("//a[text()='(empty)']");
	   public static final By deleteUser = By.id("sysverb_delete");
	   public static final By ok = By.className("btn-destructive");
	   public static final By search = By.xpath("//input[@placeholder=\"Search\"]");
	   public static final By selectProfile = By.className("formlink");
	   public static final By selectIdentities = By.xpath("//div[text()='Identities']");
	   public static final By selectAccountRequests = By.xpath("//div[text()='Account Requests']");
	   public static final By searchBy = By.className("default-focus-outline");
	   public static final By selectAll = By.xpath("//span[text()='Select All']");
	   public static final By selectAction = By.xpath("//*[@id=\"x_cls_clear_skye_i_account_operations.sysapproval_approver.sysapproval_choice_actions\"]/select");
	   public static final By selectRVS = By.xpath("//div[text()='Request Variable Settings']");
	   public static final By selectRoles = By.xpath("//div[text()='Roles']"); 
	   public static final By selectReviewTemplates = By.xpath("//div[text()='Review Templates']");
	   public static final By selectOCT = By.xpath("//div[text()='Object Category Types']");
	   public static final By selectObjectCategory = By.xpath("//div[text()='Object Category']");
	   public static final By selectAlerts = By.xpath("//div[text()='Alerts']");
	   public static final By selectEC = By.xpath("//div[text()='Entitlement Configurations']");
	   public static final By selectNamingConventions = By.xpath("//div[text()='Naming Conventions']"); 
	   public static final By selectAccountConfig = By.xpath("//div[text()='Account Configurations']");
	   public static final By selectConnectors = By.xpath("//div[text()='Connectors']");
	   public static final By switchFromGlobal = By.xpath("//a[text()='here']");
	   public static final By profiles = By.id("520c26fc9b66a300a017c13ee359ffd0");
/*	   public static final By = By.id(""); 
	   public static final By = By.id("");
	   public static final By = By.id("");
	   public static final By = By.id("");
	   public static final By = By.id(""); 
	   public static final By = By.id("");
	   public static final By = By.id("");
	   public static final By = By.id(""); */
	   public static final By user = By.className("linked");
	   public static final By formcontrol = By.className("form-control");
	   public static final By form = By.className("formlink");
   }
   public interface DataSources {
	   public static final By tab = By.xpath("//span[text()='Data Sources']");
	   public static final By datasources = By.id ("152ee7590a0a0b4f00da6ab844630782");
	   public static final By datasourcesname = By.id("sys_data_source.name");
	   public static final By datasourcesimport = By.id("sys_data_source.import_set_table_label");
	   public static final By datasourcesimportset = By.id("sys_data_source.import_set_table_name");
	   public static final By type = By.className("form-control");
	   public static final By midserver = By.id("sys_display.sys_data_source.mid_server");
	   public static final By format = By.id("sys_data_source.format");
	   public static final By database = By.id("sys_data_source.database_name");
	   public static final By port = By.id("sys_data_source.database_port");
	   public static final By username = By.id("sys_data_source.jdbc_user_name");
	   public static final By password = By.id("sys_data_source.jdbc_password");
	   public static final By server = By.id("sys_data_source.jdbc_server");
	   public static final By name = By.id("sys_data_source.table_name");
	   public static final By bottom = By.id("sysverb_insert_bottom");
	   public static final By connectors = By.id ("53d11593db8372006a47f8fdbf961908");
	   public static final By identity = By.id ("x_cls_clear_skye_i_identity_provider.name");
	   public static final By authentication = By.id ("x_cls_clear_skye_i_identity_provider.authentication_method");
	   public static final By btn = By.className("btn-primary");
	   public static final By classifier = By.id ("label.ni.x_cls_clear_skye_i_capabilities.import");
	   public static final By capabilities = By.id ("x_cls_clear_skye_i_capabilities.type");
	   public static final By description = By.id ("x_cls_clear_skye_i_capabilities.destination");
	   public static final By query = By.id ("x_cls_clear_skye_i_capabilities.query");
	   public static final By select = By.className("formlink");
	   public static final By header = By.className("table-col-header");
	   public static final By caption = By.className("tab_caption_text");
	   public static final By row = By.className("list_edit_new_row");
	   public static final By bt = By.className("color-green");
	   public static final By vt = By.className("vt");
	   public static final By checkbox = By.className("checkbox-label");
	   public static final By formcontrol = By.className("form-control");
	   public static final By list = By.id("listv2_afc02576db3019908a560bb6f496191a_labelAction");
	   public static final By def = By.className("btn-default");
	   public static final By account = By.className("sn-widget-list-title");
	   public static final By naming = By.id("x_cls_clear_skye_i_account_type.name");
	   public static final By descriptioning = By.id("x_cls_clear_skye_i_account_type.description");
	   public static final By primary = By.className("btn-primary");
	   public static final By start = By.id ("start.import_bottom");
	   public static final By display = By.id ("x_cls_clear_skye_i_account_settings.display_name");
	   public static final By accountconfig = By.id ("sys_display.x_cls_clear_skye_i_account_settings.account_type");
	   public static final By environment = By.id("sys_display.x_cls_clear_skye_i_account_settings.environment");
	   public static final By order = By.id("x_cls_clear_skye_i_account_settings.order");
	   public static final By reference = By.className("filter-reference-input");
	   public static final By tableaction = By.className("filerTableAction");
	   public static final By chosen = By.className("select2-chosen");
	   public static final By condoperator = By.className("condOperator");
	   public static final By tableinput = By.className("filerTableInput");
	   public static final By formaction = By.className("form_action_button");
	   public static final By accountsettings = By.id("lookup.x_cls_clear_skye_i_account_settings.account_type");
	   public static final By itemlink = By.className("glide_ref_item_link");
	   public static final By selectinput = By.className("select2-input");
	   public static final By namingconvention = By.id("x_cls_clear_skye_i_naming_convention.title");
	   public static final By descriptionnaming = By.id("x_cls_clear_skye_i_naming_convention.description");
	   public static final By typenaming = By.id("x_cls_clear_skye_i_naming_convention.type");
	   public static final By lengthnaming = By.id("x_cls_clear_skye_i_naming_convention.max_length");
	   public static final By defaultbtn = By.className("btn-default");
	   public static final By conventionelement = By.id("x_cls_clear_skye_i_naming_convention.element_description");
	   public static final By conventionvalue = By.id("x_cls_clear_skye_i_naming_convention.value");
	   public static final By primarybtn = By.className("btn-primary");
	   public static final By elementtype = By.id("x_cls_clear_skye_i_naming_convention.element_type");
	   public static final By conventionfield = By.id("sys_select.x_cls_clear_skye_i_naming_convention.field");
	   public static final By conventiondescription = By.id("x_cls_clear_skye_i_naming_convention.element_description");
	   public static final By conventionelements = By.id("x_cls_clear_skye_i_naming_convention.element_type");
	   public static final By formbtn = By.className("form_action_button");
	   public static final By active = By.id("label.ni.x_cls_clear_skye_i_profile_settings.active");
	   public static final By environmentprofile = By.id("sys_display.x_cls_clear_skye_i_profile_settings.environment");
	   public static final By selectchosen = By.className("select2-chosen");
	   public static final By descriptionprofile = By.id("x_cls_clear_skye_i_profile_settings.short_description");
	   public static final By profilesettings = By.id("sys_display.x_cls_clear_skye_i_profile_settings.naming_convention");
	   public static final By profiletype = By.id("sys_display.x_cls_clear_skye_i_profile_settings.default_profile_type");
	   public static final By formbtncontrol = By.className("form-control");
	   public static final By formlink = By.className("formlink");
	   public static final By addaction = By.className("context_item");
	   public static final By addactionbtn = By.className("additional-actions-context-menu-button");
	   public static final By glide = By.className("glide_ref_item_link");
	   public static final By accounttype = By.id("lookup.x_cls_clear_skye_i_account_settings.account_type");
	   public static final By namingconventions = By.id("lookup.x_cls_clear_skye_i_profile_settings.naming_convention");
	   public static final By captiontab = By.className("tab_caption_text");
	   public static final By profiles = By.id("520c26fc9b66a300a017c13ee359ffd0");
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	  
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	  
	   
	   

	   
	   
	   
	   
	   

	   
	   

	   
/*	   public static final By
	   public static final By
	   public static final By
	   public static final By
	   public static final By
	   public static final By
	   public static final By*/
	   
   }
}

