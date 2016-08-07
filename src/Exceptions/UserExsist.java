package Exceptions;

public class UserExsist extends Exception {

	public UserExsist(String Uname) {
		super("User exsists in the system :- "+Uname);
	}
	
}
