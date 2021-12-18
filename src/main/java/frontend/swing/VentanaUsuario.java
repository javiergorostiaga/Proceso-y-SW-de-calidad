package frontend.swing;

import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;

import javax.swing.JFrame;
import java.util.HashMap;

public class VentanaUsuario extends JFrame
{
    private Login ventanaAnterior;
    private VentanaPrincipal principal;
    private HashMap<String, Pelicula> hashPeliculas;
    private Persona usuario;

    public Login getVentanaAnterior() {
            return ventanaAnterior;

    }

    public VentanaUsuario(Persona usuarioo, Login login) {
        setResizable(false);
        ventanaAnterior = login;
        principal = login.getVentanaAnterior();

        hashPeliculas = principal.getHashPeliculas();
        //si se quisiera llamar al hashMap de Usuarios tb así
        //la clase que contiene el hashMap es principal, las otras lo copian a un atributo privado
        usuario = usuarioo;
    }
}
