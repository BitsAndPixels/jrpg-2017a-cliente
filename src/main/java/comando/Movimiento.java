package comando;

import java.util.Map;

import mensajeria.PaqueteDeMovimientos;
import mensajeria.PaqueteMovimiento;

public class Movimiento extends ComandoCliente{
	public void ejecutarComando() {
		juego.setUbicacionPersonajes((Map<Integer, PaqueteMovimiento>)((PaqueteDeMovimientos)paquete).getPersonajes());
	}
}
