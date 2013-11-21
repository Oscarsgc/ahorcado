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
		if (category.compareTo(categoria) != 0 || level != nivel) {

			ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);

			level = nivel;
			category = categoria;
			dibujada = ahorcado.dibujarPalabra();
		} else {

			ahorcado.setPalabraMostrada(dibujada);
		}

		String car = request.getParameter("letra");
		String res;

		if (car != null && !car.isEmpty()) {
			char letra = car.charAt(0);
			res = ahorcado.ingresarLetra(letra);
			dibujada = ahorcado.dibujarPalabra();
			response.getWriter().println(res);
			out.println("</br>");
			if (res.equals("GANO!!")) {
				ahorcado.obtenerPalabraDeDiccionario(nivel, categoria);
				dibujada = ahorcado.dibujarPalabra();
			}
		}

		response.getWriter().println(ahorcado.dibujarPalabra());

		out.println("</br>");
		out.println("<FORM action=AhorcadoServlet>");
		out.println("Ingrese letra:  <input type=text name=letra>");
		out.println("<input type=hidden name=categoria value=" + categoria
				+ ">");
		out.println("<input type=hidden name=nivel value=" + nivel + ">");
		out.println("<input type=submit name=ingresaLetra>");
		out.println("</FORM>");
		out.println("<FORM action=seleccionarNivel.html>");
		out.println("<input type=submit name=volver value=Volver>");
		out.println("</FORM>");

	}
}
