import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class TestJuego {
    Juego juego;

    @Before
    public void iniciandoJuego(){
        juego = new Juego("Prueba");
    }

    @Test
    public void dibujarEspaciosParaPalabra() {
       assertEquals("_ _ _ _ _ _ ", juego.dibujarPalabra());
    }

    @Test
    public void dibujarLetraEnPalabra() {
        Palabra palabra = new Palabra("Prueba");
        int pos = palabra.buscarLetra('r');
        juego.dibujarLetraEnPalabra('r', pos);
        assertEquals("_ r _ _ _ _ ", juego.dibujarPalabra());
    }

    @Test
    public void dibujarLetraSiExiste() {
        juego.ingresarLetra('r');
        assertEquals("_ r _ _ _ _ ", juego.dibujarPalabra());
        juego=new Juego("Correr");
        juego.ingresarLetra('r');
        assertEquals("_ _ r r _ r ", juego.dibujarPalabra());
    }

    @Test
    public void NOdibujarLetraSiExiste() {
        juego.ingresarLetra('x');
        assertEquals("_ _ _ _ _ _ ", juego.dibujarPalabra());
    }
       
    @Test
    public void mustraMensajeDeErrorSiLetraNoExiste() {
        assertEquals("Letra no encontrada!", juego.ingresarLetra('x'));
    }
}
