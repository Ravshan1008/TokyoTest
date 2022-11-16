package com.clearskye.test.automation;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileUtil {

	public Properties getProperties() {
		Properties properties = new Properties();
		loadProperties( properties, "test.properties");
		loadProperties( properties, "env.properties");
		return properties;
	}

	private void loadProperties( Properties properties, String fileName) {
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(fileName);
		assertNotNull("Properties File found", inputStream);
		try {

			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Failed to read properties file :" + fileName);
		}
	}

}
