package backend.dao;

import backend.objects.Genero;
import backend.objects.Pelicula;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDao {
    void insertPelicula(HashMap<String, String> pelicula);
    void insertGeneros(ArrayList<HashMap<String, Object>> generos);
    HashMap<String, Pelicula> selectPeliculas();
    HashMap<String, Persona> selectUsuarios();
    ArrayList<Genero> selectGeneros();
   void insertUsuario(Usuario usuario);
}
