package org.mitchell.international.Vehicles;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.JSONP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("/getData")
public class GetData implements ContainerResponseFilter {
	
		private BaseClass b = new BaseClass();
		
	 	@GET
	    @Produces("application/json")
	 	public String getAllVehicles() {
	 		JSONObject obj= new JSONObject();
	 		JSONArray results = null;
			try {
				results = (JSONArray) b.getAllVehicles();
				obj.put("status", "success");
			} catch (SQLException e) {
				e.printStackTrace();
				obj.put("status", "failed to connect to database");
			}
	 		obj.put("results", results);
	        return obj.toString();
	    }
	 	@GET
	 	@Path("/{para}")
	 	@Produces("application/json")
	 	public String getSpecificVehicle(@PathParam("para") String param) {
	 		
	 		JSONObject obj= new JSONObject();
	 		try {
				Vehicle v = b.getVehicleWithID(Integer.valueOf(param));
				obj.put("status", "success");
				obj.put("result", b.vehicleJsonObject(v));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				obj.put("status", "failed. Please check the format of id");
			} catch (SQLException e) {
				e.printStackTrace();
				obj.put("status", "failed to connect to database");
			}catch(NullPointerException ex){
	 			obj.put("status", "Please enter correct ID. There is no vehicle in database with ID "+param);
	 		}
	        return obj.toString();
	    }
	 	
	 	@GET
	 	@Path("/query")
	 	@Produces("application/json")
	 	public String filterVehicles(@Context UriInfo info){
	 		String model = info.getQueryParameters().getFirst("model");
	 		String year = info.getQueryParameters().getFirst("year");
	 		String make = info.getQueryParameters().getFirst("make");
	 		JSONObject request = new JSONObject();
	 		try{
	 		if(!model.equals(null)){
	 			request.put("model", model);
	 		}
	 		} catch(NullPointerException e){
	 			
	 		}
	 		try{
	 		if(!year.equals(null)){
	 			request.put("year", year);
	 		}
	 		}catch(NullPointerException e){
	 			
	 		}
	 		try{
	 		if(!make.equals(null)){
	 			request.put("make", make);
	 		}
	 		} catch(NullPointerException e){
	 			
	 		}
	 		
	 		JSONObject response = new JSONObject();
	 		try {
				JSONArray res = b.getVehicleWithFilter(request);
				response.put("status", "success");
				response.put("results", res);
			} catch (SQLException e) {
				e.printStackTrace();
				response.put("status", "failed to connect to database");
			}
			return response.toString();	 		
	 	}
	 	
	 	@POST
	 	@Produces("application/json")
	 	@Consumes(MediaType.APPLICATION_JSON)
	 	public String insertVehicle(Vehicle v){
	 		JSONObject resp = new JSONObject();
	 		try {
	 			System.out.println(v.getYear());
	 			if(v.getYear()==0){
	 				resp.put("status", "Failed. Check the format of year.");
	 				return resp.toString();
	 			}
				if(b.createVehicle(v)){
					resp.put("status", "success");
				}
				else{
					resp.put("status", "Failed to Insert. Please check your data.");
				}
			} catch (SQLException e) {
				resp.put("status", "failed");
			}
	 		return resp.toString();
	 		
	 	}
	 	@PUT
	 	@Produces("application/json")
	 	@Consumes(MediaType.APPLICATION_JSON)
	 	public String updateVehicle(Vehicle v){
	 		
	 		JSONObject resp = new JSONObject();
	 		try {
				if(b.updateVehicle(v)){
					resp.put("status", "success");
				}
				else{
					resp.put("status", "failed");
				}
				
			} catch (SQLException e) {
				resp.put("status", "failed");
			}
	 		
	 		return resp.toString();
	 		
	 	}
	 	@DELETE
	 	@Path("/{para}")
	 	@Produces("application/json")
	 	public String deleteVehicle(@PathParam("para") int id){
	 		System.out.println("delete id"+id);
	 		JSONObject resp = new JSONObject();
	 		try {
				if(b.deleteVehicle(id)){
					resp.put("status", "success");	
				}
				else{
					resp.put("status", "There is no vehicle in database with ID "+id);
				}
			} catch (SQLException e) {
				resp.put("status", "failed");
			}
	 		catch(NullPointerException ex){
	 			resp.put("status", "failed. Please check the format of id");
	 		}
	 		
	 		return resp.toString();
	 		
	 	}
		
	 	
	 	@Override
		public void filter(ContainerRequestContext request,
				ContainerResponseContext response) throws IOException {
			MultivaluedMap<String, Object> headers = response.getHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
			headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
		}
		
}
