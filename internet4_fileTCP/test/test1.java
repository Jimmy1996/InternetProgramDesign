
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JM
 */
public class test1 {
                
  public static void main(String[] args) throws IOException {   
String fileName = "../testFileDownload/1.txt";  

    // 一般先创建file对象  
    FileInputStream fileInput = null;  
    try {  
        File file = new File(fileName);  
        if (!file.exists()) {  
            file.createNewFile();  
        }  
        byte[] buffer = new byte[1024];  
        fileInput = new FileInputStream(file);  
        int byteread = 0;  
        // byteread表示一次读取到buffers中的数量。  
        while ((byteread = fileInput.read(buffer)) != -1) {  
            System.out.write(buffer, 0, byteread);  
        }  
  
    } catch (Exception e) {  
        // TODO: handle exception  
    } finally {  
  
        try {  
            if (fileInput != null) {  
                fileInput.close();  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
    }   
  }
}
   
