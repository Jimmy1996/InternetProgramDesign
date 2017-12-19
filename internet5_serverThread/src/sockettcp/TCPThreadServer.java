
package sockettcp;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class TCPThreadServer {
    private int port = 8008;
    private ServerSocket serverSocket;//服务器套接字。
    private ExecutorService executorService; //线程池
    private final int poolSize = 15;
 
    public TCPThreadServer() throws IOException {
            serverSocket = new ServerSocket(port);//开启8008号监听端口。
            //创建线程池
            //Runtime的availableProcessors()方法返回当前系统的CPU的数目
            //系统的CPU越多，线程池中工作的线程的数目也应该越多
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*poolSize);
            
            System.out.println("多用户服务器启动");
      }
 

 
    public void service() {//单客户版本,即每次只能同时和一个客户建立通信连接。
      while (true) {
            Socket socket=null;
            try {
                socket = serverSocket.accept(); 
                //阻塞语句，监听并等待客户发起连接，有连接请求就生成一个套接字。
                System.out.println("New income: "+socket.getInetAddress());
                //本地服务器观测台显示请求的用户信息。
                executorService.execute(new Handler(socket));
            }catch (IOException e) {
                   e.printStackTrace();
             }
      }
        }

//    public static void main(String args[])throws IOException {
//           new TCPThreadServer().service();
//      }
        private PrintWriter putWriter(Socket socket)throws IOException{
        OutputStream socketOut = socket.getOutputStream();//获得输出流缓冲区的地址。
        return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);
      }
    
    private BufferedReader getReader(Socket socket)throws IOException{
        InputStream socketIn = socket.getInputStream();//获得输入流缓冲区的地址
        return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));
      }

    }

/*
定义内部类，实现线程接口
*/
class Handler implements Runnable{
    private Socket socket;
    public Handler(Socket soc){
        this.socket = soc;
        //this.run();
    }
    
    public void run(){
        //覆盖线程体
        System.out.println("New connection acception accepted "+socket.getInetAddress());
        try {
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            
            String msg = null;
            while((msg=br.readLine())!=null){
                //pw.println("From ThreadServer:"+msg); //send to client
                
                if(msg.equalsIgnoreCase("bye")){
                    System.out.println(socket.getInetAddress()+":"+"Exit");
                    break;
                }              
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                if(socket!=null)
                    socket.close();
            }catch(IOException e){
                
            }
        }
        
        
    }
    
    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);
    }
    private BufferedReader getReader(Socket socket)throws IOException{
    InputStream socketIn = socket.getInputStream();
    return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));
  }
}




