/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2_dialog;

import java.io.*;
import java.net.*;

/**
 *
 * @author JM
 */
public class Server {
    
    private int port=8008;
    private ServerSocket serversocket; //服务器套接字
    
    public Server() throws IOException{
        //构造函数
        serversocket = new ServerSocket(8008);
        System.out.println("服务器启动");
    }
    
    private PrintWriter putWriter(Socket socket) throws IOException{
        OutputStream socketOut = socket.getOutputStream(); //获得输出流缓冲区的地址
        return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);
    }
    
    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));
    }
    
    public void service(){
        while(true){
          Socket socket = null;
          try{
              socket = serversocket.accept();
              //阻塞语句，监听并等待客户发起连接，有连接请求就生成一个套接字
              System.out.println("New income:"+socket.getInetAddress());
              //本地服务器观测台显示请求的用户信息
              BufferedReader br = getReader(socket); //定义字符串输入流
              PrintWriter pw = putWriter(socket); //定义字符串输出流
              pw.println("来自服务器：");
              String msg;
              while((msg=br.readLine())!=null){
                  //阻塞语句，从输入流中读入一行字符串
                  pw.println("来自服务器："+msg); //向输出流中输出一行字符串
                   pw.println("来自服务器："+msg); //向输出流中输出一行字符串
                  if(msg.contains("bye")) break;
              }
          }catch(IOException e){
              e.printStackTrace();
          }finally{
              try{
                  if(socket!=null){
                      socket.close();  //断开连接
                  }
              }catch(IOException e){
                  e.printStackTrace();
              }
          }
        }
    }
    public static void main(String[] args) throws IOException{
        new Server().service();
    }
}
