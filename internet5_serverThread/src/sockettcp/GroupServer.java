
package sockettcp;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class GroupServer {
    private final int port = 8008;
    private final ServerSocket serverSocket;//服务器套接字。
    private final ExecutorService executorService; //线程池
    private final int poolSize = 15;
    private static Set<Socket> members;      //groupServer 新加变量
 
    public GroupServer() throws IOException {
            serverSocket = new ServerSocket(port);//开启8008号监听端口。
            members = new HashSet<Socket>();     //实例化在线组员集合
            //创建线程池
            //Runtime的availableProcessors()方法返回当前系统的CPU的数目
            //系统的CPU越多，线程池中工作的线程的数目也应该越多
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*poolSize);
            System.out.println("多用户服务器启动");
           
      }
 
    public void service() {
      while (true) {
            Socket socket=null;
            try {
                socket = serverSocket.accept(); 
                members.add(socket);   //往聊天室中增加新成员，在线成员
                //阻塞语句，监听并等待客户发起连接，有连接请求就生成一个套接字。
                System.out.println("New income: "+socket.getInetAddress());
                //本地服务器观测台显示请求的用户信息。
                executorService.execute(new Handler(socket));
                sendToAllmembers(socket.getPort()+" comes in!");
            }catch (IOException e) {
                   e.printStackTrace();
             }
            }
        }

    /*
    在GroupServer主类中定义群组消息发送方法。
    */
    public static void sendToAllmembers(String msg) throws IOException{
        PrintWriter pw;
        Socket tempSocket;
        OutputStream out;
        
        Iterator it = members.iterator();
        while(it.hasNext()){ //遍历在线成员set集合
            tempSocket = (Socket)it.next(); //取出一个成员
            out = tempSocket.getOutputStream(); //得出输出流
            //装饰成字符流
            pw = new PrintWriter(new OutputStreamWriter(out,"GB2312"),true);
            pw.println(msg); //发送聊天信息给成员
        }//end while 群组发送结束
    }
    
    /*
    组员退出方法
    */
    public static void removeMember(Socket socket){
        members.remove(socket);
    }
    
    public static void main(String args[])throws IOException {
           new GroupServer().service();
      }
    
    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);
    }
    private BufferedReader getReader(Socket socket)throws IOException{
    InputStream socketIn = socket.getInputStream();
    return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));
  }
/*
定义内部类，实现线程接口
*/
class Handler implements Runnable{
    private Socket socket;
    public Handler(Socket soc){
        this.socket = soc;
//        this.run();
    }
    
    @Override
    public void run(){
        //覆盖线程体
        System.out.println("New connection acception accepted "+socket.getInetAddress());
        try {
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            
            String msg="";
            while((msg=br.readLine())!=null){
                //pw.println("From ThreadServer:"+msg); //send to client
                sendToAllmembers("from port:"+socket.getPort()+" :    "+msg);   //向所有成员
                if(msg.equalsIgnoreCase("bye")){
                    System.out.println(socket.getInetAddress()+":"+"Exit");
                    break;
                }              
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
                  try{
                        if(socket!=null){
                            sendToAllmembers(socket.getPort()+" comes out"); 
                            members.remove(socket);
                            socket.close();  //断开连接
                        }
                     }catch (IOException e) {e.printStackTrace();}
               }
    }
 
    }
}
