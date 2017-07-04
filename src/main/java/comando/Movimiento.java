package comando;

import java.util.Map;

import mensajeria.PaqueteDeMovimientos;
import mensajeria.PaqueteMovimiento;

public class Movimiento extends ComandoCliente{
	public void ejecutarComando() {
//		ubicacionPersonajes = (Map<Integer, PaqueteMovimiento>) gson.fromJson(objetoLeido, PaqueteDeMovimientos.class).getPersonajes();
		juego.setUbicacionPersonajes((Map<Integer, PaqueteMovimiento>)((PaqueteDeMovimientos)paquete).getPersonajes());
	}
}
