package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;

import utilities.*;

public class NewTest 
{
	public WebDriver driver;
	public Properties prop;
	
  @Test
  public void test() 
  {
	  System.out.println("Test");
  }
  @BeforeMethod
  public void beforeMethod() {
  }
  
  @BeforeClass
  public void beforeClass() 
  {
	  //this.driver = setup.driver;
	 // this.prop = setup.prop;
  }
  
  @Test
  public void LaunchApplication()
  {
	  String url = prop.getProperty("url");
	  
	  driver.get(url);
  }

  @BeforeTest
  public void beforeTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

}
