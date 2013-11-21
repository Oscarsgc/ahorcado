import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.swing.Spring;

public class AhorcadoServlet extends HttpServlet {

	Juego ahorcado = new Juego();
	final Diccionario dic = new Diccionario();
	String category = "";
	int level = 0;
	String dibujada = "";

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String categoria = request.getParameter("categoria");
		int nivel = Integer.parseInt(request.getParameter("nivel"));
		response.getWriter().println("CATEGORIA: "+categoria);
		response.getWriter().println("NIVEL: "+nivel);
		
		if (category.compareTo(categoria) != 0 || level != nivel) {
		
			ahorcado.setPalabra(dic.palabraAleatoriaPorCategoria(categoria,
					nivel));
		
			level = nivel;
			category = categoria;
			dibujada = ahorcado.dibujarPalabra();
		} else {
		
			ahorcado.setPalabraMostrada(dibujada);
		}
		
		char letra = request.getParameter("letra").charAt(0);

		String res = ahorcado.ingresarLetra(letra);
		

		String salida = ahorcado.dibujarPalabra();
		dibujada=salida;

		if (res == salida)
			response.getWriter().println(ahorcado.dibujarPalabra());
		else {
			response.getWriter().println(ahorcado.dibujarPalabra());
			response.getWriter().println(res);
		}
		
	}

}
