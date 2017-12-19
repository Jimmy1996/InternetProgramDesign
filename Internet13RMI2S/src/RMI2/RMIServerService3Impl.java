/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import static RMI2.RMIServerService3.online;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Iterator;


/**
 *
 * @author gdufs
 */
public class RMIServerService3Impl extends UnicastRemoteObject implements RMIServerService3{

    String name;
    HashSet<RMIClientService>  onLine=new HashSet<RMIClientService>();
    
    public RMIServerService3Impl() throws RemoteException{
        
    }
     public String send(String msg) throws RemoteException{
        
        return "send";
    }

    @Override
    public String addClientToOnLine(RMIClientService client, String name) throws RemoteException {
         this.name = name;
         String[] info = name.split("-");
         if(info.length != 2){
            return "Wrong name";
        }
        String No = info[0].trim();
        String Name = info[1].trim();
        
        String retSet = Name+ "Succeed";
        
        if(retSet.equalsIgnoreCase(Name+"Succeed")){
            online.add(client);
        }
        return retSet;
    }

    @Override
    public String delClientFromOnLine(RMIClientService client) throws RemoteException {
         online.remove(client);
        return name + " exit";
    }

    @Override
    public void sendMessageToServer(String msg) throws RemoteException {
       Iterator it = online.iterator();
        while(it.hasNext()){
            RMIClientService client = (RMIClientService)it.next();
            client.showMessageToClient(msg);
        }
        return;
    }


     
}
