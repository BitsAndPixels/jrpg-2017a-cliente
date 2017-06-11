package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Asesino;
import dominio.Casta;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.MyRandom;
import dominio.Orco;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoPersonaje;
import inventario.Inventario;
import inventario.Item;
import inventario.Mochila;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteInventario;
import mensajeria.PaqueteItem;
import mensajeria.PaqueteMochila;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoBatalla extends Estado {

	private Mundo mundo;
	private Personaje personaje;
	private Personaje enemigo;
	private int[] posMouse;
	private PaquetePersonaje paquetePersonaje;
	private PaquetePersonaje paqueteEnemigo;
	private PaqueteAtacar paqueteAtacar;
	private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
	private boolean miTurno;
	
	private boolean haySpellSeleccionada;
	private boolean seRealizoAccion;

	private Gson gson = new Gson();

	private BufferedImage miniaturaPersonaje;
	private BufferedImage miniaturaEnemigo;
	
	private MenuBatalla menuBatalla;
	
	private PaqueteMochila paqueteMochila;
	private PaqueteInventario paqueteInventario;
	private PaqueteItem paqueteItem;
	
	
	public EstadoBatalla(Juego juego, PaqueteBatalla paqueteBatalla) {
		super(juego);
		mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
		miTurno = paqueteBatalla.isMiTurno();

		paquetePersonaje = juego.getEscuchaMensajes().getPersonajesConectados().get(paqueteBatalla.getId());
		paqueteEnemigo = juego.getEscuchaMensajes().getPersonajesConectados().get(paqueteBatalla.getIdEnemigo());

		paqueteItem = new PaqueteItem();
		paqueteInventario = new PaqueteInventario();
		paqueteMochila = new PaqueteMochila();
		
		crearPersonajes();
		
		menuBatalla = new MenuBatalla(miTurno, personaje);
		
		miniaturaEnemigo = Recursos.personaje.get(enemigo.getNombreRaza()).get(5)[0];
		miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(5)[0];
	
		paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
		paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
		paqueteFinalizarBatalla.setIdEnemigo(enemigo.getIdPersonaje());
		
		// por defecto batalla perdida
		juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuPerderBatalla);
		
		// limpio la accion del mouse
		juego.getHandlerMouse().setNuevoClick(false);
		
		
		
	}

	@Override
	public void actualizar() {
		
		juego.getCamara().setxOffset(-350);
		juego.getCamara().setyOffset(150);
		
		seRealizoAccion = false;
		haySpellSeleccionada = false;

		if (miTurno) {
			
			if (juego.getHandlerMouse().getNuevoClick()) {
				posMouse = juego.getHandlerMouse().getPosMouse();

				if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 1) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadRaza1(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 2) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadRaza2(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 3) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadCasta1(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 4) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadCasta2(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 5) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadCasta3(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 6) {
						seRealizoAccion = true;
						personaje.serEnergizado(5);
						haySpellSeleccionada = true;
					}
				}


				if (haySpellSeleccionada && seRealizoAccion) {
					if (!enemigo.estaVivo()) {
						juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuGanarBatalla);
						if(personaje.ganarExperiencia(enemigo.getNivel() * 40)){
							juego.getPersonaje().setNivel(personaje.getNivel());
							juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuSubirNivel);
						}
						finalizarBatalla();
						Estado.setEstado(juego.getEstadoJuego());
					} else {
						paqueteAtacar = new PaqueteAtacar(paquetePersonaje.getId(), paqueteEnemigo.getId(), personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(), enemigo.getEnergia());
						enviarAtaque(paqueteAtacar);
						miTurno = false;
						menuBatalla.setHabilitado(false);
					}
				} else if(haySpellSeleccionada && !seRealizoAccion){
					JOptionPane.showMessageDialog(null, "No posees la energ�a suficiente para realizar esta habilidad.");
				}

				juego.getHandlerMouse().setNuevoClick(false);
			}
		}
		
	}

	@Override
	public void graficar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, juego.getAncho(), juego.getAlto());
		mundo.graficar(g);
		
		g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(3)[0], 0, 175, 256, 256, null);
		g.drawImage(Recursos.personaje.get(paqueteEnemigo.getRaza()).get(7)[0], 550, 75, 256, 256, null);
		
		mundo.graficarObstaculos(g);
		menuBatalla.graficar(g);
		
		g.setColor(Color.GREEN);
		
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje, miniaturaPersonaje);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 550, 5, enemigo, miniaturaEnemigo);
		
	}
	
	private void crearPersonajes() {
		String nombre = paquetePersonaje.getNombre();
		int salud = paquetePersonaje.getSaludTope();
		int energia = paquetePersonaje.getEnergiaTope();
		int fuerza = paquetePersonaje.getFuerza();
		int destreza = paquetePersonaje.getDestreza();
		int inteligencia = paquetePersonaje.getInteligencia();
		int experiencia = paquetePersonaje.getExperiencia();
		int nivel = paquetePersonaje.getNivel();
		int id = paquetePersonaje.getId();
//		Mochila mochila = this.crearMochila(paquetePersonaje.getId());
//		Inventario inventario = this.crearInventario(paquetePersonaje.getId());
		Mochila mochila = new Mochila();
		Inventario inventario = new Inventario();

		Casta casta = null;
		if (paquetePersonaje.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else if (paquetePersonaje.getCasta().equals("Hechicero")) {
			casta = new Hechicero();
		} else if (paquetePersonaje.getCasta().equals("Asesino")) {
			casta = new Asesino();
		}
		

		if (paquetePersonaje.getRaza().equals("Humano")) {
			personaje = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta, 
				experiencia, nivel, id, inventario, mochila);
		} else if (paquetePersonaje.getRaza().equals("Orco")) {
			personaje = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta, 
					experiencia, nivel, id, inventario, mochila);
		} else if (paquetePersonaje.getRaza().equals("Elfo")) {
			personaje = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id, inventario, mochila);
		}

		nombre = paqueteEnemigo.getNombre();
		salud = paqueteEnemigo.getSaludTope();
		energia = paqueteEnemigo.getEnergiaTope();
		fuerza = paqueteEnemigo.getFuerza();
		destreza = paqueteEnemigo.getDestreza();
		inteligencia = paqueteEnemigo.getInteligencia();
		experiencia = paqueteEnemigo.getExperiencia();
		nivel = paqueteEnemigo.getNivel();
		id = paqueteEnemigo.getId();
//		Mochila mochilaEnemigo = this.crearMochila(paqueteEnemigo.getId());
//		Inventario inventarioEnemigo = this.crearInventario(paqueteEnemigo.getId());
		Mochila mochilaEnemigo = new Mochila();
		Inventario inventarioEnemigo = new Inventario();

		casta = null;
		if (paqueteEnemigo.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else if (paqueteEnemigo.getCasta().equals("Hechicero")) {
			casta = new Hechicero();
		} else if (paqueteEnemigo.getCasta().equals("Asesino")) {
			casta = new Asesino();
		}

		if (paqueteEnemigo.getRaza().equals("Humano")) {
			enemigo = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id, inventarioEnemigo, mochilaEnemigo);
		} else if (paqueteEnemigo.getRaza().equals("Orco")) {
			enemigo = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id, inventarioEnemigo, mochilaEnemigo);
		} else if (paqueteEnemigo.getRaza().equals("Elfo")) {
			enemigo = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id, inventarioEnemigo, mochilaEnemigo);
		}
	}

	public void enviarAtaque(PaqueteAtacar paqueteAtacar) {
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteAtacar));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
			e.printStackTrace();
		}
	}

	private void finalizarBatalla() {
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));
			
			paquetePersonaje.setSaludTope(personaje.getSaludTope());
			paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
			paquetePersonaje.setNivel(personaje.getNivel());
			paquetePersonaje.setExperiencia(personaje.getExperiencia());
			paquetePersonaje.setDestreza(personaje.getDestreza());
			paquetePersonaje.setFuerza(personaje.getFuerza());
			paquetePersonaje.setInteligencia(personaje.getInteligencia());
			
			paqueteEnemigo.setSaludTope(enemigo.getSaludTope());
			paqueteEnemigo.setEnergiaTope(enemigo.getEnergiaTope());
			paqueteEnemigo.setNivel(enemigo.getNivel());
			paqueteEnemigo.setExperiencia(enemigo.getExperiencia());
			paqueteEnemigo.setDestreza(enemigo.getDestreza());
			paqueteEnemigo.setFuerza(enemigo.getFuerza());
			paqueteEnemigo.setInteligencia(enemigo.getInteligencia());
			
			paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
			paqueteEnemigo.setComando(Comando.ACTUALIZARPERSONAJE);
			
			juego.getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigo));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		}
	}

	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	public PaquetePersonaje getPaqueteEnemigo() {
		return paqueteEnemigo;
	}

	public void setMiTurno(boolean b) {
		miTurno = b;
		menuBatalla.setHabilitado(b);
		juego.getHandlerMouse().setNuevoClick(false);
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public Personaje getEnemigo() {
		return enemigo;
	}
	
	public PaqueteItem getPaqueteItem() {
		return paqueteItem;
	}
	
	public void setPaqueteItem(PaqueteItem paqueteItem) {
		this.paqueteItem = paqueteItem;
	}
	
	public PaqueteMochila getPaqueteMochila() {
		return paqueteMochila;
	}

	public void setPaqueteMochila(PaqueteMochila paqueteMochila) {
		this.paqueteMochila = paqueteMochila;
	}

	public PaqueteInventario getPaqueteInventario() {
		return paqueteInventario;
	}

	public void setPaqueteInventario(PaqueteInventario paqueteInventario) {
		this.paqueteInventario = paqueteInventario;
	}

	public Item obtenerItem(int idItem) {
		try {
			
			paqueteItem.setComando(Comando.OBTENERITEM);
			paqueteItem.setIdItem(idItem);
			// Envio el paquete pidiendo un item
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteItem));
			Item item = new Item(this.paqueteItem.getIdItem(),
								this.paqueteItem.getBonoAtaque(),
								this.paqueteItem.getBonoDefensa(),
								this.paqueteItem.getBonoMagia(),
								this.paqueteItem.getBonoSalud(),
								this.paqueteItem.getBonoEnergia(),
								this.paqueteItem.getTipo(),
								this.paqueteItem.getNombre(),
								"desequipado");
		
			
			return item;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		}
		return new Item();
	}
	
	public Item obtenerItemRandom() {
		
		MyRandom randomInt = new MyRandom();
		//PaqueteItem pi = new PaqueteItem();
			
		try {
			paqueteItem.setComando(Comando.CANTIDADITEMS);
			
			// PIDO CANTIDAD DE ITEMS EN LA BASE:
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteItem));
			int cantidadItems = this.getPaqueteItem().getCantidad();
			
			// GENERO ITEM RANDOM
			Item itemRandom = this.obtenerItem(randomInt.nextInt(cantidadItems));
			
			return itemRandom;
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		} 
		return new Item();
	}
	
	private Inventario crearInventario(int idPersonaje) {
		Inventario inventario = new Inventario();
		try {
			
			paqueteInventario.setComando(Comando.OBTENERINVENTARIO);
			paqueteInventario.setIdPje(idPersonaje);
			// Envio el paquete pidiendo el inventario asociado al personaje
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteInventario));
			
			// ARMO EL INVENTARIO
						
			inventario.setIdInventario(this.paqueteInventario.getIdInventario());
			
			if (this.paqueteInventario.getManoDer() > 0){
				inventario.setManoDer(this.obtenerItem(this.paqueteInventario.getManoDer()));
				inventario.getManoDer().serEquipado();
			}
			if (this.paqueteInventario.getManoIzq() > 0) {
				inventario.setManoIzq(this.obtenerItem(this.paqueteInventario.getManoIzq()));
				inventario.getManoIzq().serEquipado();
			}
			if (this.paqueteInventario.getPie() > 0) {
				inventario.setPie(this.obtenerItem(this.paqueteInventario.getPie()));
				inventario.getPie().serEquipado();
			}
			if (this.paqueteInventario.getCabeza() > 0) {
				inventario.setCabeza(this.obtenerItem(this.paqueteInventario.getCabeza()));
				inventario.getCabeza().serEquipado();
			}
			if (this.paqueteInventario.getPecho() > 0) {
				inventario.setPecho(this.obtenerItem(this.paqueteInventario.getPecho()));
				inventario.getPecho().serEquipado();
			}	
			if (this.paqueteInventario.getAccesorio() > 0) {
				inventario.setAccesorio(this.obtenerItem(this.paqueteInventario.getAccesorio()));
				inventario.getAccesorio().serEquipado();
			}		
			
			return inventario;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		}
		
		
		return inventario;
	}

	private Mochila crearMochila(int idPersonaje) {
		Mochila mochila = new Mochila();
		try {
			
			paqueteMochila.setComando(Comando.OBTENERMOCHILA);
			paqueteMochila.setIdPje(idPersonaje);
			// Envio el paquete pidiendo la mochila asociada al personaje
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteMochila));
			
			// ARMO LA MOCHILA
			mochila.setIdMochila(paqueteMochila.getIdMochila());
			
			int idItem=0;
			
			for (int i=0; i<paqueteMochila.getItems().size(); i++) {
				idItem = paqueteMochila.getItems().get(i);
				if (idItem > 0) {
					mochila.agregaItem(this.obtenerItem(idItem));
				}
			}

			return mochila;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor.");
			e.printStackTrace();
		}
		return mochila;
	}
	
}
