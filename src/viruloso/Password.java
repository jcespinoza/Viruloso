/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viruloso;

import java.io.Console;
import java.util.Arrays;

/**
 *
 * @author Jay C Espinoza
 */
public class Password {
    private static String user = "jayc";
    private static char[] pass = "secure25".toCharArray();
    
    public static void main(String[] args) {
        Console c = System.console();
        
        if( c == null){
            System.out.println("There's no freaking console!");
            System.exit(1);
        }
        
        String user = c.readLine("Enter your username: ");
        char oldPass[] = c.readPassword("Enter your password: ");
        
        if( verify(user, oldPass)){
            boolean noMatch;
            do{
                char newPass1[] = c.readPassword("Enter your new pass: ");
                char newPass2[] = c.readPassword("Enter your new pass again");
                noMatch = ! Arrays.equals(newPass1, newPass2);
                if(noMatch){
                    c.format("Passwords don't match. Try again%n");
                }else{
                    change(user, newPass1);
                    c.format("Password for %s changed.%n", user);
                }
                Arrays.fill(newPass1, ' ');
                Arrays.fill(newPass2, ' ');
            }while(noMatch);
        }else{
            c.format("Wrong credentials combination");
        }
        Arrays.fill(oldPass,' ');
    }

    private static boolean verify(String user, char[] oldPass) {
        return ( user.equals(Password.user) && Arrays.equals(oldPass, Password.pass) );
    }

    private static void change(String user, char[] newPass1) {
        Password.user = user;
        Password.pass = newPass1;
    }
}