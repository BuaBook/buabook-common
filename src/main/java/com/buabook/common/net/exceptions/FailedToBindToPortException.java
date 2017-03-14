package com.buabook.common.net.exceptions;

/**
 * (c) 2014 Sport Trades Ltd
 *
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 13 Jul 2014
 */
public class FailedToBindToPortException extends Exception {
	private static final long serialVersionUID = 5043446418501312561L;
	
	private static final String message = "ServerSocket failed to bind to the specified host.";


	public FailedToBindToPortException(Throwable cause) {
		super(message, cause);
	}
}
