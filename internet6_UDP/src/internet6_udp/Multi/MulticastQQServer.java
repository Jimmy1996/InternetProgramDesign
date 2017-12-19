/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internet6_udp.Multi;

import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastQQServer  {
    private final int port = 9000;
    private final  MulticastSocket serversocket;//服务器套接字。组播
    private Set<String> members;   //存储成员们的ip和port,以及识别号（学号）。
    private Map<String,String> memID;
    
    public MulticastQQServer () throws IOException {
            serversocket = new  MulticastSocket(port);//开启8008号监听端口。
            System.out.println("组播服务器启动");       
      }
 
    public void service() {
        byte[] data = new byte[65535];
        String msg;
        while(true){
          DatagramSocket socket = null;
          try{
              DatagramPacket packet = new DatagramPacket(data,0,data.length);
              socket = this.serversocket;
              
              socket.receive(packet);    //启动监听
              System.out.println("receive a datagram packet");  
                //打印数据包内容  
               System.out.println("from hostname : " + packet.getAddress() + ", port : " + packet.getPort() + " : " + new String(packet.getData(), 0, packet.getLength()));  
               members.add(packet.getAddress().toString()+"`"+packet.getPort());
               
               msg = new String(packet.getData(),0,packet.getLength(),"GB2312");  //接收到的信息
               
               String reply = "来自"+packet.getAddress()+":"+packet.getPort()+":"+msg;
               sendToAllmembers(reply,socket);

          }catch(IOException e){
              e.printStackTrace();
          }
        }
        }

    /*
    在GroupServer主类中定义群组消息发送方法。
    */
    public void sendToAllmembers(String reply,DatagramSocket socket) throws IOException{
         byte[] replyData = reply.getBytes("GB2312");
         
        Iterator it = members.iterator();
        while(it.hasNext()){ //遍历在线成员set集合
             String[] ipport = ((String)it.next()).split("`");
             DatagramPacket replyPacket = new DatagramPacket(replyData,replyData.length,InetAddress.getByName(ipport[1]),Integer.parseInt(ipport[2]));
             socket.send(replyPacket);  //将聊天信息组播
        }//end while 群组发送结束
    }
    
 
    public static void main(String args[])throws IOException {
           new MulticastQQServer().service();
      }
}
    
