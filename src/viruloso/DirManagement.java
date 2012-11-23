package viruloso;


import java.io.File;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jay C Espinoza
 */
public class DirManagement {
    private static File currentDir;
    private static File[] cDirFiles;
    private static File parentDir;
    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        cd("Viruloso");
        showFiles();
        showStatistics();

        int result = -1;
        do{
            String c = readCommand();
            result = executeCommand(c);
        }while(result > -1);
    }
    
    public static void deleteFile(File dir){
        if( dir.isDirectory() ){
            File[] files = dir.listFiles();
            for(File f : files){
                deleteFile(f);
            }
        }
        dir.delete();
        System.out.println("File " + dir.getPath() + " deleted!");
    }
    
    public static void showFiles(){
        File dir = currentDir;
        System.out.printf("%-20s %s\n", "<DIR>", "..");
        if( dir.exists() && dir.isDirectory() ){
            cDirFiles = dir.listFiles();
            for(File f: cDirFiles){
                if( f.isDirectory() ){
                    System.out.printf("%-20s %s\n","<DIR>", f.getName());
                }
                if( f.isFile() ){
                    System.out.printf("%-20s %s\n","", f.getName());
                }
            }
        }
    }
    
    public static void showStatistics(){
        File dir = currentDir;
        if( dir.exists()){
            int countF = 0;
            int countD = 0;
            long cBytes = currentDir.length();
            for(File f: dir.listFiles()){
                if( f.isDirectory() )
                    countD++;
                if( f.isFile() )
                    countF++;
            }
            System.out.printf("%5d (Files) %10d bytes\n", countF, cBytes);
        }
    }

    private static void cd(String path) {
        File tempF = new File(path);
        if( tempF.exists() ){
            if( tempF.isDirectory() ){
                currentDir = tempF;
                cDirFiles = currentDir.listFiles();
                parentDir = currentDir.getAbsoluteFile().getParentFile();
            }
        }else{
            throw new IllegalArgumentException("No such directory: " + path);
        }
    }

    private static String readCommand() {
        System.out.println("Enter command:");
        return sc.next();
    }

    private static int executeCommand(String c) {
        switch(c.toUpperCase()){
            case "$CD":
                String tPath = sc.next();
                try{
                    if(tPath.equals("..")){
                        cd(parentDir.getPath());
                    }else{
                        cd(currentDir.getPath() + "/" + tPath);
                    }
                    showFiles();
                    showStatistics();
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                return 0;
            case "$EXIT":
                return -1;
            default:
                System.out.println(c.substring(1) + " is not a valid command");
                return 0;
        }
    }
}