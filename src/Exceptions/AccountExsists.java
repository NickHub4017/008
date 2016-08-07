package Exceptions;

public class AccountExsists extends Exception {

	public AccountExsists(String AccountNo,String uname) {
		super("Account Exsists :- AccNo :- "+AccountNo+" By user :- "+uname);
		
	}
	
}
