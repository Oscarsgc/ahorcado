import static org.junit.Assert.*;

import org.junit.Test;

public class TestPalabra {
	Palabra palabra = new Palabra();

	@Test
	public void crearPalabraNivelFacil() {
		palabra = new Palabra("Prueba", 0, false);
		assertEquals(0, palabra.getDificultad());
	}

	@Test
	public void crearPalabraNivelMedio() {
		palabra = new Palabra("Prueba", 1, false);
		assertEquals(1, palabra.getDificultad());
	}

	@Test
	public void crearPalabraNivelDificil() {
		palabra = new Palabra("Prueba", 2, false);
		assertEquals(2, palabra.getDificultad());
	}

	@Test
	public void buscarLetraEnPalabra() {
		palabra = new Palabra("Prueba", 2, false);
		assertEquals(true, palabra.buscarLetra('b'));
		assertEquals(false, palabra.buscarLetra('x'));
	}

	@Test
	public void dibujarEspaciosParaPalabra() {
		palabra = new Palabra("Prueba", 2, false);
		assertEquals("_ _ _ _ _ _ ", palabra.dibujarPalabraVacia());
	}

	@Test
	public void dibujarLetraSiExiste() {
		palabra = new Palabra("Prueba", 2, false);
		assertEquals(
				"_ r _ _ _ _ ",
				palabra.buscarLetraEnPalabra('r', palabra.getPalabra(),
						palabra.dibujarPalabraVacia()));
	}
	
	@Test
	public void errorSiLetraNoExiste() {
		palabra = new Palabra("Prueba", 2, false);
		assertEquals(
				"Letra no encontrada!",
				palabra.buscarLetraEnPalabra('x', palabra.getPalabra(),
						palabra.dibujarPalabraVacia()));
	}
}
