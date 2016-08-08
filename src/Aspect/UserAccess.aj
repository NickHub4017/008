package Aspect;

import java.sql.SQLException;

import Bussiness.Account;
import Bussiness.DBLink;
import Bussiness.User;
import Exceptions.NotAuthorize;


import Bussiness.*;




public aspect UserAccess {
	declare precedence: UserAccess,*;

	public pointcut useraccess(User user) :call(* User.*(*)) && target(user) && !within(UserAccess);

	before(User user) : useraccess(user) {
		long curtime=System.currentTimeMillis();
		if(user.getKey()>curtime){
			user.setKey(System.currentTimeMillis()+10000);
		}else{
			System.out.println("Session Timed out");
			
		}
		
	}
	

	public pointcut useraccess2(User user) :call(* User.withdraw(String,String)) && target(user) && !within(UserAccess);

	before(User user) : useraccess(user) {
		long curtime=System.currentTimeMillis();
		if(user.getKey()>curtime){
			user.setKey(System.currentTimeMillis()+10000);
		}else{
			System.out.println("Session Timed out");
			
		}
		
	}

	
	pointcut VerifyLogging(User user) : initialization(User.new(String,String)) && target(user) && !within(UserAccess);
	
	after(User user): VerifyLogging(user){
		  user.setKey(System.currentTimeMillis()+10000);
	  }

	
}
