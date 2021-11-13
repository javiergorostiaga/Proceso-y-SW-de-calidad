package backend.gateway;
import backend.gateway.IGateway;

import java.util.*;
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
import com.google.common.base.Splitter;
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

    public String makeGetRequest() throws Exception
    {

        WebTarget getRequestController = this.webTarget.path("");

        Invocation.Builder invocationBuilder = getRequestController.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        System.out.println(response);
        if (response.getStatus() == Status.OK.getStatusCode())
        {
            //ArrayList<HashMap<String, Object>> datos = new ArrayList<>(); // ya no se usa
            JSONObject obj = response.readEntity(JSONObject.class); //devuelve objeto json, el anyo pasado nos devolvia array json
            String stringobj = obj.toString(); //convertimos el JSONOBject a String
            System.out.println("STRINGOBJ"+stringobj);
            return stringobj; // esto habra que cambiar
        }
        else
        {
            throw new Exception("BadAss error");
        }
    }

    public String obtenerimdbID(String stringobj) //esto quizas no iria en esta clase
    {
        String[] separado = stringobj.split("imdbID"); // solo queremos el id en la primera consulta

        for(int i=0;i<separado.length;i++) {
            //System.out.println("Separado (" +i +"): "+ separado[i]+"\n\n\n");
            System.out.println("Separado (" +i +") Substring: "+ separado[i].substring(3)+"\n\n\n");
            separado[i] = separado[i].substring(3);
        }
        //aqui tenemos todos los strings empezando por el id
        for(int i=0;i<separado.length;i++) {
            String[] obtId = separado[i].split("\"");
            separado[i] = obtId[0];
            if(i==1)
            {
                break;
            }
            //System.out.println(separado[i]+"\n");
        }
        System.out.println("Separado (0)" + separado[1]);
        return separado[1];// el 0 no contiene el id todavia al ser la primera division por imdbid
    }

    public String obtenerDatosPelicula(String stringobj) //esto quizas no iria en esta clase
    {
        //AQUI SE TRATARAN LOS DATOS QUE VIENEN DE LA SEGUNDA CONSULTA
        //System.out.println(stringobj);

        //String test = "pet:cat::car:honda::location:Japan::food:sushi";
        //Map<String, String> map = Splitter.on( "::" ).withKeyValueSeparator( ':' ).split( test );

        //System.out.println("UNOOOO");
        //Iterable<String> iter = Splitter.on("\",").omitEmptyStrings().split(stringobj);

        String[] splitPrimero = stringobj.split(",\"Ratings");

        System.out.println("\n\nPRIMERA PARTE\n\n");
        System.out.println(Splitter.on("\",\"").omitEmptyStrings().withKeyValueSeparator("\":\"").split(splitPrimero[0].substring(0, splitPrimero[0].length() - 1).substring(2)));
        Map<String, String> mapa1 = Splitter.on("\",\"").omitEmptyStrings().withKeyValueSeparator("\":\"").split(splitPrimero[0].substring(0, splitPrimero[0].length() - 1).substring(2));

        System.out.println("\n\nSEGUNDA PARTE\n\n");
        System.out.println(Splitter.on("\",\"").omitEmptyStrings().withKeyValueSeparator("\":\"").split(splitPrimero[1].substring(0, splitPrimero[1].length()-2).substring(splitPrimero[1].indexOf("Runtime"))));
        Map<String, String> mapa2 = Splitter.on("\",\"").omitEmptyStrings().withKeyValueSeparator("\":\"").split(splitPrimero[1].substring(0, splitPrimero[1].length() - 2).substring(splitPrimero[1].indexOf("Runtime")));


        /*
        System.out.println("UNOOOO2");
        String[] splitSegundo;
        splitSegundo = splitPrimero[1].split(",\"Ratings");
        System.out.println(splitSegundo[1]);

        System.out.println("UNOOOO3");
        System.out.println(Splitter.on(',').omitEmptyStrings().withKeyValueSeparator(':').split(splitPrimero[0]));

         */

        /*
        Splitter.on('[').split(stringobj);
        iter.forEach();
        System.out.println("DOSSSS");
        //Map<String, String> map1 = Splitter.on(',').omitEmptyStrings().limit(14).withKeyValueSeparator(':').split(stringobj);
        System.out.println(Splitter.on(',').omitEmptyStrings().limit(14).withKeyValueSeparator(':').split(stringobj));

        System.out.println("TRESSSS");
        System.out.println(Splitter.on(',').omitEmptyStrings().withKeyValueSeparator(':').split(stringobj));

         */

        return "";
    }

    @Override
    public ArrayList<HashMap<String, Object>> exportPeliculaGenerico(String pelicula) throws Exception { //sera simplemente hashmap porque se devolvera solo 1 pelicula
        String url="https://www.omdbapi.com/?s="+pelicula+"&apikey=3ae17be9";
        this.url=url;
        accessPoint();
        String imdbID="";
        imdbID=obtenerimdbID(makeGetRequest());
        System.out.println("imdbID-------)"+imdbID);

        try
        {
            exportPeliculaEspecifico(imdbID); //solo cogemos una pelicula de todas las peliculas del mismo nombre
        }
        catch(Exception e)
        {
            System.out.println("Error al obtener información específica de película" +imdbID);
        }

        return null;
    }

    @Override
    public ArrayList<HashMap<String, Object>> exportPeliculaEspecifico(String imdbID) throws Exception {
        String url="https://www.omdbapi.com/?i="+imdbID+"&apikey=3ae17be9";
        this.url=url;
        accessPoint();
        String respuesta ="";
        respuesta=makeGetRequest();
        obtenerDatosPelicula(respuesta);
        //aqui se trata la segunda respuesta
        return null;
    }


}
