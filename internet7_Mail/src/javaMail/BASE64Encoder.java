/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaMail;

/**
 *
 * @author JM
 */
public class BASE64Encoder {
       
       public static void main(String args[]){
           String username="13694252450@163.com";
           String password= "Aa2862040";
           username = new sun.misc.BASE64Encoder().encode(username.getBytes());
           password = new sun.misc.BASE64Encoder().encode(password.getBytes());
           System.out.println(username);//显示用户名的编码结果
           System.out.println(password);//显示密码的编码结果  
            System.out.println(new sun.misc.BASE64Encoder().encode("wodemimashi286204".getBytes()));
}
}
