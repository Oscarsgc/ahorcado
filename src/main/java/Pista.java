public class Pista {
	private int cantPistas;
	private char pista;

	public int getCantPista() {
		return cantPistas;
	}

	public void setCantPista(int cantPista) {
		this.cantPistas = cantPista;
	}

	public char getPista() {
		return pista;
	}

	public void setPista(char pista) {
		this.pista = pista;
	}

	public int calcularCantidadPistasPorPalabra(Palabra palabra) {
		int tamanioPal = palabra.getPalabra().length();
		if (tamanioPal % 2 == 0) {
			this.cantPistas = (tamanioPal / 2) - 1;
		} else {
			this.cantPistas = tamanioPal / 2;
		}
		return this.cantPistas;
	}

	public char obtenerUnaPista(Palabra palabra) {
		char[] arreglo = palabra.getPalabra().toCharArray();
		int random = 0 + (int) (Math.random() * ((palabra.getPalabra().length() - 0) + 1));
		return arreglo[random];
	}
}
