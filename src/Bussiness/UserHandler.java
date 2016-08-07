package Bussiness;

import java.sql.SQLException;

import Exceptions.UserExsist;

public class UserHandler {


	public static boolean Register(String Uname,String Pwd,String re_pwd,String name,String addr) throws ClassNotFoundException, SQLException, UserExsist{
		DBLink dbLink=new DBLink();
		if(re_pwd.equals(Pwd)){
			if(!dbLink.isUserExsists(Uname)){
				return dbLink.submitNewUser(Uname,Pwd,name,addr);
			}
			throw new UserExsist(Uname);		
		}
		return false;
		
	}
	
	public static User Login(String Uname,String Pwd) throws SQLException{
		int id= DBLink.CheckLogin(Uname,Pwd);
		if (id!=-1){
			User user=new User(Uname, Pwd);
			user.id=id;//ToDo add a set method
			initUserData(user);
		return user;
		}
		return null;
	}
	
	public static void initUserData(User user) throws SQLException{
		user.setAccounts(DBLink.getAccounts(user.getId()));
		for (Account act: user.getAccounts().values()){
			act.setTransactions(DBLink.getTransactions(act.getAccountNo()));
		}
		user.setProfile(DBLink.getUserProfile(user.getId()));
	}
}
