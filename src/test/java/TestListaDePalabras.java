import static org.junit.Assert.*;

import org.junit.Test;

public class TestListaDePalabras {
	ListaDePalabras listaPalabras = new ListaDePalabras();

	@Test
	public void testCrearListaVacia() {
		assertEquals(0, listaPalabras.getCantidadPalabras());
	}

	@Test
	public void testAgregarPalabraALista() {
		listaPalabras.agregarPalabra(new Palabra("Hola"));
		assertEquals(1, listaPalabras.getCantidadPalabras());
	}

	@Test
	public void testCrearListaConNombreVacia() {
		listaPalabras = new ListaDePalabras("Prueba");
		assertEquals(0, listaPalabras.getCantidadPalabras());
		assertEquals("Prueba", listaPalabras.getCategoria());
	}

	@Test
	public void eliminarPalabraXDeLista() {
		listaPalabras.agregarPalabra(new Palabra("X"));
		listaPalabras.agregarPalabra(new Palabra("Y"));
		listaPalabras.agregarPalabra(new Palabra("Z"));
		listaPalabras.eliminarPalabra(new Palabra("Z"));
		assertEquals(2, listaPalabras.getCantidadPalabras());
	}

	@Test
	public void mostrarPalabrasDeLista() {
		listaPalabras.agregarPalabra(new Palabra("X"));
		listaPalabras.agregarPalabra(new Palabra("Y"));
		listaPalabras.agregarPalabra(new Palabra("Z"));
		assertEquals("X" + "Y" + "Z", listaPalabras.mostrarLista());
	}

	@Test
	public void buscarPalabraXEnLista() {
		listaPalabras.agregarPalabra(new Palabra("V"));
		listaPalabras.agregarPalabra(new Palabra("W"));
		listaPalabras.agregarPalabra(new Palabra("X"));
		listaPalabras.agregarPalabra(new Palabra("Y"));
		listaPalabras.agregarPalabra(new Palabra("Z"));
		assertEquals(2, listaPalabras.buscarPalabra(new Palabra("X")));
	}

}
