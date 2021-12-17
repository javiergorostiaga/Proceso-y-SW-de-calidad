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
    public static boolean exportData(final Controller controller) throws Exception
    {
        return controller.exportPeliculas(); //lo que hace que se metan las peliculas por ahora
    }

    public static void selectPelicula(final Controller controller) throws  Exception{
        System.out.println("CLASE PRINCIPAL");

        //HashMap<String, Pelicula> hmPelis = controller.selectPeliculas();
        // ESTO TIENE QUE SER UN HASHMAP DE <IDPELICULA (UN STRING), PELICULA>
        // AQUÍ TB SE TIENE QUE HACER UN SELECT DE LOS USUARIOS Y MANDAR EL HASHMAP DE PELICULAS Y EL DE USUARIOS A VENTANA PRINCIPAL

        HashMap<String, Persona> hmUsuarios = controller.selectUsuarios();

        VentanaPrincipal a = new VentanaPrincipal(null, hmUsuarios);
        a.setLocationRelativeTo(null);//para que aparezca en el medio
        a.setVisible(true);
    }

}
//junit contiperf
//junit mockito...