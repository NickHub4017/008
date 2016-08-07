package Exceptions;

public class InsufficientBalance extends Exception {

	public InsufficientBalance(String User,String from_account,String to_account,double amount) {
		super("Insufficient amount "+User+" from account "+from_account+" to account "+to_account+" amount "+amount);
		
	}

}
