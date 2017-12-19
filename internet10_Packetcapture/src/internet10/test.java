/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internet10;

/**
 *
 * @author JM
 */
public class test {       
    public static void main(String args[]){
         String[] split = System.getProperty("java.library.path").split(";");  
                for (String string : split) {  
                    System.out.println(string);  
}  
    }
}
