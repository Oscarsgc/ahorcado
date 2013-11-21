import java.util.ArrayList;

public class Diccionario {
	public ArrayList<ListaDePalabras> getDiccionario() {
		return diccionario;
	}

	public void setDiccionario(ArrayList<ListaDePalabras> diccionario) {
		this.diccionario = diccionario;
	}

	private int listQty;
	private ArrayList<ListaDePalabras> diccionario;

	public Diccionario() {
		this.diccionario = new ArrayList<ListaDePalabras>();
		this.listQty = 0;
		llenarDiccionario();
	}

	public void addList(ListaDePalabras listaPalabras) {
		diccionario.add(listaPalabras);
		listQty++;
	}

	public Palabra palabraAleatoriaPorCategoria(String categoria, int nivel) {
		for (int i = 0; i < diccionario.size(); i++) {
			if (diccionario.get(i).getCategoria().equals(categoria)) {

				boolean encontrado = false;
				int pos = 0;
				while (!encontrado) {
					Palabra pal = diccionario.get(i).getPalabraEnPosicion(pos);
					if (pal.getDificultad() == nivel && !pal.getUso()) {

						pal.setUso(true);
						return pal;
					}
					pos++;
				}
			}
		}
		return null;
	}

	public int getListQty() {
		return listQty;
	}

	public void setListQty(int listQty) {
		this.listQty = listQty;
	}

	public void llenarDiccionario() {
		ListaDePalabras paises = new ListaDePalabras("Paises");
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

		ListaDePalabras peliculas = new ListaDePalabras("Peliculas");
		peliculas.agregarPalabra(new Palabra("ToyStory", 1, false));
		peliculas.agregarPalabra(new Palabra("LaMomia", 1, false));
		peliculas.agregarPalabra(new Palabra("Argo", 1, false));
		peliculas.agregarPalabra(new Palabra("Tarzan", 1, false));
		peliculas.agregarPalabra(new Palabra("AmericanPie", 1, false));
		peliculas.agregarPalabra(new Palabra("LosPitufos", 2, false));
		peliculas.agregarPalabra(new Palabra("Hancok", 2, false));
		peliculas.agregarPalabra(new Palabra("Precious", 2, false));
		peliculas.agregarPalabra(new Palabra("ScaryMovie", 2, false));
		peliculas.agregarPalabra(new Palabra("CasaTiburones", 2, false));
		peliculas.agregarPalabra(new Palabra("Adventureland", 3, false));
		peliculas.agregarPalabra(new Palabra("3MetrosSobreElCielo", 3, false));
		peliculas.agregarPalabra(new Palabra("LaPasionDeCristo", 3, false));
		peliculas.agregarPalabra(new Palabra("LosJuegosDelHambre", 3, false));
		peliculas.agregarPalabra(new Palabra("DiaroDeUnaPasion", 3, false));

		ListaDePalabras seriesTV = new ListaDePalabras("SeriesTV");
		seriesTV.agregarPalabra(new Palabra("Friends", 1, false));
		seriesTV.agregarPalabra(new Palabra("Mom", 1, false));
		seriesTV.agregarPalabra(new Palabra("Arrow", 1, false));
		seriesTV.agregarPalabra(new Palabra("Glee", 1, false));
		seriesTV.agregarPalabra(new Palabra("Hostages", 1, false));
		seriesTV.agregarPalabra(new Palabra("LosSimpson", 2, false));
		seriesTV.agregarPalabra(new Palabra("ModernFamily", 2, false));
		seriesTV.agregarPalabra(new Palabra("Vikingos", 2, false));
		seriesTV.agregarPalabra(new Palabra("Homeland", 2, false));
		seriesTV.agregarPalabra(new Palabra("Supercampeones", 2, false));
		seriesTV.agregarPalabra(new Palabra("TheBigBangTheory", 3, false));
		seriesTV.agregarPalabra(new Palabra("Supernatural", 3, false));
		seriesTV.agregarPalabra(new Palabra("TheFollowing", 3, false));
		seriesTV.agregarPalabra(new Palabra("BreakingBad", 3, false));
		seriesTV.agregarPalabra(new Palabra("AgentsOfShield", 3, false));

		addList(paises);
		addList(peliculas);
		addList(seriesTV);
	}

	public void agregarPalabra(String pal, int nivel, String categoria) {
		for (int i = 0; i < this.diccionario.size(); i++) {
			if (this.diccionario.get(i).getCategoria() == categoria) {
				this.diccionario.get(i).agregarPalabra(
						new Palabra(pal, nivel, false));
			}
		}
	}
}
