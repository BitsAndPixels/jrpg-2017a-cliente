package comando;

import java.io.IOException;

import mensajeria.Paquete;

public class Salir extends ComandoCliente{
	@Override
	public void ejecutarComando() {
		// El usuario no pudo iniciar sesi�n
//		paqueteUsuario.setInicioSesion(false);
//		salida.writeObject(gson.toJson(new Paquete(Comando.DESCONECTAR), Paquete.class));
//		cliente.close();
		cliente.getPaqueteUsuario().setInicioSesion(false);
		try {
			cliente.getSalida().writeObject(new Paquete(Comando.DESCONECTAR).getJson());
			cliente.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
