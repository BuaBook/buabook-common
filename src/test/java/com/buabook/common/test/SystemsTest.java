package com.buabook.common.test;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;

import com.buabook.common.Systems;

public class SystemsTest {
	
	// Systems.getApplicationRoot

	@Test(expected=IllegalArgumentException.class)
	public void testGetApplicationRootThrowsExceptionIfNoClassSpecified() {
		Systems.getApplicationRoot(null);
	}
	
	@Test
	public void testGetApplicationRootReturnsFileWithRoot() {
		File applicationRoot = Systems.getApplicationRoot(SystemsTest.class);
		
		assertThat(applicationRoot, is(not(nullValue())));
		assertThat(applicationRoot.isDirectory(), is(equalTo(true)));
	}
	
	// Systems.getConfig
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetConfigThrowsExceptionIfNoPropertySpecified() {
		Systems.getConfig(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetConfigThrowsExceptionIfNeitherEnvVariableOrPropertySet() {
		Systems.getConfig("property-does-not-exist");
	}
	
	@Test
	public void testGetConfigReturnsEnvironmentVariableIfSet() {
		String envVariable = Systems.getConfig("JAVA_HOME");
		
		assertThat(envVariable, is(not(nullValue())));
		assertThat(envVariable.length(), is(greaterThan(0)));
	}
	
	@Test
	public void testGetConfigReturnsPropertyIfSet() {
		System.setProperty("test-prop-1", "test-value");
		
		assertThat(Systems.getConfig("test-prop-1"), is(equalTo("test-value")));
	}
	
	@Test
	public void testGetConfigReturnsEnvironmentVariableIfBothAreSet() {
		System.setProperty("JAVA_HOME", "will-not-be-used");
		
		assertThat(Systems.getConfig("JAVA_HOME"), is(not(equalTo("will-not-be-used"))));
	}
	
	
}
