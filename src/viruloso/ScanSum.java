/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Jay C Espinoza
 */
public class ScanSum {
    public static void main(String[] args) throws IOException{
        Scanner sc = null;
        double sum = 0;
        String val = null;
        
        try{
            sc = new Scanner(new BufferedReader(new FileReader("src/res/usnumbers.txt")));
            sc.useLocale(Locale.US);
            
            while( sc.hasNext()){
                if( sc.hasNextDouble()){
                    val = sc.nextDouble() + "";
                    System.out.println("I had: " + val + " and added it to " + sum);
                    sum += Double.parseDouble(val);
                }else{
                    val = sc.next();
                    System.out.println("I had: " + val);
                }
            }
        }finally{
            if(sc != null)
                sc.close();
        }
    }
}
