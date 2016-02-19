package org.casadocodigo.loja.websockets;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/channel/sales")
public class SalesEndpoint {

	private ConnectedUsers connectedUsers;

	@Inject
	public SalesEndpoint(ConnectedUsers connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	@OnOpen
	public void onNewUser(Session session) {
		connectedUsers.add(session);
	}

	public void onClose(Session session, CloseReason closeReason) {
		connectedUsers.remove(session);
		System.out.println(closeReason.getCloseCode());
	}
}
