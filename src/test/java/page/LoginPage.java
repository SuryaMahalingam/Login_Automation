package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	public WebDriver driver;
	
	
	@FindBy(xpath="//input[@id='user_name']")
	public WebElement username_textbox;
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement password_textbox;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement Login_Button;
	
	@FindBy(xpath="//div[@class='ant-notification ant-notification-bottomLeft']")
	public WebElement tooltipcontainer;
	
	
	@FindBy(xpath="//div[@class='ant-notification-notice-with-icon']")
	public WebElement tooltip;
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
