package service;

import model.User;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/user")
public class UserService {
	
	User userObj = new User(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	 { 
		return userObj.readUsers();
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUsers(
	 @FormParam("userName") String userName, 
	 @FormParam("email") String email, 
	 @FormParam("password") String password, 
	 @FormParam("confPass") String confPass,
	 @FormParam("address") String address)
	{ 
	 String output = userObj.insertUsers(userName, email, password, confPass,address); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateUsers(String UserData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject fundObject = new JsonParser().parse(UserData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String userID = fundObject.get("userID").getAsString(); 
	 String userName = fundObject.get("userName").getAsString(); 
	 String email = fundObject.get("email").getAsString(); 
	 String password = fundObject.get("password").getAsString(); 
	 String confPass = fundObject.get("confPass").getAsString();
	 String address = fundObject.get("address").getAsString();
	String output = userObj.updateUsers(userID, userName, email, password, confPass,address); 
	return output; 
	}
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String UserData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(UserData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String userID = doc.select("userID").text(); 
	 String output = userObj.deleteUsers(userID); 
	return output; 
	}

}
