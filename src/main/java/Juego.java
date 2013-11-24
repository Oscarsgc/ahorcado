public class Juego {

	private Palabra palabra;
	private String palabraMostrada;
	private int cantidadErrores;
	private int puntuacion;
	private Diccionario diccionario;

	public Juego() {
		this.palabra = new Palabra();
		this.diccionario = new Diccionario();
		this.palabraMostrada = palabra.dibujarPalabraVacia();
		this.cantidadErrores = 0;
		this.puntuacion = 0;
	}
	
	public Juego(String palabra) {
		this.palabra = new Palabra(palabra);
		this.palabraMostrada = this.palabra.dibujarPalabraVacia();
		this.diccionario = new Diccionario();
		calcularPuntuacion();
	}

	public Juego(Palabra palabra) {
		this.palabra = palabra;
		this.palabraMostrada = this.palabra.dibujarPalabraVacia();
		this.diccionario = new Diccionario();
		calcularPuntuacion();
	}
	
	public char obtenerUnaPista() {
		puntuacion--;
		char[] arreglo = palabra.getPalabra().toCharArray();
		char res = ' ';
		boolean encontrada = false;
		int random;
		while (!encontrada) {
			random = 0 + (int) (Math.random() * ((palabra.getPalabra().length() - 1) + 1));
			res = arreglo[random];
			if(!palabraMostrada.contains(String.valueOf(res))) {
				encontrada = true;
			}
		}
		return res;
	}

	public void calcularPuntuacion(){
		puntuacion = palabra.getPalabra().length() * palabra.getDificultad();
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public void reducirPuntuacion() {
		puntuacion--;
	}
	
	public int getCantidadErrores() {
		return cantidadErrores;
	}

	public void setCantidadErrores(int cantidadErrores) {
		this.cantidadErrores = cantidadErrores;
	}

	public Palabra getPalabra() {
		return palabra;
	}

	public void setPalabra(Palabra palabra) {
		this.palabra = palabra;
		this.palabraMostrada = palabra.dibujarPalabraVacia();
		calcularPuntuacion();
	}

	public void setPalabraMostrada(String palabra) {
		this.palabraMostrada = palabra;
	}

	public Palabra obtenerPalabraDeDiccionario(int nivel, String categoria) {
		palabra = diccionario.obtenerPalabra(categoria, nivel);
		this.palabraMostrada = palabra.dibujarPalabraVacia();
		calcularPuntuacion();
		return palabra;
	}

	public String dibujarPalabra() {
		return palabraMostrada;
	}

	public String ingresarLetra(char letra) {
		int encontrado = palabra.buscarLetra(letra);
		if (encontrado != -1) {
			dibujarLetraEnPalabra(letra, encontrado);
			if (gano()) {
				return "GANO!!";

			} else {
				return "";
			}
		} else {
			return letraErronea();
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

	public String letraErronea() {
		this.cantidadErrores++;
		if (cantidadErrores == 6){
			puntuacion = 0;
			return "Letra no encontrada! <br> PERDIO!!";
		} else {
			puntuacion--;
			return "Letra no encontrada!";
		}
	}

	public String dibujarMunieco() {
		String retornar = "";
		switch (this.cantidadErrores) {
		case 0:
			retornar = " ";
			break;
		case 1:
			retornar = " " + "O" + "<br>";
			break;
		case 2:
			retornar = " " + "O" + "<br>" + "/";
			break;
		case 3:
			retornar = " " + "O" + "<br>" + "/" + "|";
			break;
		case 4:
			retornar = " " + "O" + "<br>" + "/" + "|" + "\\";
			break;
		case 5:
			retornar = " " + "O" + "<br>" + "/" + "|" + "\\" + "<br>" + "/";
			break;
		case 6:
			retornar = " " + "O" + "<br>" + "/" + "|" + "\\" + "<br>" + "/"
					+ " " + "\\";
			break;
		}
		return retornar;
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
