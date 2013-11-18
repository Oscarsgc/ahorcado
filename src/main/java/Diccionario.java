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

}
