public class Juego {

	private Palabra palabra;
	private String palabraMostrada;
	private Diccionario diccionario;

	public Juego() {
		this.palabra = new Palabra();
		this.diccionario = new Diccionario();
		this.palabraMostrada = palabra.dibujarPalabraVacia();
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
		palabra = diccionario.obtenerPalabra(categoria, nivel);
		this.palabraMostrada = palabra.dibujarPalabraVacia();
		return palabra;
	}

	public Juego(String palabra) {
		this.palabra = new Palabra(palabra);
		this.palabraMostrada = this.palabra.dibujarPalabraVacia();
		this.diccionario = new Diccionario();
	}

	public Juego(Palabra palabra) {
		this.palabra = palabra;
		this.palabraMostrada = this.palabra.dibujarPalabraVacia();
		this.diccionario = new Diccionario();
	}

	public String dibujarPalabra() {
		return palabraMostrada;
	}

	public String ingresarLetra(char letra) {
		int encontrado = palabra.buscarLetra(letra);
		if (encontrado != -1) {
			dibujarLetraEnPalabra(letra, encontrado);
			if (gano())
				return "GANO!!";
			else
				return "";
		} else {
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

	public boolean gano() {
		return !palabraMostrada.contains("_");
	}

	public String mostrarPalabrasDiccionario() {
		String res = "";
		for (int i = 0; i < diccionario.getDiccionario().size(); i++) {
			res = res + diccionario.getDiccionario().get(i).mostrarLista();
		}
		return res;
	}
}
