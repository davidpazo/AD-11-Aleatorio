package aleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ** @author oracle ***
 */
public class Aleatorio {

    private final String ruta = "/home/oracle/Desktop/Pruebas/texto11.txt";
    private final File file = new File(ruta);

    String cod[] = {"p1", "p2", "p3"};
    String desc[] = {"parafusos", "cravos", "tachas"};
    int prezo[] = {3, 4, 5};
    String prezoOut;

    RandomAccessFile raf;

    public static void main(String args[]) throws Exception {
        Aleatorio ale = new Aleatorio();
        ale.Escribir();
        ale.Leer();
        //System.out.println(padRight("Howto", 20) + "*"+ "");
        //System.out.println(padLeft("Howto", 20) + "*");
    }

    public void Escribir() throws IOException {

         try {
           raf = new RandomAccessFile(file, "rw");
           String cod = "";
           String des = "";
           int precio = 0;
           for (int i = 0; i < this.cod.length; i++) {
               cod = this.cod[i];
               des = desc[i];
               precio = prezo[i];
               cod = padRight(cod, 3);
               des = padRight(des, 10);
               raf.writeChars(cod);
               raf.writeChars(des);
               raf.writeInt(precio);
               System.out.println(cod + des + precio);
           }
           raf.close();
       } catch (FileNotFoundException ex) {
           System.out.println("File Not Found");
       } catch (IOException ex) {
           System.out.println("Error I/O");
       }
   }

    public void Leer() throws IOException {

       String cod = "";
       String des = "";
       int precio = 0;
       try {
           raf = new RandomAccessFile(file, "rw");
           raf.seek(Pos(2));
           for (int i = 0; i < 3; i++) {
               cod = cod + raf.readChar();
           }
           for (int i = 0; i < 10; i++) {
               des = des + raf.readChar();
           }
           precio = raf.readInt();
           System.out.println("\n"+cod + des + precio);
           raf.close();
       } catch (FileNotFoundException ex) {
           System.out.println("File Not Found");
       } catch (IOException ex) {
           System.out.println("Error I/O");
       }
   }
        

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    public long Pos(int nreg) {
        long pos = (nreg - 1) * 30;
        return pos;
        //numero de registro -1 x longitud
    }

}
