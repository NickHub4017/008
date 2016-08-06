package Bussiness;

import java.sql.SQLException;

public class AccountHandler {

	public static boolean CreateAccount(User byuser,String accountNo) throws SQLException{
		//if()
		if(DBLink.isAccountExsist()){
			return false;
		}
		DBLink.createAccount(accountNo, byuser.getUname());
		
		return true;
	}
	
	public static boolean TransferAmount(Account from,Account to,long amount,User byuser){
		if(from.getAmount()<amount){
			from.debit(amount);
			to.credit(amount);
			return true;
		}
		else{
			return false;
		}
	}
	public static String ShowHistory(Account from){
		DBLink.getHistory();
		return "";
	}

	public static String Getnquiry(Account from){
		DBLink.getInquiry();
		return "";
	}
	
}
