package com.buabook.common.shutdown;

/**
 * <h3>Shutdown Function Interface for {@link ShutdownThread}</h3>
 * (c) 2017 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 24 Feb 2017
 */
@FunctionalInterface
public interface ShutdownFunction {
	void doShutdown();
}
