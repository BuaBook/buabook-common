package com.buabook.common.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buabook.common.connection.Process;
import com.buabook.common.net.exceptions.FailedToBindToPortException;
import com.buabook.common.net.interfaces.INewClientSocketListener;

/**
 * <h3>Socket Acceptor Thread</h3>
 * <p>Provides a thread that will wait for incoming connections for 3rd party processes and notify
 * any listeners (of type {@link INewClientSocketListener}) when those connections are made. Multiple
 * listeners are supported.</p>
 * (c) 2016 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 11 Jul 2016
 */
public class SocketAcceptorThread extends Thread {
	private static final Logger log = LoggerFactory.getLogger(SocketAcceptorThread.class);
	

	private final Process bindToPort;
	
	private final ServerSocket serverSocket;
	
	private final List<INewClientSocketListener> newConnectionListeners;
	
	
	public SocketAcceptorThread(Process bindToPort) throws IllegalArgumentException, FailedToBindToPortException { 
		super();
		
		if(bindToPort == null || bindToPort.getPort() == null || bindToPort.getPort() <= 0)
			throw new IllegalArgumentException("Invalid port specified [ Port: " + bindToPort + " ]");
		
		this.bindToPort = bindToPort;
		this.serverSocket = openServerSocket(bindToPort);
		this.newConnectionListeners = new CopyOnWriteArrayList<>();
		
		this.setName("SocketAcceptor-" + bindToPort.toString());
	}

	
	@Override
	public void run() {
		
		while(! serverSocket.isClosed())
			waitForClient();
		
		log.error("Server socket at {} has been closed. This thread will now terminate.", bindToPort.toString());
	}
	
	
	public void addNewConnectionListener(INewClientSocketListener listener) {
		if(listener == null)
			return;
		
		if(newConnectionListeners.contains(listener))
			return;
		
		newConnectionListeners.add(listener);
	}
	
	public void removeConnectionListener(INewClientSocketListener listener) {
		if(listener == null)
			return;
		
		if(newConnectionListeners.contains(listener))
			return;
		
		newConnectionListeners.remove(listener);
	}
	
	
	/**
	 * <p>Blocks the thread and waits for a new inbound client to connect.</p>
	 * <p><b>NOTE</b>: No exception is thrown in the case a client connection cannot be 
	 * created.</p>
	 * @see ServerSocket#accept()
	 */
	@SuppressWarnings("resource")
	private void waitForClient() {
		Socket socket = null;
		
		log.info("Waiting for client connection on socket {}", serverSocket.getLocalSocketAddress());
		
		try {
			socket = serverSocket.accept();
		} catch (IOException e) {
			log.error("Failed to accept new incoming client connection [ Server Socket: {} ]. Error - {}", serverSocket.getLocalSocketAddress(), e.getMessage());
			return;
		}
		
		for(INewClientSocketListener listener : newConnectionListeners)
			listener.notifyNewClientSocket(socket);
	}
	
	
	/**
	 * Opens the {@link ServerSocket} on the specified port
	 * @param bindToPort The port to bind the server socket to
	 * @return The new server socket object to use to accept new inbound client connections
	 * @throws FailedToBindToPortException If the server socket fails to bind to the specified port.
	 */
	public static ServerSocket openServerSocket(Process bindToPort) throws FailedToBindToPortException {
		ServerSocket newServerSocket = null;
		log.info("Attempting to bind to port {}", bindToPort);
		
		try {
			newServerSocket = new ServerSocket(bindToPort.getPort());
		} catch (IOException | SecurityException e) {
			log.error("Failed to bind to port {}. Error - {}", bindToPort, e.getMessage());
			throw new FailedToBindToPortException(e);
		}
		
		log.info("Successfully bound to port {}", bindToPort);
		
		return newServerSocket;
	}
}
