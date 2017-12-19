/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author gdufs
 */
public class RMIServerServiceImpl
extends UnicastRemoteObject
implements RMIServerService{
    public RMIServerServiceImpl()throws RemoteException{ }
          @Override
    public String send(String msg) throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
        return "来自我服务器的返回： "+msg;
    }

    @Override
    public String send(String yourNo, byte[] yourName) throws RemoteException {
        return ""+yourNo+"  "+yourName.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
