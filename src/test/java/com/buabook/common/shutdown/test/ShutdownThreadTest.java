package com.buabook.common.shutdown.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Test;

import com.buabook.common.shutdown.ShutdownFunction;
import com.buabook.common.shutdown.ShutdownThread;

public class ShutdownThreadTest {

	// Constructor
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsExceptionIfNullApplicationName() {
		new ShutdownThread(null, new ArrayList<>());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsExceptionIfNullFunctionList() {
		new ShutdownThread("my-test-app", null);
	}
	
	
	// ShutdownThread.addShutdownFunction
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddShutdownFunctionThrowsExceptionIfNullFunction() {
		new ShutdownThread("my-test-app").addShutdownFunction(null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddShutdownFunctionThrowsExceptionIfFunctionAddedAfterThreadStart() {
		ShutdownThread thread = new ShutdownThread("my-test-app");
		thread.start();
		
		thread.addShutdownFunction(() -> {});
	}
	
	// ShutdownThread.run
	
	@Test
	public void testThreadRunsAddedShutdownFunction() throws InterruptedException {
		ShutdownFunction shutdownFunction = mock(ShutdownFunction.class);
		
		new ShutdownThread("my-test-app")
									.addShutdownFunction(shutdownFunction)		
									.start();
		
		Thread.sleep(1000);
		
		verify(shutdownFunction, times(1)).doShutdown();
	}
}
