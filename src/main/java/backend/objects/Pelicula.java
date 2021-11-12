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
    @Getter @Setter private int id;
    @Getter @Setter private String id_imdb;
    @Getter @Setter private String nombre;
    //@Getter @Setter private String poster_img;
    @Getter @Setter private String genero;
    @Getter @Setter private String descripcion;
    @Getter @Setter private int anyo;
    @Getter @Setter private int puntuacion;

    @Persistent
    @Order(extensions=@Extension(vendorName="datanucleus", key="list-ordering", value="id ASC"))
    @Getter @Setter private Collection<Genero> generos;
}
