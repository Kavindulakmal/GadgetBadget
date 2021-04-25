package com;

//Wickramasinghe T.W.M.K.L
//researcherService
import model.Research; 


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Research")
public class ResearchService {
	
	Research researchObj = new Research();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearch() 
	 { 
		return researchObj.readResearch(); 
	 }
	
	
	//insert data
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(@FormParam("researchername")String researchername,
								@FormParam("title")String title,
								@FormParam("email")String email,
								@FormParam("price")String price,
								@FormParam("descrip")String descrip) {
		
		String output=researchObj.insertResearch(researchername, title, email, price,descrip);
		return output;

		
	}
	
	//update data
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearch(String ResearchData) {
		
		//Convert the input string to a JSON object 
		 JsonObject itemObject = new JsonParser().parse(ResearchData).getAsJsonObject();
		 
		 
		//Read the values from the JSON object
		 String Researchid = itemObject.get("Researchid").getAsString(); 
		 String researchername = itemObject.get("researchername").getAsString(); 
		 String title = itemObject.get("title").getAsString(); 
		 String email = itemObject.get("email").getAsString(); 
		 String price = itemObject.get("price").getAsString();
		 String descrip = itemObject.get("descrip").getAsString();
		
		String output = researchObj.updateResearch(Researchid, researchername, title, email, price,descrip); 
		return output;
		
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearch(String ResearchData) {
		
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(ResearchData, "", Parser.xmlParser());
		 
		 String researchid = doc.select("researchid").text(); 
		 String output = researchObj.Research(researchid);
		 return output;

	}
	

}
