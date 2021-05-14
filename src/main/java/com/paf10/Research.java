package com.paf10;
import java.sql.*;

public class Research {

	//Connection
	public Connection connect()
	{
		Connection con = null;

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yash",	"root", "");
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
	public String insertResearch(String userName, String email, String title,String descrip, String price)
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
			String query = " insert into research(`userID`,`userName`,`email`,`title`,`descrip`,`price`) values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, title);
			preparedStmt.setString(5, descrip);
			preparedStmt.setString(6, price); 

			//execute the statement
			preparedStmt.execute();
			con.close();

			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Read
	public String readResearch()
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
			output = "<table  class='table table-dark table-striped'><tr><th>ResearcherName</th>"
					+"<th>Email</th><th>Title</th>"
					+ "<th>Item Description</th>"
					+ "<th>Price</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from research";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next())
			{
				String userID = Integer.toString(rs.getInt("userID"));
				String userName = rs.getString("userName");
				String email = rs.getString("email");
				String title = rs.getString("title");
				String descrip = rs.getString("descrip");
				String price = rs.getString("price");

				// Add a row into the HTML table
				output += "<tr><td>" + userName + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + title + "</td>"; 
				output += "<td>" + descrip + "</td>";
				output += "<td>" + price + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='" + userID + "'></td>"
						+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + userID + "'>" + "</td></tr>";
			}

			con.close();

			// Complete the HTML table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Update
	public String updateResearch(int userID, String userName, String email, String title, String descrip, String price)
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
			String query = "update research set `userName`=?,`email`=?,`title`=?,`descrip`=?,`price`=? where `userID`=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, title);
			preparedStmt.setString(4, descrip);
			preparedStmt.setString(5, price);

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();

			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
			
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Delete
	public String removeResearch(int userID)
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
			String query = "delete from research where `userID`=?;";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, userID);

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();

			String newResearch = readResearch();
			output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
}
