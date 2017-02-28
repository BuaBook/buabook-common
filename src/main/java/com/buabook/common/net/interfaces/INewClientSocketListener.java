package com.buabook.common.net.interfaces;

import java.net.ServerSocket;
import java.net.Socket;

import com.buabook.common.net.SocketAcceptorThread;

/**
 * <h3>New Client Socket Connection Listener</h3>
 * <p>Any object that wishes to be notified of a new client socket being created
 * from a {@link SocketAcceptorThread} should implement this interface and add itself
 * via {@link SocketAcceptorThread#addNewConnectionListener(INewClientSocketListener)}</p>
 * (c) 2014 - 2017 Sport Trades Ltd
 *
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 27 Jul 2014
 */
public interface INewClientSocketListener {
	
	/**
	 * Method called when a new client socket is accepted (via {@link ServerSocket#accept()}).
	 * @param newSocket The new socket object created
	 */
	public void notifyNewClientSocket(Socket newSocket);
}
