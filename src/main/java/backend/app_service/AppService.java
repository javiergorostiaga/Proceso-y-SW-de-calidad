package backend.app_service;

import backend.dao.DAO;
import backend.dao.IDao;
import backend.gateway.Gateway;
import backend.gateway.IGateway;
import backend.objects.Pelicula;

import java.util.ArrayList;
import java.util.HashMap;

public class AppService  {
    IGateway gateway;
    IDao dao;
    HashMap<String, String> respuesta;
    HashMap<String, Pelicula> hmPelicula;

    public AppService() {
        gateway = new Gateway();
    }

    public boolean exportPeliculas() throws Exception {
        try
        {
            dao=new DAO();
            respuesta = gateway.exportPeliculaGenerico("batman"); //aqui nombres de peliculas
            // vamos a empezar solo viendo si funciona con una pelicula
            // luego metemos muchas con un loop desde fichero
            dao.insertPelicula(respuesta);
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        return true; //si los datos son exportados devuelve true
    }

    public HashMap<String, Pelicula> selectPeliculas() throws Exception {
        try
        {
            dao=new DAO();
            hmPelicula=dao.selectPeliculas();
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        return hmPelicula; //si los datos son exportados devuelve true
    }

    /*
    public boolean exportGeneros() throws Exception {
        try
        {
            dao=new DAO();
            respuesta = gateway.exportGeneros(""); //RELLENAR URL
            dao.insertGeneros(respuesta);
        } catch (Exception e){

        }
        return true; //si los datos son exportados devuelve true
    }
    */

}
