package SHOPPING_APPLICATION;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class agentlogin {
	Scanner s=new Scanner(System.in);

	ConnectionManager con=new ConnectionManager();

	public void login() throws ClassNotFoundException, SQLException {
		System.out.println("Enter the username");
		String name=s.next();
		System.out.println("Enter the password");
		String pass=s.next();
		int flag=0;
		Statement st=con.getConnection().createStatement();
		ResultSet set=st.executeQuery("select * from agentlogin"); 
		while(set.next()) {
			//to display the values
			String name1=set.getString(1);
			String pw1=set.getString(2);
		
		if(name1.contentEquals(name)&& pass.contentEquals(pw1)) 
			flag=1;
		}
		if(flag==1) {
			
			System.out.println("Agent login successful");		
			agentlogin ag=new agentlogin();
			ag.option();
		}
		else {
			System.out.println("Details are incorrect");
		}
		Shopping first=new Shopping();
		first.choice();
	}

	public void option() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("\nEnter your choice\n1)BuySell\n2)View Product\n3)Logout");
		int num=s.nextInt();
		if(num==1)
		{
			Statement st=con.getConnection().createStatement();

			ResultSet r=st.executeQuery("select * from addproduct");

			while(r.next())//iterating the values of result
			{
				System.out.println("Product id\t-->\t"+r.getInt(1)+"\n"+"Product name\t-->\t"+r.getString(2)+"\n"+"Quantity\t-->\t"+r.getInt(3)+"\n"+"Price\t\t-->\t"+r.getInt(4)+" \n");
			}
			System.out.println("*******************************");

			
			System.out.println("Enter the Product id you want to buy");
			int buy =s.nextInt();
			System.out.println("Quantity");
			int quan=s.nextInt();
			int pri,tot,qa;
			ResultSet resu=st.executeQuery("select * from addproduct");
			while(resu.next()) {
				int id=resu.getInt(1);
				if(id==buy) {
					pri=resu.getInt(4);
					qa=resu.getInt(3);
					tot=pri*quan;
					System.out.println("Cost-->"+tot);
					System.out.println("Enter 1 if you want to continue");
					int enter=s.nextInt();
					if(enter==1) {
						System.out.println("Booking confirmed");
						int ta=qa-quan;
						PreparedStatement sta=con.getConnection().prepareStatement("update addproduct set minsellqty=? where productid=?");
						sta.setInt(1, ta);
						sta.setInt(2,buy);
						sta.executeUpdate();
						
					}
					
				}
			}
			
			con.getConnection().close();//5) Closing the connection
	
		}
		if(num==2) {
			ConnectionManager con=new ConnectionManager();
			Shopping first=new Shopping();
			int i=1;
			System.out.println("List of products--->>");
			System.out.println("*******************************");
			Statement st=con.getConnection().createStatement();

			ResultSet r=st.executeQuery("select * from addproduct");//query to select the details of all students
			//objects returned from the query by repeatedly calling Statement
			System.out.println("Details-->");
			while(r.next())//iterating the values of result
			{
				System.out.println("Product details"+i);
				System.out.println("Product id\t-->\t"+r.getInt(1)+"\n"+"Product name\t-->\t"+r.getString(2)+"\n"+"Quantity\t-->\t"+r.getInt(3)+"\n"+"Price\t\t-->\t"+r.getInt(4)+" \n");
				i++;
			}
			System.out.println("*******************************");

			
			}
			if(num==3) {
				return;
				
			}
			agentlogin ag=new agentlogin();
			ag.option();
		}

	}

