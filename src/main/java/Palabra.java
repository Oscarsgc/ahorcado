public class Palabra {

	private String palabra;
	private String categoria;

	public Palabra() {
		this.palabra = "";
		this.categoria = "";
	}

	public Palabra(String palabra) {
		this.palabra = palabra;
		this.categoria = "";
	}

	public Palabra(String palabra, String categoria) {
		this.palabra = palabra;
		this.categoria = categoria;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String dibujarPalabraVacia() {
		String palabraMostrada = "";
		for (int i = 0; i < this.palabra.length(); i++) {
			palabraMostrada = palabraMostrada + '_' + ' ';
		}
		return palabraMostrada;
	}

	public int buscarLetra(char letra) {
		int res = -1;
		char[] word = palabra.toCharArray();
		for (int i = 0; i < word.length && res == -1; i++) {
			if (word[i] == letra) {
				res = i;
			}
		}
		return res;
	}

}
