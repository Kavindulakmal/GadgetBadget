package com;
import com.PaymentModel;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/payment") 
public class PaymentService {
	
		PaymentModel payobj = new PaymentModel();
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems()
		 {
			return payobj.ReadPayment();
		 } 
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String PaymentInsert(@FormParam("UserID") String UserID,
									 @FormParam("payusername")String payusername,
									 @FormParam("productid") String productid,
									 @FormParam("payamount") String payamount,
									 @FormParam("paymethod") String paymethod) {
			
			String output = payobj.PaymentInsert(UserID, payusername, productid, payamount,paymethod);
			
			return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String UpdatePayment(String paydata) {
			
			JsonObject Pobj = new JsonParser().parse(paydata).getAsJsonObject();
			
			String payid = Pobj.get("payid").getAsString();
			String UserID = Pobj.get("UserID").getAsString();
			String payusername = Pobj.get("payusername").getAsString();
			String productid = Pobj.get("productid").getAsString();
			String payamount = Pobj.get("payamount").getAsString();
			String paymethod = Pobj.get("paymethod").getAsString();

			String output = payobj.UpdatePayment(payid, UserID, payusername, productid, payamount, paymethod );
			
			return output;
			
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String DeletePayment(String paydata) {
			
			Document doc = Jsoup.parse(paydata,"",Parser.xmlParser());
			
			String payid=doc.select("payid").text();
			
			String output = payobj.DeletePayment(payid);
			
			return output;
			
			
		}
	}
