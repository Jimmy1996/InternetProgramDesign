/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaMail;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class JavaMailClient {
  protected Session session;
  protected Store store,store1;
  protected Transport transport;
  
  private String sendHost="smtp.163.com"; //发送邮件服务器
  private String receiveHost="pop3.163.com"; //接收邮件服务器
  private String sendProtocol="smtp"; //发送邮件协议
  private String receiveProtocol="pop3"; //接收邮件协议
  private String username = "13694252450@163.com";//"你在某服务器的用户名";
  private String password = "Aa2862040";// 你在某服务器的密码
  private String fromAddr="13694252450@163.com";  //填入发送者地址
  private String toAddr="491987631@qq.com"; //填入接收者地址

  public String title = "给老师的一封作业邮件";
  public String conTent= "我是20151002122";
  
  public JavaMailClient(){
      try {
          this.init();
      } catch (Exception ex) {
          Logger.getLogger(JavaMailClient.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  /*
  构造函数
  传入的参数分别为：发件人邮箱地址，密码，收件人邮箱地址，邮件主题，邮件内容
  */
  public JavaMailClient(String from,String frompsw,String to,String them,String content){
//      this.fromAddr = from;
//      this.username = from;
//      this.password = frompsw;
//      this.toAddr = to;
      this.title = them;
      this.conTent = content;
      try {
          this.init();
      } catch (Exception ex) {
          Logger.getLogger(JavaMailClient.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  public void init()throws Exception{
    //设置属性
    Properties  props = new Properties();
    props.put("mail.transport.protocol", sendProtocol);
    props.put("server",receiveHost);
    props.put("mail.store.protocol", receiveProtocol);
    props.setProperty("mail.pop3.host", receiveHost);
//    props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
//    props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
    props.put("mail.smtp.host", sendHost); //设置发送邮件服务器
    props.put("mail.smtp.auth", "true"); 
    
    //创建Session对象
    session = Session.getDefaultInstance(props);
//    session.setDebug(true); //输出跟踪日志
//    //创建Store对象
    store = session.getStore(receiveProtocol);
//     //连接到收邮件服务器
   store.connect(receiveHost,"13694252450@163.com","Aa2862040"); 
  }
  

//  public void close()throws Exception{
//    store.close();
//  }
  public void sendMessage()throws Exception{
    //创建一个邮件
    MimeMessage msg = createMimeMessage(fromAddr,toAddr);
    //发送邮件
    transport=session.getTransport();
    transport.connect(username, password); 
    transport.sendMessage(msg,msg.getAllRecipients());
    System.out.println("成功发送邮件");
   // Transport.send(msg,username,password);
  }
  
  /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */public  MimeMessage createMimeMessage(String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
         message.setFrom(new InternetAddress(sendMail, "fangjiemei", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）         
        //message.setSubject("给老师的一封作业邮件");
         message.setSubject(this.title);

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        //message.setText("我是20151002122");
           message.setText(this.conTent);
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

  public void receiveMessage()throws Exception{
    browseMessagesFromFolder("inbox");
  }
  
  public void browseMessagesFromFolder(String folderName)throws Exception{
    Folder folder=store.getFolder(folderName);
    if(folder==null)
      throw new Exception(folderName+"邮件夹不存在");
    browseMessagesFromFolder(folder);
  }
  
  public void browseMessagesFromFolder(Folder folder)throws Exception{
    folder.open(Folder.READ_ONLY);
    System.out.println("你的邮箱里有"+folder.getMessageCount()+"封邮件.");
    System.out.println("你的邮箱里有"+folder.getUnreadMessageCount()+"封未读邮件.");

    //读邮件
    Message[] messages=folder.getMessages();
    for(int i=1;i<=messages.length;i++){
      System.out.println("------第"+i+"封邮件-------");
       System.out.println(folder.getMessage(i).getSubject());
      
      System.out.println(MimeUtility.decodeText(folder.getMessage(i).getContent().toString().replaceAll("<.*?>", "")));
      //打印邮件信息
     
   //  folder.getMessage(i).writeTo(System.out);
      System.out.println();
    }
    folder.close(false);  //关闭邮件夹，但不删除邮件夹中标志为“deleted”的邮件
  }

  public static void main(String[] args)throws Exception {
    JavaMailClient mClient=new JavaMailClient();

   // mClient.sendMessage();//发邮件.
  mClient.receiveMessage();//收邮件.

  }
}

