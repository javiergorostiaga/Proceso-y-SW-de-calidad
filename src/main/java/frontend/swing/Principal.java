package frontend.swing;

import backend.objects.Pelicula;
import frontend.controller.Controller;
import org.checkerframework.checker.units.qual.A;


import java.io.Serializable;
import java.util.ArrayList;

public class Principal
{
    public static boolean exportData(final Controller controller) throws Exception
    {
        return controller.exportPeliculas(); //lo que hace que se metan las peliculas por ahora
    }

    public static void selectPelicula(final Controller controller) throws  Exception{
        System.out.println("CLASE PRINCIPAL");

        controller.selectPeliculas(); // ESTO TIENE QUE SER UN HASHMAP DE <IDPELICULA (UN STRING), PELICULA>
        // AQUÍ TB SE TIENE QUE HACER UN SELECT DE LOS USUARIOS Y MANDAR EL HASHMAP DE PELICULAS Y EL DE USUARIOS A VENTANA PRINCIPAL

        VentanaPrincipal a = new VentanaPrincipal(null,null);
        a.setLocationRelativeTo(null);//para que aparezca en el medio
        a.setVisible(true);


    }

}
//junit contiperf
//junit mockito...