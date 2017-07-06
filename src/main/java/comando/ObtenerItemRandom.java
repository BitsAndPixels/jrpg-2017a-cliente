package comando;

import mensajeria.PaqueteItem;

public class ObtenerItemRandom extends ComandoCliente{
	private PaqueteItem paqueteItem;
	public void ejecutarComando() {
		this.paqueteItem = (PaqueteItem) paquete;
		juego.getEstadoBatalla().setItemGanado(paqueteItem.crearItem());
	}
}
