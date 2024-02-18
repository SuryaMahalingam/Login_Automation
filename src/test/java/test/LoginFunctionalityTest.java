package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import page.DashboardPage;
import page.LoginPage;
import utilities.TestSetup;
import utilities.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class LoginFunctionalityTest 
{
	public WebDriver driver;
	public Properties prop;
	
	LoginPage loginPage;
	DashboardPage dashboard;
	

  @Parameters({"browser"})
  @BeforeClass
  public void beforeClass(String browserName) 
  {
	TestSetup setup = new TestSetup(browserName);
	  this.driver = setup.driver;
	  this.prop = setup.prop;
	  
	  LaunchApplication();
  }

  public void LaunchApplication()
  {
	  String url = prop.getProperty("url");	  
	  driver.get(url);
  }
  
  @DataProvider(name="userData")
  public static Object[][] getTestData()
  {
	  Object[][] testData = null;
	  
	  try
	  {
	  FileReader filereader = new FileReader("testdata\\testdata.csv");
		
		CSVReader csvreader = new CSVReader(filereader);
		
		List<String[]> allData = csvreader.readAll();
		int size = allData.size();
		int rowcount = 0;
		int columncount = 0;
		testData = new Object[size][size];
		
		for(String[] nxtRecord : allData) 
		{
			testData[rowcount][columncount] = nxtRecord[0];
			testData[rowcount][columncount+1] = nxtRecord[1];
			System.out.println(nxtRecord[0] + " " + nxtRecord[1] + " " + testData);
			rowcount++; 
		}
		
	}
	catch(IOException e)
	{
		System.out.println(e.getMessage());
	} catch (CsvException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  return testData; 
  }
  
  @Test
  public void testLoginScenarios()
  { 
	  String expectedurl = prop.getProperty("url");
	  String actualurl;
	  
	  try
		{
		   loginPage = new LoginPage(driver); 
			FileReader filereader = new FileReader("testdata\\testdata.csv");
			
			CSVReader csvreader = new CSVReader(filereader);
			List<String[]> allData = csvreader.readAll();
			
			System.out.println(allData.toString());
			
			for(String[] nxtRecord : allData) 
				{
					System.out.println(nxtRecord[0] + " " + nxtRecord[1]);
					System.out.println("initial " +driver.getWindowHandle());
					
					loginPage.username_textbox.sendKeys(nxtRecord[0]);
					loginPage.password_textbox.sendKeys(nxtRecord[1]);
					loginPage.Login_Button.click();
					driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
					actualurl = driver.getCurrentUrl();
				    
				    if(expectedurl.equals(actualurl))
				    	System.out.println("Invalid Login");
				    else
				    {
				    	System.out.println("Valid Login");
				    	saveScreenhot();
				    	break;
				    }
				    	
				    Assert.assertEquals(expectedurl, actualurl);
				    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
				    driver.navigate().refresh();
				}
			csvreader.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		} catch (CsvException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  }
  
  public void saveScreenhot()
  {
		try
		{
			LocalDateTime curr_DateTime =  LocalDateTime.now();
			DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH_mm_ss");
			String formattedDate = curr_DateTime.format(myFormatter);
			
			System.out.println(formattedDate);
			
			File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShot, new File("screenShot\\homePageScreenshot_"+formattedDate+".png"));
		}
		catch(IOException exception)
		{
			System.out.println(exception.getMessage());
		}
  }
  
  @AfterClass
  public void afterclass()
  {
	  System.out.println("After class execution");
	  driver.close();
  }
}
