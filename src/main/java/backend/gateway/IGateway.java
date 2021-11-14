package backend.gateway;

import java.util.ArrayList;
import java.util.HashMap;

public interface IGateway {
    HashMap<String, String> exportPeliculaGenerico(String pelicula) throws Exception;
    HashMap<String, String> exportPeliculaEspecifico(String imdbID) throws Exception;
    //ArrayList<HashMap<String, Object>> exportGeneros(String url) throws Exception;
}
