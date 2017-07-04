package comando;

import java.util.Map;

import mensajeria.PaqueteDePersonajes;
import mensajeria.PaquetePersonaje;

public class Conexion extends ComandoCliente{
	public void ejecutarComando() {
//		personajesConectados = (Map<Integer, PaquetePersonaje>) gson.fromJson(objetoLeido, PaqueteDePersonajes.class).getPersonajes();
		juego.setPersonajesConectados((Map<Integer, PaquetePersonaje>)((PaqueteDePersonajes)paquete).getPersonajes());
	}
}
