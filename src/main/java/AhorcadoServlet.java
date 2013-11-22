import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AhorcadoServlet extends HttpServlet {

	Juego ahorcado = new Juego();
	String category = "";
	int level = 0;
	String dibujada = "";
	int cantPistas;
	String res;
	HttpServletResponse response;
	HttpServletRequest request;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// se inicializan variables y se obtienen datos de vista anterior
		request = req;
		response = resp;
		response.setContentType("text/html");
		String categoria = request.getParameter("categoria");
		int nivel = Integer.parseInt(request.getParameter("nivel"));
		String entrada = request.getParameter("letra");
		String pista = request.getParameter("pista");
		// se muestran los datos del juego
		response.getWriter().println("CATEGORIA: " + categoria + "<br>");
		response.getWriter().println("NIVEL: " + nivel + "<br>");
		// verifica si se cambio el nivel o la categoria para reiniciar juego
		if (!category.equals(categoria) || level != nivel) {
			inicializarJuego(categoria, nivel);
		} else {
			ahorcado.setPalabraMostrada(dibujada);
		}
		// se verifica y ejecuta el comando solicitado por usuario
		manejarIngresoDeLetra(categoria, nivel, entrada);
		manejarPista(categoria, nivel, pista);
		// se dibuja la palabra del juego
		response.getWriter().println("<br>" + ahorcado.dibujarPalabra());
		// se dibuja el resto de la interfaz (botones, textboxes, etc)
		dibujarInterfaz(categoria, nivel);
	}

	private void manejarPista(String categoria, int nivel, String pista)
			throws ServletException, IOException {
		if (pista.equals("pista") && cantPistas > 0) {
			darPista(categoria, nivel);
		} else {
			response.getWriter().println(
					"PISTAS DISPONIBLES: " + (cantPistas) + "<br>");
			if (cantPistas == 0) {
				response.getWriter().println(
						"Su cantidad de pistas se agoto :(");
			}
		}
	}

	private void darPista(String categoria, int nivel) throws ServletException,
			IOException {
		char letra = ahorcado.getPalabra().obtenerUnaPista();
		cantPistas--;
		response.getWriter().println(
				"PISTAS DISPONIBLES: " + (cantPistas) + "<br>");
		response.getWriter().println("Pista: " + letra);
		ingresarLetra(categoria, nivel, letra);
	}

	private void manejarIngresoDeLetra(String categoria, int nivel,
			String entrada) throws ServletException, IOException {
		if (entrada != null && !entrada.isEmpty()) {
			char letra = entrada.charAt(0);
			ingresarLetra(categoria, nivel, letra);
		}
	}

	private void ingresarLetra(String categoria, int nivel, char letra)
			throws ServletException, IOException {
		res = ahorcado.ingresarLetra(letra);
		dibujada = ahorcado.dibujarPalabra();
		response.getWriter().println(res);
		if (res.equals("GANO!!")) {
			response.getWriter().println("<br>Palabra adivinada: " + dibujada);
			reiniciarJuego(categoria, nivel);
		}
		if (!res.isEmpty()){
			response.getWriter().println("<br>");
		}
	}

	private void reiniciarJuego(String categoria, int nivel)
			throws ServletException, IOException {
		ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);
		dibujada = ahorcado.dibujarPalabra();
		cantPistas = ahorcado.getPalabra().calcularCantidadPistasPorPalabra();
	}

	private void inicializarJuego(String categoria, int nivel)
			throws ServletException, IOException {
		ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);
		cantPistas = ahorcado.getPalabra().calcularCantidadPistasPorPalabra();
		level = nivel;
		category = categoria;
		dibujada = ahorcado.dibujarPalabra();
	}

	private void dibujarInterfaz(String categoria, int nivel)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("</br>");
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("<FORM action=AhorcadoServlet>");
		out.println("Ingrese letra:  <input type=text name=letra>");
		out.println("<input type=hidden id=pista name=pista value=desactivar>");
		out.println("<input type=hidden id=categoria name=categoria value="
				+ categoria + ">");
		out.println("<input type=hidden id=nivel name=nivel value=" + nivel
				+ ">");
		out.println("<input type=submit name=ingresaLetra>");
		out.println("</FORM>");

		out.println("<FORM action=seleccionarNivel.html>");
		out.println("<input type=submit name=volver value=Volver>");
		out.println("</FORM>");

		out.println("<FORM action=AhorcadoServlet");
		out.println("<input type=hidden id=pistaa name=pistaa value=activar>");
		out.println("<input type=hidden id=categoria name=categoria value="
				+ categoria + ">");
		out.println("<input type=hidden id=nivel name=nivel value=" + nivel
				+ ">");
		out.println("<input type=submit name=pista id=pista value=pista>");
		out.println("</FORM>");
		out.println("</BODY>");
		out.println("</HTML>");
	}
}
