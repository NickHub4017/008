package Aspect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import Exceptions.AccountExsists;
import Exceptions.InsufficientBalance;
import Exceptions.NotAuthorize;
import Exceptions.SessionTimeOut;
import Exceptions.UserExsist;



public aspect LoggerAspect {
	declare precedence: UserAccess,*,LoggerAspect;
	pointcut ExceptionRecord() : call(* *.*.*(..));

	after() throwing(Exception t) : ExceptionRecord(){
	    System.err.println("SQL Aspect Log "+t.getMessage());
	    WriteFile(t.getMessage());
	} 
		pointcut ExceptionRecord7() : within(UserAccess);

	after() throwing(SessionTimeOut t) : ExceptionRecord7(){
	    System.err.println("Session2 Aspect Log "+t.getMessage());
	    WriteFile(t.getMessage());
	} 
	
	public void WriteFile(String Log){
		  try {
			  	Date curtime=new Date();
			  	
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("LoggedFile.txt", true)));
			    out.println(curtime.toGMTString()+"   "+Log);
			    out.close();
			} catch (IOException e) {
			    
			}
	  }
	
}
