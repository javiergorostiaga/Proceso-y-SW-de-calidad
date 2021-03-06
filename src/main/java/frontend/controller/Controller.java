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

    public HashMap<String, Pelicula> selectPeliculass() throws Exception
    {
        HashMap<String, Pelicula> hmPeliculaController = new HashMap<String, Pelicula>();
        hmPeliculaController =serviceLocator.getService().selectPeliculas();
        // DA ERROR LA ANTERIOR LÍNEA, PERO NO POR EL DAO (LO HE PROBADO)
        return hmPeliculaController;
    }

    public boolean exportPelicula(String pelicula) throws Exception
    {
        return serviceLocator.getService().exportPeliculas(pelicula);
    }

    public HashMap<String, Persona> selectUsuarioss() throws Exception
    {
        HashMap<String, Persona> hmUsuarioController = new HashMap<String, Persona>();
        hmUsuarioController = serviceLocator.getService().selectUsuarios();
        return hmUsuarioController;
    }

    public  boolean exportUsuario(Usuario usuario) throws Exception
    {
        return serviceLocator.getService().exportUsuarios(usuario);
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
