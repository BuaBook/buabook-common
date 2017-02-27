package com.buabook.common.concurrent.test;

import org.junit.Test;

import com.buabook.common.concurrent.NamedThreadFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NamedThreadFactoryTest {
	
	// Constructor
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsExceptionIfNullThreadPrefixSpecified() {
		new NamedThreadFactory(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsExceptionIfEmptyThreadPrefixSpecified() {
		new NamedThreadFactory("");
	}
	
	// NamedThreadFactory.newThread

	@Test
	public void testNewThreadReturnsThreadWithSpecifiedNamePrefixed() {
		NamedThreadFactory ntf = new NamedThreadFactory("test-prefix");
		Thread aThread = ntf.newThread(null);
		
		assertThat(aThread.getName(), startsWith("test-prefix"));
	}
	
	@Test
	public void testNewThreadReturnsThreadsWithUniqueThreadCounter() {
		NamedThreadFactory ntf = new NamedThreadFactory("test-prefix-again");
		Thread thread1 = ntf.newThread(null);
		Thread thread2 = ntf.newThread(null);
		
		assertThat(thread1.getName(), endsWith("1"));
		assertThat(thread2.getName(), endsWith("2"));
	}

}
