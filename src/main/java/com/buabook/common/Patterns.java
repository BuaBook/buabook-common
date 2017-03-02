package com.buabook.common;

import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * <h3>Common Regular Expressions (as {@link Pattern})</h3>
 * (c) 2016 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 19 Jul 2016
 */
public final class Patterns {

	public static final Pattern IS_VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,63}$", Pattern.CASE_INSENSITIVE);


	/** 
	 * @return <code>true</code> if the specified e-mail address is valid, <code>false</code> 
	 * otherwise (including <code>null</code> or empty string) 
	 */
	public static boolean isValidEmail(String emailAddress) {
		if(Strings.isNullOrEmpty(emailAddress))
			return false;
		
		return IS_VALID_EMAIL.matcher(emailAddress).find();
	}
}
