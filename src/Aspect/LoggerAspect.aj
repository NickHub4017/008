package Aspect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
	    
	} 
	/*
	pointcut ExceptionRecord2() : call(* *.*.*(..));

	after() throwing(AccountExsists t) : ExceptionRecord2(){
	    System.err.println("Acc Exsists Aspect Log "+t.getMessage());
	    
	} 
	pointcut ExceptionRecord3() : call(* *.*.*(..));

	after() throwing(InsufficientBalance t) : ExceptionRecord3(){
	    System.err.println("Insuff bal Aspect Log "+t.getMessage());
	    
	}
	
	pointcut ExceptionRecord4() : call(* *.*.*(..));

	after() throwing(NotAuthorize t) : ExceptionRecord4(){
	    System.err.println("Not Auth bal Aspect Log "+t.getMessage());
	    
	} 
	pointcut ExceptionRecord5() : call(* *.*.*(..));

	after() throwing(SessionTimeOut t) : ExceptionRecord5(){
	    System.err.println("Session bal Aspect Log "+t.getMessage());
	    
	} 
	
	pointcut ExceptionRecord6() : call(* *.*.*(..));

	after() throwing(UserExsist t) : ExceptionRecord6(){
	    System.err.println("User exsists Aspect Log "+t.getMessage());
	    
	} 
	*/
	pointcut ExceptionRecord7() : within(UserAccess);

	after() throwing(SessionTimeOut t) : ExceptionRecord7(){
	    System.err.println("Session2 Aspect Log "+t.getMessage());
	    
	} 
	
	
	
	
	public void WriteFile(String Log){
		  try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("myfile.txt", true)));
			    out.println(Log);
			    out.close();
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
	  }
	
}
