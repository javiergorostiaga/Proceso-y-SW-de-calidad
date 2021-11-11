package backend.gateway;
import backend.gateway.IGateway;

import java.util.ArrayList;
import java.util.HashMap;

public class Gateway implements IGateway {
    public Gateway() {

    }

    @Override
    public ArrayList<HashMap<String, Object>> exportPeliculas(String url) throws Exception {
        return null;
    }

    @Override
    public ArrayList<HashMap<String, Object>> exportGeneros(String url) throws Exception {
        return null;
    }
}
