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

import com.fasterxml.jackson.databind.JsonSerializable;
import org.json.simple.JSONArray;
import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        System.out.println(response);
        if (response.getStatus() == Status.OK.getStatusCode())
        {

            ArrayList<HashMap<String, Object>> datos = new ArrayList<>();
            JSONObject obj = response.readEntity(JSONObject.class);
            String stringobj = obj.toString(); //convertimos el JSONOBject a String

            String[] separado = stringobj.split("imdbID");
            for(int i=0;i<separado.length;i++) {
                separado[i] = separado[i].substring(3);
            }
            //aqui tenemos todos los strings empezando por el id

            for(int i=0;i<separado.length;i++) {
                String[] obtId = separado[i].split("\"");
                separado[i] = obtId[0];
                System.out.println(separado[i]+"\n");
            }
            //aqui ya hemos podido separar todos los ids.
            //a estas alturas del codigo String[] separado
            // tiene todos los ids de la peticion que se haya hecho

            //con esto, habria que hacer otra peticion y pasar el id como parametro en el enlace

            /*
            for(int i=0;i<obj.size();i++) {

                System.out.println(obj.get(i));

                datos.add((HashMap<String, Object>) obj.get(i)); //hay que mirar como recogemos esto
            //nos devuelve un arraylist(cantidad de peliculas) de hashmap
            }

             */
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
