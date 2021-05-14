package com.paf10;
import java.sql.*;

public class Company {

	//Connection
	public Connection connect()
	{
		Connection con = null;

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafproject","root", "");
			//For testing
			System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return con;
	}

	//Insert
	public String insertItem(String code, String email, String price, String desc, String com)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into client(`itemID`,`cliCode`,`cliEmail`,`cliBudget`,`cliDesc`, `cliCom`) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, email);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc); 
			preparedStmt.setString(5, com);

			//execute the statement
			preparedStmt.execute();
			con.close();

			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Read
	public String readItems()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			output = "<table  class='table table-dark table-striped'><tr><th>Register Code</th>"
					+"<th>Register Email</th><th>Research Budget</th><th>Research Description</th>"
					+ "<th>Company</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from client";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next())
			{
				String itemID = Integer.toString(rs.getInt("itemID"));
				String cliCode = rs.getString("cliCode");
				String cliEmail = rs.getString("cliEmail");
				String cliBudget = Double.toString(rs.getDouble("cliBudget"));
				String cliDesc = rs.getString("cliDesc");
				String cliCom = rs.getString("cliCom");

				// Add a row into the HTML table
				output += "<tr><td>" + cliCode + "</td>";
				output += "<td>" + cliEmail + "</td>";
				output += "<td>" + cliBudget + "</td>"; 
				output += "<td>" + cliDesc + "</td>";
				output += "<td>" + cliCom + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='" + itemID + "'></td>"
						+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + itemID + "'>" + "</td></tr>";
			}

			con.close();

			// Complete the HTML table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Update
	public String updateItem(int id, String code, String email, String price, String desc, String com)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "update client set `cliCode`=?,`cliEmail`=?,`cliBudget`=?,`cliDesc`,`cliCom`=? where `itemID`=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, email);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setString(5, com);
			preparedStmt.setInt(6, id);

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();

			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Delete
	public String removeItem(int id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "delete from client where `itemID`=?;";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, id);

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();

			String newItems = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
