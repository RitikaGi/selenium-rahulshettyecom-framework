package tests;

import java.io.IOException;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutPage;
import pages.ConfirmationPage;

import pages.ProductCatalogue;
import testComponents.BaseTest;

public class CreateOrderTest extends BaseTest{
	
	@Test
	public void createOrder() throws InterruptedException, IOException {
		
		String productName = "ADIDAS ORIGINAL";
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
		
	}
	}
