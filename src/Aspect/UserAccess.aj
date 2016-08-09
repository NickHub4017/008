package Aspect;

import java.sql.SQLException;

import Exceptions.NotAuthorize;
import Exceptions.SessionTimeOut;
import Bussiness.*;
import Database.DBLink;




public aspect UserAccess {
	declare precedence: UserAccess,*,LoggerAspect;

	public pointcut useraccess(User user) :call(* User.*(..)) && target(user) && !within(UserAccess) && !within(AccessControl) && within(UI) ;

	before(User user) throws SessionTimeOut : useraccess(user) {
		long curtime=System.currentTimeMillis();
		System.out.println(user.getKey()+" "+curtime);
		if(user.getKey()>curtime){
			user.setKey(System.currentTimeMillis()+10000);
		}else{
			
			throw new SessionTimeOut("Session Time out");
			
		}
		
	}
	

	public pointcut useraccess2(User user) :call(* User.withdraw(String,String)) && target(user) && !within(UserAccess);

	before(User user) throws SessionTimeOut: useraccess(user) {
		long curtime=System.currentTimeMillis();
		if(user.getKey()>curtime){
			user.setKey(System.currentTimeMillis()+10000);
		}else{
			throw new SessionTimeOut("Session Time out");
			
		}
		
	}

	
	pointcut VerifyLogging(User user) : initialization(User.new(String,String)) && target(user) && !within(UserAccess);
	
	after(User user): VerifyLogging(user){
		  user.setKey(System.currentTimeMillis()+10000);
	  }

	
}
