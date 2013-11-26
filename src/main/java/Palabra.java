public class Palabra {

	private String palabra;
	private int dificultad;
	private boolean uso;
	private int cantPistas;
	private String frase;

	public Palabra() {
		this.palabra = "";
		this.dificultad = -1;
		this.uso = false;
		this.frase = "";
	}

	public Palabra(String palabra) {
		this.palabra = palabra;
		this.dificultad = -1;
		this.uso = false;
		this.frase = "";
	}

	public Palabra(String palabra, int dificultad, boolean uso) {
		this.palabra = palabra;
		this.dificultad = dificultad;
		this.uso = uso;
		this.frase = "";
	}

	public Palabra(String palabra, int dificultad, boolean uso, String frase) {
		this.palabra = palabra;
		this.dificultad = dificultad;
		this.uso = uso;
		this.frase = frase;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public boolean getUso() {
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

	// public int buscarLetra(char letra) {
	// int res = -1;
	// char[] word = palabra.toCharArray();
	// for (int i = 0; i < word.length && res == -1; i++) {
	// if (word[i] == letra) {
	// res = i;
	// }
	// }
	// return res;
	// }
	
	public int buscarLetra(char letra) {
		int res = -1;
		String minus = palabra.toLowerCase();
		String mayus = palabra.toUpperCase();
		for (int i = 0; i < palabra.length() && res == -1; i++) {
			if(minus.charAt(i) == letra || mayus.charAt(i)==letra) {
				res = i;
			}
		}
		return res;
	}
	
	
	
	

	public int calcularCantidadPistasPorPalabra() {
		cantPistas = palabra.length() / 3;
		if (palabra.length() % 3 == 0)
			cantPistas--;
		return cantPistas;
	}
}
