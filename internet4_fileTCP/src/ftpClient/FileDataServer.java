/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftpClient;

import java.io.*;
import java.net.*;

/**
 * 数据传输程序专门提供文件上传和下载服务
 * @author JM
 */
public class FileDataServer {
        private int port = 2020;
        private BufferedReader br;
        private PrintWriter pw;
        private ServerSocket serverSocket;
    
    public FileDataServer() throws IOException{
        serverSocket = new ServerSocket(port);
    }
   
    //提供通信服务
    public void service() {//单客户版本,即每次只能同时和一个客户建立通信连接。
      while (true) {
            Socket socket=null;
            try {
                socket = serverSocket.accept(); 
                br = getReader(socket);
                
                String downloadFilename = br.readLine();
                System.out.println(downloadFilename); 
                System.out.println("开始下载");
                String  downFileName = "C:\\Users\\JM\\Documents\\NetBeansProjects\\互联网程序设计\\internet4_fileTCP\\src\\testFileDownload\\"+downloadFilename;
                
                OutputStream socketOut = socket.getOutputStream(); 
                FileInputStream fileIn = new FileInputStream(downFileName); 
                byte[] buff = new byte[1024];
                int len = fileIn.read(buff); 
                   System.out.println(len);
                while(len !=-1)
                {     
                     socketOut.write(buff,0,len);
                     socketOut.flush();
                     len = fileIn.read(buff);
                       System.out.println(len);
                    }  
                if(socket!=null) socket.close();
        }catch (IOException e) {
                   e.printStackTrace();
       }finally {
                  try{
                        if(socket!=null)
                        socket.close();  //断开连接
                     }catch (IOException e) {e.printStackTrace();}
               }
            }
        }
    
    
   public static void main(String[] args) throws IOException{
        new FileDataServer().service();
    }
    
    private PrintWriter putWriter(Socket socket)throws IOException{
        OutputStream socketOut = socket.getOutputStream();//获得输出流缓冲区的地址。
        return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);
      }
    
    private BufferedReader getReader(Socket socket)throws IOException{
        InputStream socketIn = socket.getInputStream();//获得输入流缓冲区的地址
        return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));
      }
    

}
