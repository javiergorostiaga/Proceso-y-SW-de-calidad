package backend.app_service;

import backend.dao.DAO;
import backend.dao.IDao;
import backend.gateway.Gateway;
import backend.gateway.IGateway;

import java.util.ArrayList;
import java.util.HashMap;

public class AppService {
    IGateway gateway;
    IDao dao;
    ArrayList<HashMap<String, Object>> respuesta;

    public AppService() {
        gateway = new Gateway();
    }

    public boolean exportPeliculas() throws Exception {
        try
        {
            dao=new DAO();
            respuesta = gateway.exportPeliculas(""); //rellenar URL
            dao.insertPeliculas(respuesta);
        } catch (Exception e){

        }
        return true; //si los datos son exportados devuelve true
    }

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
}
