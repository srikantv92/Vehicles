package org.mitchell.international.Vehicles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mysql.jdbc.Statement;

public class BaseClass {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://173.194.236.239/mitchellInternational";
    private String USER = "root";
    private String PASS = "";

    Connection conn = null;
    Statement stmt = null;
    public BaseClass(){
    	try{
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        stmt = (Statement) conn.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }
	
	public JSONObject vehicleJsonObject(Vehicle vehicle){
		JSONObject v = new JSONObject();
		v.put("id", vehicle.getId());
		v.put("year", vehicle.getYear());
		v.put("make", vehicle.getMake());
		v.put("model", vehicle.getModel());
		return v;
	}
	public JSONArray getAllVehicles() throws SQLException{
		 String query = "select * from Vehicles";
		 ResultSet rs = stmt.executeQuery(query);
		 JSONArray allVehicles = new JSONArray();
		 Vehicle vehicle;
		 while(rs.next()){
			 vehicle = new Vehicle();
			 vehicle.setId(rs.getInt("id"));
			 vehicle.setYear(Integer.valueOf(rs.getString("year")));
			 vehicle.setMake(rs.getString("make"));
			 vehicle.setModel(rs.getString("model"));
			 allVehicles.add(vehicleJsonObject(vehicle));
		 }
		return allVehicles;
	}
	public Vehicle getVehicleWithID(int id) throws SQLException{
		 String query = "select * from Vehicles";
		 ResultSet rs = stmt.executeQuery(query);
		
		 Vehicle vehicle;
		 while(rs.next()){
			 if(id==rs.getInt("id")){
				 vehicle = new Vehicle();
				 vehicle.setId(rs.getInt("id"));
				 vehicle.setYear(Integer.valueOf(rs.getString("year")));
				 vehicle.setMake(rs.getString("make"));
				 vehicle.setModel(rs.getString("model"));
				 return vehicle;
			 }
		 }
		return null;
	}
	public JSONArray getVehicleWithFilter(JSONObject filter) throws SQLException{
		
		List listFilters = new ArrayList(filter.keySet());
		StringBuilder query = new StringBuilder("select * from Vehicles where");
		for(int i=0;i<listFilters.size();i++){
			if(i>=1){
				query.append(" and");
			}
			query.append(" "+listFilters.get(i)+"='"+filter.get(listFilters.get(i)).toString()+"'");
		}
		 ResultSet rs = stmt.executeQuery(query.toString());
		 JSONArray filteredVehicles = new JSONArray();
		 Vehicle vehicle;
		 while(rs.next()){
				 vehicle = new Vehicle();
				 vehicle.setId(rs.getInt("id"));
				 vehicle.setYear(Integer.valueOf(rs.getString("year")));
				 vehicle.setMake(rs.getString("make"));
				 vehicle.setModel(rs.getString("model"));
				 filteredVehicles.add(vehicleJsonObject(vehicle));	 
		 }
		return filteredVehicles;
	}
	public boolean updateVehicle(Vehicle v) throws SQLException{
		 String query = "update Vehicles set year = '"+v.getYear()+"', make = '"+v.getMake()+"', model = '"+v.getModel()+"' where id = "+v.getId();
		 int update = stmt.executeUpdate(query);
		 if(update==0){
			 return false;
		 }
		 else{
		return true;
		 }
	}
	public boolean createVehicle(Vehicle v) throws SQLException{
		 String query = "Insert into Vehicles (`year`, `make`, `model`) values ( '"+v.getYear()+"', '"+v.getMake()+"' , '"+v.getModel()+"')";
		 int update = stmt.executeUpdate(query);
		 if(update==0){
			 return false;
		 }
		 else{
		return true;
		 }
	}
	public boolean deleteVehicle(int id) throws SQLException{
		 String query = "delete from Vehicles where id = "+id;
		 int update = stmt.executeUpdate(query);
		 if(update==0){
			 return false;
		 }
		 else{
		return true;
		 }
	}
}
