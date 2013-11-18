import java.util.ArrayList;

public class Diccionario {
	private int listQty;
	private ArrayList<ListaDePalabras> diccionario;

	public Diccionario() {
		this.diccionario = new ArrayList<ListaDePalabras>();
		this.listQty = 0;
	}

	public void addList(ListaDePalabras listaPalabras) {
		diccionario.add(listaPalabras);
		listQty++;
	}

	public int getListQty() {
		return listQty;
	}

	public void setListQty(int listQty) {
		this.listQty = listQty;
	}

	public void llenarDiccionario() {
		ListaDePalabras paises = new ListaDePalabras();
		paises.agregarPalabra(new Palabra("Afganistan", 2, false));
		paises.agregarPalabra(new Palabra("Bahamas", 1, false));
		paises.agregarPalabra(new Palabra("Bolivia", 1, false));
		paises.agregarPalabra(new Palabra("Croacia", 2, false));
		paises.agregarPalabra(new Palabra("Dinamarca", 2, false));
		paises.agregarPalabra(new Palabra("Filipinas", 2, false));
		paises.agregarPalabra(new Palabra("Georgia", 3, false));
		paises.agregarPalabra(new Palabra("Guinea", 2, false));
		paises.agregarPalabra(new Palabra("Irlanda", 1, false));
		paises.agregarPalabra(new Palabra("Japon", 1, false));
		paises.agregarPalabra(new Palabra("Nepal", 1, false));
		paises.agregarPalabra(new Palabra("Ucrania", 3, false));
		paises.agregarPalabra(new Palabra("Somalia", 3, false));
		paises.agregarPalabra(new Palabra("Tanganica", 3, false));
		paises.agregarPalabra(new Palabra("Yemen", 3, false));
		
		ListaDePalabras peliculas = new ListaDePalabras();
		peliculas.agregarPalabra(new Palabra("ToyStory", 1, false));
		peliculas.agregarPalabra(new Palabra("LaMomia", 1, false));
		peliculas.agregarPalabra(new Palabra("Argos", 1, false));
		peliculas.agregarPalabra(new Palabra("3Metrossobreelcielo", 1, false));
		peliculas.agregarPalabra(new Palabra("AmericanPie", 1, false));
		
		addList(paises);
	}

}
