package frontend.swing;

import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;
import frontend.controller.Controller;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.HashMap;

public class VentanaUsuario extends JFrame
{
    private JPanel contentPane;
    private Login ventanaAnterior;
    private VentanaPrincipal principal;
    private HashMap<String, Pelicula> hashPeliculas;
    private Persona usuario;

    public Login getVentanaAnterior() {
            return ventanaAnterior;
    }

    public VentanaUsuario(Persona usuario, Login login, Controller controller) {
        setResizable(false);
        ventanaAnterior = login;
        principal = login.getVentanaAnterior();

        //si se quisiera llamar al hashMap de Usuarios tb así
        //la clase que contiene el hashMap es principal, las otras lo copian a un atributo privado
        this.usuario = usuario;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 481, 307);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public void cancelar()
    {
        principal.setLocationRelativeTo(VentanaUsuario.this);
        principal.setVisible(true);
        VentanaUsuario.this.dispose();
    }
}
