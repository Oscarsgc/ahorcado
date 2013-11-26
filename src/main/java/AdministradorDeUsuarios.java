import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AdministradorDeUsuarios {
	private ArrayList<Usuario> usuarios;
	private ArrayList<Puntuacion> puntuaciones;
	private Usuario loggedUser;

	public AdministradorDeUsuarios() {
		usuarios = new ArrayList<Usuario>();
		puntuaciones = new ArrayList<Puntuacion>();
		deserializarUsuarios();
		deserializarPuntuaciones();
		loggedUser = null;
	}

	public Usuario getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Usuario userLogued) {
		this.loggedUser = userLogued;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void registrarUsuario(Usuario user) {
		usuarios.add(user);
		loggedUser = user;
		user.serializar();
	}

	public boolean iniciarSesion(String username, String password) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (username.equals(usuarios.get(i).getIdUsuario())
					&& password.equals(usuarios.get(i).getPassword())) {
				loggedUser = usuarios.get(i);
				return true;
			}
		}
		return false;
	}

	public void cerrarSesion() {
		if (loggedUser != null)
			loggedUser = null;
	}

	public void deserializarUsuarios() {
		Usuario user = null;
		boolean fin = false;
		try {
			FileInputStream fileIn = new FileInputStream("usuarios.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			while (!fin) {
				user = (Usuario) in.readObject();
				usuarios.add(user);
				System.out.println("Usuario obtenido correctamente");
			}
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Usuario class not found");
			c.printStackTrace();
		}
	}

	public void registrarPuntuacion(String puntuacion, String palabra) {
		if (loggedUser != null) {
			Puntuacion points = new Puntuacion(puntuacion,
					loggedUser.getIdUsuario(), palabra);
			puntuaciones.add(points);
			points.serializar();
		}
	}

	public void deserializarPuntuaciones() {
		Puntuacion puntuacion = null;
		boolean fin = false;
		try {
			FileInputStream fileIn = new FileInputStream("puntuaciones.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			while (!fin) {
				puntuacion = (Puntuacion) in.readObject();
				puntuaciones.add(puntuacion);
				System.out.println("Puntuacion obtenida correctamente");
			}
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Puntuacion class not found");
			c.printStackTrace();
		}
	}

	public String mostrarTodos() {
		String res = "";
		for (int i = 0; i < puntuaciones.size(); i++) {
			res += puntuaciones.get(i).getPalabra() + ": "
					+ puntuaciones.get(i).getPuntuacion() + " ("
					+ puntuaciones.get(i).getIdUsuario() + ") <br>";
		}
		return res;
	}

	public boolean editarUsuario(Usuario user) {
		if (loggedUser != null) {
			user.setPassword(loggedUser.getPassword());
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getIdUsuario()
						.equals(loggedUser.getIdUsuario())) {
					usuarios.set(i, user);
					editarPuntuacionesDeUsuario(user.getIdUsuario());
					editarArchivoUsuarios();
					loggedUser = user;
					return true;
				}
			}
		}
		return false;
	}

	public void editarPuntuacionesDeUsuario(String userId) {
		Puntuacion temp = new Puntuacion();
		temp.setIdUsuario(userId);
		for (int i = 0; i < puntuaciones.size(); i++) {
			if (puntuaciones.get(i).getIdUsuario()
					.equals(loggedUser.getIdUsuario())) {
				temp.setPuntuacion(puntuaciones.get(i).getPuntuacion());
				puntuaciones.set(i, temp);
			}
		}
		editarArchivoPuntuaciones();
	}

	public void editarArchivoUsuarios() {
		limpiarArchivoUsuarios();
		for (int i = 1; i < usuarios.size(); i++) {
			usuarios.get(i).serializar();
		}
	}

	public void limpiarArchivoUsuarios() {
		if (usuarios.size() > 0) {
			try {
				FileOutputStream fileOut = new FileOutputStream("usuarios.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(usuarios.get(0));
				out.close();
				fileOut.close();
				System.out.println("Serialized data is saved in usuarios.ser");
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
	}

	public void editarArchivoPuntuaciones() {
		limpiarArchivoPuntuaciones();
		for (int i = 1; i < puntuaciones.size(); i++) {
			puntuaciones.get(i).serializar();
		}
	}

	public void limpiarArchivoPuntuaciones() {
		if (puntuaciones.size() > 0) {
			try {
				FileOutputStream fileOut = new FileOutputStream(
						"puntuaciones.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(puntuaciones.get(0));
				out.close();
				fileOut.close();
				System.out
						.println("Serialized data is saved in puntuaciones.ser");
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
	}

	public String buscarPuntuacionesUsuario(String userId) {
		String res = "Puntuaciones de " + userId + ": <br>";
		int total = 0;
		for (int i = 0; i < puntuaciones.size(); i++) {
			if (puntuaciones.get(i).getIdUsuario().equals(userId)) {
				res += "Palabra: " + puntuaciones.get(i).getPalabra()
						+ " Punuacion: " + puntuaciones.get(i).getPuntuacion()
						+ "<br>";
				total += Integer.parseInt(puntuaciones.get(i).getPuntuacion());
			}
		}
		res += "Total = " + total + "<br>";
		return res;
	}

	public void ordenarPuntuacionesAscendente() {
		Puntuacion temp;
		for (int i = 1; i < puntuaciones.size(); i++) {
			for (int j = 0; j < puntuaciones.size() - 1; j++) {
				if (Integer.parseInt(puntuaciones.get(j).getPuntuacion()) > Integer
						.parseInt(puntuaciones.get(j + 1).getPuntuacion())) {
					temp = puntuaciones.get(j);
					puntuaciones.set(j, puntuaciones.get(j + 1));
					puntuaciones.set(j + 1, temp);
				}
			}
		}
	}

	public void ordenarPuntuacionesDescendente() {
		Puntuacion temp;
		for (int i = 1; i < puntuaciones.size(); i++) {
			for (int j = 0; j < puntuaciones.size() - 1; j++) {
				if (Integer.parseInt(puntuaciones.get(j).getPuntuacion()) < Integer
						.parseInt(puntuaciones.get(j + 1).getPuntuacion())) {
					temp = puntuaciones.get(j);
					puntuaciones.set(j, puntuaciones.get(j + 1));
					puntuaciones.set(j + 1, temp);
				}
			}
		}
	}
}
