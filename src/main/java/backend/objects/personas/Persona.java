package backend.objects.personas;

import lombok.Getter;
import lombok.Setter;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.io.Serializable;

/**
 * @class Persona
 * @brief La clase Persona
 */
@PersistenceCapable
public class Persona implements Serializable {

	@PrimaryKey
	@Getter	@Setter private String nombreUsuario;
	@Getter	@Setter private String password;

	public Persona(String nombreUsuario, String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

	public Persona() {
		super();
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
