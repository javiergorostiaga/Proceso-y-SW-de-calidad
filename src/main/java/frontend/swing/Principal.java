package frontend.swing;

import backend.objects.Pelicula;
import frontend.controller.Controller;


import java.util.ArrayList;

public class Principal
{
    public static boolean exportData(final Controller controller) throws Exception
    {
        return controller.exportPeliculas();
    }

    public static void selectPelicula(final Controller controller) throws  Exception{
        ArrayList<Pelicula> pelicualSelecionada = controller.selectPeliculas();

        System.out.println(pelicualSelecionada.size());


    }

}
