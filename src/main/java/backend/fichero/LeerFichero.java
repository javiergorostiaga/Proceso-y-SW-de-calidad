package backend.fichero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerFichero {


    public static ArrayList<String> cargarPeliculas() throws IOException {
        String cadena;
        ArrayList<String> ret = new ArrayList<>();

        FileReader f = new FileReader("/peliculas.txt");
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null){
            ret.add(cadena);
        }
        b.close();
        String a = ret.get(0);
        System.out.println(a);

        return ret;
    }
}
