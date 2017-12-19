/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JM
 */
public class HostScannerJFrame extends javax.swing.JFrame {
    Client1 tcp;
    /**
     * Creates new form HostScanner
     */
    public HostScannerJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        show = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        startIP = new javax.swing.JTextField();
        endIP = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        commandFormat = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("扫描结果显示：");

        show.setColumns(20);
        show.setRows(5);
        jScrollPane1.setViewportView(show);

        jLabel2.setText("起始地址：");

        jLabel3.setText("终止地址：");

        startIP.setText(" ");
        startIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startIPActionPerformed(evt);
            }
        });

        endIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endIPActionPerformed(evt);
            }
        });

        jButton1.setText("主机扫描");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("命令执行");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("输入命令格式：");

        commandFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commandFormatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(commandFormat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startIP, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endIP, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(startIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4)
                    .addComponent(commandFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startIPActionPerformed

    private void endIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endIPActionPerformed

    private void commandFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commandFormatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commandFormatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            String startHost= startIP.getText().trim();
            String endHost = endIP.getText();
            int number = scannerHostNumber(startHost,endHost);
            int point = 0;
            int timeOut = 1000;
          
            String nextIp=startHost;
//            InetAddress addr = InetAddress.getByName(nextIp);
//            boolean status = addr.isReachable(timeOut);  //status为真时则说明可达
//            if(status) show.append(nextIp + "   is reachable!" +"\n");  
//            else show.append(nextIp + "   is unreachable!" + "\n");
            while(point<=number){
                Socket socket;
                 InetAddress addr = InetAddress.getByName(nextIp);
                 boolean status = addr.isReachable(timeOut);  //status为真时则说明可达
                 if(status){ 
                     show.append(nextIp + "      is reachable!"+"\n");
                     try {
                         socket = new Socket(); //空套接字,不尝试连接工作.
                         tcp = new Client1(nextIp,"5050");
                         Thread receiver=new Thread(){//用一个线程专门来接收信息。
                         @Override
                          public void run(){
                           String msg=null;
                          while((msg=tcp.receive())!=null){
                              show.append(msg+"\n");
                            }
                             show.append("对话已关闭！\n");
                             }  };        
                       receiver.start();//启动该线程。 
                       
                       socket.close();
                } catch (IOException ex) {
                    show.append(5050    + "不开放！"+"\n");
                    Logger.getLogger(PortScannerJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
              
                 }
                 
                // else show.append(nextIp + "   is unreachable!"+"\n");
                  else show.append(nextIp + "   is unreachable!"+"\n"); 
                nextIp = StringIPAddOne(nextIp);
                 point++;
            }
           
        } catch (Exception ex) {
            Logger.getLogger(HostScannerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String msg;
        String cmd = commandFormat.getText().trim();
        try {
            Process pro = Runtime.getRuntime().exec(cmd);  //cmd是字符串命令，如：ipconfig
            InputStream in = pro.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"GB2312"));
            while((msg=br.readLine())!=null){
               show.append(msg+"\n");
            }
        } catch (IOException ex){
            Logger.getLogger(HostScannerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
  
        /**
     * //字符串形式的IP地址其第四段增1的方法：
     * @param ip
     * @return IP地址其第四段增1
     */
    private String StringIPAddOne(String ip){
       String[] group;
       ip=ip.replace('.', '/');
       group=ip.split("/");
       int num=Integer.parseInt(group[3])+1;
    
       return group[0]+"."+group[1]+"."+group[2]+"."+num;
}
    private int scannerHostNumber(String ip1,String ip2){
       String[] group1,group2;
       ip1=ip1.replace('.', '/');
       ip2=ip2.replace('.', '/');
       group1=ip1.split("/");
       group2 = ip2.split("/");
       int num1=Integer.parseInt(group1[3]);
       int num2=Integer.parseInt(group2[3]);
       return num2-num1;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HostScannerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HostScannerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HostScannerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HostScannerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HostScannerJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField commandFormat;
    private javax.swing.JTextField endIP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea show;
    private javax.swing.JTextField startIP;
    // End of variables declaration//GEN-END:variables
}
