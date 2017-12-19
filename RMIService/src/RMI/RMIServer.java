/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author gdufs
 */
public class RMIServer {
    
    public static void main(String[] args){
        try{
       //第一步 注册并监听1099端口(这是RMI的默认端口，HTTP的默认端口是80）
       LocateRegistry.createRegistry(1099);
       //第二步 实例化服务器的远程对象
       RMIServerService service = new RMIServerServiceImpl();
       RMIDatabaseService database=new RMIDatabaseServiceImpl();
       //第三步 用助记符来对外发布远程对象
       Naming.rebind( "RMIService", service );//service远程对象对外服务的名字是：RMIService
       Naming.rebind( "RMIDatabase", database);
 
       System.out.println( "服务器发布了2个Java RMI远程对象" );
    }catch(Exception e){ e.printStackTrace(); }
  

    }
    
}
