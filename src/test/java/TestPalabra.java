import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class TestPalabra {
	Palabra palabra;

	@Before
	public void palabraPrueba(){
		palabra = new Palabra("Prueba");
	}

	@Test
	public void getPalabra() {
		assertEquals("Prueba", palabra.getPalabra());
	}

	@Test
	public void setPalabra() {
		palabra.setPalabra("Pruebita");
		assertEquals("Pruebita", palabra.getPalabra());
	}

	@Test
	public void buscarLetraDevuelvePosicionSiExiste() {
		assertEquals(4, palabra.buscarLetra('b'));
	}

	@Test
	public void buscarLetraDevuelveNegativoSiNOExiste() {
		assertEquals(-1, palabra.buscarLetra('x'));
	}

	@Test
	public void dibujarPalabraVacia() {
		assertEquals("_ _ _ _ _ _ ", palabra.dibujarPalabraVacia());
	}

}
