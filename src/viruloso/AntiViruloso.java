/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

import java.io.File;
import javax.naming.spi.DirectoryManager;

/**
 *
 * @author Jay C Espinoza
 */
public class AntiViruloso {
    public static void main(String[] args) {
        File vir = new File("Viruloso");
        if( vir.exists() ){
            DirManagement.deleteFile(vir);
            System.out.println("Everything Deleted!");
        }else{
            System.out.println("There's no such Directory!");
        }
    }

    private static void delDir(File vir) {
        File[] files = vir.listFiles();
        for(File f : files){
            if( f.isDirectory() ){
                delDir(f);
            }
            if( f.isFile() ){
                f.delete();
                System.out.println("File " + f.getPath() + " Deleted!");
            }
        }
        vir.delete();
        System.out.println("Directory " + vir.getPath() + " Deleted!");
    }
}