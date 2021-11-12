package backend.gateway;
import backend.gateway.IGateway;

import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;

public class Gateway implements IGateway {

    private Client client;
    private WebTarget webTarget;
    private String url;

    public Gateway()
    {
        client = ClientBuilder.newClient();
    }

    public void accessPoint()
    {
        webTarget = client.target(url);
    }

    public ArrayList<HashMap<String, Object>> makeGetRequest() throws Exception
    {
        WebTarget getRequestController = this.webTarget.path("");
        Invocation.Builder invocationBuilder = getRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        if (response.getStatus() == Status.OK.getStatusCode())
        {
            ArrayList<HashMap<String, Object>> datos = new ArrayList<>();
            JSONArray jsonArray = response.readEntity(JSONArray.class);
            System.out.println(jsonArray);
            for(int i=0;i<jsonArray.size();i++) {
                System.out.println(jsonArray.get(i));
                datos.add((HashMap<String, Object>) jsonArray.get(i)); //hay que mirar como recogemos esto
            //nos devuelve un arraylist(cantidad de peliculas) de hashmap
            }
            return datos;
        }
        else
        {
            throw new Exception("BadAss error");
        }
    }

    @Override
    public ArrayList<HashMap<String, Object>> exportPeliculas(String url) throws Exception {
        this.url=url;
        accessPoint();
        return makeGetRequest();
    }

    //export generos necesitara primero del imdbid
    /*
    @Override
    public ArrayList<HashMap<String, Object>> exportGeneros(String url) throws Exception {
        this.url=url;
        accessPoint();
        return makeGetRequest();
    }
*/
}
