package com.buabook.common.net;

/**
 * (c) 2014 - 2017 Sport Trades Ltd
 *
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 14 Jul 2014
 */
public class DataSocketFailedToInitialiseException extends Exception {
	private static final long serialVersionUID = 7725101053269123364L;
	
	private static final String message = "The data socket failed to initialise, likely due to an issue with the underlying socket.";

	public DataSocketFailedToInitialiseException() {
		super(message);
	}

	public DataSocketFailedToInitialiseException(String arg0) {
		super(message + " " + arg0);
	}

	public DataSocketFailedToInitialiseException(Throwable arg0) {
		super(message, arg0);
	}

	public DataSocketFailedToInitialiseException(String arg0, Throwable arg1) {
		super(message + " " + arg0, arg1);
	}

	public DataSocketFailedToInitialiseException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(message + " " + arg0, arg1, arg2, arg3);
	}

}
