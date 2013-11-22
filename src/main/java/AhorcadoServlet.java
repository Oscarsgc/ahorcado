import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AhorcadoServlet extends HttpServlet {

	Juego ahorcado = new Juego();
	String categoria = "";
	int nivel = 0;
	String dibujada = "";
	int cantPistas;
	boolean fraseDada;
	String res;
	HttpServletResponse response;
	HttpServletRequest request;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// se inicializan variables y se obtienen parametros
		request = req;
		response = resp;
		response.setContentType("text/html");
		String category = request.getParameter("categoria");
		String level = request.getParameter("nivel");
		String entrada = request.getParameter("letra");
		String pista = request.getParameter("pista");
		String frase = request.getParameter("frase");
		// verifica si se cambio el nivel o la categoria para reiniciar juego
		if(category != null && level != null) {
			inicializarJuego(category, level);
		} else {
			ahorcado.setPalabraMostrada(dibujada);
		}
		// se muestran los datos del juego
		response.getWriter().println("CATEGORIA: " + categoria + "<br>");
		response.getWriter().println("NIVEL: " + nivel + "<br>");
		// se verifica y ejecuta el comando solicitado por usuario
		manejarIngresoDeLetra(entrada);
		manejarFrasePista(frase);
		manejarPista(pista);
		// se dibuja la palabra del juego
		response.getWriter().println("<br>" + ahorcado.dibujarPalabra());
		// se dibuja el resto de la interfaz (botones, textboxes, etc)
		dibujarInterfaz();
	}
	
	private void manejarFrasePista(String frase)
			throws ServletException, IOException {
		if(frase != null){
			//if (frase.equals("PedirFrase")) {
				if (!fraseDada){
					imprimirFrase();
				} else {
					response.getWriter().println("La frase ya se le fue mostrada <br>");
				}
			//}
		}
	}
	
	private void imprimirFrase() throws ServletException, IOException {
		response.getWriter().println("FRASE: " + ahorcado.getPalabra().getFrase() + "<br>");
		fraseDada = true;
	}

	private void manejarPista(String pista)
			throws ServletException, IOException {
		if(pista != null){
			if (cantPistas > 0) {
				darPista();
			} else {
				mostrarInformacionDePistas();
			}
		}
		else {
			mostrarInformacionDePistas();
		}
	}
	
	private void mostrarInformacionDePistas() throws ServletException, IOException {
		if (cantPistas == 0) {
			response.getWriter().println(
					"Su cantidad de pistas se agoto :(");
		} else {
			response.getWriter().println(
					"PISTAS DISPONIBLES: " + cantPistas);
		}
	}

	private void darPista() throws ServletException,
			IOException {
		char letra = ahorcado.getPalabra().obtenerUnaPista();
		cantPistas--;
		response.getWriter().println(
				"PISTAS DISPONIBLES: " + cantPistas + "<br>");
		response.getWriter().println("Pista: " + letra);
		ingresarLetra(letra);
	}

	private void manejarIngresoDeLetra(String entrada) throws ServletException, IOException {
		if (entrada != null) {
			char letra = entrada.charAt(0);
			ingresarLetra(letra);
		}
	}

	private void ingresarLetra(char letra)
			throws ServletException, IOException {
		res = ahorcado.ingresarLetra(letra);
		dibujada = ahorcado.dibujarPalabra();
		response.getWriter().println(res);
		if (res.equals("GANO!!")) {
			response.getWriter().println("<br>Palabra adivinada: " + dibujada);
			reiniciarJuego();
		}
		if (!res.isEmpty()){
			response.getWriter().println("<br>");
		}
	}

	private void reiniciarJuego()
			throws ServletException, IOException {
		ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);
		dibujada = ahorcado.dibujarPalabra();
		cantPistas = ahorcado.getPalabra().calcularCantidadPistasPorPalabra();
		fraseDada = false;
	}

	private void inicializarJuego(String category, String level)
			throws ServletException, IOException {
		nivel = Integer.parseInt(level);
		categoria = category;
		ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);
		cantPistas = ahorcado.getPalabra().calcularCantidadPistasPorPalabra();
		dibujada = ahorcado.dibujarPalabra();
		fraseDada = false;
	}

	private void dibujarInterfaz()
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("</br>");
		out.println("<FORM action=AhorcadoServlet>");
		out.println("Ingrese letra:  <input type=text name=letra>");
		out.println("<input type=submit name=ingresaLetra value=IngresarLetra>");
		out.println("</FORM>");
		
		out.println("<FORM action=AhorcadoServlet>");
		out.println("<input type=submit name=pista value=PedirPista>");
		out.println("</FORM>");

		out.println("<FORM action=AhorcadoServlet>");
		out.println("<input type=submit name=frase value=PedirFrase>");
		out.println("</FORM>");
		
		out.println("<FORM action=seleccionarNivel.html>");
		out.println("<input type=submit name=volver value=Cancelar>");
		out.println("</FORM>");
	}
}
