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
public class RMIDatabaseServiceImpl extends UnicastRemoteObject implements RMIDatabaseService{
    //一定要有默认构造方法
    public RMIDatabaseServiceImpl()throws RemoteException{ }
    @Override
    public String insert(String no, String name, int age, String sClass) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
