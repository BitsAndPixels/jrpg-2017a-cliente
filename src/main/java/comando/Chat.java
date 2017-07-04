package comando;

import mensajeria.PaqueteChat;

public class Chat extends ComandoCliente{
	public void ejecutarComando() {
//		paqueteChat = (PaqueteChat) gson.fromJson(objetoLeido, PaqueteChat.class);
		PaqueteChat paqueteChat = (PaqueteChat) paquete;
		cliente.getChat().nuevoMensaje(paqueteChat);
	}
}
