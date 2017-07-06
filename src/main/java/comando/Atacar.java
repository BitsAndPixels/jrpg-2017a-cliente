package comando;

import mensajeria.PaqueteAtacar;

public class Atacar extends ComandoCliente {

	private PaqueteAtacar paqueteAtacar;
	
	@Override
	public void ejecutarComando() {

		this.paqueteAtacar = (PaqueteAtacar) paquete;
		paqueteAtacar.encapsularAtributos();

		juego.getEstadoBatalla().getEnemigo().modificarAtributos(paqueteAtacar.getAtributos());
		juego.getEstadoBatalla().getPersonaje().modificarAtributos(paqueteAtacar.getAtributos());
		juego.getEstadoBatalla().setMiTurno(true);

	}

}
