package comando;

import mensajeria.PaquetePersonaje;

public class ActualizarPersonaje extends ComandoCliente {
	private PaquetePersonaje paquetePersonaje;
	
	@Override
	public void ejecutarComando() {

		this.paquetePersonaje = (PaquetePersonaje) paquete;

		juego.getPersonajesConectados().remove(paquetePersonaje.getId());
		juego.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);

		if(juego.getPersonaje().getId() == paquetePersonaje.getId()) {
			juego.actualizarPersonaje();
			juego.getEstadoJuego().actualizarPersonaje();
		}
	}

}
