package backend.objects.personas;

import lombok.AllArgsConstructor;

import javax.jdo.annotations.PersistenceCapable;
import java.io.Serializable;

/**
 * @class Administrador
 * @brief La clase Administrador
 */
@PersistenceCapable
public class Administrador extends Persona implements Serializable
{
	public Administrador() {
		super();
	}

	public Administrador(String nombreUsuario, String password) {
		super(nombreUsuario, password);
	}

}
