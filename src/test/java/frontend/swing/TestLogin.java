package frontend.swing;

import static org.junit.jupiter.api.Assertions.*;
import org.databene.contiperf.*;

import java.util.HashMap;

import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import backend.objects.personas.Administrador;
import backend.objects.personas.Persona;
import backend.objects.personas.Usuario;
import org.mockito.Mock;


class TestLogin {

	private static Login a;
	private static HashMap<String, Persona> hashUsuarios;
	private static Usuario user1;
	private static Usuario user2;
	private static Administrador admin;

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	@BeforeEach
	void setUp() throws Exception {
		a =new Login();
		user1=new Usuario("aitorz99", "aitornado99", "Aitor Zatica");
		user2=new Usuario("l_asier_ra", "passAsier", "Asier Joker");
		admin=new Administrador("admin", "passAdmin");
		hashUsuarios=new HashMap<>();
		
		hashUsuarios.put("aitorz99", user1);
		hashUsuarios.put("l_asier_ra", user2);
		hashUsuarios.put("ADMIN", admin);
	}


	@Test
	@PerfTest(invocations = 500, threads = 15)
	@Required(max = 1500, average = 200)
	void testExpectedException() {
		
		String usuarioReal="l_asier_ra";
		String contrasenaReal="passAsier";
	
		Assertions.assertThrows(Exception.class, () ->
		{
			a.comprobarUsuario(usuarioReal, "passIncorrecta", hashUsuarios);
		});
		
		Assertions.assertThrows(Exception.class, () ->
		{
			a.comprobarUsuario(usuarioReal, null, hashUsuarios);
		});
		
		Assertions.assertThrows(Exception.class, () ->
		{
			a.comprobarUsuario(null,contrasenaReal, hashUsuarios);
		});
		
		Assertions.assertThrows(Exception.class, () ->
		{
			a.comprobarUsuario("","", hashUsuarios);
		});
		
		Assertions.assertThrows(Exception.class, () ->
		{
			a.comprobarUsuario(null, null, hashUsuarios);
		});
	}
	
	@Test
	void testExpectedException1(){
		
		Assertions.assertThrows(NullPointerException.class, () -> 
		{
			a.comprobarUsuario("sdfgsdaf", "asgds", null);
		});
		
		Assertions.assertThrows(NullPointerException.class, () -> 
		{
			a.comprobarUsuario(null, null, null);
		});
	}

	@Test 
	void testExpectedException2() throws Exception{
		assertTrue(a.comprobarUsuario("l_asier_ra", "passAsier", hashUsuarios));
		assertTrue(a.comprobarUsuario("ADMIN", "passAdmin", hashUsuarios));
	}

	@Mock
	private Usuario userMock;

	@Test
	void testMockExpectedException1(){

		Assertions.assertThrows(NullPointerException.class, () ->
		{
			a.comprobarUsuario(userMock.getNombreUsuario(), userMock.getPassword(), null);
		});

		Assertions.assertThrows(NullPointerException.class, () ->
		{
			a.comprobarUsuario(null, null, null);
		});
	}
	
}
