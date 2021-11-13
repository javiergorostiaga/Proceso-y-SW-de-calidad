package backend.gateway;

import java.util.ArrayList;
import java.util.HashMap;

public interface IGateway {
    ArrayList<HashMap<String, Object>> exportPeliculaGenerico(String pelicula) throws Exception;
    ArrayList<HashMap<String, Object>> exportPeliculaEspecifico(String imdbID) throws Exception;
    //ArrayList<HashMap<String, Object>> exportGeneros(String url) throws Exception;
}
