import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Usuario implements java.io.Serializable {
	private String nombre;
	private String apellidos;
	private String email;
	private String idUsuario;
	private String password;

	public Usuario() {
		this.nombre = "";
		this.apellidos = "";
		this.email = "";
		this.idUsuario = "";
		this.password = "";
	}

	public Usuario(String name, String lastName, String email, String userId,
			String pass) {
		this.apellidos = lastName;
		this.email = email;
		this.idUsuario = userId;
		this.nombre = name;
		this.password = pass;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void serializar() {
		try {
			FileOutputStream fileOut = new FileOutputStream("usuarios.ser",true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in usuarios.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

}
