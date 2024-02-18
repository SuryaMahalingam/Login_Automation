package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestSetup 
{

	public static WebDriver driver;
	public static Properties prop;
	public static int value;
	
	public TestSetup(String browserName)
	{
		try
		{	
			if(browserName.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver","C:\\Selenium\\driver\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();	
				
				options.addArguments("--remote-allow-origins=*");//settings to allow non-trusted sites
				options.addArguments("start-maximized");//settings to start the browser maximized
				options.addArguments("headless");
				driver = new ChromeDriver(options);
			}
			else if(browserName.equals("firefox"))
			{
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("-start-maximized");//settings to start the browser maximized
				options.addArguments("-headless");
				driver = new FirefoxDriver(options);
			}
			else
			{
				System.setProperty("webdriver.edge.driver", "C:\\Selenium\\driver\\msedgedriver.exe");
				EdgeOptions options = new EdgeOptions();
				driver = new EdgeDriver(options);
			}
			
			File obj_file = new File("C:\\Java Programs\\Login_Automation\\config\\input.properties");
			FileInputStream read_properties = new FileInputStream(obj_file);
			prop = new Properties();
			prop.load(read_properties);
		}
		catch(Exception e)
		{
			System.out.println("Exception Message " + e.getMessage());
		}
	}
}
