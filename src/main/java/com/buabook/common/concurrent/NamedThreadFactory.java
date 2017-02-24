package com.buabook.common.concurrent;

import java.util.concurrent.ThreadFactory;

import com.google.common.base.Strings;

/**
 * <h3>{@link ThreadFactory} with Custom Name Prefixes</h3> 
 * (c) 2016 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 6 Dec 2016
 */
public class NamedThreadFactory implements ThreadFactory {
	
	private final String threadPrefix;
	
	private Integer threadCounter;
	
	/** @throws IllegalArgumentException If the thread prefix is <code>null</code> or empty */
	public NamedThreadFactory(String threadPrefix) throws IllegalArgumentException {
		if(Strings.isNullOrEmpty(threadPrefix))
			throw new IllegalArgumentException("Thread prefix must be specified");
		
		this.threadPrefix = threadPrefix;
		this.threadCounter = 1;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread theThread = new Thread(r);
		
		synchronized (threadCounter) {
			theThread.setName(threadPrefix + "-" + threadCounter.toString());
			threadCounter++;
		}
		
		return theThread;
	}

}
