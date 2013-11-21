import java.io.*;

public class SerializeDemo
{
	public void serializar(){
		Palabra p = new Palabra();
	      p.setDificultad(2);
	      p.setPalabra("Mongolia");;
	      p.setUso(false);;
	      try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("palabra.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(p);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in palabra.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
   public static void main(String [] args)
   {
      Palabra p = new Palabra();
      p.setDificultad(2);
      p.setPalabra("Mongolia");;
      p.setUso(false);;
      try
      {
         FileOutputStream fileOut =
         new FileOutputStream("/tmp/palabra.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(p);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/palabra.ser");
      }catch(IOException i)
      {
          i.printStackTrace();
      }
   }
}