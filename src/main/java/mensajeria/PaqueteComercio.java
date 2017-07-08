package mensajeria;

import java.io.Serializable;
import chat.TipoOperacionComercio;
import inventario.Item;

public class PaqueteComercio extends Paquete implements Serializable, Cloneable{

	private String nombreUsuarioActivo;
	private String nombreUsuarioPasivo;
	private int idReceptor;
	private Item item;
	private TipoOperacionComercio tipoOperacion;
	
	public String getNombreUsuarioActivo() {
		return nombreUsuarioActivo;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void setNombreUsuarioActivo(String nombreUsuarioActivo) {
		this.nombreUsuarioActivo = nombreUsuarioActivo;
	}
	public String getNombreUsuarioPasivo() {
		return nombreUsuarioPasivo;
	}
	public void setNombreUsuarioPasivo(String nombreUsuarioPasivo) {
		this.nombreUsuarioPasivo = nombreUsuarioPasivo;
	}
	public Item getItem() {
		return item;
	}
	
	public void setIdReceptor(int id) {
		this.idReceptor=id;
	}
	
	public int getIdReceptor() {
		return this.idReceptor;
	}
	

	public TipoOperacionComercio getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoOperacionComercio tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}