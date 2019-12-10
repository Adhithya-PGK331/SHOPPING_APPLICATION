package SHOPPING_APPLICATION;

import java.sql.SQLException;
import java.util.Scanner;

public class Shopping {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Shopping first=new Shopping();
		first.choice();
	}

	public void choice() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("1.Adminlogin\n2.Agent login\n3.Exit");
		System.out.println("Enter your choice \n");
		int n=s.nextInt();
		switch(n)
		{
		case 1:
			adminlogin ad=new adminlogin();
			ad.login();
			ad.option();
			break;
		case 2:
			agentlogin ag=new agentlogin();
			ag.login();
			ag.option();
			
			break;
		case 3:
			System.out.println("Exitted successfully..!!");
			System.exit(0);
		}

	}


}
