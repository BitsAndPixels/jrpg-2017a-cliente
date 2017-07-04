package comando;

import mensajeria.PaqueteAtacar;

public class Atacar extends ComandoCliente {

	@Override
	public void ejecutarComando() {
//		PaqueteAtacar paqueteAtacar = (PaqueteAtacar) paquete;
//		HashMap<String, Object> datos = juego.getEstadoBatalla().getPersonaje().getTodo();
//		datos.putAll(paqueteAtacar.getTodoEnemigo());
//		juego.getEstadoBatalla().getPersonaje().actualizar(datos);
//		datos = juego.getEstadoBatalla().getEnemigo().getTodo();
//		datos.putAll(paqueteAtacar.getTodoPersonaje());
//		juego.getEstadoBatalla().getEnemigo().actualizar(datos);
//
//		juego.getEstadoBatalla().setMiTurno(true);
		
//		paqueteAtacar = (PaqueteAtacar) gson.fromJson(objetoLeido, PaqueteAtacar.class);
		PaqueteAtacar paqueteAtacar = (PaqueteAtacar) paquete;
		paqueteAtacar.encapsularAtributos();

		juego.getEstadoBatalla().getEnemigo().modificarAtributos(paqueteAtacar.getAtributos());
		juego.getEstadoBatalla().getPersonaje().modificarAtributos(paqueteAtacar.getAtributos());
		juego.getEstadoBatalla().setMiTurno(true);

	}

}
