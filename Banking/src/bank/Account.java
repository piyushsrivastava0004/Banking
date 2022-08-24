package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Account {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//step2
		String url  = "jdbc:oracle:thin:@localhost:1521/orcl.iiht.tech";
		String userName = "scott";
		String password = "tiger";
		Connection con=DriverManager.getConnection(url, userName, password);
		
		
		Statement stmt = con.createStatement();
		menu(stmt);
				
		

	}
	public static void menu(Statement stmt) throws SQLException
	{
		 System.out.println("1. Create New Account");
		    System.out.println("2. Login");
		    
		    System.out.println("Enter Your  choice");
		    Scanner sc = new Scanner(System.in);
		    int choice = sc.nextInt();
		    
		    switch(choice)
		    {
		    case 1:
		    	operation.openAccount(stmt);
		    	break;
		    case 2:
		    	operation.login(stmt);
		    	break;
		    default:
		    	System.out.println("Enter valid choice!!");
		    }
	}

}
