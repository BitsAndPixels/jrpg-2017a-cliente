package chat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Chat extends JFrame {

	private Cliente cliente;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private PaqueteChat paqueteChat;

	private final Gson gson = new Gson();

	public Chat(final Cliente cliente) {
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(),
				new Point(0,0),"custom cursor"));
		
		this.cliente=cliente;
		String usuarioActivo=cliente.getPaquetePersonaje().getNombre();
		setTitle("CHAT: " + usuarioActivo);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(29, 27, 307, 168);
		textArea.setForeground(Color.BLUE);
		contentPane.add(textArea, new Integer(1));
		JScrollPane scrollableText = new JScrollPane(textArea);

		scrollableText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollableText.setBounds(29, 27, 307, 168);
		contentPane.add(scrollableText, new Integer(1));

		JButton btnEnviar = new JButton("");
		btnEnviar.setBounds(239, 213, 97, 25);
		btnEnviar.setFocusable(false);
		contentPane.add(btnEnviar, new Integer(1));
		btnEnviar.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/BotonMenu.png")));

		textField = new JTextField();

		textField.setBounds(29, 213, 206, 25);
		contentPane.add(textField, new Integer(1));
		textField.setColumns(10);
		
		JLabel labelBackground = new JLabel("");
		labelBackground.setBounds(0, 0, 444, 271);
		contentPane.add(labelBackground, new Integer(0));
		labelBackground.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/menuBackground.jpg")));

		this.paqueteChat = new PaqueteChat();

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(textField.getText().isEmpty())) //No permito enviar msgs en blanco
				{	textArea.append(textField.getText() + System.lineSeparator());
					creacionPaqueteChat(textField.getText(),usuarioActivo);
					enviarMensaje();
					textField.setText("");
				}	
			}
		});

		textField.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(!(textField.getText().isEmpty())) //No permito enviar msgs en blanco
				{	textArea.append(textField.getText() + System.lineSeparator());
					creacionPaqueteChat(textField.getText(),usuarioActivo);
					enviarMensaje();
					textField.setText("");
				}
			}
			});
	}

	private void creacionPaqueteChat(String mensajeTextField, String usuarioActivo) {
		int posblanco = 0; //indica la posicion en blanco "JUAN hola  -> me daria 5
		posblanco=mensajeTextField.indexOf(' ');
		paqueteChat.setNombreUsuarioPasivo(mensajeTextField.substring(1, posblanco));
		paqueteChat.setNombreUsuarioActivo(usuarioActivo);
		paqueteChat.setMensajeChat(mensajeTextField.substring(posblanco+1,mensajeTextField.length()));
	}

	private void enviarMensaje() {
		try {
			this.cliente.getSalida().writeObject(gson.toJson(this.paqueteChat));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
			e.printStackTrace();
		}
	}

	public void nuevoMensaje(PaqueteChat paqueteChat){
		if (paqueteChat.getNombreUsuarioPasivo().isEmpty()) //el mensaje es para todos
		{
			this.textArea.append("! "+paqueteChat.getNombreUsuarioActivo()
			+ ": " + paqueteChat.getMensajeChat() + System.lineSeparator());		
		} 
		else //el mensaje es privado
		{ 
			this.textArea.append("\"" +paqueteChat.getNombreUsuarioActivo()
			+ ": " + paqueteChat.getMensajeChat() + System.lineSeparator());	
		}		
	}
}
