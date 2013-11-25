import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Puntuacion implements java.io.Serializable {
	private String puntuacion;
	private String idUsuario;
	
	public Puntuacion(){
		puntuacion = "0";
		idUsuario = "";
	}
	
	public Puntuacion(String puntuacion, String idUsuario) {
		this.puntuacion = puntuacion;
		this.idUsuario = idUsuario;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public void serializar() {
		try {
			FileOutputStream fileOut = new FileOutputStream("puntuaciones.ser",true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in puntuaciones.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

}
