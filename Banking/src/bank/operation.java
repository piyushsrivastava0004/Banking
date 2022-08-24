package bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class operation {
	
	private static String name;
	private static String gender;
	private static String mobile;
	private static String dob;
	private static String pin;
	private static int Balance = 0;
	private static String account_no;
	static Scanner sc = new Scanner(System.in);
	
	public static void openAccount(Statement stmt) throws SQLException{
		System.out.print("Enter your name : ");
		name = sc.next();
		System.out.print("Enter your gender(Male/Female/Others) : ");
		gender = sc.next();
		System.out.print("Enter your mobile number : ");
		mobile = sc.next();
		System.out.print("Enter your Date of Birth : ");
		dob = sc.next();
		System.out.print("Enter your pin : ");
		pin = sc.next();

		String save = "insert into customer (cus_name, Gender, Moble_num,Dateob,pin, Balance, Account_no ) values ( '"+name+"','"+gender+"','"+mobile+"','"+dob+"','"+pin+"','"+Balance+"',seql.nextval) " ;
		try {
			stmt.executeQuery(save);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ResultSet rs1 = null ;
   	    try {
			rs1 = stmt.executeQuery("select max(account_no) from customer");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(rs1.next()) {
	    System.out.println("Account number is : " +rs1.getInt(1));
		}
		
		Account.menu(stmt);
	}
	
	public static int checkBalance(Statement stmt,String acc) throws SQLException
	{
		
		ResultSet rs = stmt.executeQuery("select balance from customer where account_no = '"+acc+"'");
		if(rs.next())
		{
			return rs.getInt(1);
		}
		else
		{
			System.out.println("Enter a valid account number!!");
			checkBalance(stmt,acc);
		}
		return 0;
	}
	
	public static void depositMoney(Statement stmt,String acc) throws SQLException
	{
		int balance = checkBalance(stmt,acc);
		System.out.println("Enter the amount you want to deposit ");
		int amount = sc.nextInt();
		balance = balance + amount;
		stmt.executeUpdate("update customer set balance = '"+balance+"' where account_no = '"+acc+"' ");
		System.out.println("Your update balance is : " +balance);
	}
	
	public static void withdrawMoney(Statement stmt,String acc) throws SQLException
	{
		int balance = checkBalance(stmt,acc);
		System.out.println("Enter the amount you want to Withdraw ");
		int amount = sc.nextInt();
		if(balance < amount)
		{
			System.out.println("Insufficient balance");
			menu2(stmt,acc);
			return;
		}
		balance = balance - amount;
		
		stmt.executeUpdate("update customer set balance = '"+balance+"' where account_no = '"+acc+"' ");
		System.out.println("Your update balance is : " +balance);
	}
	
	public static void login(Statement stmt) throws SQLException
	{
		System.out.println("Enter your account number");
		String acc = sc.next();
		System.out.println("Enter your Pin");
		String pi = sc.next();
		ResultSet rs = stmt.executeQuery("select * from customer where account_no = '"+acc+"' and pin = '"+pi+"' ");
		if(rs.next())
		{
			System.out.println("Login Successful");
			menu2(stmt,acc);
		}
		else
		{
			System.out.println("invalid username password");
		}
	}
	
	public static void menu2(Statement stmt,String acc) throws SQLException
	{
		System.out.println("1. Checkbalance");
		System.out.println("2. Deposit Money");
		System.out.println("3. Withdrew Money");
		System.out.println("4. Back");
		System.out.println("Enter your choice");
		int choice = sc.nextInt();
		switch(choice)
		{
		case 1:
			int balance = checkBalance(stmt,acc);
			System.out.println("Your current balance is : "+balance);
			menu2(stmt,acc);
			break;
		case 2:
			depositMoney(stmt,acc);
			menu2(stmt,acc);
			break;
		case 3:
			withdrawMoney(stmt,acc);
			menu2(stmt,acc);
			break;
		case 4:
			Account.menu(stmt);
			break;
		default:
			System.out.println("Enter a valid choice!!");
			menu2(stmt,acc);
		}	
				
	}
	
	
	

}
