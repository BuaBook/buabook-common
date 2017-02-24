package com.buabook.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;

/**
 * <h3>Object Helper Functions</h3>
 * (c) 2016 - 2017 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 02 Aug 2016
 */
public final class Objects {

	/**
	 * Converts a string into the specified class type by assuming a new instance of the class can be constructed with a string 
	 * argument (e.g. {@link Boolean#Boolean(String)}, {@link Double#Double(String)}, etc).  
	 * @param theString
	 * @param targetType The type must have a constructor taking a single string argument to be used with this function
	 * @return The newly instantiated object representing the string or <code>null</code> if the construction fails. <b>NOTE</b>: Classes
	 * might accept an incorrect argument and return an object.
	 * @throws IllegalArgumentException If the string to convert is <code>null</code> or empty, or no target type specified
	 */
	public static <T> T convertStringToClass(String theString, Class<T> targetType) throws IllegalArgumentException {
		if(Strings.isNullOrEmpty(theString))
			throw new IllegalArgumentException("No string to convert");
		
		if(targetType == null)
			throw new IllegalArgumentException("No class to convert to");
		
		T newClassInstance = null;
		
		try {
			newClassInstance = targetType.getConstructor(String.class).newInstance(theString);
		} catch (InvocationTargetException | ClassCastException | InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException e) {
			return null;
		}
		
		return newClassInstance;
	}
	
	/**
	 * Extracts all fields, including private fields, from the specified object and returns them in a {@link Map}.
	 * @see Class#getDeclaredFields()
	 */
	public static Map<String, Object> getObjectFieldsAsMap(Object object) {
		Map<String, Object> fieldsMap = new HashMap<>();
		
		if(object == null)
			return fieldsMap;
		
		Field[] objFields = object.getClass().getDeclaredFields();
		
		if(objFields.length == 0)
			return fieldsMap;
		
		for(Field objField : objFields) {
			boolean isPublic = objField.isAccessible();
			
			if(! isPublic)
				objField.setAccessible(true);
			
			try {
				fieldsMap.put(objField.getName(), objField.get(object));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				fieldsMap.put(objField.getName(), null);
			}
			
			// If it was not accessible before, put it back after accessing it
			if(! isPublic)
				objField.setAccessible(false);
		}
		
		return fieldsMap;
	}
	
}
