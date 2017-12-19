/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internet11_database;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.*;


public class DBOperate2{

 private final ConnectionProvider provider;
 private Connection con=null;
 private Statement stmt=null;
 private ResultSet rs=null;

public DBOperate2(ConnectionProvider provider){
    this.provider=provider;    
  }
  public void addStudent(String sNo,String sName,int age,String sClass) throws SQLException, UnsupportedEncodingException{
     
       con=provider.getConnection();
       stmt=con.createStatement();
       sName=new String(sName.getBytes("GB2312"),"ISO-8859-1");
       sClass=new String(sClass.getBytes("GB2312"),"ISO-8859-1");
       String sql="insert into STUDENTS(NO,NAME,AGE,CLASS) values("
               + "'"+sNo+"'"+"," + "'"+sName+"'"+"," +age+"," + "'"+sClass+"'"+")";

      stmt.execute(sql);
    
      closeStatement();
      closeConnection();
    
  }

  public void saveClobToDatabase(String fileName)throws Exception{
    con=provider.getConnection();
    PreparedStatement stmtp=con.prepareStatement("insert into ACLOB(ID,FILE) values(?,?) ");
    stmtp.setLong(1,1);
    FileInputStream fin=new FileInputStream(fileName);
    InputStreamReader reader=new InputStreamReader(fin);
    stmtp.setCharacterStream(2,reader,fin.available());
    stmtp.executeUpdate();
    reader.close();
    stmtp.close();
  }

  public void deleteStudent(String name) throws SQLException{
     try{
      con=provider.getConnection();
      stmt=con.createStatement();
      String sql="delete from CUSTOMERS where NAME='"+name+"'";
      stmt.execute(sql);
    }finally{
       closeStatement();
       closeConnection();
    }
  }

  public void printAllStudents() throws SQLException, UnsupportedEncodingException{
    
    try{
      con=provider.getConnection();
      stmt=con.createStatement();
      //查询记录
      rs= stmt.executeQuery("SELECT NO,NAME,AGE,CLASS from STUDENTS");
      //输出查询结果
      while (rs.next()){
        String sNo = rs.getString(1);
        String sName = new String((rs.getString(2)).getBytes("ISO-8859-1"),"GB2312");
        int age = rs.getInt(3);
        String sClass = rs.getString(4);

        //打印所显示的数据
        System.out.println("no="+sNo+",name="+sName+",age="+age+",address="+sClass);

      }
    }finally{
        closeAll();
    }
  }
public ResultSet DatabaseSet()throws SQLException{
    
      con=provider.getConnection();
      stmt=con.createStatement();
      //查询记录
      rs= stmt.executeQuery("SELECT NO,NAME,AGE,CLASS from STUDENTS");
      //输出查询结果

   return rs;
}

public void closeAll( ){
    try{
      if(rs!=null)rs.close();
      if(stmt!=null)stmt.close();
      if(con!=null)con.close();
    }catch(SQLException e){}
  }

private void closeResultSet( ){
    try{
      if(rs!=null)rs.close();
    }catch(SQLException e){}
  }

  private void closeStatement(){
    try{
      if(stmt!=null)stmt.close();
    }catch(SQLException e){}
  }

  private void closeConnection(){
    try{
      if(con!=null)con.close();
    }catch(SQLException e){}
  }

  public static void main(String args[])throws Exception{

    DBOperate2 tester=new DBOperate2(new ConnectionProvider());
   // tester.addStudent("2009","小王五",40,"软件工程");
    tester.printAllStudents();
  //  tester.deleteCustomer("小王");
  }
}
