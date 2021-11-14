package backend.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.datanucleus.*;
import java.util.Collection;

import javax.jdo.annotations.*;

@PersistenceCapable
@AllArgsConstructor
public class Pelicula

{
    @PrimaryKey
    @Getter @Setter private String imdbID;
    @Getter @Setter private String title;
    @Getter @Setter private String plot;
    @Getter @Setter private String boxOffice; //dinero
    @Getter @Setter private String actors; //nuevo objeto¿?
    @Getter @Setter private String imdbRating;
    @Getter @Setter private String imdbVotes;
    @Getter @Setter private String runtime; //la duracion
    @Getter @Setter private String type; //movie_series por ahora todos los metemos en peliculas
    @Getter @Setter private String awards;
    @Getter @Setter private String language;
    @Getter @Setter private int year;
    @Getter @Setter private String released;
    @Getter @Setter private String poster;
    @Getter @Setter private String genre; //por ahora metemos genero como atributo
    @Getter @Setter private String writer;
    @Getter @Setter private String director;

    @Persistent
    @Order(extensions=@Extension(vendorName="datanucleus", key="list-ordering", value="id ASC"))
    @Getter @Setter private Collection<Genero> generos;

    public Pelicula(String imdbID, String title, String plot, String boxOffice, String actors, String imdbRating, String imdbVotes, String runtime, String type, String awards, String language, int year, String released, String poster, String genre, String writer, String director) {
        this.imdbID = imdbID;
        this.title = title;
        this.plot = plot;
        this.boxOffice = boxOffice;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.runtime = runtime;
        this.type = type;
        this.awards = awards;
        this.language = language;
        this.year = year;
        this.released = released;
        this.poster = poster;
        this.genre = genre;
        this.writer = writer;
        this.director = director;
    }
}