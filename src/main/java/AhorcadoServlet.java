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

	Juego ahorcado = new Juego(3,"Paises");

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*String categoria = request.getParameter("categoria");
		String nivel = request.getParameter("nivel");*/
		char letra = request.getParameter("letra").charAt(0);
		
		final String dibujada=ahorcado.dibujarPalabra();
		
//		File archivo = new File ("archivo.txt");
//		FileReader fr = new FileReader (archivo);
//		BufferedReader br = new BufferedReader(fr);
//		String dibujada = br.readLine();
//		if(dibujada == null)
//			dibujada = ahorcado.dibujarPalabra();
//		else
//			ahorcado.setPalabraMostrada(dibujada);
//		fr.close();
//		FileWriter fw = new FileWriter(archivo);
//		PrintWriter pw = new PrintWriter(fw);

		//Juego ahorcado = new Juego(Integer.parseInt(nivel), categoria);
		//ahorcado.obtenerPalabraDeDiccionario(Integer.parseInt(nivel),categoria);
		String res = ahorcado.ingresarLetra(letra);
		
		String salida = ahorcado.dibujarPalabra();
		
		if (res==salida)
			response.getWriter().println(ahorcado.dibujarPalabra());
		else
		{
			response.getWriter().println(ahorcado.dibujarPalabra());
			response.getWriter().println(res);
		}
//		pw.println(salida);
//		pw.close();
	}

}
