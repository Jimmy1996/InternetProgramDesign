/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.RemoteException;
import java.util.HashSet;

/**
 *
 * @author gdufs
 */
public interface RMIServerService3 {
     HashSet<RMIClientService> online = new HashSet<RMIClientService>();
    public String send(String msg) throws RemoteException;
    //
    public String addClientToOnLine(RMIClientService client,String name)throws RemoteException;
    public String  delClientFromOnLine(RMIClientService client) throws RemoteException;
    public void sendMessageToServer(String msg) throws RemoteException;
}
