package backend.dao;

import backend.objects.Genero;
import backend.objects.Pelicula;


import org.json.simple.JSONObject;

import javax.jdo.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class DAO implements IDao{
    @Override
    public void insertPelicula(HashMap<String, String> pelicula) {

    }

    @Override
    public void insertGeneros(ArrayList<HashMap<String, Object>> repositories) {

    }

    @Override
    public ArrayList<Pelicula> selectPeliculas() {
        return null;
    }

    @Override
    public ArrayList<Genero> selectGeneros() {
        return null;
    }
}
