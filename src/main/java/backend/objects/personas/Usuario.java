package backend.objects.personas;


import lombok.Getter;
import lombok.Setter;

import javax.jdo.annotations.PersistenceCapable;
import java.io.Serializable;

/**
 * @class Usuario
 * @brief La clase usuario
 */
@PersistenceCapable
public class Usuario extends Persona implements Serializable
{
	@Getter	@Setter private String nombreReal;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombreUsuario, String password) {
		super(nombreUsuario, password);
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombreUsuario, String password, String nombreReal) {
		super(nombreUsuario, password);
		this.nombreReal = nombreReal;
	}

	public String getNombreReal() { return nombreReal; }

	public void setNombreReal(String nombreReal) { this.nombreReal = nombreReal; }

}













