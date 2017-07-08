package comando;

import java.io.IOException;

import javax.swing.JOptionPane;

import mensajeria.Paquete;

public class Salir extends ComandoCliente{
	@Override
	public void ejecutarComando() {
		// El usuario no pudo iniciar sesi�n
		cliente.getPaqueteUsuario().setInicioSesion(false);
		try {
			cliente.getSalida().writeObject(new Paquete(Comando.DESCONECTAR).obtenerJson());
			cliente.getSocket().close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo al intentar cerrar la aplicación.");
			System.exit(1);
			e.printStackTrace();
		}
		
	}

}
