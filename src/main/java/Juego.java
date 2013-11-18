public class Juego {

	private Palabra palabra;
	private String palabraMostrada;
	private Diccionario diccionario;

	public Juego() {
		this.palabra = new Palabra();
		this.palabraMostrada = palabra.dibujarPalabraVacia();
		this.diccionario.llenarDiccionario();
	}

	public Palabra getPalabra() {
		return palabra;
	}

	public void setPalabra(Palabra palabra) {
		this.palabra = palabra;
		this.palabraMostrada = palabra.dibujarPalabraVacia();

	}

	public void obtenerPalabraDeDiccionario(int nivel, String categoria) {
		this.palabra = diccionario.palabraAleatoriaPorCategoria(categoria,
				nivel);
		this.palabraMostrada = palabra.dibujarPalabraVacia();
	}

	public Juego(String palabra) {
		this.palabra = new Palabra(palabra);
		this.palabraMostrada = this.palabra.dibujarPalabraVacia();
		this.diccionario.llenarDiccionario();
	}

	public Juego(Palabra palabra) {
		this.palabra = palabra;
		this.palabraMostrada = this.palabra.dibujarPalabraVacia();
		this.diccionario.llenarDiccionario();
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
		char[] original = palabra.getPalabra().toCharArray();
		for (int i = pos; i < original.length; i++) {
			if (original[i] == letra)
				mostrada[i * 2] = letra;
		}
		palabraMostrada = "";
		for (int i = 0; i < mostrada.length; i++) {
			palabraMostrada += mostrada[i];
		}
	}

	public String mostrarMensajeError() {
		return "Letra no encontrada!";
	}
}
