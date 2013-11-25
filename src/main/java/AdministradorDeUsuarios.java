import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class AdministradorDeUsuarios {
	private ArrayList<Usuario> usuarios;
	private Usuario userLoged;

	public AdministradorDeUsuarios() {
		usuarios = new ArrayList<Usuario>();
		deserializar();
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

	public void deserializar() {
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

}