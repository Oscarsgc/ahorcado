public class Palabra {

	private String palabra;
	private int dificultad;
	private boolean uso;

	public Palabra() {
		this.palabra = "";
		this.dificultad = -1;
		this.uso = false;
	}

	public Palabra(String palabra) {
		this.palabra = palabra;
		this.dificultad = -1;
		this.uso = false;
	}

	public Palabra(String palabra, int dificultad, boolean uso) {
		this.palabra = palabra;
		this.dificultad = dificultad;
		this.uso = uso;

	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public boolean isUso() {
		return uso;
	}

	public void setUso(boolean uso) {
		this.uso = uso;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
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
