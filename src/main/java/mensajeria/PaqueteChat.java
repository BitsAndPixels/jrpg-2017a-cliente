package mensajeria;

import java.io.Serializable;

public class PaqueteChat extends Paquete implements Serializable, Cloneable {

	private   String nombreUsuarioActivo;
	private   String nombreUsuarioPasivo;
	private   String mensajeChat;
	
	public PaqueteChat(){
		setComando(Comando.CHAT);
	}

	public String getNombreUsuarioActivo() {
		return nombreUsuarioActivo;
	}

	public void setNombreUsuarioActivo(String nombreUsuarioActivo) {
		this.nombreUsuarioActivo = nombreUsuarioActivo;
	}

	public String getNombreUsuarioPasivo() {
		return nombreUsuarioPasivo;
	}

	public void setNombreUsuarioPasivo(String nombreUsuarioPasivo) {
		this.nombreUsuarioPasivo = nombreUsuarioPasivo;
	}

	public String getMensajeChat() {
		return mensajeChat;
	}

	public void setMensajeChat(String mensajeChat) {
		this.mensajeChat = mensajeChat;
	}
	
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}