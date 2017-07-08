package comando;

import frames.MenuComercio;
import mensajeria.PaqueteComercio;

public class IniciarComercio extends ComandoCliente{
	private PaqueteComercio paqueteComercio;
	@Override
	public void ejecutarComando() {
		this.paqueteComercio = (PaqueteComercio) paquete;
		if(paqueteComercio.getComando() == Comando.INICIARCOMERCIO) {
			MenuComercio menuComercio = new MenuComercio(juego.getCliente(),juego.getPersonajesConectados().get(paqueteComercio.getIdReceptor()), juego.getPersonaje());
			menuComercio.setVisible(true);
		}
	}

}
