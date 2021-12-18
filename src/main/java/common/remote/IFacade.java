package common.remote;

import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface IFacade extends Remote
{
    boolean exportPeliculas() throws Exception;
    HashMap<String, Pelicula> selectPeliculas() throws  Exception;

    HashMap<String, Persona> selectUsuarios() throws Exception;
   boolean exportUsuarios(Usuario usuario) throws Exception;

}
