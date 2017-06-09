package mensajeria;

import java.io.Serializable;

public class PaqueteItem extends Paquete implements Serializable, Cloneable{
	
	//private int idPj;
	private int idItem;
	private int bonoAtaque;
	private int bonoDefensa;
	private int bonoMagia;
	private int bonoSalud;
	private int bonoEnergia;
	private int tipo;
	private String nombre;
	
	public PaqueteItem() {
		setComando(Comando.ITEM);
	}
	
	public PaqueteItem(int idItem, int bonoAtaque, int bonoDefensa, int bonoMagia, int bonoSalud,
			int bonoEnergia, int tipo, String nombre) {
		setComando(Comando.ITEM);
		//this.idPj = idPj;
		this.idItem = idItem;
		this.bonoAtaque = bonoAtaque;
		this.bonoDefensa = bonoDefensa;
		this.bonoMagia = bonoMagia;
		this.bonoSalud = bonoSalud;
		this.bonoEnergia = bonoEnergia;
		this.tipo = tipo;
		this.nombre = nombre;
	}
//	public int getIdPj() {
//		return idPj;
//	}
//	public void setIdPj(int idPj) {
//		this.idPj = idPj;
//	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public int getBonoAtaque() {
		return bonoAtaque;
	}
	public void setBonoAtaque(int bonoAtaque) {
		this.bonoAtaque = bonoAtaque;
	}
	public int getBonoDefensa() {
		return bonoDefensa;
	}
	public void setBonoDefensa(int bonoDefensa) {
		this.bonoDefensa = bonoDefensa;
	}
	public int getBonoMagia() {
		return bonoMagia;
	}
	public void setBonoMagia(int bonoMagia) {
		this.bonoMagia = bonoMagia;
	}
	public int getBonoSalud() {
		return bonoSalud;
	}
	public void setBonoSalud(int bonoSalud) {
		this.bonoSalud = bonoSalud;
	}
	public int getBonoEnergia() {
		return bonoEnergia;
	}
	public void setBonoEnergia(int bonoEnergia) {
		this.bonoEnergia = bonoEnergia;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
