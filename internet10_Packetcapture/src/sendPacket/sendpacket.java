/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendPacket;

import java.io.IOException;
import java.net.InetAddress;
import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

/**
 *
 * @author gdufs
 */
public class sendpacket {
    public static void main(String args[]){
        try{
            //（1）   获取网络接口参数：
             NetworkInterface[] devices = JpcapCaptor.getDeviceList();
             //(2)   实例化JpcapSender类，取名sender
             JpcapSender sender=JpcapSender.openDevice(devices[0]);
            //根据自己机器配置情况，取其中的某网卡，如第一个网卡.
             //(3)   构造一个TCP包
             TCPPacket tcp=new TCPPacket(8000,80,56,78,false,false,false,false,true,false,true,true,20,10);
              //(4)  设置TCPIPv4头参数：
             tcp.setIPv4Parameter(0,false,false,false,0,false,false,false,0,1010101,100, IPPacket.IPPROTO_TCP, InetAddress.getByName("192.168.207.150"), InetAddress. getByName ("202.116.195.22"));
                //源IP地址中填入自己主机的IP地址；
                //目的IP地址填入包要到达的主机地址；
                //建议2人一组，以对方的IP地址为目的地址
              //(5)   填充TCP中的用户数据：
                tcp.data="20151002122-方洁梅".getBytes();
              //(6)   构造相应的MAC头：
              EthernetPacket ether=new EthernetPacket();
                ether.frametype=EthernetPacket.ETHERTYPE_IP;
                tcp.datalink=ether;
              //（7）   给MAC地址赋值：
            //MAC地址要转换成十进制，ipconfig /all 查看本机的MAC地址.
            //源地址是自己机器的MAC地址。
            ether.src_mac=new byte[]{(byte)68,(byte)55,(byte)230,(byte)197,(byte)162,(byte)248};
            //arp -a,查看默认网关（其IP一般是.1或.254的机器）的MAC地址，用默认网关作//为下一跳转发该包.
            ether.dst_mac=new byte[]{(byte)00,(byte)17,(byte)93,(byte)156,(byte)148,(byte)00};
            //理解什么是下一跳。
           
            //8发送特定的TCP包：
            sender.sendPacket(tcp);
        }catch(IOException ex){
            
        }
    }
    
}
