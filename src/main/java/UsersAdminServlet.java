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
	protected void service(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		response = resp;
		request = req;
		response.setContentType("text/html");
		String signup = request.getParameter("signup");
		if (signup != null) {
			registrarUsuario();
		} else {
			iniciarSesion();
		}
	}
	
	private void iniciarSesion() throws IOException, ServletException {
		String login = request.getParameter("login");
		if (login != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (administrador.iniciarSesion(username, password)){
				response.encodeRedirectURL("seleccionarNivel.html");
				response.getWriter().println("Usuario autentificado correctamente<br>");
			} else {
				response.encodeRedirectURL("login.html");
				response.getWriter().println("Usuario o password inexistentes<br>");
			}
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
			Usuario user = new Usuario(nombre, apellido, email, username, password);
			administrador.registrarUsuario(user);
			response.encodeRedirectURL("seleccionarNivel.html");
			response.getWriter().println("Usuario autentificado correctamente<br>");
		} else {
			response.encodeRedirectURL("signUp.html");
			response.getWriter().println("Todos los datos deben ser llenados<br>");
		}
	}
}
