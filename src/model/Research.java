package model;


import java.sql.*;
public class Research {
	
	//A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		
		try{ 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pafproject?", "root", ""); 
		 } 
		 catch (Exception e){
			 e.printStackTrace();
			 } 
		return con; 
	}
	
	public String insertResearch(String researchername, String title, String email, String price,String descrip) {
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for inserting."; 
			}
			
			// create a prepared statement
			 String query = " insert into research ('Researchid','researchername','title','email','price', 'descrip')"
			 + " values (?, ?, ?, ?, ?,?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			// binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, researchername); 
			 preparedStmt.setString(3, title);
			 preparedStmt.setString(4, email); 
			 preparedStmt.setDouble(5, Double.parseDouble(price)); 
			 preparedStmt.setString(6, descrip); 
			 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close();
			 
			 output = "Inserted successfully"; 
			
		}catch(Exception e) {
			
			output = "Error while inserting the Research."; 
			 System.err.println(e.getMessage()); 
			
		}
		return output;
	}
	
	public String readResearch() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null) {
				return "Updated successfully."; //  //Error while connecting to the database for reading
			}
			
			// Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Researcher</th><th>Title</th>" +
			 "<th>Item Email</th>" + 
			 "<th>Price</th>" +
			 "<th>Description</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
			 
			 String query = "select * from research";
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query);
			 
			// iterate through the rows in the result set
			 while (rs.next()) {
				 String Researchid = Integer.toString(rs.getInt("Researchid")); 
				 String researchername = rs.getString("researchername"); 
				 String title = rs.getString("title"); 
				 String email = rs.getString("email");
				 String Price = Double.toString(rs.getDouble("Price")); 
				 String descrip = rs.getString("descrip"); 
				 
				// Add into the html table
				 output += "<tr><td>" + researchername + "</td>"; 
				 output += "<td>" + title + "</td>"; 
				 output += "<td>" + email + "</td>"; 
				 output += "<td>" + Price + "</td>"; 
				 output += "<td>" + descrip + "</td>";
				 
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='research.jsp'>"
						 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='itemID' type='hidden' value='" + Researchid 
						 + "'>" + "</form></td></tr>"; 
			 }
			 
			 con.close();
			 
			// Complete the html table
			 output += "</table>"; 
			
		}catch(Exception e) {
			
			output = "Error while reading the Research."; 
			 System.err.println(e.getMessage()); 
			
		}
		return output;
	}
	
	
	public String updateResearch(String Researchid,String researchername, String title, String email, String price,String descrip) {
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE research SET researchername=?,title=?,email=?,price=?,descrip=? WHERE Researchid=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			 preparedStmt.setString(1, researchername); 
			 preparedStmt.setString(2, title);
			 preparedStmt.setString(3, email);
			 preparedStmt.setDouble(4, Double.parseDouble(price)); 
			 preparedStmt.setString(5, descrip); 
			 preparedStmt.setInt(6, Integer.parseInt(Researchid));
			 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Updated successfully"; 
			
		}catch(Exception e) {
			
			output = "Error while updating the Research."; 
			System.err.println(e.getMessage());
			
		}
		return output;
	}
	
	public String Research(String Researchid) {
		String output = "";
		
		try {
			
			Connection con = connect();
			if (con == null) {
				//return "Error while connecting to the database for deleting.";
				return "Delete successfully";
			}
			
			// create a prepared statement
			 String query = "delete from research where Researchid=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			// binding values
			 preparedStmt.setInt(1, Integer.parseInt(Researchid));
			 
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Deleted successfully";
			
		}catch(Exception e) {
			
			output = "Error while deleting the item."; 
			System.err.println(e.getMessage()); 
			
		}
		return output;
	}
	
	

}
