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

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String categoria = request.getParameter("categoria");

		int nivel = Integer.parseInt(request.getParameter("nivel"));
		response.getWriter().println("CATEGORIA: " + categoria);
		out.println("</br>");
		response.getWriter().println("NIVEL: " + nivel);
		out.println("</br>");
		

		if (!category.equals(categoria) || level != nivel) {
			ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);
			cantPistas = ahorcado.getPalabra()
					.calcularCantidadPistasPorPalabra();
			level = nivel;
			category = categoria;
			dibujada = ahorcado.dibujarPalabra();
		} else {

			ahorcado.setPalabraMostrada(dibujada);
		}
		response.getWriter().println("PISTAS DISPONIBLES: " + cantPistas);
		out.println("</br>");
		String car = request.getParameter("letra");
		String res;
		String pista = request.getParameter("pista");
		if (car != null && !car.isEmpty()) {
			char letra = car.charAt(0);
			res = ahorcado.ingresarLetra(letra);
			dibujada = ahorcado.dibujarPalabra();
			response.getWriter().println(res);
			out.println("</br>");
			if (res.equals("GANO!!")) {
				ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);
				dibujada = ahorcado.dibujarPalabra();
				cantPistas = ahorcado.getPalabra()
						.calcularCantidadPistasPorPalabra();
			}
		} else {
			if (pista.equals("pista") && cantPistas > 0) {
				char letra = ahorcado.getPalabra().obtenerUnaPista();
				cantPistas--;
				out.println("</br>");
				out.println("</br>");
				out.println("Pista: " + letra);
				out.println("</br>");
				res = ahorcado.ingresarLetra(letra);
				dibujada = ahorcado.dibujarPalabra();
				response.getWriter().println(res);
				out.println("</br>");
				if (res.equals("GANO!!")) {
					ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);
					out.println("</br>");
					dibujada = ahorcado.dibujarPalabra();
					cantPistas = ahorcado.getPalabra()
							.calcularCantidadPistasPorPalabra();
				}
			} else {
				if (cantPistas == 0) {
					out.println("</br>");
					out.println("</br>");
					out.println("Su cantidad de pistas se agoto =( ");
					out.println("</br>");
				}
			}
		}

		response.getWriter().println(ahorcado.dibujarPalabra());

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
