/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internet6_udp;

import java.io.*;
import java.net.*;

/**
 *
 * @author JM
 */
public class UDPServer {
        
    private int port=8008;
    private DatagramSocket serversocket; //服务器套接字
    
    public UDPServer() throws IOException{
        //构造函数
        serversocket = new DatagramSocket(8008);
        System.out.println("UDP服务器启动");
    }
    

    
    public void service(){
        byte[] data = new byte[65535];
        String msg;
        while(true){
          DatagramSocket socket = null;
          try{
              DatagramPacket packet = new DatagramPacket(data,0,data.length);
              socket = this.serversocket;
              socket.receive(packet);
               System.out.println("receive a datagram packet");  
                //打印数据包内容  
               System.out.println("from hostname : " + packet.getAddress() + ", port : " + packet.getPort() + " : " + new String(packet.getData(), 0, packet.getLength()));  
               msg = new String(packet.getData(),0,packet.getLength(),"GB2312");
               
               String reply = "来自服务器端:"+msg;
               byte[] replyData = reply.getBytes("GB2312");
               DatagramPacket replyPacket = new DatagramPacket(replyData,replyData.length,packet.getAddress(),packet.getPort());
               
               socket.send(replyPacket);

          }catch(IOException e){
              e.printStackTrace();
          }
        }
    }
    public static void main(String[] args) throws IOException{
        new UDPServer().service();
    }
}
