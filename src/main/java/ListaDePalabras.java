import java.util.ArrayList;

public class ListaDePalabras {
	private String categoria;
	private ArrayList<Palabra> palabras;
	private int cantidadPalabras;

	public ArrayList<Palabra> getPalabras() {
		return palabras;
	}

	public void setPalabras(ArrayList<Palabra> palabras) {
		this.palabras = palabras;
	}

	public ListaDePalabras() {
		this.categoria = "";
		this.palabras = new ArrayList<Palabra>();
		this.cantidadPalabras = 0;
	}

	public ListaDePalabras(String categoria) {
		this.categoria = categoria;
	}

	public int getCantidadPalabras() {
		return cantidadPalabras;
	}

	public void setCantidadPalabras(int cantidadPalabras) {
		this.cantidadPalabras = cantidadPalabras;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void agregarPalabra(Palabra palabra) {
		palabras.add(palabra);
		cantidadPalabras++;

	}

	public void eliminarPalabra(Palabra palabra) {
		palabras.remove(palabra);
		cantidadPalabras--;

	}

	public String mostrarLista() {
		String res = "";
		for (Palabra word : palabras) {
			System.out.println(word.getPalabra());
			res = res + word.getPalabra();
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
}
