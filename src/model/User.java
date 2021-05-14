package model;

import java.sql.*;


public class User {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yash","root",""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	
	public String insertUsers( String userName, String email, String password, String confPass,String address) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into register (`userID`,`userName`,`email`,`password`,`confPass`,`address`)"
	 + " values (?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, userName); 
	 preparedStmt.setString(3, email); 
	 preparedStmt.setString(4, password);
	 preparedStmt.setString(5, confPass);
	 preparedStmt.setString(6, address);
	 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	public String readUsers() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>User Name</th>" +
	 "<th>Email</th>" + 
	 "<th>Passowrd</th>" +
	 "<th>Confirm Passowrd</th>" +
	 "<th>Address</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from register"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String userID = Integer.toString(rs.getInt("userID")); 
	 String userName = rs.getString("userName"); 
	 String email = rs.getString("email"); 
	 String password = rs.getString("password");
	 String confPass = rs.getString("confPass");
	 String address = rs.getString("address");
	 // Add into the html table
	 output += "<tr><td>" + userName + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + password + "</td>"; 
	 output += "<td>" + confPass + "</td>";
	 output += "<td>" + address + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + userID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateUsers(String ID,String name, String email, String password, String confPass,String address)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE register SET userName=?,email=?,password=?,confPass=?,address=? WHERE userID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, email); 
		 preparedStmt.setString(3, password);
		 preparedStmt.setString(4, confPass);
		 preparedStmt.setString(5, address);
		 preparedStmt.setInt(6, Integer.parseInt(ID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
	
	public String deleteUsers(String userID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from register where userID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(userID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
