package com.learning.api.Utils;

/**
 * 
 */

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.putnam.bdd.automation.core.BaseStep;

/**
 * @author prabhat
 *
 */
public final class ConfigProperties {

	private static final String FILE_NAME_SEPARATOR = ",";

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigProperties.class);

	private static final String DEFAULT_PROPS = "/configuration.properties";
	private static final String PROP_EXTENSIONS = "app.properties.files";

	public static final String PROPERTY_FILE_KEY = "PROPERTY_FILE_BDD";

	private static final Properties properties = new Properties();

	private ConfigProperties() {
	}

	static {

		String propertyFiles = System.getProperty(PROPERTY_FILE_KEY);

		if (propertyFiles == null) {
			LOGGER.warn("No custom properties defined with environment variable {}. Loading default properties at {}",
					PROPERTY_FILE_KEY, DEFAULT_PROPS);
			propertyFiles = DEFAULT_PROPS;
		}

		LOGGER.info("Loading property files {}", propertyFiles);

		String[] files = propertyFiles.split(FILE_NAME_SEPARATOR);

		if ((files == null) || (files.length == 0)) {
			throw new IllegalArgumentException(String.format(
					"Expecting the property files to be present at the deafult location `%s` OR it needs to be specified by setting the environment variable `%s`",
					DEFAULT_PROPS, PROPERTY_FILE_KEY));
		}

		for (String fileName : files) {
			loadProperty(fileName, true);
		}

		initalizeExternalProperties();
	}

	private static void initalizeExternalProperties() {
		String fileLocations = properties.getProperty(PROP_EXTENSIONS);
		if (fileLocations != null) {
			String[] files = fileLocations.split(FILE_NAME_SEPARATOR);
			if (files != null && files.length > 0) {
				for (String fileName : files) {
					loadProperty(fileName, false);
				}
			}
		}
	}

	private static void loadProperty(final String file) throws IOException {
		properties.load(ConfigProperties.class.getResourceAsStream(file));
	}

	private static void loadProperty(final String file, final boolean faileOnError) {
		try {
			loadProperty(file);
		} catch (Exception e) {
			LOGGER.error("Error loding properties ", e);

			if (faileOnError) {
				throw new RuntimeException(String.format("Unable to locate properties file %s", file), e);
			}
		}

	}

	public static String get(final String key) {
		return properties.getProperty(key);
	}
}



