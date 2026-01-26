package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends AbstractComponents{
	
    WebDriver driver;
	
	public RegistrationPage(WebDriver driver) {
		super(driver);   
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="firstName")
	WebElement firstName;
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(css="input[placeholder='enter your number']")
	WebElement phoneNumber;

	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement selectCheckbox;
	
	@FindBy(css="input[type='submit']")
	WebElement registerButton;

}
