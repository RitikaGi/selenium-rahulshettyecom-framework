package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents{
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By CountryList = By.cssSelector(".ta-results");
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(Country,countryName)
		.build().perform();
		waitForElementToAppear(CountryList);
		try {
		waitForElementToBeClickable(selectCountry);
		selectCountry.click();
		}
		catch (ElementClickInterceptedException e) {
	        // Fallback to JavaScript click
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", selectCountry);
	    }
	}
	
	public ConfirmationPage submitOrder() {
		try {
			waitForElementToBeClickable(placeOrder);
			placeOrder.click();
			}
			catch (ElementClickInterceptedException e) {
		        // Fallback to JavaScript click
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].click();", placeOrder);
		    }
		
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
	}
	
	

}
