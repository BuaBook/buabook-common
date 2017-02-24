package com.buabook.common.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;

import com.buabook.common.StringSplitter;

public class StringSplitterTest {

	// StringSplitter.split
	
	@Test
	public void testSplitStringByReturnsListWithEmptyElementIfNoStringToSplit() {
		List<String> splitNull = StringSplitter.split(null, ",");
		List<String> splitEmpty = StringSplitter.split("", ",");
		
		assertThat(splitNull, hasSize(1));
		assertThat(splitNull, contains(""));
		
		assertThat(splitEmpty, hasSize(1));
		assertThat(splitEmpty, contains(""));
	}
	
	@Test
	public void testSplitStringByReturnsListWithSingleElementWithInput() {
		List<String> splitWithNull = StringSplitter.split("string", null);
		List<String> splitWithEmptyStr = StringSplitter.split("string", "");
		
		assertThat(splitWithNull, hasSize(1));
		assertThat(splitWithNull, contains("string"));
		
		assertThat(splitWithEmptyStr, hasSize(1));
		assertThat(splitWithEmptyStr, contains("string"));
	}
	
	@Test
	public void testSplitStringByReturnsListWithSingleElementIfNoSeparatorInIt() {
		List<String> splitWithNoSep = StringSplitter.split("noseparator", ",");

		assertThat(splitWithNoSep, hasSize(1));
		assertThat(splitWithNoSep, contains("noseparator"));
	}
	
	@Test
	public void testSplitStringByReturnsListSplitBySeparator() {
		List<String> theSplitNoSpaces = StringSplitter.split("abc,def,ghi", ",");
		List<String> theSplitWithSpaces = StringSplitter.split("abc, def, ghi", ",");
		
		assertThat(theSplitNoSpaces, hasSize(3));
		assertThat(theSplitNoSpaces, containsInRelativeOrder("abc", "def", "ghi"));
		
		assertThat(theSplitWithSpaces, hasSize(3));
		assertThat(theSplitWithSpaces, containsInRelativeOrder("abc", "def", "ghi"));
	}
	
	// StringSplitter.splitAndIgnoreEmpty
	
	@Test
	public void testSplitStringByAndIgnoreEmptyReturnsListWithEmptyElementIfNoStringToSplit() {
		List<String> splitNull = StringSplitter.splitAndIgnoreEmpty(null, ",");
		
		assertThat(splitNull, hasSize(1));
		assertThat(splitNull, contains(""));
	}
	
	@Test
	public void testSplitByStringAndIgnoreEmptyReturnsEmptyListIfEmptyStringToSplit() {
		assertThat(StringSplitter.splitAndIgnoreEmpty("", ","), is(emptyCollectionOf(String.class)));
	}
	
	@Test
	public void testSplitStringByAndIgnoreEmptyReturnsListWithSingleElementWithInput() {
		List<String> splitWithNull = StringSplitter.splitAndIgnoreEmpty("string", null);
		List<String> splitWithEmptyStr = StringSplitter.splitAndIgnoreEmpty("string", "");
		
		assertThat(splitWithNull, hasSize(1));
		assertThat(splitWithNull, contains("string"));
		
		assertThat(splitWithEmptyStr, hasSize(1));
		assertThat(splitWithEmptyStr, contains("string"));
	}
	
	@Test
	public void testSplitStringByAndIgnoreEmptyReturnsListWithSingleElementIfNoSeparatorInIt() {
		List<String> splitWithNoSep = StringSplitter.splitAndIgnoreEmpty("noseparator", ",");

		assertThat(splitWithNoSep, hasSize(1));
		assertThat(splitWithNoSep, contains("noseparator"));
	}
	
	@Test
	public void testSplitStringByAndIgnoreEmptyReturnsListSplitBySeparator() {
		List<String> theSplitNoSpaces = StringSplitter.splitAndIgnoreEmpty("abc,def,ghi", ",");
		List<String> theSplitWithSpaces = StringSplitter.splitAndIgnoreEmpty("abc, def, ghi", ",");
		
		assertThat(theSplitNoSpaces, hasSize(3));
		assertThat(theSplitNoSpaces, containsInRelativeOrder("abc", "def", "ghi"));
		
		assertThat(theSplitWithSpaces, hasSize(3));
		assertThat(theSplitWithSpaces, containsInRelativeOrder("abc", "def", "ghi"));
	}
	
	@Test
	public void testSplitStringByAndIgnoreEmptyIgnoresEmptyElements() {
		List<String> theSplit = StringSplitter.splitAndIgnoreEmpty("abc,,ghi", ",");
		
		assertThat(theSplit, hasSize(2));
		assertThat(theSplit, containsInRelativeOrder("abc", "ghi"));
	}

}
