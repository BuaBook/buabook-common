package com.buabook.common.connection;

import com.google.common.base.Strings;

/**
 * <h3>Generic Server Definition Container</h3>
 * (c) 2014 - 2017 Sport Trades Ltd
 *
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 31 Mar 2014
 */
public class Process {
	private final String hostname;
	private final Integer port;
	
	public Process(String hostname, Integer port) {
		this.hostname = hostname;
		this.port = port;
	}
	
	/**
	 * Provides object instantiation with the port as a {@link String} and performs the
	 * cast to {@link Integer} as part of the construction
	 * @param hostname The hostname of the process
	 * @param portStr The port of the process <i>as a {@link String}</li>
	 * @throws NumberFormatException If the parse to {@link Integer} fails
	 */
	public Process(String hostname, String portStr) throws NumberFormatException {
		this.hostname = hostname;
		this.port = Integer.parseInt(portStr);
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public Integer getPort() {
		return port;
	}
	
	@Override
	public String toString() {
		if(Strings.isNullOrEmpty(hostname))
			return "::" + port;
		
		return hostname + ":" + port;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(!(obj instanceof Process))
			return false;
		
		Process sObj = (Process) obj;
		
		return (this.hostname.equals(sObj.hostname)) && (this.port.equals(sObj.port));
	}
	
	@Override
	public int hashCode() {
		return hostname.hashCode() + port.hashCode();
	}
}
