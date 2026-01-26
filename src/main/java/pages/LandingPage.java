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
	
	@FindBy(id="toast-container")
	WebElement getLoginMessage;
	
	@FindBy(xpath="//div[text()='*Email is required']")
	WebElement emailValidationMessage;
	
	@FindBy(xpath="//*[text()='*Password is required']")
	WebElement passwordValidationMessage;
	
	@FindBy(css=".forgot-password-link")
	WebElement forgetPasswordLink;
	
	
	By toastMessage = By.id("toast-container");
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalogue loginApplication(String username ,String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginButton.click();
		waitForElementToAppear(toastMessage);
		
		////object creation for the next page as we will land on product catalogue page
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue; 
	}
	
	public void clickForgetPassword() {
		forgetPasswordLink.click();
	}
	
	public String getLoginMessage() {
		return getLoginMessage.getText();
	}
	
	public boolean emailValidationMessageIsVisible() {
		return emailValidationMessage.isDisplayed();
	}
	
	public boolean passwordValidationMessageIsVisible() {
		return passwordValidationMessage.isDisplayed();
	}
	
	public void loginToApplication(String username ,String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginButton.click();
	}
}
