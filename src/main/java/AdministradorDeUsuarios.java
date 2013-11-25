import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class AdministradorDeUsuarios {
	private ArrayList<Usuario> usuarios;
	private ArrayList<Puntuacion> puntuaciones;
	private Usuario userLoged;

	public AdministradorDeUsuarios() {
		usuarios = new ArrayList<Usuario>();
		puntuaciones = new ArrayList<Puntuacion>();
		deserializarUsuarios();
		deserializarPuntuaciones();
		userLoged = null;
	}

	public Usuario isUserLogued() {
		return userLoged;
	}

	public void setUserLogued(Usuario userLogued) {
		this.userLoged = userLogued;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void registrarUsuario(Usuario user) {
		usuarios.add(user);
		userLoged = user;
		user.serializar();
	}

	public boolean iniciarSesion(String username, String password) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (username.equals(usuarios.get(i).getIdUsuario())
					&& password.equals(usuarios.get(i).getPassword())) {
				userLoged = usuarios.get(i);
				return true;
			}
		}
		return false;
	}

	public void cerrarSesion() {
		if (userLoged != null)
			userLoged = null;
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

	public void registrarPuntuacion(String puntuacion) {
		if (userLoged != null) {
			Puntuacion points = new Puntuacion(puntuacion,
					userLoged.getIdUsuario());
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
			res += puntuaciones.get(i).getIdUsuario() + ": "
					+ puntuaciones.get(i).getPuntuacion() + "<br>";
		}
		return res;
	}
	
	public boolean editarUsuario(Usuario user) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getIdUsuario().equals(userLoged.getIdUsuario())) {
				usuarios.set(i, user);
				editarPuntuacionesDeUsuario(user.getIdUsuario());
				userLoged = user;
				return true;
			}
		}
		return false;
	}
	
	public void editarPuntuacionesDeUsuario(String userId) {
		Puntuacion temp = new Puntuacion();
		temp.setIdUsuario(userId);
		for (int i = 0; i < puntuaciones.size(); i++) {
			if (puntuaciones.get(i).getIdUsuario().equals(userLoged.getIdUsuario())) {
				temp.setPuntuacion(puntuaciones.get(i).getPuntuacion());
				puntuaciones.set(i, temp);
			}
		}
	}

	public String buscarPuntuacionesUsuario(String userId) {
		String res = "";
		int total = 0;
		for (int i = 0; i < puntuaciones.size(); i++) {
			if (puntuaciones.get(i).getIdUsuario().equals(userId)) {
				res += puntuaciones.get(i).getPuntuacion() + "<br>";
				total += Integer.parseInt(puntuaciones.get(i).getPuntuacion());
			}
		}
		res += "Total = " + total + "<br>";
		return res;
	}
	
	public void ordenarPuntuacionesAscendente(){
		Puntuacion temp;
		for (int i = 1; i < puntuaciones.size(); i++) {
			for (int j = 0; j < puntuaciones.size()-1; j++) {
				if (Integer.parseInt(puntuaciones.get(j).getPuntuacion()) > Integer.parseInt(puntuaciones.get(j+1).getPuntuacion())){
				    temp = puntuaciones.get(j);
				    puntuaciones.set(j, puntuaciones.get(j+1));
				    puntuaciones.set(j+1, temp);
				}
			}
		}
	}
	
	public void ordenarPuntuacionesDescendente(){
		Puntuacion temp;
		for (int i = 1; i < puntuaciones.size(); i++) {
			for (int j = 0; j < puntuaciones.size()-1; j++) {
				if (Integer.parseInt(puntuaciones.get(j).getPuntuacion()) < Integer.parseInt(puntuaciones.get(j+1).getPuntuacion())){
				    temp = puntuaciones.get(j);
				    puntuaciones.set(j, puntuaciones.get(j+1));
				    puntuaciones.set(j+1, temp);
				}
			}
		}
	}
}
