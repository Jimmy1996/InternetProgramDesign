/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftpClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *用来传送双方的对话信息，如文件目录等信息
 * @author JM
 */
public class FileDialogServer {
    
        private final int port = 2021;
        private final ServerSocket serverSocket;
        private BufferedReader br ;
        private PrintWriter pw ;
    
    public FileDialogServer() throws IOException{
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动");
    }
    
    public static void main(String[] args) throws IOException{
        new FileDialogServer().service();
    }
    
    private PrintWriter putWriter(Socket socket)throws IOException{
        OutputStream socketOut = socket.getOutputStream();//获得输出流缓冲区的地址。
        return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);
      }
    
    private BufferedReader getReader(Socket socket)throws IOException{
        InputStream socketIn = socket.getInputStream();//获得输入流缓冲区的地址
        return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));
      }
    
    public void fileDir(PrintWriter pw){
         pw.println("可供下载的文件如下：");
                //String fName1="E:\\";//给出下载目录路经
                String fName1 ="C:\\Users\\JM\\Documents\\NetBeansProjects\\互联网程序设计\\internet4_fileTCP\\src\\testFileDownload";
                File f=new File(fName1);
                String[] s=f.list();//获得目录下所有的子目录和文件名.
                int len=s.length;
                int n=0;
                long filelength=0;
                File temp;
                while(n<len){
                   temp=new File(f+"\\"+s[n]);
                   if(temp.isFile()){
                       filelength=temp.length()/1024;//字节单位KB
                       pw.println(s[n]+" ["+filelength+"KB]");
                   }else
                       pw.println(s[n]+" [DIR]");
                   n++;
                }

                pw.println("************文件下载********************");
                pw.println("输入文件全名，点击下载按钮,退出输入bye");
    }
    
    //提供通信服务
    public void service() {//单客户版本,即每次只能同时和一个客户建立通信连接。
      while (true) {
            Socket socket=null;
            try {
                socket = serverSocket.accept(); 
                //阻塞语句，监听并等待客户发起连接，有连接请求就生成一个套接字。
                System.out.println("New income: "+socket.getInetAddress());
                //本地服务器观测台显示请求的用户信息。
                br =getReader(socket);//定义字符串输入流。
                pw = putWriter(socket);//定义字符串输出流。
                if(br.readLine().equalsIgnoreCase("123-123")){
                     fileDir(pw);
                }

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
}
