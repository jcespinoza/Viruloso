/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jay C Espinoza
 */
public class ScanXan {
    public static void main(String[] args) throws IOException{
        Scanner sc = null;
        
        try{
            sc = new Scanner(new BufferedReader( new FileReader("src/res/xanadu.txt")));
            sc.useDelimiter(",\\s*");
            while( sc.hasNext() ){
                System.out.println(sc.next());
            }
        }finally{
            if(sc != null)
                sc.close();
        }
    }
}

