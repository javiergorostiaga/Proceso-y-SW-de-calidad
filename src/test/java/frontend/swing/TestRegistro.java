package frontend.swing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import backend.objects.personas.Administrador;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;
import frontend.swing.Registro;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
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

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

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
	@PerfTest(invocations = 500, threads = 15)
	@Required(max = 1500, average = 200)
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
