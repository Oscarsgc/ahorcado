import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AhorcadoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String categoria = request.getParameter("categoria");
		String nivel = request.getParameter("nivel");
		Juego ahorcado = new Juego(Integer.parseInt(nivel), categoria);
		 ahorcado.obtenerPalabraDeDiccionario(Integer.parseInt(nivel),
		 categoria);
		response.getWriter().println(ahorcado.dibujarPalabra());
	}

}
