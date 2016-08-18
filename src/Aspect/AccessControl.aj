package Aspect;
import java.sql.SQLException;

import Bussiness.*;
import Database.DBLink;
import Exceptions.NotAuthorize;
import Exceptions.SessionTimeOut;
import Handlers.*;

public aspect AccessControl {
	declare precedence: UserAccess,*,LoggerAspect;
	public pointcut useraccess(User user) :call(* User.*(*)) && target(user) && !within(AccessControl) &&!within(UserAccess);

	before(User user) throws SessionTimeOut : useraccess(user) {
		long curtime=System.currentTimeMillis();
		if(user.getKey()>curtime){
			user.setKey(System.currentTimeMillis()+100000);
		}else{
			System.out.println("Session Timed out");
			throw new SessionTimeOut("Session Timed out");
			
			
		}
		
	}
	
	
	pointcut VerifyLogging(User user) : initialization(User.new(String,String)) && target(user) && !within(AccessControl);
	
	after(User user): VerifyLogging(user){
		  user.setKey(System.currentTimeMillis()+100000);
	  }

	public pointcut accountauthorization(Account account,User user) :call(* AccountHandler.TransferAmount(Account,..,User)) && args(account,..,user);
	boolean around(Account account,User user) throws SQLException, NotAuthorize : accountauthorization(account,user) {
		
			
			if(DBLink.isAccountBelong(account.getAccountNo(),user.getId() )){
				System.out.println("Ok");
				return proceed(account,user);
				
			}
			else{
				System.out.println("You dont have authorize");
				throw new NotAuthorize("You dont have authorize "+user.getUname()+" for " +account.getAccountNo()+" "+thisJoinPoint.toString()); 
					
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

	public pointcut accountAuthorizHistory(String account,User user) :execution(* User.showHsitory(String)) && args(account) && target(user);
	
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
			System.out.println("Ok "+account+" "+user.getId());
			
		}
		else{
			System.out.println("You dont have authorize");
			throw new NotAuthorize("You dont have authorize");		
		}
	}
	
	
}

