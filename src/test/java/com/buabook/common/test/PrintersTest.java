package com.buabook.common.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.buabook.common.Printers;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class PrintersTest {

	// Printers.getListJoiner
	
	@Test
	public void testGetListJoinerReturnsListJoiner() {
		assertThat(Printers.getListJoiner(), is(not(nullValue())));
		assertThat(Printers.getListJoiner(), is(instanceOf(Joiner.class)));
	}
	
	// Printers.listToString

	@Test
	public void testListToStringReturnsStringSeparatedByCommaSpace() {
		List<String> list = Lists.newArrayList("abc", "def", "ghi");
		assertThat(Printers.listToString(list), is(equalTo("abc, def, ghi")));
	}
	
	@Test
	public void testListToStringStringifiesNonStrings() {
		List<Object> list = Lists.newArrayList("abc", 1234, 'a');
		assertThat(Printers.listToString(list), is(equalTo("abc, 1234, a")));
	}
	
	@Test
	public void testListToStringReturnsEmptyStringIfNullListPassed() {
		assertThat(Printers.listToString(null), is(equalTo("")));
	}
	
	@Test
	public void testListToStringReturnsEmptyStringIfEmptyListPassed() {
		assertThat(Printers.listToString(new LinkedList<String>()), is(equalTo("")));
	}
	
	// Printers.arrayToString
	
	@Test
	public void testArrayToStringReturnsStringSeparatedByCommaSpace() {
		String[] array = { "abc", "bcd", "cde" };
		assertThat(Printers.arrayToString(array), is(equalTo("abc, bcd, cde")));
	}

	@Test
	public void testArrayToStringStringifiesNonStrings() {
		Object[] array = { "abc", 1234, 'a' };
		assertThat(Printers.arrayToString(array), is(equalTo("abc, 1234, a")));
	}
	
	@Test
	public void testArrayToStringReturnsEmptyStringIfNullArrayPassed() {
		assertThat(Printers.arrayToString(null), is(equalTo("")));
	}
	
	@Test
	public void testArrayToStringReturnsEmptyStringIfEmptyArrayPassed() {
		assertThat(Printers.arrayToString(new Object[0]), is(equalTo("")));
	}
	
	// Constructor
	
	@Test
	public void testConstructorThrowsNoError() {
		new Printers();
	}

}
