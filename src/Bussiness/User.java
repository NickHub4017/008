package Bussiness;

import java.sql.SQLException;
import java.util.HashMap;

import javax.print.attribute.HashAttributeSet;

public class User {
	 String Uname;
	 String Pwd;
	 int id;//This will get 0< value when login successful
	 HashMap<String, Account> accounts=new HashMap<String,Account>();
	 String Name;
	 String address;
	
	public User(String uname, String pwd) {
		super();
		Uname = uname;
		Pwd = pwd;
	}



	public String getUname() {
		return Uname;
	}



	public int getId() {
		return id;
	}



	public boolean Register(String re_pwd) throws ClassNotFoundException, SQLException{
		DBLink dbLink=new DBLink();
		if(re_pwd.equals(Pwd)){
			if(!dbLink.isUserExsists(Uname)){
				return dbLink.submitNewUser(Uname,Pwd);
			}
			else{
				System.out.println("UName is exsists");
			}
			
		}
		return false;
		
	}
	
	public boolean Login() throws SQLException{
		int id= DBLink.CheckLogin(this.Uname,this.Pwd);
		if (id!=-1){
			this.id=id;
		return true;
		}
		return false;
	}
	
	public boolean createAccount(String accountNo) throws SQLException{
		boolean res= AccountHandler.CreateAccount(this, accountNo);
		if(res){
			Account newAcc=new Account(accountNo);
			accounts.put(accountNo, newAcc);
		}
		return res;
	}
	
	public boolean TransferAmount(String from_accountNo,String to_accountNo,long amount) throws SQLException{
		Account toaccount=new Account(to_accountNo);
		return AccountHandler.TransferAmount(accounts.get(from_accountNo), toaccount, amount,this);
	}



	public void showHsitory(String accountNo) {
		Account account=accounts.get(accountNo);
		account.printHistory();
		
		
	}



	public void InquireAccount(String accountNo) {
		Account account=accounts.get(accountNo);
		System.out.println(account.showInquiry());
	}



	public void changeName(String newname) {
		this.Name=newname;
		
	}



	public void changeAddress(String newaddr) {
		this.address=newaddr;
		
	}



	public void putMessage(String msg) throws SQLException {
		MessageHandler.putMessage(msg,this);
		
	}
	
	
}
