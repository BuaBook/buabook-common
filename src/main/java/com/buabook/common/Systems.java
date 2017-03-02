package com.buabook.common;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.google.common.base.Strings;

/**
 * <h3>Host System Interface Methods</h3>
 * (c) 2015 - 2017 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.1.0
 * @since 17 Jun 2015
 */
public final class Systems {

	/** 
	 * Determines the root folder that the application is running from based on the location of class containing the application
	 * <code>main</code> method.
	 * @param bootClass The class containing the <code>main</code> method
	 * @return The root folder the application resides in. If the application is in a JAR, it will return the folder that
	 * contains the JAR.
	 */
	public static File getApplicationRoot(Class<?> bootClass) throws IllegalArgumentException {
		if(bootClass == null)
			throw new IllegalArgumentException("No boot class specified");
		
		String projectRoot = bootClass.getProtectionDomain().getCodeSource().getLocation().getPath();
		
		// If we're running from a JAR, get the containing folder as the project root to load the config from
		if(projectRoot.endsWith(".jar") || projectRoot.endsWith(".class"))
			projectRoot = projectRoot.substring(0, projectRoot.lastIndexOf("/"));
		
		String decodedProjectRoot = null;
		
		try {
			decodedProjectRoot = URLDecoder.decode(projectRoot, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		
		return new File(decodedProjectRoot);
	}
	
	/**
	 * Provides a way of retrieving system configuration from <i>either</i> an environment variable first or, if not set, then a Java system property.
	 * @param envPropName The system configuration value to load
	 * @return The system property value
	 * @throws IllegalArgumentException If the property is not set
	 * @see System#getenv(String)
	 * @see System#getProperty(String)
	 */
	public static String getConfig(String envPropName) throws IllegalArgumentException {
		if(Strings.isNullOrEmpty(envPropName))
			throw new IllegalArgumentException("No property to check");
		
		String configValue = System.getenv(envPropName);
    	
    	if(Strings.isNullOrEmpty(configValue))
    		configValue = System.getProperty(envPropName);
    	
    	if(Strings.isNullOrEmpty(configValue))
    		throw new IllegalArgumentException("Property '" + envPropName + "' does not exist");
    	
    	return configValue;
	}

}
