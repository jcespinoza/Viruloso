/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Jay C Espinoza
 */
public class RandomAccesTest {
    static Scanner sc;
    static RandomAccessFile raf;
    static RandomAccessFile count;
    public static void main(String[] args) throws FileNotFoundException, IOException {
        sc = new Scanner(System.in);
        File f = new File("src/res/info.jc");
        File fc = new File("src/res/count.jc");
        if(!f.exists()){
            f.createNewFile();
            if(fc.exists()){
                fc.delete();
            }
            fc.createNewFile();
        }
        raf =  new RandomAccessFile(f, "rw");
        count = new RandomAccessFile(fc, "rw");
        do{
            showMenu();
            switch(sc.nextInt()){
                case 1:
                    if(count.length() < 3){
                        count.writeInt(0);
                    }
                    addRecord();
                    break;
                case 2:
                    getRecord();
                    break;
                case 3:
                    getAll();
                    break;
                case 4:
                    changeAge();
                    break;
                default:
                    System.out.println("Invalid Option Selected");
            }
            System.out.println("Continue?");
        }while( sc.next().charAt(0) != 'n');
        raf.close();
        count.close();
    }

    private static void showMenu() {
        System.out.println("Menu - People I know");
        System.out.println("1. Add Record");
        System.out.println("2. Get Record");
        System.out.println("3. Get 'em all");
        System.out.println("4. Change somebody's age");
        System.out.println("5. Get out");
    }

    private static void addRecord() throws IOException {
        System.out.println("Enter the name");
        String name = sc.next();
        System.out.println("Enter the age");
        int age = sc.nextInt();
        System.out.println("Is relative? Y/N");
        char fam = sc.next().toUpperCase().charAt(0);
        
        if(fam != 'Y' && fam != 'N'){
            System.out.println("Invalid answer");
            count.seek(0);
            if( count.readInt() == 0){
                count.seek(0);
                count.writeInt(-1);
            }
            return;
        }

        raf.seek(raf.length());
            count.seek(0);
            raf.writeInt( count.readInt() );
        raf.writeUTF(name);
        raf.writeInt(age);
        raf.writeBoolean(fam == 'Y');
        
        count.seek(0);
        int c = count.readInt() + 1;
        count.seek(0);
        count.writeInt(c);
        System.out.println("Record code " + (c-1) + " created succesfully");
    }

    private static void getRecord() throws IOException {
        if( raf.length() == 0){
            System.out.println("File's empty");
            return;
        }
        raf.seek(0);
        System.out.println("Enter the record code");
        int code = sc.nextInt();
        while(raf.getFilePointer() < raf.length()){
            if( raf.readInt() == code){
                System.out.println("Name: " + raf.readUTF());
                System.out.println("Age: " + raf.readInt());
                System.out.println("Is relative?: " + ( (raf.readBoolean()) ?
                        "Yes" : "No") );
                return;
            }else{
                raf.readUTF();
                raf.readInt();
                raf.readBoolean();
            }
        }
        System.out.println("Code wasn't found!");
    }

    private static void getAll() throws IOException {
        if( raf.length() == 0){
            System.out.println("File's empty");
            return;
        }
        System.out.println("Would you like to see the codes too? Y/N");
        char res = sc.next().toUpperCase().charAt(0);
        boolean yes = false;
        if( res == 'Y' ){
            yes = true;
        }else if(res == 'N'){
            yes = false;
        }else{
            System.out.println("Wrong answer!");
            return;
        }

        System.out.println("\n\nPEOPLE I KNOW");
        raf.seek(0);
        while(raf.getFilePointer() < raf.length()){
            if(yes){
                System.out.println("Code: " + raf.readInt());
            }else{
                raf.readInt();
            }
            System.out.println("Name: " + raf.readUTF());
            System.out.println("Age: " + raf.readInt());
            System.out.println("Is relative?: " + ( (raf.readBoolean()) ?
                    "Yes" : "No") + "\n");
        }
    }

    private static void changeAge() throws IOException {
        if( raf.length() == 0){
            System.out.println("File's empty");
            return;
        }
        System.out.println("Enter the code");
        int code = sc.nextInt();
        
        raf.seek(0);
        while(raf.getFilePointer() < raf.length()){
            if(raf.readInt() == code){
                System.out.println("Enter the new age");
                int newAge = sc.nextInt();
                
                raf.readUTF();
                raf.writeInt(newAge);
                System.out.println("Done!");
                return;
            }else{
                raf.readUTF();
                raf.readInt();
                raf.readBoolean();
            }
        }
        System.out.println("Code wasn't found!");
    }
}
