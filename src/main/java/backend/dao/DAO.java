package backend.dao;

import backend.objects.Genero;
import backend.objects.Pelicula;


import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;
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
    public void insertUsuario(Usuario usuario) {
        try
        {
            this.transaction.begin();

            this.persistentManager.makePersistent(
                    new Usuario(
                            usuario.getNombreUsuario(),
                            usuario.getPassword(),
                            usuario.getNombreReal()
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
    public HashMap<String, Pelicula> selectPeliculas() {
        HashMap<String, Pelicula> peliculas = new HashMap<String, Pelicula>();
        try
        {
            System.out.println("Llega a DAO (Películas)");
            this.transaction.begin();

            Query<Pelicula> peliculaQuery = this.persistentManager.newQuery("SELECT FROM " +Pelicula.class.getName());
            for (Pelicula pelicula : peliculaQuery.executeList())
            {
                peliculas.put(pelicula.getImdbID(), pelicula);
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
       // System.out.println("Array DAO ------)"+ ((ArrayList<Pelicula>)peliculas).get(0).getActors());
        return peliculas; //ahora solo devolvera una pelicula
    }

    @Override
    public HashMap<String, Persona> selectUsuarios() {
        HashMap<String, Persona> usuarios = new HashMap<String, Persona>();

        try {
            System.out.println("Llega a DAO (Usuarios)");
            this.transaction.begin();

            Query<Persona> usuarioQuery = this.persistentManager.newQuery("SELECT FROM " +Persona.class.getName());
            for(Persona usuario : usuarioQuery.executeList()) {
                usuarios.put(usuario.getNombreUsuario(), usuario);
            }
            this.transaction.commit();

        } catch (Exception ex){
            System.err.println("* Exception selecting data from db: " + ex.getMessage());
        }

        finally {
            if (this.transaction.isActive())
            {
                this.transaction.rollback();
            }

            this.persistentManager.close();
        }

        return usuarios;
    }

    @Override
    public ArrayList<Genero> selectGeneros() {
        return null;
    }
}
