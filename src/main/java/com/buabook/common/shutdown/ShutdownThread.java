package com.buabook.common.shutdown;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buabook.common.Formatters;
import com.google.common.base.Strings;

/**
 * <h3>Shutdown Hook Thread</h3>
 * <p>Provides a standard way of notifying when an application is shutting down.</p>
 * <p>Functions can be specified as {@link ShutdownFunction}s to be run serially as the application shuts down</p>
 * (c) 2015 - 2017 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.1.0
 * @since 12 Jun 2015
 */
public class ShutdownThread extends Thread {
	private static final Logger log = LoggerFactory.getLogger(ShutdownThread.class);

	
	private final String applicationName;
	
	private final List<ShutdownFunction> shutdownFunctions;
	

	public ShutdownThread(String applicationName) {
		this(applicationName, new ArrayList<>());
	}
	
	/**
	 * @param applicationName The name of the application for logging
	 * @param shutdownFunctions The list of functions that should be executed when the JVM signals 
	 * it is exiting. This cannot be <code>null</code>.
	 * @throws IllegalArgumentException If either parameter is <code>null</code>
	 */
	public ShutdownThread(String applicationName, List<ShutdownFunction> shutdownFunctions) throws IllegalArgumentException {
		super();
		
		if(shutdownFunctions == null || Strings.isNullOrEmpty(applicationName))
			throw new IllegalArgumentException("Application name / shutdown function list");
		
		this.applicationName = applicationName;
		this.shutdownFunctions = shutdownFunctions;
		this.setName("Shutdown-Thread");
		this.setPriority(Thread.MIN_PRIORITY);
		this.setDaemon(true);
	}

	/**
	 * @throws IllegalArgumentException If the function to add is <code>null</code>
	 * @throws IllegalStateException If the thread is running when the add takes place
	 */
	public ShutdownThread addShutdownFunction(ShutdownFunction function) throws IllegalArgumentException, IllegalStateException {
		if(function == null)
			throw new IllegalArgumentException("Function cannot be null");
		
		if(getState() != State.NEW)
			throw new IllegalStateException();
		
		shutdownFunctions.add(function);
		return this;
	}
	

	@Override
	public void run() {
		log.error("APPLICATION SHUTDOWN SIGNALLED");
		
		if(shutdownFunctions.size() > 0) {
			log.error("Running shutdown functions [ Function Count: " + shutdownFunctions.size() + " ]");
			shutdownFunctions.forEach(ShutdownFunction::doShutdown);
		}
		
		String exitStr = "*** " + applicationName.toUpperCase() + " IS EXITING @ " + DateTime.now().toString(Formatters.DATE_TIME_LOG) + " ***";
					
		log.error(exitStr);
		System.err.println(exitStr);
	}

	/** Adds the specified shutdown listener to be executed when the JVM signals its going to halt */
	public static void addShutdownListener(ShutdownThread thread) {
		Runtime.getRuntime().addShutdownHook(thread);
	}
}
