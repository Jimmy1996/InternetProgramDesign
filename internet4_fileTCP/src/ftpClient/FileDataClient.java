///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package ftpClient;

import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author JM
 */
public class FileDataClient {
    //套接字程序设计.  
    private Socket dataSocket;
    //用于字节和字符之间转换用的变量.
    private PrintWriter pw;
    private BufferedReader br;

    public FileDataClient(String ip,int port) throws IOException{
        
        dataSocket=new Socket(ip,port);
             //主动向服务器发起连接,实现TCP中三次握手的过程。
             //若不成功(网络问题,地址错误,服务器资源紧张等),抛出错误，其错误信息交由调用者处理。
             //若成功,做下面两件事情。
         pw = putWriter(dataSocket);
             //得到网络输出字节流地址,并装饰成网络输出字符流
         br= getReader(dataSocket);
            //得到网络输入字节流地址,并装饰成网络输入字符流
    }
   
    /*
    实现文件下载gongneng
    */
    public String  fileGet(String fileName) throws Exception{
      
        boolean flag = false;
        if(dataSocket!=null){
           
            //（1）文件保存对话框
            JFileChooser chooser = new JFileChooser();
            File saveFile = new File(fileName); 
            chooser.setSelectedFile(saveFile);
            int stat = chooser.showSaveDialog(null);
            
            if(stat==JFileChooser.APPROVE_OPTION)   //获得选中的文件对象
                 saveFile = chooser.getSelectedFile();
            else saveFile=null;
            
            if(saveFile!=null){
                FileOutputStream fileOut = new FileOutputStream(saveFile); //新建本地空文件 
                OutputStream  socketOut = dataSocket.getOutputStream(); //网络字节输出流
                
                //(2)发送请求的文件名，字符串读写功能
                pw.println(fileName);
                
                InputStream socketIn = dataSocket.getInputStream();  //网络字节输入流
                //(3)接收服务器的数据文件，字节读写功能
                 byte[] buff = new byte[1024*2]; //用来缓冲接的字节数据
            
                int len = socketIn.read(buff); //读一块到缓冲区
                while(len!= -1){
                    fileOut.write(buff,0,len); //写一块到文件
                    len = socketIn.read(buff);
                    flag = true;
                }
              
                //文件传输完毕，关闭数据套接字
                fileOut.close();
                dataSocket.close();
                JOptionPane.showMessageDialog(null, "文件接收完毕。");
                
                if(flag){
                    System.out.println("文件下载成功");
                    return "文件下载成功";
                   }
                else{
                    saveFile.delete();
                    System.out.println("文件名错误或文件下载失败.");
                    return "文件名错误或文件下载失败.";
                } 
            }
            else{
                dataSocket.close();
                System.out.println("本地文件创建失败。");
                return "本地文件创建失败。";
            } 
       
        }
        else 
            System.out.println("服务器连接失败。");
            return "服务器连接失败。";
    }
    
    public void fileput(String filename){
        
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

