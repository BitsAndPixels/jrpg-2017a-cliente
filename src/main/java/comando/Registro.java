package comando;

import java.io.IOException;

import javax.swing.JOptionPane;

import frames.MenuCreacionPj;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

public class Registro extends ComandoCliente{
	@Override
	public void ejecutarComando() {
		
		PaquetePersonaje paquetePersonaje = cliente.getPaquetePersonaje();
		PaqueteUsuario paqueteUsuario = cliente.getPaqueteUsuario();
		
		if (paquete.getMensajeChat().equals(Paquete.msjExito)) {

			// Abro el menu para la creaci�n del personaje
			MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(this, paquetePersonaje);
			menuCreacionPJ.setVisible(true);
			
			// Espero a que el usuario cree el personaje
			try {
				wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			// Le envio los datos al servidor
			paquetePersonaje.setComando(Comando.CREACIONPJ);
			try {
				cliente.getSalida().writeObject(paquetePersonaje.getJson());
			} catch (IOException e1) {
				e1.printStackTrace();
			}									
			JOptionPane.showMessageDialog(null, "Registro exitoso.");
			
			// Recibo el paquete personaje con los datos (la id incluida)
//			paquetePersonaje = (PaquetePersonaje) gson.fromJson((String) entrada.readObject(), PaquetePersonaje.class);

			try {
				paquetePersonaje = (PaquetePersonaje) Paquete.loadJson((String) cliente.getEntrada().readObject());
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			// Indico que el usuario ya inicio sesion
			paqueteUsuario.setInicioSesion(true);
			
		} else {
			if (paquete.getMensajeChat().equals(Paquete.msjFracaso))
				JOptionPane.showMessageDialog(null, "No se pudo registrar.");

			// El usuario no pudo iniciar sesi�n
			paqueteUsuario.setInicioSesion(false);
		}
		
	}
}
