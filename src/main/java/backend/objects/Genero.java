package backend.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import javax.jdo.annotations.*;

@PersistenceCapable
@AllArgsConstructor
public class Genero implements Serializable
{
    @PrimaryKey
    @Getter
    @Setter
    private int id;
    @Getter @Setter private String nombre;

    @Persistent(table="GENEROS_PELICULAS")
    @Join(column="GENERO_ID")
    @Element(column="PELICULA_ID")
    @Order(extensions=@Extension(vendorName="datanucleus", key="list-ordering", value="id ASC"))
    @Getter @Setter private Collection<Pelicula> peliculas;
}