package frontend.controller;

import java.util.*;

import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;
import frontend.swing.Principal;
import frontend.remote.RMIServiceLocator;

import common.properties.PropertyManager;
import common.properties.AppPropertyManager;

public class Controller
{
    private RMIServiceLocator serviceLocator = null;

    public Controller(String[] args) throws Exception
    {
        PropertyManager apm = new AppPropertyManager();
        Properties p = apm.getProperties();
        serviceLocator = new RMIServiceLocator();
        serviceLocator.setService(p.getProperty("ip"),p.getProperty("port"),p.getProperty("serviceName"));
        //new Principal(this);
        //Principal.exportData(this);

        Principal.selectPelicula(this);
    }

    public boolean exportPeliculas() throws Exception
    {
        return serviceLocator.getService().exportPeliculas();
    }

    public HashMap<String, Pelicula> selectPeliculas() throws Exception
    {
        HashMap<String, Pelicula> arrayPeliculaController = new HashMap<String, Pelicula>();
        //ArrayList<Pelicula> arrayPeliculaController =serviceLocator.getService().selectPeliculas();
        //System.out.println(arrayPeliculaController.get(0).getActors());
        return arrayPeliculaController;
    }

    public HashMap<String, Persona> selectUsuarios() throws Exception
    {
        HashMap<String, Persona> arrayUsuarioController = new HashMap<String, Persona>();
        return arrayUsuarioController;
    }

    public void exit()
    {
        System.exit(0);
    }

    public static void main(String[] args) throws Exception
    {
        new Controller(args);
    }
}
