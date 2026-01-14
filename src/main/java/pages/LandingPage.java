package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);   
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Using Page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(css="input[type='submit']")
	WebElement loginButton;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalogue loginApplication(String username ,String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginButton.click();
		////object creation for the next page as we will land on product catalogue page
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue; 
	}
	
	

}
