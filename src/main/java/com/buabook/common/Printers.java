package com.buabook.common;

import java.util.List;

import com.google.common.base.Joiner;

/**
 * <h3>Object to {@link String} Printers - For Logging</h3>
 * (c) 2017 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.1.0
 * @since 27 Apr 2014
 */
public final class Printers {
	
	private static final Joiner LIST_JOINER = Joiner.on(", ").skipNulls();
	
	
	public static Joiner getListJoiner() {
		return LIST_JOINER;
	}

	/** @return A string containing all list elements (stringified), or empty string if list is <code>null</code> or empty */ 
	public static String listToString(List<?> list) {
		if(list == null || list.isEmpty())
			return "";
		
		return LIST_JOINER.join(list);
	}
	
	/** @return A string containing all array elements (stringified), or empty string if array is <code>null</code> or empty */
	public static String arrayToString(Object[] array) {
		if(array == null || array.length == 0)
			return "";
		
		return LIST_JOINER.join(array);
	}
}
