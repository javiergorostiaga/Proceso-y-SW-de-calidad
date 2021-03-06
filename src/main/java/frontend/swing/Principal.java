package frontend.swing;

import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;
import frontend.controller.Controller;
import org.checkerframework.checker.units.qual.A;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Principal
{
    public static boolean exportData(final Controller controller, String pelicula) throws Exception
    {
        return controller.exportPelicula(pelicula); //lo que hace que se metan las peliculas por ahora
    }

    public static boolean exportUsuario(final Controller controller, Usuario usuario) throws Exception
    {
        return controller.exportUsuario(usuario); //lo que hace que se metan las peliculas por ahora
    }

    public static void selectPelicula(final Controller controller) throws  Exception{
        HashMap<String, Pelicula> hmPeliculas = controller.selectPeliculass();
        HashMap<String, Persona> hmUsuarios = controller.selectUsuarioss();
        VentanaPrincipal a = new VentanaPrincipal(controller, hmPeliculas, hmUsuarios);
        a.setLocationRelativeTo(null);//para que aparezca en el medio
        a.setVisible(true);
    }
}
