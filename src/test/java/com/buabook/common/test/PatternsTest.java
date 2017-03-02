package com.buabook.common.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.buabook.common.Patterns;

public class PatternsTest {

	// Patterns.isValidEmail
	
	@Test
	public void testIsValidEmailReturnsFalseForEmptyAndNullEmailAddresses() {
		assertThat(Patterns.isValidEmail(""), is(equalTo(false)));
		assertThat(Patterns.isValidEmail(null), is(equalTo(false)));
	}
	
	@Test
	public void testIsValidEmailReturnsFalseForBadlyFormedEmailAddresses() {
		assertThat(Patterns.isValidEmail("no-at.com"), is(equalTo(false)));
		assertThat(Patterns.isValidEmail("no@dot"), is(equalTo(false)));
		assertThat(Patterns.isValidEmail("two@symbols@com"), is(equalTo(false)));
		assertThat(Patterns.isValidEmail("white@\r\nspace.com"), is(equalTo(false)));
		assertThat(Patterns.isValidEmail("quote@\"marks.com"), is(equalTo(false)));
	}
	
	@Test
	public void testIsValidEmailReturnsTrueForValidEmailAddress() {
		assertThat(Patterns.isValidEmail("test@email.com"), is(equalTo(true)));
		assertThat(Patterns.isValidEmail("test@email.co.uk"), is(equalTo(true)));
	}
	
	// Constructor
	
	@Test
	public void testConstructorThrowsNoError() {
		new Patterns();
	}
}

