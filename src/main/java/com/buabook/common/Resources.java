package com.buabook.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.io.CharStreams;

public final class Resources {

	/**
	 * @return The contents of the specified file as a string or <code>null</code> if the file does not exist
	 * @see Helpers#getResourceFromClassPath(String)
	 */
	public static String getContentsFromClassPath(String fileName) throws IllegalArgumentException {
		InputStream is = Resources.class.getClassLoader().getResourceAsStream(fileName);
		
		if(is == null)
			return null;
		
		try(InputStreamReader isr = new InputStreamReader(is)) {
			return CharStreams.toString(isr);
		} catch (IOException e) {
			return null;
		} finally {
			try {
				is.close();
			} catch (IOException e) {}
		}
	}

}
