package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import chat.Chat;
import comando.Comando;
import comando.ComandoCliente;
import estados.Estado;
import estados.EstadoBatalla;
import juego.Juego;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteChat;
import mensajeria.PaqueteDeMovimientos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteItem;

public class EscuchaMensajes extends Thread {

	private Juego juego;
	private Cliente cliente;
	private ObjectInputStream entrada;
	private final Gson gson = new Gson();


	public EscuchaMensajes(Juego juego) {
		this.juego = juego;
		cliente = juego.getCliente();
		entrada = cliente.getEntrada();
	}

	public void run() {

		try {

			Paquete paquete;
			
			juego.setPersonajesConectados(new HashMap<Integer, PaquetePersonaje>());
			juego.setUbicacionPersonajes(new HashMap<Integer, PaqueteMovimiento>());

			while (true) {

				String objetoLeido = (String)entrada.readObject();
				paquete = Paquete.cargarJson(objetoLeido);

				// DESCOMENTAR PARA MODO DEBUG POR CONSOLA:
//				Paquete p = gson.fromJson(objetoLeido, Paquete.class);
//				
//				System.out.println("Intento correr: " +p.getClassname());
//				System.out.println(objetoLeido);
				
				ComandoCliente comando = (ComandoCliente) paquete.obtenerInstanciaComando(ComandoCliente.COMANDO);
				comando.setJuego(juego);
				comando.ejecutarComando();
				
	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		}
	}

}