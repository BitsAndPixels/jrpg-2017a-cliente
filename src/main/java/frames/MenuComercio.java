package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import chat.TipoMensajeChat;
import chat.TipoOperacionComercio;
import cliente.Cliente;
import inventario.Item;
import inventario.Mochila;
import mensajeria.PaqueteChat;
import mensajeria.PaqueteComercio;
import mensajeria.PaquetePersonaje;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

public class MenuComercio extends JFrame {
	
	private JPanel contentPane;
	DefaultListModel modelo = new DefaultListModel();
	private JList lstItems = new JList(modelo); 
	private JLabel lblItemO = new JLabel("");
	private JLabel lblItemI = new JLabel("");
	private JButton btnComerciar = new JButton("Comerciar");
	private JButton btnOfertar = new JButton("Ofertar");
	private PaquetePersonaje paquetePersonaje;
	private Cliente cliente;
	private HashMap items;
	private PaqueteComercio paqueteComercio;
	private final Gson gson = new Gson();
	private Mochila mochila;
	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		PaquetePersonaje personaje=new PaquetePersonaje ();
//		Item i=new Item ();
//		Mochila m = new Mochila();
//		m.agregaItem(i);
//		personaje.setMochila(m);
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Cliente c=new Cliente();
//					MenuComercio frame = new MenuComercio(c,personaje);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MenuComercio(Cliente cliente ,PaquetePersonaje personaje,PaquetePersonaje enemigo) {
		
		paquetePersonaje=personaje;
		this.cliente=cliente;
		String usuarioActivo = cliente.getPaquetePersonaje().getNombre();
		String usuarioPasivo= enemigo.getNombre();
		mochila=personaje.getMochila();
		items=mochila.getItems();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lstItems.setVisibleRowCount(20);
		lstItems.setBounds(29, 36, 129, 183);
		contentPane.add(lstItems);
		
//		Map map = items;
//		Iterator entries = map.entrySet().iterator();
//		while (entries.hasNext()) {
//		    Map.Entry entry = (Map.Entry) entries.next();
//		    Integer key = (Integer)entry.getKey();
//		    Item value = (Item)entry.getValue();
//		    modelo.add(key, value.getNombre());
//		}
	    
		JLabel lblMochila = new JLabel("Inventario");
		lblMochila.setBounds(69, 13, 69, 16);
		contentPane.add(lblMochila);
		
		
		
		btnOfertar.setBounds(179, 83, 97, 25);
		contentPane.add(btnOfertar);
		
		btnComerciar.setBounds(179, 121, 97, 25);
		contentPane.add(btnComerciar);
		
		JLabel lblItemQueOfreces = new JLabel("Item que ofreces:");
		lblItemQueOfreces.setBounds(359, 37, 129, 16);
		contentPane.add(lblItemQueOfreces);
		
		
		lblItemO.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemO.setBounds(300, 65, 231, 16);
		contentPane.add(lblItemO);
		
		JLabel lblItemOfrecido = new JLabel("Item ofrecido:");
		lblItemOfrecido.setBounds(359, 125, 115, 16);
		contentPane.add(lblItemOfrecido);
		
		
		lblItemI.setBounds(300, 157, 219, 16);
		contentPane.add(lblItemI);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				comportamientoBotonCerrar(usuarioActivo,usuarioPasivo);
			}
		});
		
		btnOfertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comportamientoBotonOfertar(usuarioActivo,usuarioPasivo,items);
					
			}
		});
		btnComerciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comportamientoBotonComerciar(usuarioActivo,usuarioPasivo,items);
			}
		});
		
	}
	private void comportamientoBotonOfertar(String usuarioActivo ,String usuarioPasivo, HashMap item) {
		if (lstItems.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Seleccione un Item");
		} else {
			lblItemO.setText(lstItems.getSelectedValues().toString());
			paqueteComercio.setTipoOperacion(TipoOperacionComercio.OFERTAR);
			paqueteComercio.setNombreUsuarioActivo(usuarioActivo);
			paqueteComercio.setNombreUsuarioPasivo(usuarioPasivo);
			paqueteComercio.setItem((Item) (item.get(lstItems.getSelectedIndex()))); ///revisar q esto este bien
			enviarMensaje();		
		}
	}
	
	private void comportamientoBotonCerrar(String usuarioActivo ,String usuarioPasivo){
		paqueteComercio.setTipoOperacion(TipoOperacionComercio.CANCELAR);
		paqueteComercio.setNombreUsuarioActivo(usuarioActivo);
		paqueteComercio.setNombreUsuarioPasivo(usuarioPasivo);
		enviarMensaje();
	}
	
	
	private void comportamientoBotonComerciar(String usuarioActivo,String usuarioPasivo, HashMap item) {
		if (lstItems.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Seleccione un Item");
		} else {
			lblItemO.setText(lstItems.getSelectedValues().toString());
			paqueteComercio.setTipoOperacion(TipoOperacionComercio.COMERCIAR);
			paqueteComercio.setNombreUsuarioActivo(usuarioActivo);
			paqueteComercio.setNombreUsuarioPasivo(usuarioPasivo);
			paqueteComercio.setItem((Item) (item.get(lstItems.getSelectedIndex()))); ///revisar q esto este bien
			enviarMensaje();		
		}
	}
	private void enviarMensaje() {
		try {
			this.cliente.getSalida().writeObject(gson.toJson(this.paqueteComercio));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
			e.printStackTrace();
		}
	}
	
	public void nuevoMensaje(PaqueteComercio paqueteComercio) {
		switch (paqueteComercio.getTipoOperacion()) {
		case OFERTAR:
			this.lblItemI.setText(paqueteComercio.getItem().getNombre());
			break;
		case CANCELAR:
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			break;
		case COMERCIAR:
			
			Map map = items;
			Iterator entries = map.entrySet().iterator();
			while (entries.hasNext()) {
			    Map.Entry entry = (Map.Entry) entries.next();
			    Integer key = (Integer)entry.getKey();
			    Item value = (Item)entry.getValue();
			    if (value.getNombre()==lblItemO.getText()){
			    	items.replace(key, paqueteComercio.getItem());
			    	cargarMochila();
			    	
			    	paquetePersonaje.setMochila(mochila);
			    	/*****************/
					/////// aca hay q llarmar a actualizar el personaje
			    	/*****************/
			    	
			    	break;
			    }
			}
		    
			break;
		default:
			break;
		}	
	}
	
	
	private void cargarMochila(){	
		mochila=new Mochila();
		Map map = items;
		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    Integer key = (Integer)entry.getKey();
		    Item value = (Item)entry.getValue();
		    mochila.agregaItem(value);
		}	
	}		
}

