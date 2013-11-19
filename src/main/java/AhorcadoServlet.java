import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Spring;

public class AhorcadoServlet extends HttpServlet {

	Juego ahorcado = new Juego(1,"Paises");

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*String categoria = request.getParameter("categoria");
		String nivel = request.getParameter("nivel");*/
		char letra = request.getParameter("letra").charAt(0);
		
		final String dibujada=ahorcado.dibujarPalabra();
		String res = ahorcado.ingresarLetra(letra);
		
		String salida = ahorcado.dibujarPalabra();
		
		if (res==salida)
			response.getWriter().println(ahorcado.dibujarPalabra());
		else
		{
			response.getWriter().println(ahorcado.dibujarPalabra());
			response.getWriter().println(res);
		}
	}

}
