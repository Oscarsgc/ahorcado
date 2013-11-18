public class Palabra {

	private String palabra;
	private int dificultad;
	private boolean usado;

	public Palabra() {
		this.palabra = "";
		this.dificultad = -1;
		this.usado = false;

	}

	public Palabra(String palabra, int dificultad, boolean usado) {
		this.palabra = palabra;
		this.dificultad = dificultad;
		this.usado = usado;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public boolean isUsado() {
		return usado;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}

	public boolean buscarLetra(char c) {
		if (palabra.indexOf(c) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public String dibujarPalabraVacia() {
		String palabraVacia = "";
		for (int i = 0; i < this.palabra.length(); i++) {
			palabraVacia = palabraVacia + '_' + ' ';
		}
		// System.out.println(palabraVacia);
		return palabraVacia;
	}

	public String buscarLetraEnPalabra(char letra, String palabra,
			String palabraVacia) {
		String res = "";
		boolean encontrado = false;
		char[] aux = palabra.toCharArray();
		char[] empty = palabraVacia.toCharArray();
		for (int i = 0; i < aux.length; i++) {
			if (aux[i] == letra) {
				empty[i * 2] = letra;
				encontrado = true;
			}
		}
		for (int i = 0; i < empty.length; i++) {
			res = res + empty[i];
		}
		if (encontrado == false) {
			res = "Letra no encontrada!";
		}
		// System.out.println(res);
		return res;

	}

}
