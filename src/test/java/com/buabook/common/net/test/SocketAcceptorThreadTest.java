package com.buabook.common.net.test;

import org.junit.Test;

import com.buabook.common.connection.Process;
import com.buabook.common.net.SocketAcceptorThread;
import com.buabook.common.net.exceptions.FailedToBindToPortException;

public class SocketAcceptorThreadTest {
	
	// Constructor
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsExceptionIfNullProcess() throws IllegalArgumentException, FailedToBindToPortException {
		new SocketAcceptorThread(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsExceptionIfNullPortProcess() throws IllegalArgumentException, FailedToBindToPortException {
		new SocketAcceptorThread(new Process(null, (Integer) null));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorThrowsExceptionIfNegativePortProcess() throws IllegalArgumentException, FailedToBindToPortException {
		new SocketAcceptorThread(new Process(null, -1));
	}
	
	// SocketAcceptorThread.openServerSocket

	@Test(expected=FailedToBindToPortException.class)
	public void testOpenServerSocketThrowsExceptionIfSecurityException() throws IllegalArgumentException, FailedToBindToPortException {
		System.setSecurityManager(new SecurityManager() {
			@Override
			public void checkListen(int port) {
				throw new SecurityException();
			}
		});
		
		new SocketAcceptorThread(new Process(null, 23423));
	}
	
	@Test(expected=FailedToBindToPortException.class)
	public void testOpenServerSocketThrowsExceptionIfFailToBind() throws IllegalArgumentException, FailedToBindToPortException {
		new SocketAcceptorThread(new Process(null, 12345));
		// Can't bind to the same port twice
		new SocketAcceptorThread(new Process(null, 12345));
	}
}
