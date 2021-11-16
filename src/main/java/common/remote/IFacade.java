package common.remote;

import backend.objects.Pelicula;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;

public interface IFacade extends Remote
{
    boolean exportPeliculas() throws Exception;
    ArrayList<Pelicula> selectPeliculas() throws  Exception;
}
