package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

public class PaqueteMochila extends Paquete implements Serializable, Cloneable {
	
	private int idPje;
	private int idMochila;
	private ArrayList<Integer> items;
	
	public PaqueteMochila() {
		
	}

	public PaqueteMochila(int idPj, int idMochila, ArrayList<Integer> items) {
		super();
		this.idPje = idPj;
		this.idMochila = idMochila;
		this.items = items;
	}

	public int getIdPje() {
		return idPje;
	}

	public void setIdPje(int idPj) {
		this.idPje = idPj;
	}

	public int getIdMochila() {
		return idMochila;
	}

	public void setIdMochila(int idMochila) {
		this.idMochila = idMochila;
	}

	public ArrayList<Integer> getItems() {
		return items;
	}

	public void setItems(ArrayList<Integer> items) {
		this.items = items;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
