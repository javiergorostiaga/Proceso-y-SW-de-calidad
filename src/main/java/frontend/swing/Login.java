package frontend.swing;

import javax.swing.JFrame;

import javax.swing.JPanel;

import backend.objects.Pelicula;
import frontend.swing.*;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
	private HashMap <String, Pelicula> hashPeliculas;
	private VentanaPrincipal ventanaAnterior ;
	public static HashMap<String, Boolean> hashEsperar=new HashMap<>();
	
	public VentanaPrincipal getVentanaAnterior() {
		return ventanaAnterior;
	}


	public Login() throws HeadlessException {
		super();//lo uso para hacer el test con JUnit para no tener que pasar ning�n par�metro.
	}


	public Login(VentanaPrincipal principal)
	{
		setResizable(false);
		ventanaAnterior = principal;
		setTitle("Login");
		//this.hashPeliculas=principal.getHashPeliculas();
		
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
		
		lblNewLabel = new JLabel("New label");
		//NO FUNCIONA LA FOTO POR AHORA
		//lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/loginFoto.png")));
		lblNewLabel.setBounds(22, 62, 128, 130);
		panel.add(lblNewLabel);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = textUsuario.getText();
				if(hashEsperar.containsKey(usuario)&&hashEsperar.get(usuario))
				{
					JOptionPane.showMessageDialog(Login.this, "Ha agotado el maximo de intentos para este usuario. Pruebe otra vez en ");
				}
				else
				{
					boolean bien=true;

					//SOLO SE EJECUTA SI LO OTRO NO ES TRES
					if(bien)
					{
						String contrasenya=passUsuario.getText();
						try {
							boolean encontrado=comprobarUsuario(usuario, contrasenya,hashPeliculas);
							if(encontrado) {
								informacion(usuario);//------------------------------------
								}
						}
						catch(Exception e1) {
							JOptionPane.showMessageDialog(Login.this, e1.getMessage());//en vez de "No existe usuario" puedes poner e1.getMessage() para que te de el mensaje de la excepci�n
						}
					}
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


	public boolean comprobarUsuario(String usuario, String contrasenya, HashMap <String,Pelicula > hashPeliculas) throws Exception {//hay que avisar que este m�todo lanza excepciones
		if(!hashPeliculas.containsKey(usuario))
		{
			throw new Exception("Usuario no existente");//sale y ya no sigue el c�digo hacia abajo
		}
		else
		{
			String passDelHash=hashPeliculas.get(usuario).getTitle();//este get ensi es la contrasenya
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
		/*if(hashPeliculas.get(usuario) instanceof Administrador)//si es administrador le mando al menu de administradores de Univook
		{
			//VentanaAdministrador a = new VentanaAdministrador(Login.this,(Administrador)(hashUsuarios.get(usuario)));
			//a.setLocationRelativeTo(Login.this);
			//a.setVisible(true);
			//Login.this.setVisible(false);
		}
		else 
		{
			ThreadContar.detener(usuario);
			UsuarioMenu a = new UsuarioMenu((Usuario)(hashUsuarios.get(usuario)),Login.this);
			a.setLocationRelativeTo(Login.this);
			a.setVisible(true);
			Login.this.setVisible(false);
		}
		 */
	}
	
}
