package comando;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;

public class FinalizarBatalla extends ComandoCliente{
//	private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
	public void ejecutarComando() {
//		this.paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) paquete;
		juego.getPersonaje().setEstado(Estado.estadoJuego);
		Estado.setEstado(juego.getEstadoJuego());
	}
}
