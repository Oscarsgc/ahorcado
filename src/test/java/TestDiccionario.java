import static org.junit.Assert.*;

import org.junit.Test;

public class TestDiccionario {
	Diccionario diccionario = new Diccionario();

	 @Test
	 public void testDiccionarioLlenoAlConstruir() {
	 assertEquals(3, diccionario.getDiccionario().size());
	 }
	
	 @Test
	 public void testAgregarListaPorTipoEnDiccionario() {
	 ListaDePalabras listaPalabras = new ListaDePalabras();
	 diccionario.addList(listaPalabras);
	 assertEquals(4, diccionario.getDiccionario().size());
	 }
	
	 @Test
	 public void seleccionarPalabra(){
	 assertEquals("ToyStory",
	 diccionario.obtenerPalabra("Peliculas", 1).getPalabra());
	 }

}
