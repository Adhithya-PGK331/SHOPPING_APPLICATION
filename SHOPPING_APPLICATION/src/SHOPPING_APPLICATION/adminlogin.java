package SHOPPING_APPLICATION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class adminlogin {
	public void login() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ConnectionManager con=new ConnectionManager();
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the username");
		String name=s.next();
		System.out.println("Enter the password");
		String pass=s.next();
		int flag=0;
		Statement st=con.getConnection().createStatement();
		ResultSet set=st.executeQuery("select * from adminlogin"); 
		while(set.next()) {
			//to display the values
			String name1=set.getString(1);
			String pw1=set.getString(2);
		
		if(name1.contentEquals(name)&& pass.contentEquals(pw1)) 
			flag=1;
		}
		if(flag==1) {

			System.out.println("Admin login successful");
			adminlogin ad=new adminlogin();
			ad.option();
		}
		else {
			System.out.println("Details are incorrect");
		}
			
		Shopping first=new Shopping();
		first.choice();
	}

	public void option() throws ClassNotFoundException, SQLException {
		Scanner s=new Scanner(System.in);
		ConnectionManager con=new ConnectionManager();

		System.out.println("\nEnter your choice\n1.Add product\n2.Display Product\n3.Update product\n4.Remove product\n5.Logout");
		int num=s.nextInt();
		if(num==1)
		{
			System.out.println("Enter the Product id");
			int pid=s.nextInt();
			System.out.println("Enter the Product name");
			String pname=s.next();
			System.out.println("Enter the no of products selling");
			int minq=s.nextInt();
			System.out.println("Enter the Price");
			int price=s.nextInt();
			
			PreparedStatement sta=con.getConnection().prepareStatement("insert into addproduct(productid,productname,minsellqty,price) values(?,?,?,?)");
			//prepare statement
			//we are passing parameter (?) for the values. 
			//Its value will be set by calling the setter methods of PreparedStatement.
			sta.setInt(1,pid);
			sta.setString(2,pname);
			sta.setInt(3,minq);
			sta.setInt(4,price);
			
			sta.executeUpdate();//value will be updated to the database
			//Returns an integer representing the number of rows affected by the SQL statement.
			System.out.println("Insertion successful");
			con.getConnection().close();//5) Closing the connection
	
		}
		if(num==2) {
			Statement st=con.getConnection().createStatement();

			ResultSet r=st.executeQuery("select * from addproduct");//query to select the details of all students
			//objects returned from the query by repeatedly calling Statement
			System.out.println("************DETAILS************");
			while(r.next())//iterating the values of result
			{
				System.out.println("Product id\t-->\t"+r.getInt(1)+"\n"+"Product name\t-->\t"+r.getString(2)+"\n"+"Quantity\t-->\t"+r.getInt(3)+"\n"+"Price\t\t-->\t"+r.getInt(4)+" \n");
			}
			System.out.println("*******************************");

		}
		if(num==3) {
			System.out.println("Enter the id to update the product :");
			int id=s.nextInt();
			System.out.println("Enter the new user id to update ");
			int id1=s.nextInt();
			System.out.println("Enter the new product name ");
			String pname=s.next();
			System.out.println("Enter the new product quantity ");
			int pqty=s.nextInt();
			System.out.println("Enter the new price ");
			int price2=s.nextInt();
			PreparedStatement st=con.getConnection().prepareStatement("update addproduct set productid=?,productname=?,minsellqty=?,price=? where productid=?");
			st.setInt(1,id1);
			st.setString(2, pname);
			st.setInt(3,pqty);
			st.setInt(4,price2);
			st.setInt(5,id);
			st.executeUpdate();
			System.out.println("Updated successfully..!!");
		}
		
		if(num==4) {
			System.out.println("Enter the id to remove the product :");
			int id=s.nextInt();
			PreparedStatement sta=con.getConnection().prepareStatement("delete from addproduct where productid=?");
			sta.setInt(1, id);
			sta.executeUpdate();
			System.out.println("Product removed successfully..!!");
			
		}
		if(num==5) {
			System.out.println("Process will be completed");
			return;
		}
		adminlogin ad=new adminlogin();
		ad.option();
	}

}
