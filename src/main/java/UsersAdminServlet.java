import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersAdminServlet extends HttpServlet {
	private HttpServletResponse response;
	private HttpServletRequest request;
	private AdministradorDeUsuarios administrador = new AdministradorDeUsuarios();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		response = resp;
		request = req;
		response.setContentType("text/html");
		String signup = request.getParameter("signup");
		if (signup != null) {
			registrarUsuario();
		} else {
			String login = request.getParameter("login");
			if (login != null) {
				iniciarSesion();
			} else {
				String puntuacion = request.getParameter("puntuacion");
				if (puntuacion != null) {
					guardarPuntuacion(puntuacion);
				} else {
					String filtrar = request.getParameter("filtrar");
					if (filtrar != null) {
						String username = request.getParameter("username");
						response.getWriter().println(administrador.buscarUsuario(username));
					} else {
						response.getWriter().println(administrador.mostrarTodos());
					}
					dibujarInterfaz();
				}
			}
		}
	}
	
	private void guardarPuntuacion(String puntuacion) throws IOException, ServletException {
		administrador.registrarPuntuacion(puntuacion);
		response.sendRedirect("AhorcadoServlet");
	}

	private void iniciarSesion() throws IOException, ServletException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (administrador.iniciarSesion(username, password)) {
			response.getWriter().println(
					"Usuario autentificado correctamente<br>");
			response.sendRedirect("seleccionarNivel.html");
		} else {
			response.getWriter().println("Usuario o password inexistentes<br>");
			response.sendRedirect("login.html");
		}
	}

	private void registrarUsuario() throws IOException, ServletException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("mail");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmacion = request.getParameter("confirmacion");
		if (password.equals(confirmacion)) {
			Usuario user = new Usuario(nombre, apellido, email, username,
					password);
			administrador.registrarUsuario(user);
			response.getWriter().println(
					"Usuario autentificado correctamente<br>");
			response.sendRedirect("seleccionarNivel.html");

		} else {
			response.getWriter().println(
					"Todos los datos deben ser llenados<br>");
			response.sendRedirect("signUp.html");

		}
	}
	
	private void dibujarInterfaz() throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("</br>");
		out.println("<FORM action=UsersAdminServlet>");
		out.println("Ingrese ID de Usuario:  <input type=text name=username>");
		out.println("<input type=submit name=filtrar value=filtrar>");
		out.println("</FORM>");
		
		out.println("<FORM action=index.html>");
		out.println("<input type=submit name=volver value=Volver>");
		out.println("</FORM>");
	}
}
