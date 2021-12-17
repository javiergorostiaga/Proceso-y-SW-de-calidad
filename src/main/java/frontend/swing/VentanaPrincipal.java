package frontend.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.applet.AudioClip;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import frontend.swing.*;



public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private HashMap<String, Pelicula> hashPeliculas;
	private HashMap <String, Persona> hashUsuarios;

	public VentanaPrincipal(HashMap<String,Pelicula> hashPeliculass, HashMap<String,Persona> hashUsuarioss)
	{
		this.hashPeliculas=hashPeliculass;
		this.hashUsuarios=hashUsuarioss;

		setTitle("OMDB");
		setResizable(false);//para que no se pueda agrandar	
		VentanaPrincipal.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//-------para que no se cierre y defina yo que hacer
		setBounds(100, 100, 639, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMenuPrincipal = new JLabel("\u00A1BIENVENIDO!");
		lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuPrincipal.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblMenuPrincipal.setBounds(271, 49, 168, 37);
		contentPane.add(lblMenuPrincipal);
		
		JButton opcionesUsuarios = new JButton("Iniciar Sesi\u00F3n");
		opcionesUsuarios.setBounds(320, 136, 199, 37);
		contentPane.add(opcionesUsuarios);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 244, 370);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 244, 370);
		panel.add(label);
		//NO FUNCIONA LA FOTO POR AHORA
		//label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondo.jpg")));
		
		//---------------------INICIAR SESIN---------------------
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registro a=new Registro(VentanaPrincipal.this);
				a.setLocationRelativeTo(VentanaPrincipal.this);
				a.setVisible(true);
				VentanaPrincipal.this.setVisible(false);
			}
		});
		btnRegistrarse.setBounds(320, 216, 199, 37);
		contentPane.add(btnRegistrarse);
		
		opcionesUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login a=new Login(VentanaPrincipal.this);
				a.setLocationRelativeTo(VentanaPrincipal.this);
				a.setVisible(true);
				VentanaPrincipal.this.setVisible(false);
			}
		});
		
		VentanaPrincipal.this.addWindowListener(new WindowAdapter() {//lo que hace al dar a la X
			   public void windowClosing(WindowEvent evt) {
			       int respuesta = JOptionPane.showOptionDialog(VentanaPrincipal.this, "¿Estas seguro de salir?","Confirmar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
			       if (respuesta == 0) 
			       {
			    	   System.exit(0);
			       }
			   }
		});
	}

	public HashMap<String, Persona> getHashUsuarios() {
		return hashUsuarios;
	}

	public void setHashUsuarios(HashMap<String, Persona> hashUsuarios) {
		this.hashUsuarios = hashUsuarios;
	}

	public HashMap<String, Pelicula> getHashPeliculas() {
		return hashPeliculas;
	}


	public void setHashPeliculas(HashMap<String, Pelicula> hashPeliculas) {
		this.hashPeliculas = hashPeliculas;
	}


}
