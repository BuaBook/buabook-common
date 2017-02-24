package com.buabook.common;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

public final class StringSplitter {

	/** 
	 * Provides a more reliable version of {@link String#split(String)}, using Guava's {@link Splitter}.
	 * @return Strings split by specified separator. All split strings will be trimmed ({@link Splitter#trimResults()}). If the string to
	 * split is <code>null</code> or empty, an array with a single empty string will be returned. If the separator string is <code>null</code> 
	 * or empty, an array with a single element containing the original string will be returned.
	 */
	public static List<String> split(String toSplit, String separator) {
		if(toSplit == null)
			return Arrays.asList("");
		
		if(Strings.isNullOrEmpty(separator))
			return Arrays.asList(toSplit);
		
		return Splitter.on(separator)
						.trimResults()
						.splitToList(toSplit);
	}
	
	/** 
	 * Provides a more reliable version of {@link String#split(String)}, using Guava's {@link Splitter}. This particular version also removes
	 * any strings that are empty post-split ({@link Splitter#omitEmptyStrings()}).
	 * @return Strings split by specified separator. All split strings will be trimmed ({@link Splitter#trimResults()}). Any strings that are 
	 * empty <i>post-split</i> will be removed ({@link Splitter#omitEmptyStrings()}). If the string to split is <code>null</code>, an array 
	 * with a single empty string will be returned. If the string to split is empty, an empty array will be returned. If the separator string 
	 * is <code>null</code> or empty, an array with a single element containing the original string will be returned.
	 */
	public static List<String> splitAndIgnoreEmpty(String toSplit, String separator) {
		if(toSplit == null)
			return Arrays.asList("");
		
		if(Strings.isNullOrEmpty(separator))
			return Arrays.asList(toSplit);
		
		return Splitter.on(separator)
						.omitEmptyStrings()
						.trimResults()
						.splitToList(toSplit);
	}

}
