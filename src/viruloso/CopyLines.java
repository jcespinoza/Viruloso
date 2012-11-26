/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Jay C Espinoza
 */
public class CopyLines {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        PrintWriter pw = null;
        
        try{
            br = new BufferedReader( new FileReader("src/res/xanadu.txt"));
            pw = new PrintWriter(new FileWriter("src/res/output.mp3"));
            
            String l;
            while ( (l = br.readLine()) != null){
                System.out.println("Writing: "+ l);
                pw.println(l);
            }
        }finally{
            if( br != null)
                br.close();
            if( pw != null)
                pw.close();
        }
    }
}