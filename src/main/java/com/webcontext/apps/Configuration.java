/**
 * 
 */
package com.webcontext.apps;

import java.io.IOException;
import java.util.Properties;

/**
 * Configuration based on a YAML file.
 * 
 * @author Frédéric Delorme
 *
 */
public class Configuration {

	private static final Configuration _instance = new Configuration();

	Properties props = new Properties();

	/**
	 * Load configuration from file sparku.properties.
	 */
	private Configuration() {
		try {
			props.load(this.getClass().getResourceAsStream("sparky.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * return key value or default value.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String get(String key, String defaultValue) {
		return _instance.props.getProperty(key, defaultValue);

	}

}
