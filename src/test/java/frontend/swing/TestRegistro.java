package frontend.swing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import backend.objects.personas.Administrador;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;
import frontend.swing.Registro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestRegistro {
	
	private static Registro a;
	private static HashMap<String, Persona> hashUsuarios;
	private static Usuario user1;
	private static Usuario user2;
	private static Administrador admin;
	private static String username;


	@BeforeEach
	void setUp() throws Exception {
		a =new Registro();
		user1=new Usuario("aitorz99", "aitornado99", "Aitor Zatica");
		user2=new Usuario("l_asier_ra", "passAsier", "Asier Joker");
		admin=new Administrador("ADMIN", "superSecretPass");
		hashUsuarios=new HashMap<>();
		
		hashUsuarios.put("aitorz99", user1);
		hashUsuarios.put("l_asier_ra", user2);
		hashUsuarios.put("ADMIN", admin);
	}

	@Test
	void comprobarUsuario() {
		username="aitorz99";
		
		Assertions.assertThrows(Exception.class, () ->
		{
			a.comprobarUsuario(username, hashUsuarios);
		});

		username="userDiferente";
		try {
			assertTrue(a.comprobarUsuario(username, hashUsuarios));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void comprobarPrimaryKey() {
		
		username="EVA";
		
		a.setTextReal("EVA");
		a.setTextPasswordField("pass");
		Assertions.assertThrows(Exception.class, () -> 
		{
			a.comprobarPrimaryKey(username, hashUsuarios);
		});
		
		a.setTextReal("EVA STODULKA");
		a.setTextPasswordField("EVA");
		Assertions.assertThrows(Exception.class, () -> 
		{
			a.comprobarPrimaryKey(username, hashUsuarios);
		});

		a.setTextReal("EVA");
		Assertions.assertThrows(Exception.class, () -> 
		{
			a.comprobarPrimaryKey(username, hashUsuarios);
		});

		username="ejemplo";
		a.setTextReal("ejemplo1");
		a.setTextPasswordField("ejemplo1");
		try {
			assertTrue(a.comprobarPrimaryKey(username, hashUsuarios));
			a.setTextPasswordField("diferente");
			assertTrue(a.comprobarPrimaryKey(username, hashUsuarios));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
