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

//	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
//	private Map<Integer, PaquetePersonaje> personajesConectados;

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
				
//				switch (paquete.getComando()) {

//				case Comando.CONEXION:
//					personajesConectados = (Map<Integer, PaquetePersonaje>) gson.fromJson(objetoLeido, PaqueteDePersonajes.class).getPersonajes();
//					break;

//				case Comando.MOVIMIENTO:
//					ubicacionPersonajes = (Map<Integer, PaqueteMovimiento>) gson.fromJson(objetoLeido, PaqueteDeMovimientos.class).getPersonajes();
//					break;

//				case Comando.BATALLA:
//					paqueteBatalla = gson.fromJson(objetoLeido, PaqueteBatalla.class);
//					juego.getPersonaje().setEstado(Estado.estadoBatalla);
//					Estado.setEstado(null);
//					juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
//					Estado.setEstado(juego.getEstadoBatalla());
//					break;

//				case Comando.ATACAR:
//					paqueteAtacar = (PaqueteAtacar) gson.fromJson(objetoLeido, PaqueteAtacar.class);
//					paqueteAtacar.encapsularAtributos();
//
//					juego.getEstadoBatalla().getEnemigo().modificarAtributos(paqueteAtacar.getAtributos());
//					juego.getEstadoBatalla().getPersonaje().modificarAtributos(paqueteAtacar.getAtributos());
//					juego.getEstadoBatalla().setMiTurno(true);
//					break;

//				case Comando.FINALIZARBATALLA:
//					paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(objetoLeido, PaqueteFinalizarBatalla.class);
//					juego.getPersonaje().setEstado(Estado.estadoJuego);
//					Estado.setEstado(juego.getEstadoJuego());
//					break;

//				case Comando.ACTUALIZARPERSONAJE:
//					paquetePersonaje = (PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class);
//
//					personajesConectados.remove(paquetePersonaje.getId());
//					personajesConectados.put(paquetePersonaje.getId(), paquetePersonaje);
//
//					if(juego.getPersonaje().getId() == paquetePersonaje.getId()) {
//						juego.actualizarPersonaje();
//						juego.getEstadoJuego().actualizarPersonaje();
//					}
//				case Comando.OBTENERITEMRANDOM:
//					paqueteItem = (PaqueteItem) gson.fromJson(objetoLeido, PaqueteItem.class);
//					juego.getEstadoBatalla().setItemGanado(paqueteItem.crearItem());
//					break;
					
//				case Comando.CANTIDADITEMS:
//					paqueteItem = (PaqueteItem) gson.fromJson(objetoLeido, PaqueteItem.class);
//					juego.getEstadoBatalla().getPaqueteItem().setCantidad(paqueteItem.getCantidad());
//					break;
					
//				case Comando.OBTENERINVENTARIO:
//					paqueteInventario = (PaqueteInventario) gson.fromJson(objetoLeido, PaqueteInventario.class);
//					juego.getEstadoBatalla().setPaqueteInventario(paqueteInventario);
//					break;
//				
//				case Comando.OBTENERMOCHILA:
//					paqueteMochila = (PaqueteMochila) gson.fromJson(objetoLeido, PaqueteMochila.class);
//					juego.getEstadoBatalla().setPaqueteMochila(paqueteMochila);
//					break;
					
//				case Comando.INVENTARIO:
//					paquetePersonaje = (PaquetePersonaje) gson.fromJson(objetoLeido, PaquetePersonaje.class);
//					juego.getPersonaje().setEstado(Estado.estadoInventario);

//				case Comando.CHAT:
//					paqueteChat = (PaqueteChat) gson.fromJson(objetoLeido, PaqueteChat.class);
//					cliente.getChat().nuevoMensaje(paqueteChat);
//					break;
//				}	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		}
	}

}