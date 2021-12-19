package backend.app_service;

import backend.dao.DAO;
import backend.dao.IDao;
import backend.gateway.Gateway;
import backend.gateway.IGateway;
import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class AppService  {
    IGateway gateway;
    IDao dao;
    HashMap<String, String> respuesta;
    HashMap<String, Pelicula> hmPelicula;
    HashMap<String, Persona> hmUsuario;

    public AppService() {
        gateway = new Gateway();
    }

    public boolean exportPeliculas(String pelicula) throws Exception {
        try
        {
            dao=new DAO();
            respuesta = gateway.exportPeliculaGenerico(pelicula);
            dao.insertPelicula(respuesta);
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        return true; //si los datos son exportados devuelve true
    }

    public boolean exportUsuarios(Usuario usuario) throws Exception {
        try
        {
            dao=new DAO();
            dao.insertUsuario(usuario);
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

    public HashMap<String, Persona> selectUsuarios() throws Exception {
        try {
            dao=new DAO();
            hmUsuario=dao.selectUsuarios();
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }

        return hmUsuario;
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
