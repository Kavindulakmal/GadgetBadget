package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PaymentModel {
	
	private Connection connect() {
	 
	      Connection con = null;
	 try
	 {
	      Class.forName("com.mysql.jdbc.Driver");

	      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafproject", "root", "");
	 }
	 
	 catch (Exception e)
	 {
		 e.printStackTrace();
	 
	 }
	 return con;
	 }
	
	 public String PaymentInsert(String UserID, String payusername,String productid, String payamount, String paymethod) {
	 
	 String output = "";
	 
	 try
	 
	 {
	
		 Connection con = connect();
	 
	 if (con == null)
		 
	 {
		 return "Error while connecting to the database for inserting."; 
		 
	 }
	 
	 String query = "insert into payment(`UserID`,`payusername`,`productid`,`payamount`,`paymethod`)"
	 + " values ( ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
//	 preparedStmt.execute();
//	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(1, UserID);
	 preparedStmt.setString(2, payusername);
	 preparedStmt.setString(3, productid);
	 preparedStmt.setString(4, payamount);
	 preparedStmt.setString(5, paymethod);	
	 preparedStmt.execute();
	
	 con.close();
	    output = "Inserted successfully";
	 }
	 
	 catch (Exception e) {
	 
	 output = "Error while inserting the payment.";
	 System.err.println(e.getMessage());
	 
	 }
	 
	 return output;
	 
	 }
	
	 public String ReadPayment() {
	 
	 String output = "";
	 
	 try {
	 
	 Connection con = connect();
	 if (con == null)
	 {
		 return "Error while connecting to the database for reading."; }
	
	 output = "<table border='1'><tr><th> Payment ID</th>" +
	 "<th>User ID</th>" +
	 "<th> UserName</th>" +
	 "<th> Product ID </th>" +
	 "<th> Amount </th>" +
	 "<th> Method </th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from payment";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	
	 while (rs.next())
	 {
	 String payid  = Integer.toString(rs.getInt("payid"));
	 String UserID = rs.getString("UserID");
	 String payusername = rs.getString("payusername");
	 String productid = rs.getString("productid");
	 String payamount = rs.getString("payamount");
	 String paymethod = rs.getString("paymethod");
		 
	 output += "<tr><td>" + payid  + "</td>";
	 output += "<td>" + UserID + "</td>";
	 output += "<td>" + payusername + "</td>";
	 output += "<td>" + productid + "</td>";
	 output += "<td>" + payamount + "</td>";
	 output += "<td>" + paymethod + "</td>";
	 
	 
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='payment.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='payid' type='hidden' value='" + payid
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 
	 output += "</table>";
	 }
	 
	 catch (Exception e) {
	 
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
	 }
	 
	 return output;
	 
	 }
	
	
	public String UpdatePayment(String payid, String UserID, String payusername,String productid, String payamount, String paymethod) {
	
	 String output = "";
	 
	 try {
	 
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 
	 String query = "UPDATE payment SET UserID=?,payusername=?,productid=?,payamount=?,paymethod=? WHERE payid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 //preparedStmt.setInt(1, Integer.parseInt(payid));
	 preparedStmt.setString(1, UserID); 
	 preparedStmt.setString(2, payusername);
	 preparedStmt.setString(3, productid);
	 preparedStmt.setString(4, payamount);
	 preparedStmt.setString(5, paymethod);
		 
	 preparedStmt.execute();
	 con.close();
	 
	 output = "Updated successfully";
	 }
	 
	 catch (Exception e) {
	 
	 output = "Error while updating the payment.";
	 System.err.println(e.getMessage());
	 }
	 
	 return output;
	 
	}
	
	public String DeletePayment(String payid) {
	 
	 String output = "";
	 
	 try {
	 
	 Connection con = connect();
	 if (con == null) {
		 
		 return "Error while connecting to the database for deleting."; }
	 
	 String query = "delete from payment where payid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	
	 preparedStmt.setInt(1, Integer.parseInt(payid));
	
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 
	 }
	 
	 catch (Exception e) {
	 
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 
	 }
	 
	 return output;
	 
	 } 
	
}

