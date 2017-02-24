package com.buabook.common;

import java.text.DecimalFormat;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * <h3>Data Formatters</h3>
 * (c) 2015 - 2017 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 3 Dec 2015
 */
public final class Formatters {
	
	/** Will convert a decimal to currency format (e.g. 10.1231 -> 10.12) */
	public static final DecimalFormat TO_CURRENCY = new DecimalFormat("0.00");
	
	/** Will print Joda {@link DateTime} as date with dashes (e.g. 2016-07-25) */
	public static final DateTimeFormatter DATE_DASH = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	/** Will print Joda {@link DateTime} as date with dots (e.g. 2016.07.25) */
	public static final DateTimeFormatter DATE_DOT = DateTimeFormat.forPattern("yyyy.MM.dd");
	
	/** Will print Joda {@link DateTime} as date/time (24-hour time) with dashes (e.g. 2016-07-25-14-30-10-211) */
	public static final DateTimeFormatter DATE_TIME_DASH = DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm-ss-SSS-z");
	
	/** Will print Joda {@link DateTime} as user-friendly date with 24 hour time (e.g. 15:23 on 05 Sep 2016) */
	public static final DateTimeFormatter FRIENDLY_DATE_TIME = DateTimeFormat.forPattern("HH:mm 'on' dd MMM yyyy");
	
	/** Will print Joda {@link DateTime} as date/time for logging (e.g. 2016.07.25 12:23:16.123 BST") */
	public static final DateTimeFormatter DATE_TIME_LOG = DateTimeFormat.forPattern("yyyy.MM.dd HH:mm:ss.SSS Z");
	
}
