package com.buabook.common.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buabook.common.net.exceptions.DataSocketFailedToInitialiseException;

/**
 * <h3>Simple {@link Socket} Wrapper</h3>
 * <p>This class is a simple {@link Socket} wrapper that exposes the native {@link Socket#getInputStream()} and
 * {@link Socket#getOutputStream()} as a {@link DataInputStream} and {@link DataOutputStream} respectively.</p>
 * <p>This is useful when wanting to send data over a socket at a low-level (i.e. serialising / de-serialising 
 * Google Protobuf messages).</p>
 * (c) 2014 - 2017 Sport Trades Ltd
 *
 * @author Jas Rajasansir
 * @version 1.0.2
 * @since 14 Jul 2014
 */
public class DataSocket {
	private static final Logger log = LoggerFactory.getLogger(DataSocket.class);
	
	
	private final InetSocketAddress remoteSocketAddress;
	
	
	/** The socket that is wrapped by this class. */
	private Socket socket;
	
	/** @see Socket#getInputStream() */
	private DataInputStream receiveStream;
	
	/** @see Socket#getOutputStream() */
	private DataOutputStream sendStream;

	
	/**
	 * Instantiates the wrapper object based on an existing {@link Socket}
	 * @throws DataSocketFailedToInitialiseException If the socket provided is <code>null</code> or if there 
	 * is any error when attempting to create the {@link DataInputStream} or {@link DataOutputStream}.
	 * @see #initialiseIOStreams()
	 */
	public DataSocket(Socket socket) throws DataSocketFailedToInitialiseException {
		if(socket == null)
			throw new DataSocketFailedToInitialiseException(new NullPointerException("socket"));
		
		this.socket = socket;
		this.remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

		initialiseIOStreams();
		
		log.info("New connection on socket: {}", remoteSocketAddress);
	}
	
	/** @see Socket#getRemoteSocketAddress() */
	public SocketAddress getRemoteSocketAddress() {
		return remoteSocketAddress;
	}
	
	/**  
	 * @see Socket#getInputStream()
	 * @see DataInputStream
	 */
	public DataInputStream getReceiveStream() {
		return receiveStream;
	}
	
	/**
	 * @see Socket#getOutputStream()
	 * @see DataOutputStream
	 */
	public DataOutputStream getSendStream() {
		return sendStream;
	}
	
	/** 
	 * @return <code>true</code> if the socket has been closed. This will only occur when the {@link #disconnect()}
	 * method is called (which in turn should be called when an {@link IOException} occurs on one of the 
	 * data streams). Unfortunately the state change of the socket isn't accurately reflected in the 
	 * {@link Socket} object.
	 */
	public Boolean isClosed() {
		if(socket == null)
			return true;
		
		return socket.isClosed();
	}
	
	/** Disconnects the socket. Any error that occurs during disconnect is suppressed. */
	public void disconnect() {
		// Already disconnected, nothing to do
		if(socket == null)
			return;
		
		log.warn("Disconnecting socket connection [ Remote: {} ]", remoteSocketAddress);
		
		try {
			sendStream.close();
			receiveStream.close();
			
			socket.close();
		} catch (IOException e) {
			log.debug("Failed to close to socket [ Remote: {} ]. Error - {}", remoteSocketAddress, e.getMessage());
		}
		
		socket = null;
	}
	
	/** 
	 * This method will only attempt a reconnect on a disconnected socket (i.e. {@link #isClosed()} returns <code>true</code>).
	 * The socket that is created has the following properties set:
	 * <ul>
	 * 	<li>{@link Socket#setKeepAlive(boolean)} = <code>true</code></li>
	 * </ul>
	 * @throws DataSocketFailedToInitialiseException If the socket cannot be connected
	 * @see #isClosed()
	 * @see #initialiseIOStreams()
	 */
	public synchronized void reconnect() throws DataSocketFailedToInitialiseException {
		if(! isClosed())
			return;
		
		log.info("Attempting socket reconnection [ Remote: {} ]", remoteSocketAddress);
		
		try {
			socket = new Socket(remoteSocketAddress.getAddress(), remoteSocketAddress.getPort());
			socket.setKeepAlive(true);
		} catch (IOException e) {
			log.error("Failed to create new socket for reconnection! Error - {}", e.getMessage());
			throw new DataSocketFailedToInitialiseException(e);
		}
		
		initialiseIOStreams();
		
		log.info("Reconnection successful! [ Remote: {} ]", remoteSocketAddress);
	}

	
	private void initialiseIOStreams() throws DataSocketFailedToInitialiseException {
		try {
			this.receiveStream = new DataInputStream(socket.getInputStream());
			this.sendStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			log.error("Failed to create DataInputStream / DataOutputStream's for socket {}. Error - {}", remoteSocketAddress, e.getMessage());
			throw new DataSocketFailedToInitialiseException(e);
		}
	}
	
}
