package Interface;

import java.sql.SQLException;
import java.util.Scanner;

import Bussiness.User;
import Database.DBLink;
import Exceptions.AccountExsists;
import Exceptions.InsufficientBalance;
import Exceptions.NotAuthorize;
import Exceptions.SessionTimeOut;
import Exceptions.UserExsist;
import Handlers.UserHandler;


public class UI {
	
	public static void main(String args[]) {
		
		DBLink p;
		try {
			p = new DBLink();
			p.initializeDB();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
	//		e.printStackTrace();
		}
		
		optionOne();
		
	}
	

	public static void optionOne(){
		Scanner scanner=new Scanner(System.in);
		String mainoption;
		int mainoptionint;
		
		do{
			System.out.println("Enter Options 3-Login  2-Register 1-Exit");
			mainoption=scanner.nextLine();
			try{
			mainoptionint=Integer.parseInt(mainoption);
			}
			catch(Exception e){
				mainoptionint=-1;
			}
			
			if(mainoptionint==2){
					Register();
			}
			else if(mainoptionint==3){
				User user=null;
				user = Login();
				
				if(user!=null){
					optionTwo(user);
				
				}
				else{
					System.err.println("Invalid Credentials");
				}
			}
			else if(mainoptionint==4){
				break;
			}
			
		}while(!mainoption.equals("1"));
		
		return;
	}
	
	public static void optionTwo(User user){
		
		Scanner scanner=new Scanner(System.in);
		String suboption;
		int suboptionint;
		do{
			System.out.println("Enter Options 5-CreateAccount"
					+ " 6-Transfer Account  7-Inquiry  "
					+ "8-History  9-Profile  10-Message  "
					+ "11-Deposit  12-Withdraw 13-Logout");
			suboption=scanner.nextLine();
			try{
				suboptionint=Integer.parseInt(suboption);
			}
			
			catch(Exception e){
				suboptionint=-1;
			}
			
			if(suboptionint==5){
				CreateAccount(user);	
			}

			else if(suboptionint==6){
				TransferAccount(user);
				
			}
			else if(suboptionint==7){
				Inquiry(user);
				
			}
			else if(suboptionint==8){
				ShowHistory(user);
			}
			else if(suboptionint==9){
				Profile(user);
			}
			else if(suboptionint==10){
				Message(user);
			}
			else if(suboptionint==11){
				Deposit(user);
			}
			else if(suboptionint==12){
				Withdraw(user);
			}
			
			
		}while(!suboption.equals("13"));
		System.out.println("You have Logout from System");
		return;
	}
	
	
	
	public static void Register() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("UserName");
		String uname=scanner.nextLine();
		System.out.println("Password");
		String pwd=scanner.nextLine();
		System.out.println("Re-Password");
		String re_pwd=scanner.nextLine();
		System.out.println("Name");
		String name=scanner.nextLine();
		System.out.println("Address");
		String addr=scanner.nextLine();
		try {
			if(!UserHandler.Register(uname, pwd,re_pwd,name,addr)){
				System.err.println("Password Didn't match");
			}
			
		} catch (ClassNotFoundException e) {
		//	System.err.println("Sorry System error occured in Register Please Try Again"+e.getMessage());
		} catch (SQLException e) {
			//System.err.println("Sorry System error occured in Register Please Try Again"+e.getMessage());
		} catch (UserExsist e) {
			//System.err.println("User Name already exsists "+e.getMessage());
		}
	}
	
	public static User Login() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("UserName");
		String uname=scanner.nextLine();
		System.out.println("Password");
		String pwd=scanner.nextLine();
		User User=null;
		try {
			User=UserHandler.Login(uname, pwd);
			
		} catch (SQLException e) {
			//System.err.println("Sorry System error occured in Login Please Try Again");
			//e.printStackTrace();
		}
		return User;
	}
	
	public static void CreateAccount(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Account No");
		String accountNo=scanner.nextLine();
		try {
			byuser.createAccount(accountNo);
		} 
		catch (SQLException   e) {
		} catch (AccountExsists e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SessionTimeOut e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
	}
	
	public static void TransferAccount(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter From Account No");
		String from_accountNo=scanner.nextLine();
		System.out.println("Enter To Account No");
		String to_accountNo=scanner.nextLine();
		System.out.println("Enter Amount");
		long amount=scanner.nextLong();
		try {
			byuser.TransferAmount(from_accountNo, to_accountNo, amount);
			
		} catch (SQLException  e) {
			//System.err.println(e.getMessage());
			
		} catch (InsufficientBalance e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (NotAuthorize e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SessionTimeOut e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	public static boolean ShowHistory(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Account No");
		String accountNo=scanner.nextLine();
		try {
			byuser.showHsitory(accountNo);
		} catch (SQLException e) {

			
		} catch (NotAuthorize e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SessionTimeOut e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return true;
		
	}
	
	public static void Inquiry(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Account No");
		String accountNo=scanner.nextLine();
		try {
			byuser.InquireAccount(accountNo);
		} catch (SQLException e) {

		} catch (NotAuthorize e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SessionTimeOut e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	public static void Profile(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("1 Edit Name   2 Edit Address");
		String option=scanner.nextLine();
		if(option.equals("1")){
			EditProfileName(byuser);
		}
		else if(option.equals("2")){
			EditProfileAddress(byuser);
		}		
	}
	
	public static boolean EditProfileName(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Name ");
		String newname=scanner.nextLine();
		try {
			byuser.changeName(newname);
		} catch (SessionTimeOut e) {
			
		}
		return true;
		
	}
	
	public static void EditProfileAddress(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Addr ");
		String newaddr=scanner.nextLine();
		try {
			byuser.changeAddress(newaddr);
		} catch (SessionTimeOut e) {
			
		}
	}
	
	public static void Message(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Message");
		String msg=scanner.nextLine();
		try {
			byuser.putMessage(msg);
		} catch (SQLException e) {

			
		} catch (SessionTimeOut e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
		
		
	}
	public static boolean Deposit(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Account No Deposit");
		String accno=scanner.nextLine();
		System.out.println("Amount");
		double amount=scanner.nextDouble();
		try {
			byuser.deposit(accno, amount);
		}catch (SQLException e) {
	
		} catch (NotAuthorize e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SessionTimeOut e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return true;
		
	}

	public static boolean Withdraw(User byuser){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Account No Withdraw");
		String accno=scanner.nextLine();
		System.out.println("Amount");
		double amount=scanner.nextDouble();
		try {
			byuser.withdraw(accno, amount);
		} catch (SQLException e) {
			
		} catch (NotAuthorize e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SessionTimeOut e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return true;
		
	}
	
}
