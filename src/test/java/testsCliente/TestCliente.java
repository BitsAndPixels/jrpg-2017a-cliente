package testsCliente;

import java.io.IOException;

import javax.swing.JTextArea;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cliente.Cliente;
import comando.Comando;
import inventario.*;
import mensajeria.Paquete;
import mensajeria.PaqueteItem;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

public class TestCliente {

	/// Para realizar los test es necesario iniciar el servidor

	@Test
	public void testConexionConElServidor() {
		Gson gson = new Gson();

		Cliente cliente = new Cliente();

		// Pasado este punto la conexi�n entre el cliente y el servidor resulto exitosa
		Assert.assertEquals(1, 1);

		try {

			// Cierro las conexiones
			Paquete p = new Paquete();
			p.setComando(Comando.DESCONECTAR);
			p.setIp(cliente.getMiIp());
			cliente.getSalida().writeObject(gson.toJson(p));
			cliente.getSalida().close();
			cliente.getEntrada().close();
			cliente.getSocket().close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//
//	@Test
//	public void testRegistro() {
//		Gson gson = new Gson();
//
//		// Registro el usuario
//		PaqueteUsuario pu = new PaqueteUsuario();
//		pu.setComando(Comando.REGISTRO);
//		pu.setUsername("nuevoUser");
//		pu.setPassword("test");
//
//		Cliente cliente = new Cliente();
//
//		try {
//
//			// Envio el paquete para registrarme
//			cliente.getSalida().writeObject(gson.toJson(pu));
//
//			// Recibo la respuesta del servidor
//			Paquete resultado = (Paquete) gson.fromJson((String) cliente.getEntrada().readObject(), Paquete.class);
//
//			// Cierro las conexiones
//			Paquete p = new Paquete();
//			p.setComando(Comando.DESCONECTAR);
//			p.setIp(cliente.getMiIp());
//			cliente.getSalida().writeObject(gson.toJson(p));
//			cliente.getSalida().close();
//			cliente.getEntrada().close();
//			cliente.getSocket().close();
//
//			Assert.assertEquals(Paquete.msjExito, resultado.getMensaje());
//
//		} catch (JsonSyntaxException | ClassNotFoundException | IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testRegistroFallido() {
//		Gson gson = new Gson();
//
//		// Registro el usuario
//		PaqueteUsuario pu = new PaqueteUsuario();
//		pu.setComando(Comando.REGISTRO);
//		pu.setUsername("nuevoUser");
//		pu.setPassword("test");
//
//		Cliente cliente = new Cliente();
//
//		try {
//
//			// Envio el paquete para registrarme
//			cliente.getSalida().writeObject(gson.toJson(pu));
//
//			// Recibo la respuesta del servidor
//			Paquete resultado = (Paquete) gson.fromJson((String) cliente.getEntrada().readObject(), Paquete.class);
//
//			// Cierro las conexiones
//			Paquete p = new Paquete();
//			p.setComando(Comando.DESCONECTAR);
//			p.setIp(cliente.getMiIp());
//			cliente.getSalida().writeObject(gson.toJson(p));
//			cliente.getSalida().close();
//			cliente.getEntrada().close();
//			cliente.getSocket().close();
//
//			Assert.assertEquals(Paquete.msjFracaso, resultado.getMensaje());
//
//		} catch (JsonSyntaxException | ClassNotFoundException | IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testRegistrarPersonaje() {
//		Gson gson = new Gson();
//
//		Cliente cliente = new Cliente();
//
//		// Registro de usuario
//		PaqueteUsuario pu = new PaqueteUsuario();
//		pu.setComando(Comando.REGISTRO);
//		pu.setUsername("nuevoUser");
//		pu.setPassword("test");
//
//		// Registro de personaje
//		PaquetePersonaje pp = new PaquetePersonaje();
//		pp.setComando(Comando.CREACIONPJ);
//		pp.setCasta("Humano");
//		pp.setDestreza(1);
//		pp.setEnergiaTope(1);
//		pp.setExperiencia(1);
//		pp.setFuerza(1);
//		pp.setInteligencia(1);
//		pp.setNivel(1);
//		pp.setNombre("PjTest");
//		pp.setRaza("Asesino");
//		pp.setSaludTope(1);
//
//		try {
//
//			// Envio el paquete de registro de usuario
//			cliente.getSalida().writeObject(gson.toJson(pu));
//
//			// Recibo la respuesta del servidor
//			Paquete paquete = (Paquete) gson.fromJson((String) cliente.getEntrada().readObject(), Paquete.class);
//
//			// Envio el paquete de registro de personaje
//			cliente.getSalida().writeObject(gson.toJson(pp));
//
//			// Recibo el personaje de mi usuario
//			pp = (PaquetePersonaje) gson.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class);
//
//			// Cierro las conexiones
//			Paquete p = new Paquete();
//			p.setComando(Comando.DESCONECTAR);
//			p.setIp(cliente.getMiIp());
//			cliente.getSalida().writeObject(gson.toJson(p));
//			cliente.getSalida().close();
//			cliente.getEntrada().close();
//			cliente.getSocket().close();
//
//			Assert.assertEquals("PjTest", pp.getNombre());
//		} catch (IOException | JsonSyntaxException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test
//	public void testIniciarSesion() {
//		Gson gson = new Gson();
//		Cliente cliente = new Cliente();
//
//		PaqueteUsuario pu = new PaqueteUsuario();
//		pu.setComando(Comando.INICIOSESION);
//		pu.setUsername("cortex");
//		pu.setPassword("1234567");
//
//		try {
//
//			// Envio el paquete de incio de sesion
////			cliente.getSalida().writeObject(gson.toJson(pu));
//			cliente.getSalida().writeObject(pu.getJson());
//
//			// Recibo el paquete con el personaje
//			PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson
//					.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class);
//
//			// Cierro las conexiones
//			Paquete p = new Paquete();
//			p.setComando(Comando.DESCONECTAR);
//			p.setIp(cliente.getMiIp());
//			cliente.getSalida().writeObject(gson.toJson(p));
//			cliente.getSalida().close();
//			cliente.getEntrada().close();
//			cliente.getSocket().close();
//
//			Assert.assertEquals("cortex", paquetePersonaje.getNombre());
//		} catch (IOException | JsonSyntaxException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test
//	public void testActualizarPersonaje() {
//		Gson gson = new Gson();
//		Cliente cliente = new Cliente();
//
//		PaquetePersonaje pp = new PaquetePersonaje();
//		pp.setComando(Comando.ACTUALIZARPERSONAJE);
//		pp.setCasta("Humano");
//		pp.setDestreza(1);
//		pp.setEnergiaTope(1);
//		pp.setExperiencia(1);
//		pp.setFuerza(1);
//		pp.setInteligencia(1);
//		pp.setNivel(1);
//		pp.setNombre("PjTest");
//		pp.setRaza("Asesino");
//		pp.setSaludTope(10000);
//		pp.setMochila(new Mochila());
//		pp.setInventario(new Inventario());
//
//		try {
//
//			// Envio el paquete de actualizacion de personaje
//			cliente.getSalida().writeObject(gson.toJson(pp));
//
//			// Recibo el paquete con el personaje actualizado
//			PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson
//					.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class);
//
//			// Cierro las conexiones
//			Paquete p = new Paquete();
//			p.setComando(Comando.DESCONECTAR);
//			p.setIp(cliente.getMiIp());
//			cliente.getSalida().writeObject(gson.toJson(p));
//			cliente.getSalida().close();
//			cliente.getEntrada().close();
//			cliente.getSocket().close();
//
//			Assert.assertEquals(10000, paquetePersonaje.getSaludTope());
//		} catch (IOException | JsonSyntaxException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testObtenerItem() {
//		Gson gson = new Gson();
//		Cliente cliente = new Cliente();
//		PaqueteItem pi = new PaqueteItem();
//		
//		pi.setComando(Comando.OBTENERITEMRANDOM);
//		pi.setIdItem(5);
//		
//		try {
//			// Envio el paquete pidiendo un item
////			cliente.getSalida().writeObject(gson.toJson(pi));
//			cliente.getSalida().writeObject(gson.toJson(pi));
//			
//			// Recibo el item:
//			PaqueteItem paqueteItem = (PaqueteItem) gson
//					.fromJson((String) cliente.getEntrada().readObject(), PaqueteItem.class);
//			
//			
//			
//			// Cierro Conexion:
//			Paquete p = new Paquete();
//			p.setComando(Comando.DESCONECTAR);
//			p.setIp(cliente.getMiIp());
//			cliente.getSalida().writeObject(gson.toJson(p));
//			cliente.getSalida().close();
//			cliente.getEntrada().close();
//			cliente.getSocket().close();
//			
//			Assert.assertEquals("Botas", paqueteItem.getNombre());
//			Assert.assertEquals(1, paqueteItem.getBonoDefensa());
//			
//		} catch (IOException | JsonSyntaxException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//	@Test
//	public void testMochilaInventario() {
//		Gson gson = new Gson();
//		Cliente cliente = new Cliente();
//
//		PaqueteUsuario pu = new PaqueteUsuario();
//		pu.setComando(Comando.INICIOSESION);
//		pu.setUsername("geralt");
//		pu.setPassword("123");
//
//		try {
//
//			// Envio el paquete de incio de sesion
//			cliente.getSalida().writeObject(gson.toJson(pu));
//
//			// Recibo el paquete con el personaje
//			PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson
//					.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class);
//
//			// Cierro las conexiones
//			Paquete p = new Paquete();
//			p.setComando(Comando.DESCONECTAR);
//			p.setIp(cliente.getMiIp());
//			cliente.getSalida().writeObject(gson.toJson(p));
//			cliente.getSalida().close();
//			cliente.getEntrada().close();
//			cliente.getSocket().close();
//
//			Assert.assertEquals("Geralt", paquetePersonaje.getNombre());
//			Assert.assertEquals("Espada", paquetePersonaje.getInventario().getManoDer().getNombre());
//			Assert.assertEquals("vacio", paquetePersonaje.getInventario().getManoIzq().getEstado());
//			
//		} catch (IOException | JsonSyntaxException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	

	
}
