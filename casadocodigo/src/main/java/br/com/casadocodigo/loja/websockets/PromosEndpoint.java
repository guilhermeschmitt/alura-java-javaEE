package br.com.casadocodigo.loja.websockets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.casadocodigo.loja.models.Promo;

@ServerEndpoint(value = "/canal/promos")
public class PromosEndpoint {

	@Inject
	private UsuariosSession usuarios;

	@OnOpen
	public void onMessage(Session session) {
		usuarios.add(session);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		usuarios.remove(session);
		System.out.println(closeReason.getCloseCode());
	}

	public void send(Promo promo) {
		List<Session> sessions = usuarios.getUsuarios();
		System.out.println("TO NO SEND");
		System.out.println(sessions);
		for (Session session : sessions) {
			System.out.println("DENTRO DO FOREACH");
			if (session.isOpen()) {
				System.out.println("DENTRO DO IF");
				try {
					session.getBasicRemote().sendText(promo.toJson());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
