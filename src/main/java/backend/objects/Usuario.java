package backend.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.io.Serializable;

@PersistenceCapable
@AllArgsConstructor
public class Usuario implements Serializable
{
    @PrimaryKey
    @Getter @Setter private int id;
    @Getter @Setter private String username;
    @Getter @Setter private String pass;

}