package com.buabook.common.net.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import com.buabook.common.net.DataSocket;
import com.buabook.common.net.exceptions.DataSocketFailedToInitialiseException;

public class DataSocketTest {
	
	private final int testAddressPort = 1234;
	
	private final String testInputString = "test-string";
	
	private final Boolean testIsClosedBoolean = Boolean.FALSE;
	
	private final ByteArrayOutputStream testOutputStream = new ByteArrayOutputStream();

	
	private Socket testSocket;
	
	@Before
	public void initialise() throws IOException {
		this.testSocket = mock(Socket.class);
		when(testSocket.getRemoteSocketAddress()).thenReturn(new InetSocketAddress(testAddressPort));
		when(testSocket.getInputStream()).thenReturn(new ByteArrayInputStream(testInputString.getBytes()));
		when(testSocket.getOutputStream()).thenReturn(testOutputStream);
		when(testSocket.isClosed()).thenReturn(testIsClosedBoolean);
	}
	
	
	// Constructor
	
	@Test(expected=DataSocketFailedToInitialiseException.class)
	public void testConstructorThrowsExceptionOnNullSocket() throws DataSocketFailedToInitialiseException {
		new DataSocket(null);
	}
	
	@Test
	public void testConstructorReturnsNoErrorWithValidSocket() throws DataSocketFailedToInitialiseException {
		new DataSocket(testSocket);
	}
	
	// DataSocket.getRemoteSocketAddress
	
	@Test
	public void testGetRemoteSocketAddressReturnsConfiguredAddress() throws DataSocketFailedToInitialiseException {
		InetSocketAddress address = (InetSocketAddress) new DataSocket(testSocket).getRemoteSocketAddress();
		
		assertThat(address, is(not(nullValue())));
		assertThat(address.getPort(), is(equalTo(testAddressPort)));
	}
	
	// DataSocket.getReceiveStream
	
	@Test
	@SuppressWarnings("resource")
	public void testGetReceiveStreamReturnsDataInputStreamBasedOnSocket() throws DataSocketFailedToInitialiseException, IOException {
		DataInputStream dis = new DataSocket(testSocket).getReceiveStream();
		
		assertThat(dis, is(not(nullValue())));
		assertThat(dis.available(), is(equalTo(testInputString.length())));
	
		byte[] stringOutput = new byte[testInputString.length()];
		dis.readFully(stringOutput);
		
		assertThat(new String(stringOutput), is(equalTo(testInputString)));
	}
	
	// DataSocket.getSendStream
	
	@Test
	@SuppressWarnings("resource")
	public void testGetSendStreamReturnsDataOutputStreamBasedOnSocket() throws DataSocketFailedToInitialiseException, IOException {
		DataOutputStream dos = new DataSocket(testSocket).getSendStream();
		
		assertThat(dos.size(), is(equalTo(0)));
		assertThat(testOutputStream.size(), is(equalTo(0)));
		
		dos.writeBytes("some string data");
		
		assertThat(dos.size(), is(equalTo(testOutputStream.size())));
	}
	
	// DataSocket.isClosed
	
	@Test
	public void testIsClosedReturnsTrueIfSocketIsNull() throws DataSocketFailedToInitialiseException {
		DataSocket socket = new DataSocket(testSocket);
		socket.disconnect();
		
		assertThat(socket.isClosed(), is(equalTo(true)));
	}
	
	@Test
	public void testIsClosedReturnsSocketStateIfOpen() throws DataSocketFailedToInitialiseException {
		DataSocket socket = new DataSocket(testSocket);
		assertThat(socket.isClosed(), is(equalTo(testIsClosedBoolean)));
	}
	
}
