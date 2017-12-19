/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author gdufs
 */
public class RMIServer {
    public static void main(String args[]){
        RMIServer rmis = new RMIServer();
        rmis.Service();
    }
    
    public void Service(){
        try{
            LocateRegistry.createRegistry(1099);
            RMIServerService3 service = new RMIServerService3Impl();
            
            Naming.rebind("RMIService3", service);
            
            System.out.println("服务器发布了RMIService3");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
