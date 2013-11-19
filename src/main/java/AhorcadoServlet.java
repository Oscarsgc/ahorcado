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

public class AhorcadoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*String categoria = request.getParameter("categoria");
		String nivel = request.getParameter("nivel");*/
		Juego ahorcado = new Juego("hola");
		
		File archivo = new File ("archivo.txt");
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		String dibujada = br.readLine();
		if(dibujada != " ")
			ahorcado.setPalabraMostrada(dibujada);
		archivo = new File ("archivo.txt");
		FileWriter fw = new FileWriter(archivo);
		PrintWriter pw = new PrintWriter(fw);

		//Juego ahorcado = new Juego(Integer.parseInt(nivel), categoria);
		 //ahorcado.obtenerPalabraDeDiccionario(Integer.parseInt(nivel),categoria);
		String salida = ahorcado.dibujarPalabra();
		response.getWriter().println(ahorcado.dibujarPalabra());
		pw.println(salida);
		response.sendRedirect("ingresar_letra.html");
	}

}
