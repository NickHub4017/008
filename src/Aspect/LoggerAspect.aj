package Aspect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Exceptions.NotAuthorize;

public aspect LoggerAspect {
	pointcut ExceptionRecord() : execution(* *.*.*(*));

	after() throwing(NotAuthorize t) : ExceptionRecord(){
	    System.out.println("Authorize fail catch by Aspect");
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
