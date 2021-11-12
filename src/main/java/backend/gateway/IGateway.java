package backend.gateway;

import java.util.ArrayList;
import java.util.HashMap;

public interface IGateway {
    ArrayList<HashMap<String, Object>> exportPeliculas(String url) throws Exception;
    //ArrayList<HashMap<String, Object>> exportGeneros(String url) throws Exception;
}
