/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internet11_database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class DBOperate1 {
    public static void main(String args[]) {
        try {
            //加载MySQL驱动器，其中com.mysql.jdbc.Driver包含在mysqldriver.jar中。
            Class jdbcDriver=Class.forName("com.mysql.jdbc.Driver");
            //注册MySQL驱动器
            java.sql.DriverManager.registerDriver((Driver)jdbcDriver.newInstance());
            String dbUrl ="jdbc:mysql://202.116.195.22:6868/STUDENTDB1?useUnicode=true&characterEncoding=utf-8";
            String dbUser="tester";
            String dbPwd="Tester123";
            //创建数据库连接对象：
            Connection con = (Connection) java.sql.DriverManager.getConnection(dbUrl,dbUser,dbPwd);
            //创建数据库执行对象：
            Statement stmt = (Statement) con.createStatement();
          
            stmt.executeUpdate("insert into STUDENTS (NO,NAME,AGE,CLASS,IP) " 
             +"VALUES ('20151002122','"+"方洁梅"+"',21,'"+"software"+"', '"+"192.168.207.158"+"')");
            ResultSet rs= (ResultSet) stmt.executeQuery("SELECT NO,NAME,AGE,CLASS,IP from STUDENTS");
            String sName;
            while(rs.next())
            { sName = rs.getString("STUDENTS.NAME"); //将数据库的字段值赋值给程序变量sName
            System.out.print(sName);}  
            
//注意：其中的IP地址和端口先用自己机器的参数代替。

        } catch (Exception ex) {
            Logger.getLogger(DBOperate1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
