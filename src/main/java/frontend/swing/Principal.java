package frontend.swing;

import backend.objects.Pelicula;
import frontend.controller.Controller;
import org.checkerframework.checker.units.qual.A;


import java.util.ArrayList;

public class Principal
{
    public static boolean exportData(final Controller controller) throws Exception
    {
        return controller.exportPeliculas(); //lo que hace que se metan las peliculas por ahora
    }

    public static void selectPelicula(final Controller controller) throws  Exception{
        System.out.println("CLASE PRINCIPAL");
        controller.selectPeliculas();
        //System.out.println(arrayPeliculas.get(0).getActors());


    }

}
