/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Timer;

/**
 *
 * @author Jay C Espinoza
 */
public class Viruloso{
    private static int maxFiles = 10;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            if(args.length > 0 && args[0] != null){
                System.out.print("Settin maximum number of folders and files per folder:");
                maxFiles = Integer.parseInt(args[0]);                
            }
        }catch(Exception ex){
            System.out.println("\nThat was not an integer you stupid!" +
                    "Since you're that dumb I'll punish you setting maxFiles to 1000");
        }

        File mas = new File("Viruloso");
        
        if( !mas.exists() ){
            System.out.println("I don't exist!");            
            System.out.println("Creating...");
            if( mas.mkdir() ){
                createSubDirs();
            }
            System.out.println("....Created!");
        }else{
            System.out.println("I already exist!");
        }
    }

    private static void createSubDirs() {
        for(int i = 0; i < maxFiles; i++){
            File tempDir = new File("Viruloso/Viruloso" + i);
            if( !tempDir.exists() ){
                if( tempDir.mkdir() ){
                    createFiles(tempDir);
                }
            }
        }
    }

    private static void createFiles(File tempDir) {
        String parentPath = tempDir.getPath();
        for(int i = 0; i < maxFiles; i++){
            File tempFile = new File(parentPath + "/Archivo" + i + ".txt");
            if( !tempFile.exists() ){
                try {
                    tempFile.createNewFile();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}