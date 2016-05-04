package org.mitchell.international.TestCases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


public class TestcasesApi {
	URL url = null;
	HttpURLConnection conn = null;
	/*
	 * Test Case: Request for vehicle which is not in database
	 * Test Data: Request for vehicle details with ID 100
	 * Expected Result: The status message from API should be "Please enter correct ID. There is no vehicle in database with ID 100"
	 * Actual Result: API returns JSON object with key as status and value as "Please enter correct ID. There is no vehicle in database with ID 100"
	 * Result:  PASS
	 * 
	 */
	@Test
	public void vehicleNotInDatabase(){
        try {
        	final String expectedStatus = "Please enter correct ID. There is no vehicle in database with ID 100";
        	
            url = new URL("http://localhost:8080/Vehicles/webapi/getData/100");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type","application/json");
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = rd.readLine()) != null)
            	responseStrBuilder.append(inputStr);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseStrBuilder.toString());
            
            Assert.assertEquals(200, conn.getResponseCode());
            String actualStatus = jsonObject.get("status").toString();
            Assert.assertEquals(expectedStatus, actualStatus);
        }catch(Exception ex){
        	ex.printStackTrace();
        }
	}
	
	/*
	 * Test Case: Request for vehicle with wrong format vehicle ID
	 * Test Data: Request for vehicle details with ID abc
	 * Expected Result: The status message from API should be "failed. Please check the format of id"
	 * Actual Result: API returns JSON object with key as status and value as "failed. Please check the format of id"
	 * Result:  PASS
	 * 
	 */
	@Test
	public void vehicleIDFormat(){
		
        try {
        	final String expectedStatus = "failed. Please check the format of id";
        	
            url = new URL("http://localhost:8080/Vehicles/webapi/getData/abc");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type","application/json");
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = rd.readLine()) != null)
            	responseStrBuilder.append(inputStr);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseStrBuilder.toString());
            
            Assert.assertEquals(200, conn.getResponseCode());
            String actualStatus = jsonObject.get("status").toString();
            Assert.assertEquals(expectedStatus, actualStatus);
            
        }catch(Exception ex){
        	ex.printStackTrace();
        }
	}
	/*
	 * Test Case: Delete a vehicle the dosen't exist in Database
	 * Test Data: Request for vehicle details with ID 100
	 * Expected Result: The status message from API should be "There is no vehicle in database with ID 100"
	 * Actual Result: API returns JSON object with key as status and value as "There is no vehicle in database with ID 100"
	 * Result:  PASS
	 * 
	 */
	@Test
	public void deleteNonExistantVehicle(){
	    try {
        	final String expectedStatus = "There is no vehicle in database with ID 100";
        	
            url = new URL("http://localhost:8080/Vehicles/webapi/getData/100");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type","application/json");
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = rd.readLine()) != null)
            	responseStrBuilder.append(inputStr);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseStrBuilder.toString());
            
            String actualStatus = jsonObject.get("status").toString();
            Assert.assertEquals(200, conn.getResponseCode());
            Assert.assertEquals(expectedStatus, actualStatus);
        }catch(Exception ex){
        	ex.printStackTrace();
        }	
	}
	/*
	 * Test Case: Retrieve vehicles with year as filter
	 * Test Data: Request for vehicle details from year 2010
	 * Expected Result: Vehicle with ID as 13, Model as Camry and Make as Toyota
	 * Actual Result: API returns JSON response with Vehicle ID as 13, Model as Camry and Make as Toyota
	 * Result:  PASS
	 * 
	 */
	@Test
	public void filterVehicles(){
	    try {
        	final String expectedStatus = "success";
        	final String expectedModel = "Camry";
        	final int expectedId = 13;
        	final String expectedMake = "Toyota";
            url = new URL("http://localhost:8080/Vehicles/webapi/getData/query?year=2010");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type","application/json");
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            Assert.assertEquals(200, conn.getResponseCode());
            while ((inputStr = rd.readLine()) != null)
            	responseStrBuilder.append(inputStr);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseStrBuilder.toString());
            
            JSONArray results = (JSONArray) jsonObject.get("results");
            JSONObject result = (JSONObject) (results.get(0));
            String actualStatus = jsonObject.get("status").toString();
            String actualId = result.get("id").toString();
            String actualMake = result.get("make").toString();
            String actualModel = result.get("model").toString();
            Assert.assertEquals(expectedStatus, actualStatus);
            Assert.assertEquals(expectedId, (int) Integer.valueOf(actualId));
            Assert.assertEquals(expectedMake, actualMake);
            Assert.assertEquals(expectedModel, actualModel);
        }catch(Exception ex){
        	ex.printStackTrace();
        }	
	}
	/*
	 * Test Case: Update Vehicle
	 * Test Data: Update vehicle(ID 12) make as Maruti, model as 800 and year as 1992
	 * Expected Result: Response from API with status as success
	 * Actual Result: API returns JSON response with key as status value as success.
	 * Result:  PASS
	 * 
	 */
	@Test
	public void updateVehicle(){
	    try {
        	final String expectedStatus = "success";
            url = new URL("http://localhost:8080/Vehicles/webapi/getData/");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type","application/json");
            JSONObject request = new JSONObject();
            request.put("year", 1992);
            request.put("make", "Maruti");
            request.put("model", "800");
            request.put("id", 12);
            OutputStream out = conn.getOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer.write(request.toString());
            writer.close();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = rd.readLine()) != null)
            	responseStrBuilder.append(inputStr);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseStrBuilder.toString());
            String actualStatus = jsonObject.get("status").toString();
            Assert.assertEquals(200, conn.getResponseCode());
            Assert.assertEquals(expectedStatus, actualStatus);
        }catch(Exception ex){
        	ex.printStackTrace();
        }	
	}

}
