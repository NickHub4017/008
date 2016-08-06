package Bussiness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBLink {
	static Connection c = null;
	public DBLink() throws ClassNotFoundException, SQLException{
		
	    
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test");
	    
	}
	public void initializeDB() throws SQLException{
		
		
	      
	      Statement stmt = null;
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT name FROM sqlite_master WHERE type='table'" );
	      if(!rs.next()){//Tables are not created
	    	  initUserTable();
	    	  initAccountTable();
	    	  initMessageTable();
	      }
	      
	      rs.close();
	      stmt.close();
	      
		
	}
	
	public void initUserTable() throws SQLException{
		Statement stmt = null;
		stmt = c.createStatement();
	      String sql = "CREATE TABLE User " +
	                   "(UserID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
	                   " Uname           CHAR(50) UNIQUE   NOT NULL," + 
	                   " Pwd            CHAR(50)     NOT NULL)"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	}
	
	public void initAccountTable() throws SQLException{
		Statement stmt = null;
		stmt = c.createStatement();
	      String sql = "CREATE TABLE Account " +
	                   "(AccountID CHAR(10) PRIMARY KEY NOT NULL," +
	                   " UserID     INTEGER    NOT NULL," +
	                   " CreateDate TIMESTAMP    NOT NULL," +
	                   " Amount     INTEGER     NOT NULL)"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	}
	
	public void initMessageTable() throws SQLException{
		Statement stmt = null;
		stmt = c.createStatement();
	      String sql = "CREATE TABLE Messages " +
	    		  		"(MessageID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
	                   " Message     TEXT    NOT NULL," +
	                   " CreateDate TIMESTAMP    NOT NULL," +
	                   " UserID  INTEGER    NOT NULL," +
	                   " Reply  TEXT  )"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	}
	

	public boolean submitNewUser(String uname, String pwd) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		 c.setAutoCommit(false);

	      
	      String sql = "INSERT INTO User (Uname,Pwd) " +
	                   "VALUES (?,?);";
	      stmt = c.prepareStatement(sql);
	      stmt.setString(1,uname);
	      stmt.setString(2, pwd);
	      int result=stmt.executeUpdate();
	      stmt.close();
	      c.commit();
	      if(result==1){
	    	  return true;
	      }
		return false;
		
	}

	public static boolean isAccountExsist() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void getHistory() {
		// TODO Auto-generated method stub
		
	}

	public static void getInquiry() {
		// TODO Auto-generated method stub
		
	}
	public static boolean createAccount(String AccountID,String UserID) throws SQLException{
		PreparedStatement stmt = null;
		 c.setAutoCommit(false);

	      
	      String sql = "INSERT INTO Account (AccountID,UserID,CreateDate,Amount) " +
	                   "VALUES (?,?,?,?);";
	      stmt = c.prepareStatement(sql);
	      stmt.setString(1,AccountID);
	      stmt.setString(2, UserID);
	      
	      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	      Date date = new Date();
	      stmt.setString(3, dateFormat.format(date));
	      stmt.setInt(4, 0);
	      int result=stmt.executeUpdate();
	      stmt.close();
	      c.commit();
	      if(result==1){
	    	  return true;
	      }
		return false;
	}
	
	public static boolean isUserExsists(String Uname) throws SQLException{
		PreparedStatement stmt = null;
		 c.setAutoCommit(false);

	      
	      String sql = "SELECT * FROM User WHERE Uname = ? ";
	      stmt = c.prepareStatement(sql);
	      stmt.setString(1,Uname);
	      ResultSet result=stmt.executeQuery();
	      
	      c.commit();
	      if(result.next()){
	    	//  System.out.println(result.getString(1));
	    	  stmt.close();
	    	  return true;
	      }
	      //System.out.println(result.getString(1));
	      stmt.close();
		return false;
		
	}

	public static int CheckLogin(String Uname,String Pwd) throws SQLException{
		PreparedStatement stmt = null;
		 c.setAutoCommit(false);

	      
	      String sql = "SELECT * FROM User WHERE Uname = ? and Pwd = ?";
	      stmt = c.prepareStatement(sql);
	      stmt.setString(1,Uname);
	      stmt.setString(2,Pwd);
	      ResultSet result=stmt.executeQuery();
	      
	      c.commit();
	      if(result.next()){
	    	System.out.println(result.getString(1));
	    	  int res=result.getInt(1);
	    	  stmt.close();
	    	  return res;
	      }
	      System.out.println("No out");
	      stmt.close();
		return -1;

		
		
	}
	public static boolean putMessage(String msg, int uid, Date date) throws SQLException {
		PreparedStatement stmt = null;
		 c.setAutoCommit(false);
		
		 
	      
	      String sql = "INSERT INTO Message (Message,CreateDate,UserID) " +
	                   "VALUES (?,?,?);";
	      stmt = c.prepareStatement(sql);
	      stmt.setString(1,msg);
	      
	      
	      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	      stmt.setString(2, dateFormat.format(date));
	      stmt.setInt(3, uid);
	      int result=stmt.executeUpdate();
	      stmt.close();
	      c.commit();
	      if(result==1){
	    	  return true;
	      }
		return false;

		
	}

}
