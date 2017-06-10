package mensajeria;

import java.io.Serializable;


public class PaqueteInventario extends Paquete implements Serializable, Cloneable{
	private int idPje;
	private int idInventario;
	private int manoDer;
	private int manoIzq;
	private int pie;
	private int cabeza;
	private int pecho;
	private int accesorio; 
	
	public PaqueteInventario(){
		
	}

	public PaqueteInventario(int idPje, int idInventario, int manoDer, int manoIzq, int pie, int cabeza, int pecho,
			int accesorio) {
		this.idPje = idPje;
		this.idInventario = idInventario;
		this.manoDer = manoDer;
		this.manoIzq = manoIzq;
		this.pie = pie;
		this.cabeza = cabeza;
		this.pecho = pecho;
		this.accesorio = accesorio;
	}

	public int getIdPje() {
		return idPje;
	}

	public void setIdPje(int idPje) {
		this.idPje = idPje;
	}

	public int getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}

	public int getManoDer() {
		return manoDer;
	}

	public void setManoDer(int manoDer) {
		this.manoDer = manoDer;
	}

	public int getManoIzq() {
		return manoIzq;
	}

	public void setManoIzq(int manoIzq) {
		this.manoIzq = manoIzq;
	}

	public int getPie() {
		return pie;
	}

	public void setPie(int pie) {
		this.pie = pie;
	}

	public int getCabeza() {
		return cabeza;
	}

	public void setCabeza(int cabeza) {
		this.cabeza = cabeza;
	}

	public int getPecho() {
		return pecho;
	}

	public void setPecho(int pecho) {
		this.pecho = pecho;
	}

	public int getAccesorio() {
		return accesorio;
	}

	public void setAccesorio(int accesorio) {
		this.accesorio = accesorio;
	}
	
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
	
	

}
