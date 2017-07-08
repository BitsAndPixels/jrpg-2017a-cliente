package mensajeria;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import comando.Comando;

public class Paquete implements Serializable, Cloneable {
	
	public static String msjExito = "1";
	public static String msjFracaso = "0";
	
	private String mensaje;
	private String ip;
	private int comando;
	
	private String clase;
	
	public static final Gson gson = new Gson();

	public Paquete() {
		
	}
	
	public Paquete(String mensaje, String nick, String ip, int comando) {
		this.mensaje = mensaje;
		this.ip = ip;
		this.comando = comando;
	}
	
	public Paquete(String mensaje, int comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}

	public Paquete(int comando) {
		this.comando = comando;
	}

	public void setMensajeChat(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setComando(int comando) {
		this.comando = comando;
	}

	public String getMensajeChat() {
		return mensaje;
	}


	public String getIp() {
		return ip;
	}

	public int getComando() {
		return comando;
	}
	
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	
	public String obtenerJson() {
		this.clase = this.getClass().getName();
		return gson.toJson(this);
	}

	public static Paquete cargarJson(String json) {
		Paquete p = gson.fromJson(json, Paquete.class);
//		System.out.println(p.classname);
		try {
			return (Paquete) gson.fromJson(json, Class.forName(p.clase));
		} catch (JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 

	}

	public Comando obtenerInstanciaComando(String nombrePackage) {
		try {
			Comando c;
			c = (Comando) Class.forName(nombrePackage + "." + Comando.COMANDOS[comando]).newInstance();
			c.setPaquete(this);
			return c;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public String getClassname() {
		return this.clase;
	}

}
