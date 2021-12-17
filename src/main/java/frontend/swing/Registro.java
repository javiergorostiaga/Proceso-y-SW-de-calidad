package frontend.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.objects.Pelicula;
import frontend.swing.Login;
import frontend.swing.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JPasswordField;


public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textReal;
	private VentanaPrincipal principal ;	
	private JPasswordField passwordField;
	

	public Registro() throws HeadlessException {
		super();
		textReal = new JTextField();
		passwordField = new JPasswordField();
	}

	public Registro(VentanaPrincipal ventanaPrincipal) {
		
		setResizable(false);
		principal = ventanaPrincipal;
		setTitle("Login");
		HashMap<String, Pelicula> hashPeliculas; //ventanaPrincipal.getHashUsuarios();
		
		setTitle("Registro de Usuario");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 455, 303);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreUsuario.setBounds(32, 39, 187, 20);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblNombreReal = new JLabel("Nombre Real:");
		lblNombreReal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreReal.setBounds(32, 92, 123, 20);
		contentPane.add(lblNombreReal);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasea.setBounds(32, 147, 141, 20);
		contentPane.add(lblContrasea);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(216, 41, 165, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textReal = new JTextField();
		textReal.setBounds(216, 94, 165, 20);
		contentPane.add(textReal);
		textReal.setColumns(10);
		
		JButton btnVolver = new JButton("Cancelar");
		btnVolver.setBounds(312, 202, 115, 29);
		contentPane.add(btnVolver);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(162, 202, 115, 29);
		contentPane.add(btnOk);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(216, 149, 165, 20);
		contentPane.add(passwordField);
		
		btnOk.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				if(!textUsuario.getText().isEmpty()&&!passwordField.getText().isEmpty()&&!textReal.getText().isEmpty())
				{
					String username=textUsuario.getText();
					boolean valor=false;
					try {
						/*
						comprobarUsuario(username,hashUsuarios);//valor=
						UsuarioEstandar nuevoUsuario=null;//solo se pueden crear usuarios estandar luego se dara la posibilidad de que sean premium
						String contrasenya=passwordField.getText();
						int fechaAlta=Utilidades.obtenerFechaActual();
						String nombreReal=textReal.getText();
						double calific = 0;//hasta que alguien les valore ser� 0, lo que aparece en calificaci�n es la calificaci�n de la �ltima operaci�n de venta
						comprobarPrimaryKey(username,hashUsuarios);//compruebo que nombre real y la pass no coincidan con el username porque tiene que ser unique en la base de datos
						nuevoUsuario = new UsuarioEstandar(username,contrasenya,nombreReal,fechaAlta,calific);
						hashUsuarios.put(username, nuevoUsuario);
						principal.setHashUsuarios(hashUsuarios);
						DBManager myDBManager= new DBManager("Univook.db");//----------BASE DE DATOS -- Insertar en Usuarios
				        myDBManager.createLink();//----------
						myDBManager.insertUsuarios(hashUsuarios, username);//----------INSERTA
				        myDBManager.closeLink();//----------CIERRO BBDD
						cancelar();

					}catch(Exception e1){
						JOptionPane.showMessageDialog(Registro.this, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);//icono X rojo de error*/
					}
						catch (Exception e1) {
						JOptionPane.showMessageDialog(Registro.this, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);//icono X rojo de error
					}
				}
				else
				{
					JOptionPane.showMessageDialog(Registro.this, "Debes rellenar todas las casillas.","Error",JOptionPane.ERROR_MESSAGE);
				}
		}
		});

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		
		Registro.this.addWindowListener(new WindowAdapter() {//lo que hace al dar a la X
			   public void windowClosing(WindowEvent evt) {
			        int respuesta = JOptionPane.showOptionDialog(Registro.this, "�Est�s seguro de salir?","Confirmar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
			        if (respuesta == 0) 
			        {
			        	System.exit(0);//para que se cierre
			        }
			   	}
		});
		
	}
	
	public void setTextReal(String nomReal) {
		this.textReal.setText(nomReal);
	}

	public void setTextPasswordField(String pass) {
		this.passwordField.setText(pass); 
	}
	

	public boolean comprobarUsuario(String username,HashMap<String, Pelicula> hashUsuarios) throws Exception //static para acceder desde modificar si cambia nombre usuario
	{
		if(hashUsuarios.containsKey(username))
		{
			throw new Exception("Ese usuario ya existe. Pruebe con otro.");
		}
		return true;
		
	}
	
	public boolean comprobarPrimaryKey(String username,HashMap<String, Pelicula> hashUsuarios) throws Exception //static para acceder desde modificar si cambia nombre usuario
	{
		if(username.equals(passwordField.getText()))
		{
			throw new Exception("La contraseña y el nombre de usuario no pueden coincidir.");
		}
		else if(username.equals(textReal.getText()))
		{
			throw new Exception("El nombre real y el nombre de usuario no pueden coincidir.");
		}
		return true;
	}


	public void cancelar()
	{
		principal.setLocationRelativeTo(Registro.this);
		principal.setVisible(true);
		Registro.this.dispose();
	}
}
