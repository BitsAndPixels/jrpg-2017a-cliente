package comando;

import mensajeria.PaqueteChat;

public class Chat extends ComandoCliente{
	public void ejecutarComando() {
		PaqueteChat paqueteChat = (PaqueteChat) paquete;
		juego.getCliente().getChat().nuevoMensaje(paqueteChat);
	}
}
