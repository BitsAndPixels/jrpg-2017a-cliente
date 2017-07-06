package comando;

import estados.Estado;
import estados.EstadoBatalla;
import mensajeria.PaqueteBatalla;

public class Batalla extends ComandoCliente{
	private PaqueteBatalla paqueteBatalla;
	
	public void ejecutarComando() {
		this.paqueteBatalla = (PaqueteBatalla) paquete;
		juego.getPersonaje().setEstado(Estado.estadoBatalla);
		Estado.setEstado(null);
		juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
		Estado.setEstado(juego.getEstadoBatalla());
	}
}
