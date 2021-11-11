package backend.dao;

import backend.objects.Genero;
import backend.objects.Pelicula;

import java.util.ArrayList;
import java.util.HashMap;

public class DAO implements IDao{
    @Override
    public void insertPeliculas(ArrayList<HashMap<String, Object>> contributors) {

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
