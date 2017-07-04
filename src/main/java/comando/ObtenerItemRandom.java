package comando;

import mensajeria.PaqueteItem;

public class ObtenerItemRandom extends ComandoCliente{
	public void ejecutarComando() {
//		paqueteItem = (PaqueteItem) gson.fromJson(objetoLeido, PaqueteItem.class);
		PaqueteItem paqueteItem = (PaqueteItem) paquete;
		juego.getEstadoBatalla().setItemGanado(paqueteItem.crearItem());
	}
}
