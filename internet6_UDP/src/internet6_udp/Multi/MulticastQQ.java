/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internet6_udp.Multi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author JM
 */
public class MulticastQQ {
    InetAddress groupIP = InetAddress.getByName("224.0.1.8");
    int port = 8900; 
    MulticastSocket ms = null; //组播套接字
    byte[] inBuff;
    byte[] outBuff;
    
    public MulticastQQ() throws IOException{
        ms =  new MulticastSocket(port); //开启一个组播端口（UDP端口）.
        ms.joinGroup(groupIP);  //告诉网卡这样的IP地址包要收 ,//加入groupIP组
        ms.setLoopbackMode(false);   //必须是false才能开启广播功能！！  
         
        inBuff = new byte[1024];
        outBuff = new byte[1024];
    }
    public void send(String msg){
        try{
            outBuff = ("2012100000 老师："+msg).getBytes("GB2312");
            DatagramPacket outdp = new DatagramPacket(outBuff,outBuff.length,groupIP,port);
            ms.send(outdp);
        }catch(Exception e){
            
        }
    }
    
      public String receive() {
            try {
                 DatagramPacket indp = new DatagramPacket(inBuff, inBuff.length);

                 ms.receive(indp);
                 String msg = new String(indp.getData(),0,indp.getLength(),"GB2312");
                 return indp.getAddress()+"->"+msg;
                }catch (Exception e) {
                    return null;
                }       
  }
      
      public void close(){
       if (ms != null) {
        try {
              ms.leaveGroup(groupIP);
              ms.close();
              }catch (IOException e) {}
       }
    }
}
