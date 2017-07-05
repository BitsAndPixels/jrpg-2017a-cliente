package comando;

import javax.swing.JOptionPane;

import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

public class InicioSesion extends ComandoCliente{
	@Override
	public void ejecutarComando() {
		synchronized (this) {
					
			PaqueteUsuario paqueteUsuario = cliente.getPaqueteUsuario();
			if (paquete.getMensajeChat().equals(Paquete.msjExito)) {
				
				// El usuario ya inicio sesi�n
				paqueteUsuario.setInicioSesion(true);
				
				// Recibo el paquete personaje con los datos
	//			paquetePersonaje = (PaquetePersonaje) gson.fromJson(cadenaLeida, PaquetePersonaje.class);
				cliente.setPaquetePersonaje((PaquetePersonaje) paquete);
	
			} else {
				if (paquete.getMensajeChat().equals(Paquete.msjFracaso))
					JOptionPane.showMessageDialog(null, "Error al iniciar sesion. Revise el usuario y la contrase�a");
	
				// El usuario no pudo iniciar sesi�n
				paqueteUsuario.setInicioSesion(false);
			}
		}
	}
	
}
