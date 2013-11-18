public class Juego {

	private Palabra palabra;
	private String palabraMostrada;

	public Juego() {
		this.palabra = new Palabra();
		this.palabraMostrada = palabra.dibujarPalabraVacia();
	}

	public Juego(String palabra) {
		this.palabra = new Palabra(palabra);
		this.palabraMostrada = this.palabra.dibujarPalabraVacia();
	}

	public String dibujarPalabra() {
		return palabraMostrada;
	}

	public String ingresarLetra(char letra) {
		int encontrado = palabra.buscarLetra(letra);
		if (encontrado != -1) {
			dibujarLetraEnPalabra(letra, encontrado);
			return dibujarPalabra();
		} else {
			dibujarPalabra();
			return mostrarMensajeError();
		}
	}

	public void dibujarLetraEnPalabra(char letra, int pos) {
		char[] mostrada = palabraMostrada.toCharArray();
		mostrada[pos * 2] = letra;
		palabraMostrada = "";
		for (int i = 0; i < mostrada.length; i++) {
			palabraMostrada += mostrada[i];
		}
	}

	public String mostrarMensajeError() {
		return "Letra no encontrada!";
	}
}
