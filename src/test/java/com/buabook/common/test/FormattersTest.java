package com.buabook.common.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.buabook.common.Formatters;

public class FormattersTest {
	
	private static final DateTimeZone LOCAL_DATE_TIME_ZONE = DateTime.now().getZone();
	

	@Test
	public void testToCurrencyRendersDecimalCorrectly() {
		assertThat(Formatters.TO_CURRENCY.format(1.22d), is(equalTo("1.22")));
		assertThat(Formatters.TO_CURRENCY.format(1.222342342d), is(equalTo("1.22")));
		assertThat(Formatters.TO_CURRENCY.format(1.226d), is(equalTo("1.23")));
		assertThat(Formatters.TO_CURRENCY.format(1d), is(equalTo("1.00")));
	}
	
	@Test
	public void testDateDashReturnsDateWithDashesBetweenTheElements() {
		DateTime toTest = new DateTime(2016, 05, 24, 0, 0);
		
		assertThat(toTest.toString(Formatters.DATE_DASH), is(equalTo("2016-05-24")));
	}
	
	@Test
	public void testDateDotReturnsDateWithDotsBetweenTheElements() {
		DateTime toTest = new DateTime(2016, 05, 24, 0, 0);
		
		assertThat(toTest.toString(Formatters.DATE_DOT), is(equalTo("2016.05.24")));
	}
	
	
	@Test
	public void testDateTimeDashReturnsEarlyDateTimeWithDashes() {
		DateTime toTest = new DateTime(2016, 03, 10, 1, 23);
		String tzId = LOCAL_DATE_TIME_ZONE.getShortName(toTest.getMillis());
		
		assertThat(toTest.toString(Formatters.DATE_TIME_DASH), is(equalTo("2016-03-10-01-23-00-000-" + tzId)));
	}
	
	@Test
	public void testDateTimeDashReturnsDateTimeWithDashes() {
		DateTime toTest = new DateTime(2016, 03, 10, 14, 23);
		String tzId = LOCAL_DATE_TIME_ZONE.getShortName(toTest.getMillis());
		
		assertThat(toTest.toString(Formatters.DATE_TIME_DASH), is(equalTo("2016-03-10-14-23-00-000-" + tzId)));
	}
	
	@Test
	public void testDateTimeDashReturnsMidnightDateTimeWithDashes() {
		DateTime toTest = new DateTime(2016, 03, 10, 0, 23);
		String tzId = LOCAL_DATE_TIME_ZONE.getShortName(toTest.getMillis());
		
		assertThat(toTest.toString(Formatters.DATE_TIME_DASH), is(equalTo("2016-03-10-00-23-00-000-" + tzId)));
	}
	
	
	// Constructor
	
	@Test
	public void testConstructorThrowsNoError() {
		new Formatters();
	}
}
