package backend.dao;

import backend.objects.Genero;
import backend.objects.Pelicula;


import org.json.simple.JSONObject;

import javax.jdo.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class DAO implements IDao
{
    private PersistenceManagerFactory persistentManagerFactory;
    private PersistenceManager persistentManager;
    private Transaction transaction;

    public DAO()
    {
        try
        {
            this.persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
            //Insert data in the DB
            this.persistentManager = this.persistentManagerFactory.getPersistenceManager();
            this.transaction = this.persistentManager.currentTransaction();
        }
        catch (Exception ex)
        {
            System.err.println("* Exception: " + ex.getMessage());
        }
    }

    @Override
    public void insertPelicula(HashMap<String, String> hashPelicula) {
        try
        {
            this.transaction.begin();
            //cuando mandemos un arraylist de varias peliculas haremos for
            //for(int i=0;i<hashPelicula.size();i++)
            //{
                this.persistentManager.makePersistent(
                        new Pelicula(
                                hashPelicula.get("imdbID"),
                                hashPelicula.get("Title"),
                                hashPelicula.get("Plot"),
                                hashPelicula.get("BoxOffice"),
                                hashPelicula.get("Actors"), //*
                                hashPelicula.get("imdbRating"),
                                hashPelicula.get("imdbVotes"),
                                hashPelicula.get("Runtime"),
                                hashPelicula.get("Type"),
                                hashPelicula.get("Awards"),
                                hashPelicula.get("Language"), //*
                                Integer.parseInt(hashPelicula.get("Year")),
                                hashPelicula.get("Released"),
                                hashPelicula.get("Poster"),
                                hashPelicula.get("Genre"), //*
                                hashPelicula.get("Writer"), //* si hay varios se meten como string separados por coma
                                hashPelicula.get("Director")
                        ));
            //}
            this.transaction.commit();
        }
        catch(Exception ex)
        {
            System.err.println("* Exception inserting data into db: " + ex.getMessage());
        }

        finally
        {
            if (this.transaction.isActive())
            {
                this.transaction.rollback();
            }

            this.persistentManager.close();
        }
    }

    @Override
    public void insertGeneros(ArrayList<HashMap<String, Object>> genero) {

    }

    @Override
    public ArrayList<Pelicula> selectPeliculas() {
        Collection<Pelicula> peliculas = null;
        try
        {
            this.transaction.begin();

            Query<Pelicula> contributorQuery = this.persistentManager.newQuery("SELECT FROM " +Pelicula.class.getName());

            peliculas = new ArrayList<>();
            for (Pelicula pelicula : contributorQuery.executeList())
            {
                peliculas.add(pelicula);
            }
            this.transaction.commit();
        }
        catch(Exception ex)
        {
            System.err.println("* Exception selecting data from db: " + ex.getMessage());
        }

        finally
        {
            if (this.transaction.isActive())
            {
                this.transaction.rollback();
            }

            this.persistentManager.close();
        }
        System.out.println("Array Peliculas ------)"+ ((ArrayList<Pelicula>) peliculas).get(0));
        return (ArrayList<Pelicula>) peliculas; //ahora solo devolvera una pelicula
    }

    @Override
    public ArrayList<Genero> selectGeneros() {
        return null;
    }
}
