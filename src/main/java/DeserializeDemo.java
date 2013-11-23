import java.io.*;

public class DeserializeDemo {
	public static void main(String[] args) {
		Palabra p = null;
		try {
			FileInputStream fileIn = new FileInputStream("/tmp/palabra.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			p = (Palabra) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Palabra class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Deserialized Palabra...");
		System.out.println("Dificultad: " + p.getDificultad());
		System.out.println("Palabra: " + p.getPalabra());
	}
}