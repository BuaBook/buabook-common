package com.buabook.common.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.UUID;

import org.junit.Test;

import com.buabook.common.Uuids;

public class UuidsTest {

	// Uuids.getNullUuid
	
	@Test
	public void testGetNullUuidIsZeros() {
		UUID nullUuid = Uuids.getNullUuid();
		
		assertThat(nullUuid.getLeastSignificantBits(), is(equalTo(0l)));
		assertThat(nullUuid.getMostSignificantBits(), is(equalTo(0l)));
	}
	
	@Test
	public void testGetNullUuidIsZeroInStringRepresentation() {
		assertThat(Uuids.getNullUuid().toString(), is(equalTo("00000000-0000-0000-0000-000000000000")));
	}
	
	@Test
	public void testGetNullUuidObjectIsEqualAcrossMultipleInvocations() { 
		assertThat(Uuids.getNullUuid(), is(equalTo(Uuids.getNullUuid())));
	}
	
	// Uuids.isNullOrEmpty
	
	@Test
	public void testIsNullOrEmptyReturnsTrueForNullObject() {
		assertThat(Uuids.isNullOrEmpty(null), is(equalTo(true)));
	}
	
	@Test
	public void testIsNullOrEmptyReturnsTrueForEmptyUuid() {
		assertThat(Uuids.isNullOrEmpty(UUID.fromString("00000000-0000-0000-0000-000000000000")), is(equalTo(true)));
	}
	
}
