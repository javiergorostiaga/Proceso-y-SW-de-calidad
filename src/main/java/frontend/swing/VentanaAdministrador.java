package frontend.swing;

import backend.objects.Pelicula;
import backend.objects.personas.Administrador;
import backend.objects.personas.Persona;
import frontend.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.ActionEvent;

import java.awt.Font;


public class VentanaAdministrador extends JFrame {

    private JPanel contentPane;
    private JTextField textoBusqueda;
    private HashMap<String, Pelicula> hashPeliculas;
    private JList <String> list;
    private Persona a;
    private Login ventanaAnterior;
    private VentanaPrincipal principal;
    private Controller controller;

    public Login getVentanaAnterior() {
        return ventanaAnterior;
    }

    public VentanaAdministrador(Login login, Persona a, HashMap<String,Pelicula> hashPeliculass, Controller controller)
    {
        this.controller = controller;
        this.hashPeliculas = hashPeliculass;
        setResizable(false);
        setTitle("Menu "+a.getNombreUsuario());
        ventanaAnterior=login;
        this.a=a;
        principal=login.getVentanaAnterior();
        this.hashPeliculas=principal.getHashPeliculas();
        //this.hashUsuarios=principal.getHashUsuarios();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 492, 283);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 236, 246);
        contentPane.add(panel);

        JButton btnAnlisisDatos = new JButton("Añadir Película");
        btnAnlisisDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Principal.exportData(controller, textoBusqueda.getText());
                    System.out.println("¡Película insertada!");
                    JOptionPane.showMessageDialog(null, "Se ha introducido la película en la BBDD.");
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(VentanaAdministrador.this, e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnAnlisisDatos.setBounds(36, 118, 141, 23);
        panel.add(btnAnlisisDatos);

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(36, 30, 141, 23);
        panel.add(btnCerrarSesion);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(220, 0, 271, 246);
        contentPane.add(panel_2);
        panel.setBackground(new Color(0, 112, 255));
        panel_2.setLayout(null);

        JLabel lblMenuAdministradores = new JLabel("PELÍCULA A AÑADIR:");
        lblMenuAdministradores.setFont(new Font("Arial Black", Font.PLAIN, 13));
        lblMenuAdministradores.setBounds(55, 16, 207, 21);
        panel_2.add(lblMenuAdministradores);

        textoBusqueda = new JTextField();
        textoBusqueda.setBounds(30, 100, 210, 25);
        panel_2.add(textoBusqueda);
        textoBusqueda.setColumns(21);

         btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    hashPeliculas = controller.selectPeliculass();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                login.setLocationRelativeTo(VentanaAdministrador.this);
                login.setVisible(true);
                VentanaAdministrador.this.dispose();
            }
        });
        VentanaAdministrador.this.addWindowListener(new WindowAdapter() {//lo que hace al dar a la X
            public void windowClosing(WindowEvent evt) {
                int respuesta = JOptionPane.showOptionDialog(VentanaAdministrador.this, "¿Estás seguro de salir?","Confirmar", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (respuesta == 0)
                {
                    System.exit(0);//para que se cierre
                }
            }
        });
    }

}


