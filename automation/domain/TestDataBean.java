package com.clearskye.test.automation.domain;

public class TestDataBean {

	private String userName;
	private String password;
	private String errorMessage;
	private String skipTest;
	private String testName;
	private String firstName;
	private String lastName;
	private String country;
	private String position;
	private String address;
	private String address2;
	private String billingAddress;
	private String billingAddress2;
	private String billingZipCode;
	private String city;
	private String state;
	private String phoneNumber;
	private String zipCode;
	private String cardNumber;
	private String companyName;
	private String expirationMonth;
	private String expirationYear;
	private String cardCVV;
	private String productID1;
	private String productID2;
	private String productID3;
	private String brand;
	private String sku;
	private String brands;
	private String skus;
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getBrands() {
		return brands;
	}
	
	public void setBrands(String brands) {
		this.brands = brands;
	}
	
	public String getSKUs() {
		return skus;
	}
	
	public void setSKUs(String skus) {
		this.skus = skus;
	}
	
	public String getSKU() {
		return sku;
	}
	
	public void setSKU(String sku) {
		this.sku = sku;
	}
	
	public String getProductID1() {
		return productID1;
	}
	
	public void setProductID1(String id) {
		this.productID1 = id;
	}
	
	public String getProductID2() {
		return productID2;
	}
	
	public void setProductID2(String id) {
		this.productID2 = id;
	}
	
	public String getProductID3() {
		return productID3;
	}
	
	public void setProductID3(String id) {
		this.productID3 = id;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String name) {
		this.address = name;
	}
	
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String name) {
		this.address2 = name;
	}
	
	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String name) {
		this.billingAddress = name;
	}
	
	public String getBillingAddress2() {
		return billingAddress2;
	}

	public void setBillingAddress2(String name) {
		this.billingAddress2 = name;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String name) {
		this.city = name;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String name) {
		this.state = name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String code) {
		this.zipCode = code;
	}
	
	public String getBillingZipCode() {
		return billingZipCode;
	}

	public void setBillingZipCode(String code) {
		this.billingZipCode = code;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String number) {
		this.cardNumber = number;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String name) {
		this.companyName = name;
	}
	
	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String name) {
		this.expirationMonth = name;
	}
	
	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String name) {
		this.expirationYear = name;
	}
	
	public String getCardCVV() {
		return cardCVV;
	}

	public void setCardCVV(String name) {
		this.cardCVV = name;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String name) {
		this.country = name;
	}
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String name) {
		this.position = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage.trim();
	}
	
	public String getSkipTest() {
		return skipTest;
	}

	public void setSkipTest(String s) {
		this.skipTest = s;
	}
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String test) {
		this.testName = test;
	}

	public boolean isErrorExpected() {
		if (getErrorMessage() != null && !getErrorMessage().equals("")) {
			return true;
		}
		return false;
	}

	public boolean isSkippedTest() {
		if (getSkipTest().equalsIgnoreCase("T")) {
			return true;
		}
		return false;
	}
	
	public void printAllCSVValues() {
		System.out.println("Address is " + address);
		System.out.println("city is " + city);
		System.out.println("state is " + state);
		System.out.println("phoneNumber is " + phoneNumber);
		System.out.println("zipCode is " + zipCode);
		System.out.println("cardNumber is " + cardNumber);
		System.out.println("companyName is " + companyName);
		System.out.println("expirationMonth is " + expirationMonth);
		System.out.println("expirationYear is " + expirationYear);
		System.out.println("cardCVV is " + cardCVV);
	}
}
