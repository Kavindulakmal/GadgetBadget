package com;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;

import model.Company;

@Path("/Companys")
public class CompanyService {
	
Company companyObj = new Company();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCompany() {
		
		return companyObj.readCompany();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String InsertCompany(@FormParam("userid") String userid,
			@FormParam("useremail") String useremail,
			@FormParam("userphone") String userphone,
			@FormParam("udescription") String udescription,
			@FormParam("ubudjet") String ubudjet,
			@FormParam("company1") String company1,
			@FormParam("company2") String company2)
			
			{
		String output = companyObj.insertCompany(userid, useremail, userphone, udescription, ubudjet, company1, company2);
		return output;
			}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCompany(String companyData)
	{
		//Convert the input string to a JSON object
		JsonObject companyObject = new JsonParser().parse(companyData).getAsJsonObject();
		
		//Read the values from the JSON object
		String id = companyObject.get("id").getAsString();
		String userid = companyObject.get("userid").getAsString();
		String useremail = companyObject.get("useremail").getAsString();
		String userphone = companyObject.get("userphone").getAsString();
		String udescription = companyObject.get("udescription").getAsString();
		String ubudjet = companyObject.get("ubudjet").getAsString();
		String company1 = companyObject.get("company1").getAsString();
		String company2 = companyObject.get("company2").getAsString();
		String output = companyObj.updateCompany(id, userid, useremail, userphone, udescription, ubudjet, company1, company2);
		return output;
	}
	
	@DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteCompany(String companyData)
	 {
		//Convert the input string to an XML document
		  Document doc = (Document) Jsoup.parse(companyData, "", Parser.xmlParser());
		  
		//Read the value from the element <id>
		  String id = ((Element) doc).select("id").text();
		  String output = companyObj.deleteCompany(id);
		 return output;
	 }
	
	
	

}

