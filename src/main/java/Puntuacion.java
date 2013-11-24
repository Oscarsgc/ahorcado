public class Puntuacion {
	private int puntuacion;

	public Puntuacion() {
		this.puntuacion = 0;

	}

	public Puntuacion(int puntos) {
		this.puntuacion = puntos;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void aumentarPuntuacion() {
		this.puntuacion++;
	}

}
