package Aspect;


import Exceptions.SessionTimeOut;
import Handlers.Logger;



public aspect LoggerAspect {
	declare precedence: UserAccess,*,LoggerAspect;
	pointcut ExceptionRecord() : call(* *.*.*(..));

	after() throwing(Exception t) : ExceptionRecord(){
	    System.err.println(" Log "+t.getMessage());
	    Logger.WriteFile(t.getMessage());
	} 
		pointcut ExceptionRecord7() : within(UserAccess);

	after() throwing(SessionTimeOut t) : ExceptionRecord7(){
	    System.err.println(" Log "+t.getMessage());
	    Logger.WriteFile(t.getMessage());
	} 
	
	
	
}
