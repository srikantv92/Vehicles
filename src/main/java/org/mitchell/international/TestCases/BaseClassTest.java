package org.mitchell.international.TestCases;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


public class BaseClassTest extends Resources {
	
	WebDriver driver ;
	@Before
	public void precondition() throws InterruptedException{
		File pathBinary = new File("C:\\program files\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary Binary = new FirefoxBinary(pathBinary);
		FirefoxProfile firefoxPro = new FirefoxProfile();       
		driver = new FirefoxDriver(Binary,firefoxPro);
		driver.get("http://vadrevu-stackflownover.herokuapp.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);	
	}
	
	@After
	public void postCondition(){
		driver.close();
	}

}
