/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author gdufs
 */
public interface RMIServerService  extends Remote{
    //远程方法之一
     public String send(String msg) throws RemoteException;
    //远程方法之二
     public String send(String yourNo,byte[] yourName) throws RemoteException;
  //在这里可以创建更多的远程方法……
  }

