package chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import frames.MenuJugar;
import frames.MenuRegistro;
import mensajeria.PaqueteChat;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Chat extends JFrame {

	private Cliente cliente;
	private JLayeredPane layeredPane;
	private JTextField textField;
	private JTextArea textArea;
	private PaqueteChat paqueteChat;
	private final Gson gson = new Gson();

	public Chat(final Cliente cliente) {
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		this.cliente = cliente;
		String usuarioActivo = cliente.getPaquetePersonaje().getNombre();
		setTitle("CHAT: " + usuarioActivo);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(layeredPane);
		layeredPane.setLayout(null);

		this.textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(29, 27, 307, 168);
		textArea.setForeground(Color.BLUE);
		layeredPane.add(textArea, new Integer(1));
		JScrollPane scrollableText = new JScrollPane(textArea);

		scrollableText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollableText.setBounds(29, 27, 307, 168);
		layeredPane.add(scrollableText, new Integer(1));

		JLabel lblEnviar = new JLabel("Enviar");
		lblEnviar.setBounds(271, 213, 66, 23);
		layeredPane.add(lblEnviar, new Integer(2));
		lblEnviar.setForeground(Color.WHITE);
		lblEnviar.setEnabled(true);
		lblEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnviar.setBackground(Color.WHITE);
		
		JButton btnEnviar = new JButton("");
		btnEnviar.setBounds(243, 213, 97, 25);
		btnEnviar.setFocusable(false);
		layeredPane.add(btnEnviar, new Integer(1));
		btnEnviar.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/BotonMenu.png")));

		textField = new JTextField();

		textField.setBounds(29, 213, 206, 25);
		layeredPane.add(textField, new Integer(1));
		textField.setColumns(10);

		JLabel labelBackground = new JLabel("");
		labelBackground.setBounds(0, 0, 444, 271);
		layeredPane.add(labelBackground, new Integer(0));
		labelBackground.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/menuBackground.jpg")));

		this.paqueteChat = new PaqueteChat();

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comportamientoBoton(usuarioActivo);
			}
		});

		textField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				comportamientoBoton(usuarioActivo);
			}
		});
	}

	private void comportamientoBoton(String usuarioActivo) {
		if (selectorArmadoPaqueteChat(textField.getText(), usuarioActivo) == true) {
			textArea.append(textField.getText() + System.lineSeparator());
			enviarMensaje();
		} else {
			JOptionPane.showMessageDialog(null, "Los me mensajes deben comenzar con /, ! o \"");
		}
		textField.setText("");
	}

	private boolean selectorArmadoPaqueteChat(String mensajeTextField, String usuarioActivo) {
		switch (mensajeTextField.charAt(0)) {
		case '/': // Es un comando
			armadoPaqueteComando(mensajeTextField, usuarioActivo);
			return true;
		case '"': // Mensaje privado
			armadoPaquetePrivado(mensajeTextField, usuarioActivo);
			return true;
		case '!': // Mensaje para todo el mundo
			armadoPaqueteGlobal(mensajeTextField, usuarioActivo);
			return true;
		default:
			return false;
		}
	}

	private void armadoPaqueteGlobal(String mensajeTextField, String usuarioActivo) {
		paqueteChat.setTipoMensaje(TipoMensajeChat.GLOBAL);
		paqueteChat.setNombreUsuarioActivo(usuarioActivo);
		paqueteChat.setMensajeChat(mensajeTextField.substring(1, mensajeTextField.length()));
	}

	private void armadoPaquetePrivado(String mensajeTextField, String usuarioActivo) {
		paqueteChat.setTipoMensaje(TipoMensajeChat.PRIVADO);
		int posblanco = mensajeTextField.indexOf(' ');
		paqueteChat.setNombreUsuarioPasivo(mensajeTextField.substring(1, posblanco));
		paqueteChat.setNombreUsuarioActivo(usuarioActivo);
		paqueteChat.setMensajeChat(mensajeTextField.substring(posblanco + 1, mensajeTextField.length()));
	}

	private void armadoPaqueteComando(String mensajeTextField, String usuarioActivo) {
		paqueteChat.setTipoMensaje(TipoMensajeChat.COMANDO);
		paqueteChat.setNombreUsuarioActivo(usuarioActivo);
		paqueteChat.setMensajeChat(mensajeTextField.substring(1, mensajeTextField.length()));
	}

	private void enviarMensaje() {
		try {
			this.cliente.getSalida().writeObject(gson.toJson(this.paqueteChat));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
			e.printStackTrace();
		}
	}

	public void nuevoMensaje(PaqueteChat paqueteChat) {
		switch (paqueteChat.getTipoMensaje()) {
		case COMANDO:
			this.textArea.append("Servidor: " + paqueteChat.getMensajeChat() + System.lineSeparator());
			break;
		case PRIVADO:
			this.textArea.append("\"" + paqueteChat.getNombreUsuarioActivo() + ": " + paqueteChat.getMensajeChat()
					+ System.lineSeparator());
			break;
		case GLOBAL:
			this.textArea.append("!" + paqueteChat.getNombreUsuarioActivo() + ": " + paqueteChat.getMensajeChat()
					+ System.lineSeparator());
			break;
		default:
			break;
		}
	}
}