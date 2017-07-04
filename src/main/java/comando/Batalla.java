package comando;

import estados.Estado;
import estados.EstadoBatalla;
import mensajeria.PaqueteBatalla;

public class Batalla extends ComandoCliente{
	public void ejecutarComando() {
//		paqueteBatalla = gson.fromJson(objetoLeido, PaqueteBatalla.class);
		
		PaqueteBatalla paqueteBatalla = (PaqueteBatalla) paquete;
		juego.getPersonaje().setEstado(Estado.estadoBatalla);
		Estado.setEstado(null);
		juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
		Estado.setEstado(juego.getEstadoBatalla());
	}
}
