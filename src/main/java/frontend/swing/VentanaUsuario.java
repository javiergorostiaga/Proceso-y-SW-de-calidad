package frontend.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;


public class VentanaUsuario extends JFrame {

	private JPanel contentPane;
	private HashMap<String, Pelicula> hashPelic;
	private HashMap<String, Persona> hashUsuarios;
	private VentanaPrincipal principal;
	private String username;
	private String idPublic;
	private JTextPane textPaneLeer;
	private JButton btnDarPorVendido;
	private ArrayList<Integer> valoresOrdenar;
	private Usuario user;

	public VentanaUsuario(String usuario, Login login)
	{
		setResizable(false);
		username=usuario;
		principal=login.getVentanaAnterior();
		this.hashPelic =principal.getHashPeliculas();
		this.hashUsuarios=principal.getHashUsuarios();
		this.user=(Usuario)(hashUsuarios.get(username));

		setTitle("Menu "+usuario);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 680, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 245, 375);
		contentPane.add(panel);
		panel.setLayout(null);


		JLabel lblContenidoDeLa = new JLabel("Buscar Pelicula");
		lblContenidoDeLa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContenidoDeLa.setBounds(273, 199, 177, 20);
		contentPane.add(lblContenidoDeLa);

		JButton btnCerrarSes = new JButton("Volver");
		btnCerrarSes.setBounds(534, 330, 110, 29);
		contentPane.add(btnCerrarSes);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(273, 330, 110, 29);
		contentPane.add(btnOk);

		JTextPane textPaneEscribir = new JTextPane();
		textPaneEscribir.setBounds(297, 76, 279, 79);
		contentPane.add(textPaneEscribir);

		JScrollPane scrollPane = new JScrollPane(textPaneEscribir);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(273, 225, 371, 89);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBounds(273, 41, 371, 145);
		contentPane.add(scrollPane_1);

		textPaneLeer = new JTextPane();
		textPaneLeer.setEditable(false);
		textPaneLeer.setText((String) null);
		scrollPane_1.setViewportView(textPaneLeer);

		JLabel lblMensajes = new JLabel("Informaci\u00F3n");
		lblMensajes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensajes.setBounds(273, 16, 177, 20);
		contentPane.add(lblMensajes);

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textPaneEscribir.getText().isEmpty())
				{
					String pelicula=textPaneEscribir.getText();//en el hashMap el String entrada irá con saltos de línea... pero en el fichero no.
					int msg=JOptionPane.showConfirmDialog(VentanaUsuario.this, "¿Buscar Película?","Confirmar",JOptionPane.YES_NO_CANCEL_OPTION);

					if(msg==JOptionPane.YES_OPTION)
					{
						cargarTexto(pelicula);
						textPaneEscribir.setText("");//después de guardar se deja todo vacío por si quiere escribir otro mensaje

					}
				}
				else
				{
					JOptionPane.showMessageDialog(VentanaUsuario.this, "Se debe introducir información en todas las celdas","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCerrarSes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login.setLocationRelativeTo(VentanaUsuario.this);
				login.setVisible(true);
				VentanaUsuario.this.dispose();
			}
		});
		VentanaUsuario.this.addWindowListener(new WindowAdapter() {//lo que hace al dar a la X
			public void windowClosing(WindowEvent evt) {
				int respuesta = JOptionPane.showOptionDialog(VentanaUsuario.this, "¿Estás seguro de salir?","Confirmar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (respuesta == 0)
				{
					System.exit(0);//para que se cierre
				}
			}
		});
	}

	public void cargarTexto(String tituloPelic) {
		String texto = "";
		Iterator<String> it1 = hashPelic.keySet().iterator();
		HashMap<String, String[]> valores = new HashMap<>();
		valoresOrdenar = new ArrayList<>();//se meter todos los idMensajes de la conversación y se ordenan para saber cual fue enviado antes
		boolean existe = false;
		while (it1.hasNext()) {
			String idPelic = it1.next();//ID
			if (hashPelic.get(idPelic).getTitle().toUpperCase(Locale.ROOT).contains((tituloPelic).toUpperCase())) {
				existe = true;
				texto = "TITULO: " + hashPelic.get(idPelic).getTitle() + "\n\nGENERO: " + hashPelic.get(idPelic).getGenre() + "\n\nESCRITOR: " + hashPelic.get(idPelic).getWriter() + "\n\nDIRECTOR: " + hashPelic.get(idPelic).getDirector() + "\n\nRATING: " + hashPelic.get(idPelic).getImdbRating() + "\n\nARGUMENTO: " + hashPelic.get(idPelic).getPlot() + "\n\nDURACION: " + hashPelic.get(idPelic).getRuntime() + "\n\nPREMIOS: " + hashPelic.get(idPelic).getAwards() + "\n\nACTORES: " + hashPelic.get(idPelic).getActors() + "\n\nIDIOMA: " + hashPelic.get(idPelic).getLanguage() + "\n\nAÑO: " + hashPelic.get(idPelic).getReleased();
				textPaneLeer.setText(texto);
			}
		}
		if (existe == false) {
			JOptionPane.showMessageDialog(VentanaUsuario.this, "No tenemos constancia de esa pelicula");
		}
	}
}
