import java.util.ArrayList;

public class ListaDePalabras {
	private String categoria;
	private ArrayList<Palabra> palabras;

	public ListaDePalabras() {
		this.categoria = "";
		this.palabras = new ArrayList<Palabra>();
	}

	public ListaDePalabras(String categoria) {
		this.categoria = categoria;
		this.palabras = new ArrayList<Palabra>();
	}

	public ArrayList<Palabra> getPalabras() {
		return palabras;
	}

	/*
	 * public void setPalabras(ArrayList<Palabra> palabras) { this.palabras =
	 * palabras; }
	 */

	public int getCantidadPalabras() {
		return palabras.size();
	}

	public Palabra getPalabraEnPosicion(int pos) {
		return this.palabras.get(pos);
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void agregarPalabra(Palabra palabra) {
		palabras.add(palabra);

	}

	public void eliminarPalabra(Palabra palabra) {
		palabras.remove(palabra);
	}

	public String mostrarLista() {
		String res = "";
		for (Palabra word : palabras) {
			System.out.println(word.getPalabra());
			res = res + word.getPalabra() + '\n';
		}
		return res;
	}

	public int buscarPalabra(Palabra palabra) {
		for (int i = 0; i < palabras.size(); i++) {
			if (palabras.get(i).getPalabra() == palabra.getPalabra()) {
				return i;
			}
		}
		return -1;
	}

	public Palabra obtenerPalabraPorNivel(int nivel) {
		boolean encontrado = false;
		for (int i = 0; i < palabras.size() && !encontrado; i++) {
			Palabra pal = getPalabraEnPosicion(i);
			if (pal.getDificultad() == nivel && !pal.getUso()) {
				pal.setUso(true);
				return pal;
			}
		}
		return null;
	}
}
