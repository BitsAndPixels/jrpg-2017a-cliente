package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import inventario.Item;
import mensajeria.PaquetePersonaje;

import constantes.TiposItem;

public class MenuMochilaInventario extends JFrame {

	private static Item[] slots = new Item[21];

	private static int cantitems = 0;
	private static int manoD = 0;
	private static int manoI = 0;
	private static int cabeza = 0;
	private static int piernas = 0;
	private static int pecho = 0;

	private JLayeredPane layeredPane;
	private static JLabel lblManoD = new JLabel("");
	private static JLabel lblManoI = new JLabel("");
	private static JLabel lblPecho = new JLabel("");
	private static JLabel lblCabeza = new JLabel("");
	private static JLabel lblPiernaD = new JLabel("");
	private static JLabel lblPiernaI = new JLabel("");
	private static JLabel lblNombre = new JLabel("");
	private static JLabel lblBonoat = new JLabel("+0");
	private static JLabel lblBonoener = new JLabel("+0");
	private static JLabel lblBonomag = new JLabel("+0");
	private static JLabel lblBonosal = new JLabel("+0");
	private static JLabel lblBonodef = new JLabel("+0");
	private static JPanel panelitems = new JPanel();
	private static JLabel lblPe = new JLabel("Pe:");
	private static JLabel lblPs = new JLabel("Ps:");
	private static JLabel lblAtd = new JLabel("At:");
	private static JLabel lblDefp = new JLabel("Def:");
	private static JLabel lblMagia = new JLabel("Magia:");

	private static JButton[] vectorBotonesItems = new JButton[20];

	/*****************************/
	// atributos del personaje a modificar
	/*****************************/
	private static int ataque = 10;
	private static int defensa = 10;
	private static int magia = 10;
	private static int salud = 100; // Imagino que la salud es la salud tope. y
	// salud normal
	private static int energia = 100; // Imagino que es la energia tope. y
	// energia normal
	private String nombre;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// PaquetePersonaje paquetePersonaje = new PaquetePersonaje();
	// paquetePersonaje.setNombre("Mr electron");
	// MenuMochilaInventario frame = new
	// MenuMochilaInventario(paquetePersonaje);
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */

	public MenuMochilaInventario(PaquetePersonaje paquetePersonaje) {
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		// JLabel labelBackground = new JLabel("");
		// labelBackground.setBounds(0, 0, 444, 271);
		// layeredPane.add(labelBackground, new Integer(0));
		// labelBackground.setIcon(new
		// ImageIcon(MenuRegistro.class.getResource("/frames/menuBackground.jpg")));

		setTitle("Mochila - Inventario");
		ActualizarAtributosPersonaje();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 574);
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(SystemColor.menu);
		layeredPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(layeredPane);
		layeredPane.setLayout(null);

		// Mapeo de los atributos que vienen del paquete personaje
		this.nombre = paquetePersonaje.getNombre();
		this.ataque = paquetePersonaje.getAtaque();
		this.defensa = paquetePersonaje.getDefensa();
		this.magia = paquetePersonaje.getMagia();
		this.salud = paquetePersonaje.getSaludTope();
		this.energia = paquetePersonaje.getEnergiaTope();

		JPanel panel = new JPanel();
		panel.setBounds(22, 232, 489, 228);
		layeredPane.add(panel);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));

		/*********************************************/
		// Configuracion inicial Buttons
		/*********************************************/

		// creacion e inicializacion de los 20 botones para los items

		panel.setLayout(new GridLayout(0, 5, 0, 0));

		for (int i = 0; i < vectorBotonesItems.length; i++) {
			vectorBotonesItems[i] = new JButton("");
			vectorBotonesItems[i].setBackground(Color.GRAY);
			vectorBotonesItems[i].setEnabled(false);
			panel.add(vectorBotonesItems[i]);
		}

		for (Item item : paquetePersonaje.getMochila().getItems().values()) {
			AgregarItem(item);
		}

		/************************************************/
		// Configuracion del Panel de items
		/************************************************/

		panelitems.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		panelitems.setBounds(259, 13, 252, 206);
		layeredPane.add(panelitems);
		panelitems.setLayout(null);
		panelitems.setVisible(false);

		/******************************************/
		// Configuracion inicial Labels
		/******************************************/

		JLabel lblNombred = new JLabel("Nombre:");
		lblNombred.setBounds(12, 13, 56, 16);
		panelitems.add(lblNombred);
		JLabel lblBonoatd = new JLabel("At.");
		lblBonoatd.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonoatd.setBounds(12, 57, 56, 16);
		panelitems.add(lblBonoatd);
		JLabel lblBonodefd = new JLabel("Def.");
		lblBonodefd.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonodefd.setBounds(103, 57, 44, 16);
		panelitems.add(lblBonodefd);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(74, 13, 147, 16);
		panelitems.add(lblNombre);
		lblBonoat.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonoat.setBounds(22, 86, 32, 16);
		panelitems.add(lblBonoat);
		lblBonodef.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonodef.setBounds(103, 86, 44, 16);
		panelitems.add(lblBonodef);
		JLabel lblBonomagd = new JLabel("Magia.");
		lblBonomagd.setBounds(185, 57, 56, 16);
		panelitems.add(lblBonomagd);
		JLabel lblBonosald = new JLabel("Ps.");
		lblBonosald.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonosald.setBounds(50, 115, 56, 16);
		panelitems.add(lblBonosald);
		JLabel lblBonoenerd = new JLabel("Pe.");
		lblBonoenerd.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonoenerd.setBounds(140, 115, 56, 16);
		panelitems.add(lblBonoenerd);
		lblBonosal.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonosal.setBounds(50, 144, 56, 16);
		panelitems.add(lblBonosal);
		lblBonoener.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonoener.setBounds(140, 144, 56, 16);
		panelitems.add(lblBonoener);
		lblBonomag.setHorizontalAlignment(SwingConstants.CENTER);
		lblBonomag.setBounds(172, 86, 56, 16);
		panelitems.add(lblBonomag);
		lblManoD.setBackground(Color.GRAY);
		lblManoD.setOpaque(true);
		lblManoD.setBounds(110, 55, 30, 66);
		layeredPane.add(lblManoD);
		lblManoI.setOpaque(true);
		lblManoI.setBackground(Color.GRAY);
		lblManoI.setBounds(203, 55, 30, 66);
		layeredPane.add(lblManoI);
		lblPecho.setOpaque(true);
		lblPecho.setBackground(Color.GRAY);
		lblPecho.setBounds(152, 55, 39, 66);
		layeredPane.add(lblPecho);
		lblCabeza.setOpaque(true);
		lblCabeza.setBackground(Color.GRAY);
		lblCabeza.setBounds(152, 0, 39, 48);
		layeredPane.add(lblCabeza);
		lblPiernaD.setOpaque(true);
		lblPiernaD.setBackground(Color.GRAY);
		lblPiernaD.setBounds(152, 123, 39, 35);
		layeredPane.add(lblPiernaD);
		lblPiernaI.setOpaque(true);
		lblPiernaI.setBackground(Color.GRAY);
		lblPiernaI.setBounds(152, 158, 39, 35);
		layeredPane.add(lblPiernaI);
		lblAtd.setBounds(22, 40, 56, 16);
		layeredPane.add(lblAtd);
		lblDefp.setBounds(22, 55, 56, 16);
		layeredPane.add(lblDefp);
		lblMagia.setBounds(22, 69, 56, 16);
		layeredPane.add(lblMagia);
		lblPs.setBounds(22, 84, 56, 16);
		layeredPane.add(lblPs);
		lblPe.setBounds(22, 98, 56, 16);
		layeredPane.add(lblPe);

		/***************************************************************/
		// Evento Mostrar atributos del objeto al pasar con el puntero
		/***************************************************************/
		// No funciona, dejo constancia asi no lo intentan.
		
		// for (int i=0; i < vectorBotonesItems.length; i++) {
		// vectorBotonesItems[i].addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseEntered(MouseEvent arg0) {
		// VerAtributos(vectorBotonesItems[i], i);
		// }
		//
		// @Override
		// public void mouseExited(MouseEvent e) {
		// panelitems.setVisible(false);
		// }
		// });
		// }

		for (int i = 0; i < vectorBotonesItems.length; i++) {
			escuchaListener(i);
		}

		JButton btnSave = new JButton("Guardar Cambios");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		btnSave.setBounds(202, 489, 120, 25);
		layeredPane.add(btnSave);

		JLabel lblNombreper = new JLabel(nombre);
		lblNombreper.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreper.setBounds(22, 7, 102, 35);

		layeredPane.add(lblNombreper);

		/************************************************/
		// Slots Buttons Actions
		/***********************************************/
		////
		vectorBotonesItems[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[0], 0);
			}
		});	
		vectorBotonesItems[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[1], 1);
			}
		});
		vectorBotonesItems[2].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[2], 2);
			}
		});
		vectorBotonesItems[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[3], 3);
			}
		});
		
		vectorBotonesItems[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[4], 4);
			}
		});
		vectorBotonesItems[5].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[5], 5);
			}
		});
		vectorBotonesItems[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[6], 6);
			}
		});
		
		vectorBotonesItems[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[7], 7);
			}
		});
		vectorBotonesItems[8].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[8], 8);
			}
		});
		vectorBotonesItems[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[9], 9);
			}
		});
		
		vectorBotonesItems[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[10], 10);
			}
		});
		vectorBotonesItems[11].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[11], 11);
			}
		});
		vectorBotonesItems[12].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[12], 12);
			}
		});
		
		vectorBotonesItems[13].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[13], 13);
			}
		});
		vectorBotonesItems[14].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[14], 14);
			}
		});
		vectorBotonesItems[15].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[15], 15);
			}
		});
		
		vectorBotonesItems[16].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[16], 16);
			}
		});
		vectorBotonesItems[17].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[17], 17);
			}
		});
		vectorBotonesItems[18].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[18], 18);
			}
		});
		
		vectorBotonesItems[19].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EquiparODesequipar(vectorBotonesItems[19], 19);
			}
		});	

	}// Termina el Jframe

	private static void escuchaListener(int numeroBoton) {
		vectorBotonesItems[numeroBoton].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				VerAtributos(vectorBotonesItems[numeroBoton], numeroBoton);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelitems.setVisible(false);
			}
		});
	}

	/**********************************************************************************/
	// Metodo para equipar o desquipar un objeto y actualizar los valores del
	// personaje
	/**********************************************************************************/

	public static void EquiparODesequipar(JButton btn, int slot) {
		String ruta = new String(slots[slot].getIdItem() + ".png");
		String rutab = new String(slots[slot].getIdItem() + "b.png");

		switch (slots[slot].getTipo()) {
		case TiposItem.MANO_DERECHA:
			if (manoD == 0) {
				manoD = slot;
				btn.setBackground(Color.green);
				lblManoD.setBackground(SystemColor.menu);
				seteaAtributosInventario(slot, '+');
				ActualizarAtributosPersonaje();
				lblManoD.setIcon(new ImageIcon(
						((new ImageIcon(ruta)).getImage()).getScaledInstance(30, 60, java.awt.Image.SCALE_SMOOTH)));

			} else if (manoD == slot) {
				manoD = 0;
				btn.setBackground(Color.GRAY);
				lblManoD.setBackground(Color.GRAY);
				seteaAtributosInventario(slot, '-');
				ActualizarAtributosPersonaje();
				lblManoD.setIcon(null);

			} else {

				JOptionPane.showMessageDialog(null, "Desequipe su objeto acual primero");
			}
			;
			break;

		case TiposItem.MANO_IZQUIERDA:
			if (manoI == 0) {
				manoI = slot;
				btn.setBackground(Color.green);
				lblManoI.setBackground(SystemColor.menu);
				seteaAtributosInventario(slot, '+');
				ActualizarAtributosPersonaje();
				lblManoI.setIcon(new ImageIcon(
						((new ImageIcon(ruta)).getImage()).getScaledInstance(30, 60, java.awt.Image.SCALE_SMOOTH)));

			} else if (manoI == slot) {
				manoI = 0;
				btn.setBackground(Color.GRAY);
				lblManoI.setBackground(Color.GRAY);
				seteaAtributosInventario(slot, '-');
				ActualizarAtributosPersonaje();
				lblManoI.setIcon(null);

			} else {

				JOptionPane.showMessageDialog(null, "Desequipe su objeto acual primero");
			}
			;
			break;

		case TiposItem.CABEZA:
			if (cabeza == 0) {
				cabeza = slot;
				btn.setBackground(Color.green);
				lblCabeza.setBackground(SystemColor.menu);
				seteaAtributosInventario(slot, '+');
				ActualizarAtributosPersonaje();
				lblCabeza.setIcon(new ImageIcon(
						((new ImageIcon(ruta)).getImage()).getScaledInstance(40, 60, java.awt.Image.SCALE_SMOOTH)));

			} else if (cabeza == slot) {
				cabeza = 0;
				btn.setBackground(Color.GRAY);
				lblCabeza.setBackground(Color.GRAY);
				seteaAtributosInventario(slot, '-');
				ActualizarAtributosPersonaje();
				lblCabeza.setIcon(null);
			} else {
				JOptionPane.showMessageDialog(null, "Desequipe su objeto acual primero");
			}
			;
			break;
		case TiposItem.PIES:
			if (piernas == 0) {
				piernas = slot;
				btn.setBackground(Color.green);
				lblPiernaI.setBackground(SystemColor.menu);
				lblPiernaD.setBackground(SystemColor.menu);
				seteaAtributosInventario(slot, '+');
				ActualizarAtributosPersonaje();
				lblPiernaD.setIcon(new ImageIcon(
						((new ImageIcon(ruta)).getImage()).getScaledInstance(38, 60, java.awt.Image.SCALE_SMOOTH)));
				lblPiernaI.setIcon(new ImageIcon(
						((new ImageIcon(rutab)).getImage()).getScaledInstance(35, 60, java.awt.Image.SCALE_SMOOTH)));

			} else if (piernas == slot) {
				piernas = 0;
				btn.setBackground(Color.GRAY);
				lblPiernaI.setBackground(Color.GRAY);
				lblPiernaD.setBackground(Color.GRAY);
				seteaAtributosInventario(slot, '-');
				ActualizarAtributosPersonaje();
				lblPiernaD.setIcon(null);
				lblPiernaI.setIcon(null);
			} else {
				JOptionPane.showMessageDialog(null, "Desequipe su objeto acual primero");
			}
			;
			break;
		case TiposItem.PECHO:
			if (pecho == 0) {
				pecho = slot;
				btn.setBackground(Color.green);
				lblPecho.setBackground(SystemColor.menu);
				seteaAtributosInventario(slot, '+');
				ActualizarAtributosPersonaje();
				lblPecho.setIcon(new ImageIcon(
						((new ImageIcon(ruta)).getImage()).getScaledInstance(40, 70, java.awt.Image.SCALE_SMOOTH)));

			} else if (pecho == slot) {
				pecho = 0;
				btn.setBackground(Color.GRAY);
				lblPecho.setBackground(Color.GRAY);
				seteaAtributosInventario(slot, '-');
				ActualizarAtributosPersonaje();
				lblPecho.setIcon(null);
			} else {
				JOptionPane.showMessageDialog(null, "Desequipe su objeto acual primero");
			}
			;
			break;
		}

	}//// termina EquiparODesequipar

	private static void seteaAtributosInventario(int slot,char signo) {
		if(signo=='+'){
		ataque = ataque + slots[slot].getBonoAtaque();
		defensa = defensa + slots[slot].getBonoDefensa();
		magia = magia + slots[slot].getBonoMagia();
		salud = salud + slots[slot].getBonoSalud();
		energia = energia + slots[slot].getBonoEnergia();
		}
		if(signo=='-'){
		ataque = ataque - slots[slot].getBonoAtaque();
		defensa = defensa - slots[slot].getBonoDefensa();
		magia = magia - slots[slot].getBonoMagia();
		salud = salud - slots[slot].getBonoSalud();
		energia = energia - slots[slot].getBonoEnergia();
		}
	}

	/*****************************************************************************/

	// Actualiza y vuelve visibles los labels que muestran los atributos del
	// item
	/*****************************************************************************/

	public static void VerAtributos(JButton btn, int slot) {
		if (btn.isEnabled()) {
			panelitems.setVisible(true);
			lblBonoat.setText("+" + slots[slot].getBonoAtaque());
			lblBonodef.setText("+" + slots[slot].getBonoDefensa());
			lblBonomag.setText("+" + slots[slot].getBonoMagia());
			lblBonosal.setText("+" + slots[slot].getBonoSalud());
			lblBonoener.setText("+" + slots[slot].getBonoEnergia());
			lblNombre.setText(slots[slot].getNombre());
		}
	}

	/****************************************************************/
	// Actualiza los labels de los valores de atributos del personaje
	/****************************************************************/

	public static void ActualizarAtributosPersonaje() {
		lblPs.setText("Ps:" + salud);
		lblPe.setText("Pe:" + Integer.toString(energia));
		lblAtd.setText("At:" + Integer.toString(ataque));
		lblDefp.setText("Def:" + Integer.toString(defensa));
		lblMagia.setText("Magia:" + Integer.toString(magia));
	}

	/******************************************************************/
	// Agrega un item , tanto al array de slots como al Panel de slots
	/******************************************************************/

	public static void AgregarItem(Item item) {
		String ruta = new String(item.getIdItem() + ".png");

		cantitems++;
		switch (cantitems) {
		case 1:
			itemSlotsPanelSlots(item, 1, ruta);
			break;
		case 2:
			itemSlotsPanelSlots(item, 2, ruta);
			break;
		case 3:
			itemSlotsPanelSlots(item, 3, ruta);
			break;
		case 4:
			itemSlotsPanelSlots(item, 4, ruta);
			break;
		case 5:
			itemSlotsPanelSlots(item, 5, ruta);
			break;
		case 6:
			itemSlotsPanelSlots(item, 6, ruta);
			break;
		case 7:
			itemSlotsPanelSlots(item, 7, ruta);
			break;
		case 8:
			itemSlotsPanelSlots(item, 8, ruta);
			break;
		case 9:
			itemSlotsPanelSlots(item, 9, ruta);
			break;
		case 10:
			itemSlotsPanelSlots(item, 10, ruta);
			break;
		case 11:
			itemSlotsPanelSlots(item, 11, ruta);
			break;
		case 12:
			itemSlotsPanelSlots(item, 12, ruta);
			break;
		case 13:
			itemSlotsPanelSlots(item, 13, ruta);
			break;
		case 14:
			itemSlotsPanelSlots(item, 14, ruta);
			break;
		case 15:
			itemSlotsPanelSlots(item, 15, ruta);
			break;
		case 16:
			itemSlotsPanelSlots(item, 16, ruta);
			break;
		case 17:
			itemSlotsPanelSlots(item, 17, ruta);
			break;
		case 18:
			itemSlotsPanelSlots(item, 18, ruta);
			break;
		case 19:
			itemSlotsPanelSlots(item, 19, ruta);
			break;
		case 20: // Este deberia ser el 0
			itemSlotsPanelSlots(item, 20, ruta);
			break;
		case 21:
			JOptionPane.showMessageDialog(null, "Inventario lleno");
			cantitems = 20;
			break;
		}
	}

	private static void itemSlotsPanelSlots(Item item, int numeroBoton, String ruta) {
		vectorBotonesItems[numeroBoton].setEnabled(true);
		slots[cantitems] = new Item(item);
		vectorBotonesItems[numeroBoton].setIcon(new ImageIcon(
				((new ImageIcon(ruta)).getImage()).getScaledInstance(30, 60, java.awt.Image.SCALE_SMOOTH)));
	}
}
