package com.buabook.common;

import java.util.UUID;

/**
 * <h3>Helper Functions for {@link UUID}</h3>
 * (c) 2014 - 2017 Sport Trades Ltd
 *
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 10 Jan 2015
 */
public class Uuids {
	private static final UUID NULL_UUID = new UUID(0l, 0l);
	
	
	/** @return A null UUID (i.e. 00000000-0000-0000-0000-000000000000) */
	public static UUID getNullUuid() {
		return NULL_UUID;
	}
	
	/**
	 * Tests if the specified parameter is a <code>null</code> or an empty {@link UUID}. An empty
	 * {@link UUID} is defined as equalling <code>00000000-0000-0000-0000-000000000000</code>.
	 * @param aUuid The UUID object to test
	 * @return <code>true</code> if the UUID is <code>null</code> or empty, otherwise <code>false</code>.
	 * @see #getNullUuid()
	 */
	public static Boolean isNullOrEmpty(UUID aUuid) {
		if(aUuid == null)
			return true;
		
		return aUuid.equals(getNullUuid());
	}
}
