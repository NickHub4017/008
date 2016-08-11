package Handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {

	public static void WriteFile(String Log){
		  try {
			  	Date curtime=new Date();
			  	FileWriter filewriter=new FileWriter("LoggedFile.txt", true);
			  	BufferedWriter bufferwriter= new BufferedWriter(filewriter);
			    PrintWriter out = new PrintWriter(bufferwriter);
			    out.println(curtime.toGMTString()+"   "+Log);
			    out.close();
			} catch (IOException e) {
			    
			}
	  }
	
}
