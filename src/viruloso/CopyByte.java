/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

/**
 *
 * @author Jay C Espinoza
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyByte {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("src/res/xanadu.txt");
            out = new FileOutputStream("src/res/outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                System.out.println("Writing: " + (char)c);
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
