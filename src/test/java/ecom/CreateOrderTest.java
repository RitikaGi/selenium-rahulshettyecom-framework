package ecom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutPage;
import pages.ConfirmationPage;
import pages.LandingPage;
import pages.ProductCatalogue;

public class CreateOrderTest {
	
	@Test
	public void createOrder() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String productName = "ADIDAS ORIGINAL";
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue=landingPage.loginApplication("keshu@gmail.com", "Keshri@123");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		Assert.assertEquals(productCatalogue.getToastMessage(), "Product Added To Cart");
		CartPage cartPage=productCatalogue.goToCartPage();  //method in abstract class which has been extended in productCatalogue page
		Boolean match=cartPage.verifyProductIsDisplayed(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.verifyConfirmMessage();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		driver.close();
		
	}
	}
