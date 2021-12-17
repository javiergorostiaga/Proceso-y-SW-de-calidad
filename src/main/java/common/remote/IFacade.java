package common.remote;

import backend.objects.Pelicula;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface IFacade extends Remote
{
    boolean exportPeliculas() throws Exception;
    HashMap<String, Pelicula> selectPeliculas() throws  Exception;
}
