package backend.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
@AllArgsConstructor
public class Usuario
{
    @PrimaryKey
    @Getter @Setter private int id;
    @Getter @Setter private String username;
    @Getter @Setter private String pass;

}