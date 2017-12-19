/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI1;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gdufs
 */
public class RMIServerServiceImpl extends UnicastRemoteObject implements RMIServerService{
    //默认构造方法
  public RMIServerServiceImpl( )throws RemoteException{ }
 
  public String send(String msg)throws RemoteException{
       return "来自20141000000 张三服务器的返回："+msg;
}

    @Override
  //第二个send()方法的实现同学们选做 
 public String send(String yourNo,byte[] yourName) throws RemoteException{
    
      try {
          System.out.println(yourNo+ new String(yourName,"某字符编码"));
          //这是一段在服务器端实现的课堂实验记分程序
      } catch (UnsupportedEncodingException ex) {
          Logger.getLogger(RMIServerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
}

}