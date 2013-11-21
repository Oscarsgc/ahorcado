import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {
	Juego ahorcado = new Juego();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String palabra = request.getParameter("palabra");
		String dificultad = request.getParameter("nivel");
		String categoria = request.getParameter("categoria");
		ahorcado.agregarPalabraAlDiccionario(palabra,
				Integer.parseInt(dificultad), categoria);
		SerializeDemo ser = new SerializeDemo();
		ser.serializar();
		// request.getSession().setAttribute("Diccionario", ahorcado);
		out.println("Este es el diccionario: " + '\n'
				+ ahorcado.mostrarPalabrasDiccionario() + "<br/>");

		//	out.println("<a href=\"administrador.html\" > Volver </a>");
	}
}
