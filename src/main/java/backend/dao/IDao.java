package backend.dao;

import backend.objects.Genero;
import backend.objects.Pelicula;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDao {
    void insertPeliculas(ArrayList<HashMap<String, Object>> peliculas);
    void insertGeneros(ArrayList<HashMap<String, Object>> generos);
    ArrayList<Pelicula> selectPeliculas();
    ArrayList<Genero> selectGeneros();
}
