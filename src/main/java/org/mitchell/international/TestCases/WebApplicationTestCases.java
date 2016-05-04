package org.mitchell.international.TestCases;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


public class WebApplicationTestCases extends BaseClassTest {
	/*
	 * Test Case: Insert Vehicle (Web Application automation using selenium web driver)
	 * Test Data: Vehicle year 2010, model Ciy GXI and make Honda
	 * Expected Result: Increase in number of vehicles in list of vehicles by 1
	 * Actual Result: Number of vehicles in list of vehicles is increased by 1.
	 * Result:  PASS
	 * 
	 */
	
	@Test
	public void webApplicationInsert() throws InterruptedException{
		
		final int expectedSize = driver.findElements(By.className(listOfVehiclesClass)).size() + 1;
		driver.findElement(By.id(insertYearID)).sendKeys("2010");
		driver.findElement(By.id(insertModelID)).sendKeys("City GXI");
		driver.findElement(By.id(insertMakeID)).sendKeys("Honda");
		driver.findElement(By.id(insertSubmitID)).click();
		Thread.sleep(2000);
		int actualSize = driver.findElements(By.className(listOfVehiclesClass)).size();
		Assert.assertEquals(expectedSize, actualSize);
	}
	/*
	 * Test Case: Add Filters to list of Vehicles (Web Application automation using selenium web driver)
	 * Test Data: Vehicle year 2010 and make Honda
	 * Expected Result: All vehicles from list of vehicles should be Honda vehicles made in 2010
	 * Actual Result: All vehicles from list of vehicles are Honda vehicles made in 2010
	 * Result:  PASS
	 * 
	 */
	@Test
	public void webApplicationFilters() throws InterruptedException{
		
		driver.findElement(By.id(filterMakeID)).sendKeys("Honda");
		driver.findElement(By.id(filterYearID)).sendKeys("2010");
		driver.findElement(By.id(filterSearchID)).click();
		Thread.sleep(2000);
		List<WebElement> listOfResults = driver.findElements(By.className("list-group-item"));
		for(WebElement result: listOfResults){
			Assert.assertTrue(result.getText().contains("Honda"));
			Assert.assertTrue(result.getText().contains("2010"));
		}
	}
}
