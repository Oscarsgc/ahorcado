public class Juego {

	private Palabra palabra = new Palabra();
	private String palabraMostrada = new String();
	private Diccionario diccionario = new Diccionario();

	public Juego() {
		this.diccionario.llenarDiccionario();
		this.palabra = new Palabra();
		this.palabraMostrada = palabra.dibujarPalabraVacia();

	}

	public Juego(int nivel, String categoria) {
		this.diccionario.llenarDiccionario();
		this.palabra = obtenerPalabraDeDiccionario(nivel, categoria);
		this.palabraMostrada = this.palabra.dibujarPalabraVacia();

	}

	public Palabra getPalabra() {
		return palabra;
	}

	public void setPalabra(Palabra palabra) {
		this.palabra = palabra;
		this.palabraMostrada = palabra.dibujarPalabraVacia();

	}

	public void setPalabraMostrada(String palabra) {
		this.palabraMostrada = palabra;

	}

	public Palabra obtenerPalabraDeDiccionario(int nivel, String categoria) {
		palabra = diccionario.palabraAleatoriaPorCategoria(categoria, nivel);
		this.palabraMostrada = palabra.dibujarPalabraVacia();
		return palabra;
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

	public void agregarPalabraAlDiccionario(String pal, int nivel,
			String categoria) {
		diccionario.agregarPalabra(pal, nivel, categoria);
	}

	public Diccionario getDiccionario() {
		return diccionario;
	}

	public void setDiccionario(Diccionario diccionario) {
		this.diccionario = diccionario;
	}

	public String getPalabraMostrada() {
		return palabraMostrada;
	}

	public String mostrarPalabrasDiccionario() {
		String res = "";
		for (int i = 0; i < diccionario.getDiccionario().size(); i++) {
			res = res + diccionario.getDiccionario().get(i).mostrarLista();
		}
		return res;
	}
}
