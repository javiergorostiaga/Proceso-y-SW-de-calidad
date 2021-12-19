package frontend.swing;

import javax.swing.JFrame;

import javax.swing.JPanel;

import backend.objects.personas.Administrador;
import backend.objects.personas.Persona;
import frontend.controller.Controller;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;


public class Login extends JFrame{

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passUsuario;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblBienvenido;
	private JPanel panel;
	private JLabel lblNewLabel;
	private HashMap <String, Persona> hashUsuarios;
	private VentanaPrincipal ventanaAnterior ;
	private Controller controller;

	public VentanaPrincipal getVentanaAnterior() {
		return ventanaAnterior;
	}

	public Login() throws HeadlessException {
		super();//lo uso para hacer el test con JUnit para no tener que pasar ningun parametro.
	}


	public Login(VentanaPrincipal principal, Controller controller)
	{
		this.controller = controller;

		setResizable(false);
		ventanaAnterior = principal;
		setTitle("Login");
		this.hashUsuarios=principal.getHashUsuarios();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 481, 307);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(219, 58, 198, 26);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(219, 34, 65, 14);
		contentPane.add(lblUsuario);
		
		passUsuario = new JPasswordField();
		passUsuario.setBounds(219, 126, 198, 26);
		contentPane.add(passUsuario);
		
		lblContrasea = new JLabel("CONTRASE\u00D1A");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(219, 101, 95, 14);
		contentPane.add(lblContrasea);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(219, 181, 89, 23);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(328, 181, 89, 23);
		contentPane.add(btnCancel);
		
		lblBienvenido = new JLabel("Login Necesario");
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBienvenido.setBounds(246, 205, 198, 30);
		contentPane.add(lblBienvenido);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 0, 4, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, 0, 176, 268);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(22, 62, 128, 130);
		panel.add(lblNewLabel);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = textUsuario.getText();
				String contrasenya=passUsuario.getText();
				try {
					boolean encontrado=comprobarUsuario(usuario, contrasenya, hashUsuarios);
					if(encontrado) {
						informacion(usuario); // AQUÍ ES DONDE VE SI ES ADMIN O USER
						}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(Login.this, e1.getMessage());
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.setLocationRelativeTo(Login.this);
				principal.setVisible(true);
				Login.this.dispose();
			}
		});
		Login.this.addWindowListener(new WindowAdapter() {//lo que hace al dar a la X
			   public void windowClosing(WindowEvent evt) {
			        int respuesta = JOptionPane.showOptionDialog(Login.this, "�Est�s seguro de salir?","Confirmar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
			        if (respuesta == 0) 
			        {
			        	System.exit(0);
			        }
			   	}
			  });
	}


	public boolean comprobarUsuario(String usuario, String contrasenya, HashMap <String,Persona> hashPeliculas) throws Exception {
		if(!hashPeliculas.containsKey(usuario))
		{
			throw new Exception("Usuario no existente");
		}
		else
		{
			String passDelHash=hashPeliculas.get(usuario).getPassword();
			if(passDelHash.equals(contrasenya))
			{
				return true;
			}
			else
				throw new Exception("Clave incorrecta");
		}
	}


	public void informacion(String usuario)
	{
		if(hashUsuarios.get(usuario) instanceof Administrador || usuario.equals("admin"))
		{
			VentanaAdministrador a = new VentanaAdministrador(Login.this, (hashUsuarios.get(usuario)), controller);
			a.setLocationRelativeTo(Login.this);
			a.setVisible(true);
			Login.this.setVisible(false);
		}
		else 
		{
			VentanaUsuario a = new VentanaUsuario(hashUsuarios.get(usuario).getNombreUsuario(),Login.this);
			a.setLocationRelativeTo(Login.this);
			a.setVisible(true);
			Login.this.setVisible(false);
		}
	}
	
}
