package Aspect;
import java.sql.SQLException;

import Bussiness.*;
import Exceptions.NotAuthorize;


public aspect AccessControl {

	public pointcut useraccess(User user) :call(* User.*(*)) && target(user) && !within(AccessControl);

	before(User user) : useraccess(user) {
		long curtime=System.currentTimeMillis();
		if(user.getKey()>curtime){
			user.setKey(System.currentTimeMillis()+10000);
		}else{
			System.out.println("Session Timed out");
			
		}
		
	}
	
	
	pointcut VerifyLogging(User user) : initialization(User.new(String,String)) && target(user) && !within(AccessControl);
	
	after(User user): VerifyLogging(user){
		  user.setKey(System.currentTimeMillis()+10000);
	  }

	public pointcut accountauthorization(Account account,User user) :call(* AccountHandler.TransferAmount(Account,..,User)) && args(account,..,user);
	boolean around(Account account,User user) throws SQLException, NotAuthorize : accountauthorization(account,user) {
		
			
			if(DBLink.isAccountBelong(account.getAccountNo(),user.getId() )){
				System.out.println("Ok");
				return proceed(account,user);
				
			}
			else{
				System.out.println("You dont have aithorize");
				throw new NotAuthorize("You dont have aithorize"); 
					
			}
			
			
		
	}

	public pointcut accountauthorizDeposit(Account account,User user) :call(* AccountHandler.Deposit(Account,User,..)) && args(account,user,..);
	boolean around(Account account,User user) throws SQLException, NotAuthorize: accountauthorizDeposit(account,user) {
		
		if(DBLink.isAccountBelong(account.getAccountNo(),user.getId() )){
			System.out.println("Ok");
			return proceed(account,user);
		}
		else{
			System.out.println("You dont have aithorize");
			throw new NotAuthorize("You dont have aithorize");			
		}
		
	}
	
	
	

	public pointcut accountAuthorizWithdraw(Account account,User user) :(call(* AccountHandler.Withdraw(Account,User,..))|| call(* AccountHandler.Withdraw(Account,User,..))) && args(account,user,..);
	
	boolean around(Account account,User user) throws SQLException, NotAuthorize: accountAuthorizWithdraw(account,user) {
		if(DBLink.isAccountBelong(account.getAccountNo(),user.getId() )){
			System.out.println("Ok");
			return proceed(account,user);
		}
		else{
			System.out.println("You dont have aithorize");
			throw new NotAuthorize("You dont have aithorize");		
		}
	}

	public pointcut accountAuthorizHistory(String account,User user) :call(* User.showHsitory(String)) && args(account) && target(user);
	
	void around(String account,User user) throws SQLException, NotAuthorize: accountAuthorizHistory(account,user) {
		if(DBLink.isAccountBelong(account,user.getId() )){
			System.out.println("Ok");
			proceed(account,user);
		}
		else{
			System.out.println("You dont have aithorize");
			throw new NotAuthorize("You dont have aithorize");		
		}
	}
	

	public pointcut accountAuthorizInquiry(String account,User user) :execution(* User.InquireAccount(String)) && args(account) && target(user);
	
	 before(String account,User user) throws SQLException, NotAuthorize: accountAuthorizInquiry(account,user) {
		 
		if(DBLink.isAccountBelong(account,user.getId())){
			System.out.println("Ok"+account+" "+user.getId());
			
		}
		else{
			System.out.println("You dont have aithorize");
			throw new NotAuthorize("You dont have aithorize");		
		}
	}
	
	
}

