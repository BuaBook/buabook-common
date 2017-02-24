package com.buabook.common.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import com.buabook.common.Resources;

public class ResourcesTest {

	// Resources.getResourceContentsFromClassPath
	
	@Test
	public void testGetResourceContentsFromClassPathReturnsNullForNonExistantFile() {
		assertThat(Resources.getContentsFromClassPath("does-not-exist"), is(nullValue()));
	}
	
	@Test
	public void testGetResourceContentsFromClassPathReturnsFileContentsForFileName() {
		String fileContents = Resources.getContentsFromClassPath("resource.test");
		
		assertThat(fileContents, is(not(emptyString())));
		assertThat(fileContents, containsString("HelpersTest.java"));
	}
	
	@Test
	public void testGetResourceContentsFromClassPathReturnsFileContentsForFilePath() {
		String fileContents = Resources.getContentsFromClassPath("test-folder/resource-folder.test");
		
		assertThat(fileContents, is(not(emptyString())));
		assertThat(fileContents, containsString("HelpersTest.java"));
	}

}
