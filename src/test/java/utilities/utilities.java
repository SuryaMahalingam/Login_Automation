package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class utilities 
{
	public WebDriver driver;
	
	public utilities(WebDriver driver)
	{
		this.driver = driver;
	}
	
}
