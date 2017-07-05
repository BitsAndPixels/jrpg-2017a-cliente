package comando;

import cliente.Cliente;
import juego.Juego;

public abstract class ComandoCliente extends Comando {

	public static final String PACKAGEO = "comando";
	
	protected Juego juego;
	protected Cliente cliente;

	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}