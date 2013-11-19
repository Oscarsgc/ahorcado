import static org.junit.Assert.*;

import org.junit.Test;

public class TestDiccionario {
	Diccionario diccionario = new Diccionario();

	@Test
	public void testDiccionarioVacio() {
		assertEquals(0, diccionario.getListQty());
	}

	@Test
	public void testAgregarListaPorTipoEnDiccionario() {
		ListaDePalabras listaPalabras = new ListaDePalabras();
		diccionario.addList(listaPalabras);
		assertEquals(1, diccionario.getListQty());
	}
	
	@Test
	public void seleccionarPalabraAleatoria(){
		diccionario.llenarDiccionario();
		
		assertEquals("ToyStory", diccionario.palabraAleatoriaPorCategoria("Peliculas", 1).getPalabra());
	}

}
