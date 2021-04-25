package model;

import java.sql.*;

public class Company {
	
	private Connection connect()
	 {
	      Connection con = null;
	 try
	 {
	      Class.forName("com.mysql.jdbc.Driver");
	 //Provide the correct details: DBServer/DBName, username, password
	      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafproject","root","");
	      
	      
	      
	      
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	 public String insertCompany(String userid, String useremail,String userphone , String udescription, String ubudjet, String company1, String company2)
	 {
	 String output = "";
	 try
	 {
	
		 Connection con = connect();
	 
	 if (con == null)
	 {
		 //return "Error while connecting to the database for inserting.";
		 return "Successfully inserted";
	 }
	 // create a prepared statement
	 String query = " insert into company(`id`,`userid`,`useremail`,`userphone`,`udescription`,`ubudjet`,`company1`,`company2`)"
	 + " values (?, ?, ?, ?, ?,?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 	// binding values
	 	
	 	preparedStmt.setInt(1, 0);
	 	preparedStmt.setString(2, userid);
	 	preparedStmt.setString(3, useremail);
	 	preparedStmt.setString(4, userphone);
	 	preparedStmt.setString(5, udescription);
	 	preparedStmt.setString(6, ubudjet);
	 	preparedStmt.setString(7, company1);
	 	preparedStmt.setString(8, company2);
	 	
	 // execute the statement
		
		 preparedStmt.execute();
		 con.close();
		    output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		   output = "Error while inserting the company.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
	public String readCompany()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; 
			 
			 
		 }
		 
		 
		// Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Register ID</th><th>E-mail</th>" +
				 "<th>Contact</th>" +
				 "<th>Product Description</th>" +
				 "<th>Expected Budjet</th>" +
				 "<th>Company 01</th>" +
				 "<th>Company 02</th>" +
				 "<th>Update</th><th>Remove</th></tr>";
		 
		 String query = "select * from company";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
			 
		 {
			 String gID = Integer.toString(rs.getInt("id"));
			 String uID = rs.getString("userid");
			 String uEmail = rs.getString("useremail");
			 String uPhone = rs.getString("userphone");
			 String uDesc = rs.getString("udescription");
			 String uBudget = rs.getString("ubudjet");
			 String comCode01 = rs.getString("company1");
			 String comCode02 = rs.getString("company2");
			 
			 
			// Add into the html table
			 output += "<tr><td>" + uID + "</td>";
			 output += "<td>" + uEmail + "</td>";
			 output += "<td>" + uPhone + "</td>";
			 output += "<td>" + uDesc + "</td>";
			 output += "<tr><td>" + uBudget + "</td>";
			 output += "<td>" + comCode01 + "</td>";
			 output += "<td>" + comCode02 + "</td>";
			 
			 
			 
			// buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
			 + "<input name='itemID' type='hidden' value='" + gID
			 + "'>" + "</form></td></tr>";
			 } 
		 
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the company.";
		 System.err.println(e.getMessage());
		 }
		 return output;
	 }
		 
	public String updateCompany(String id, String userid, String useremail,String userphone , String udescription, String ubudjet, String company1, String company2) {
		
		String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for updating."; 
			 
		 }
		 
		 // create a prepared statement
		 String query = "UPDATE company SET userid=?,useremail=?,userphone=?,udescription=?,ubudjet=?,company1=?,company2=? WHERE id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		// binding values
		 preparedStmt.setString(1, userid);
		 preparedStmt.setString(2, useremail); 
		 preparedStmt.setString(3, userphone);
		 preparedStmt.setString(4, udescription);
		 preparedStmt.setString(5, ubudjet); 
		 preparedStmt.setString(6, company1);
		 preparedStmt.setString(7, company2); 
		 preparedStmt.setInt(8,Integer.parseInt(id));
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	
	public String deleteCompany(String id)
	 	{
			String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
				
			}
			// create a prepared statement
			String query = "delete from company where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
	
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			}
		catch (Exception e)
		{
			output = "Error while deleting.";
			System.err.println(e.getMessage());
		}
		return output;
	 	} 
		 
		 
		 
		 
		 
}
			 	 
		
		 
