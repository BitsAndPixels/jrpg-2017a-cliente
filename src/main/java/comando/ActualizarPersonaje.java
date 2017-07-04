package comando;

import mensajeria.PaquetePersonaje;

public class ActualizarPersonaje extends ComandoCliente {
	
	@Override
	public void ejecutarComando() {
//		PaquetePersonaje paquetePersonaje = (PaquetePersonaje) paquete;
//
//		juego.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
//		
//		if(juego.getPersonaje().getId() == paquetePersonaje.getId()) {
//			juego.actualizarPersonaje();
//			juego.getEstadoJuego().actualizarPersonaje();
//		}
		
//		paquetePersonaje = (PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class);
		PaquetePersonaje paquetePersonaje = (PaquetePersonaje) paquete;

		juego.getPersonajesConectados().remove(paquetePersonaje.getId());
		juego.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);

		if(juego.getPersonaje().getId() == paquetePersonaje.getId()) {
			juego.actualizarPersonaje();
			juego.getEstadoJuego().actualizarPersonaje();
		}
	}

}
