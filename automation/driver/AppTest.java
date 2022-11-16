package com.clearskye.test.automation.driver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.clearskye.test.automation.AppPage;
import com.clearskye.test.automation.WebDriverUtil;
import com.clearskye.test.automation.domain.TestDataBean;

/**
 * Unit test for simple App.
 */
public abstract class AppTest extends TestCase {

	private final Log log = LogFactory.getLog(AppTest.class);
	public WebDriverUtil webDriverUtil = new WebDriverUtil();

	protected <T> List<T> readTestDataFile(Class<T> testDataType,
			String fileNameKey) throws IOException {
		String[] header = getHeaderArray(fileNameKey);
		CSVReader reader = getCsvReader(fileNameKey);
		ColumnPositionMappingStrategy<T> strat = new ColumnPositionMappingStrategy<T>();
		strat.setType(testDataType);
		strat.setColumnMapping(header);
		CsvToBean<T> csv = new CsvToBean<T>();
		List<T> outList = csv.parse(strat, reader);
		outList.remove(0); // Pop header line
		return outList;
	}

	protected <T> List<TestDataBean> readTestData(Class<T> testDataType,
			String fileNameKey) throws IOException {
		System.out.println(fileNameKey);
		String[] header = getHeaderArray(fileNameKey);
		CSVReader reader = getCsvReader(fileNameKey);
		ColumnPositionMappingStrategy<T> strat = new ColumnPositionMappingStrategy<T>();
		strat.setType(testDataType);
		strat.setColumnMapping(header);
		CsvToBean<T> csv = new CsvToBean<T>();
		System.out.println("Parsing is starting");
		List<TestDataBean> outList = (List<TestDataBean>) csv.parse(strat,reader);
		if (outList.size() > 0) {
			outList.remove(0); // Pop header line
		}
		return outList;
	}

	protected String readJsonFile(String filename) throws IOException {
		InputStream sequencingFile = getClass().getClassLoader()
				.getResourceAsStream(filename);
		if (sequencingFile != null) {
			System.out.println(" HERE " + sequencingFile);
			String data = IOUtils.toString(sequencingFile, "UTF-8");
			return data;
		} else {
			return null;
		}
	}

	private HashMap<String, String> getDataFromHeader(String[] header,
			String[] words, int starter) {
		HashMap<String, String> data = new HashMap<String, String>();
		for (int i = starter; i <= header.length - 1; i++) {
			if (i < words.length) {
				data.put(header[i], words[i]);
			} else {
				data.put(header[i], "");
			}
		}
		return data;
	}
	
	public void takeAScreenShot(String fileName) {
		TakesScreenshot scrShot =((TakesScreenshot) webDriverUtil.getDriver());
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		String fileWithPath = System.getProperty("user.dir") + "/" + webDriverUtil.getProperty("screenshotFolder") + fileName + ".png";
		File DestFile=new File(fileWithPath);
        try {
			FileUtils.copyFile(srcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        srcFile.delete();
	}
	
	
	private String[] getHeaderArray(String fileName) throws IOException {

		CSVReader reader = getCsvReader(fileName);
		String[] header;
		while ((header = reader.readNext()) != null) {
			// First line is header for now TODO add comment handling
			return header;
		}
		return null;
	}

	private CSVReader getCsvReader(String fileName1) {
		String fileName = webDriverUtil.getProperty(fileName1);
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(fileName);
		assertNotNull("File not found", inputStream);
		CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
		return reader;
	}

	@BeforeTest
	protected void setUp() {
		webDriverUtil.loadProperties();
		webDriverUtil.setDefaults();
		webDriverUtil.setupDriver();
	}

	protected void loadProperties() {
		webDriverUtil.loadProperties();
	}

	@AfterTest
	protected void tearDown() {
		webDriverUtil.quit();
	}

	public AppTest() {

	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	protected String getProperty(String info) {
		String data = webDriverUtil.getProperty(info);
		return data;
	}

	protected void runCase(List<TestDataBean> testData, AppPage currentPage) throws Exception {
		for (TestDataBean tdb : testData) {
			log.info(">>> >>> BEGIN TEST CASE :" + tdb.getTestName()
					+ " <<< <<<");
			if (tdb.isSkippedTest()) {
				log.info(">>> >>> !!! TEST CASE SKIPPED :" + tdb.getTestName()
						+ " !!! <<< <<<");
				continue;
			}

			if (tdb.isErrorExpected()) {
				executeErrorCase(currentPage, tdb);
			} else {
				try {
					executeSuccessCase(currentPage, tdb);
				}catch(Exception ex) {
					takeAScreenShot(this.getClass().getName());
					ex.printStackTrace();
					webDriverUtil.getDriver().close();
					throw ex;
				}
			}
			log.info(">>> >>> END TEST CASE :" + tdb.getTestName() + " <<< <<<");

		}
	}

	protected abstract void executeErrorCase(AppPage appPage, TestDataBean tdb);

	protected abstract void executeSuccessCase(AppPage appPage, TestDataBean tdb) throws Exception;
	
	public void logTestStart() {
		log.info(">>> BEGIN TEST :" + this.getClass().getName() + " <<<");

	}

	public void logTestEnd() {
		log.info(">>> END TEST :" + this.getClass().getName() + " <<<");

	}

	public static String nonNull(String s) {
		if (s == null || s.trim().isEmpty())
			return "";
		return s;
	}

	public String getFilePath(String filename) {
		String filepath = "";
		String workingDir = System.getProperty("user.dir");
		String fileSeparator = System.getProperty("file.separator");
		filepath = workingDir + fileSeparator + filename;
		return filepath;
	}
	
	
//Legrand related Common Functions	
	public void navigateToClearSkyeHomePage() {
		String URL = "https://" + webDriverUtil.getProperty("clearsky.homepage.url");
		Reporter.log("Loading Url = " + URL);
		webDriverUtil.loadLocation(URL);
		webDriverUtil.waitAWhile();
	}
}
