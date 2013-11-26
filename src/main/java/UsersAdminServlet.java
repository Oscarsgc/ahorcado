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
					String editar = request.getParameter("editar");
					if (editar != null) {
						manejarEdicionDeUsuarios(editar);
					} else {
						manejarPuntuaciones();
					}
				}
			}
		}
	}
	
	private void manejarEdicionDeUsuarios(String editar) throws IOException, ServletException {
		if (editar.equals("editar")) {
			editarDatosUsuario();
		} else {
			interazEditarUsuario();
		}
	}
	
	private void manejarPuntuaciones() throws IOException, ServletException {
		String filtrar = request.getParameter("filtrar");
		if (filtrar != null) {
			String username = request.getParameter("username");
			response.getWriter().println(
					administrador
							.buscarPuntuacionesUsuario(username));
		} else {
			mostrarPuntuaciones();
		}
		dibujarInterfaz();
	}
	
	private void mostrarPuntuaciones() throws IOException, ServletException {
		String ordenar = request.getParameter("ordenar");
		if (ordenar != null) {
			String sentido = request
					.getParameter("sentido");
			if (sentido.equals("ascendente")) {
				administrador
						.ordenarPuntuacionesAscendente();
				response.getWriter().println(
						administrador.mostrarTodos());
			} else {
				administrador
						.ordenarPuntuacionesDescendente();
				response.getWriter().println(
						administrador.mostrarTodos());
			}
		} else {
			response.getWriter().println(
					administrador.mostrarTodos());
		}
	}

	private void editarDatosUsuario() throws IOException,
			ServletException {
		String nombre = request
				.getParameter("nombre");
		String apellido = request
				.getParameter("apellido");
		String username = request
				.getParameter("username");
		String email = request.getParameter("mail");
		if(apellido!=null && nombre!=null && username!=null && email!=null) {
			Usuario user = new Usuario(nombre, apellido,
					email, username, "");
			if (administrador.editarUsuario(user))
				response.sendRedirect("index.html");
			else
				response.sendRedirect("UsersAdminServlet?editar=EditarDatos&error=error");
		} else {
			response.sendRedirect("UsersAdminServlet?editar=EditarDatos&error=faltan");
		}
	}

	private void guardarPuntuacion(String puntuacion) throws IOException,
			ServletException {
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

	private void interazEditarUsuario() throws ServletException, IOException {
		if (administrador.getLoggedUser() != null) {
			PrintWriter out = response.getWriter();
			String error = request.getParameter("error");
			if(error !=null){
				if(error.equals("error"))
					out.println("Error al editar usuario </br>");
				else
					out.println("Debe llenar todos los campos</br>");
			}
			out.println("</br>");
			out.println("<FORM action=UsersAdminServlet>");
			out.println("Nombre:  Nombre: <input type=text name=nombre value="
					+ administrador.getLoggedUser().getNombre()
					+ "><br>"
					+ "Apellidos: <input type=text name=apellido value="
					+ administrador.getLoggedUser().getApellidos()
					+ "><br>"
					+ "E-mail: <input type=text name=mail value="
					+ administrador.getLoggedUser().getEmail()
					+ "><br>"
					+ "Nombre	de usuario: <input type=text name=username value="
					+ administrador.getLoggedUser().getIdUsuario() + "><br>");
			out.println("<input type=submit name=editar value=editar>");
			out.println("</FORM>");
		} else {
			response.getWriter().println(
					"Debe iniciar sesion para realizar esta operacion");
		}
	}

	private void dibujarInterfaz() throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("</br>");
		out.println("<FORM action=UsersAdminServlet>");
		out.println("Ingrese ID de Usuario:  <input type=text name=username>");
		out.println("<input type=submit name=filtrar value=filtrar>");
		out.println("</FORM>");

		out.println("<FORM action=UsersAdminServlet>");
		out.println("Ordenar de forma:  <input type=radio name=sentido value=ascendente checked> Ascendente "
				+ "<input type=radio name=sentido value=descendente> Descendente<br> ");
		out.println("<input type=submit name=ordenar value=ordenar>");
		out.println("</FORM>");

		out.println("<FORM action=index.html>");
		out.println("<input type=submit name=volver value=Volver>");
		out.println("</FORM>");
	}
}
