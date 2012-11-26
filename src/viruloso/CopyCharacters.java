/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

/**
 *
 * @author Jay C Espinoza
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters {
    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("src/res/xanadu.txt");
            outputStream = new FileWriter("src/res/characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1 && c != 65) {
                System.out.println("Writing: " + c);
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
