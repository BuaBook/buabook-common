package com.buabook.common.connection.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.buabook.common.connection.Process;

public class ProcessTest {

	// Constructor
	
	@Test(expected=NumberFormatException.class)
	public void testConstructorThrowsExceptionIfUnparseableStringPortPassed() {
		new Process("hostname", "bad-port");
	}
	
	@Test
	public void testConstructorParsesStringPortCorrectly() {
		Process process = new Process("hostname", "12345");
		assertThat(process.getPort(), is(equalTo(12345)));
	}
	
	// Process.getHostname / Process.getPort
	
	@Test
	public void testGettersReturnCorrectFields() {
		Process process = new Process("hostname", 123456);
		
		assertThat(process.getHostname(), is(equalTo("hostname")));
		assertThat(process.getPort(), is(equalTo(123456)));
	}
	
	// Process.toString

	@Test
	public void testToStringReturnsHostnameAndPortConcatenated() {
		Process process = new Process("host.name", 4321);
		assertThat(process.toString(), is(equalTo("host.name:4321")));
	}
	
	@Test
	public void testToStringReturnsDoubleColonPlusPortWhenNoHostname() {
		Process process = new Process(null, 45345);
		assertThat(process.toString(), is(equalTo("::45345")));
	}
	
	// Process.equals
	
	@Test
	public void testEquals() {
		Process process = new Process("host-name", 43531);
		
		Process equalProcess = new Process("host-name", 43531);
		
		Process unequalPort = new Process("host-name", 64533);
		Process unequalHost = new Process("other-host-name", 43531);
		Process completelyDifferent = new Process("some-other-host-name", 90343);
		String notTheSameObject = new String();
		
		assertThat(process.equals(process), is(equalTo(true)));
		assertThat(process.equals(equalProcess), is(equalTo(true)));
		
		assertThat(process.equals(null), is(equalTo(false)));
		assertThat(process.equals(unequalPort), is(equalTo(false)));
		assertThat(process.equals(unequalHost), is(equalTo(false)));
		assertThat(process.equals(completelyDifferent), is(equalTo(false)));
		assertThat(process.equals(notTheSameObject), is(equalTo(false)));
	}
	
	@Test
	public void testEqualsReturnsCorrectlyIfEitherElementIsNull() {
		Process nullHostProcess = new Process(null, 12345);
		Process nullPortProcess = new Process("host-name", (Integer) null);

		Process otherProcess = new Process("host-name", 12345);
		
		assertThat(nullHostProcess.equals(otherProcess), is(equalTo(false)));
		assertThat(nullPortProcess.equals(otherProcess), is(equalTo(false)));
	}
}
