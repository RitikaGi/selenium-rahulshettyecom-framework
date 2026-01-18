package tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.ProductCatalogue;
import testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{
	
	@Test//(groups="Smoke Tests")
	public void invalidLoginTest() {
		landingPage.loginApplication("keshu@gmail.com", "keshu12");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	public void invalidProductTest() {
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue=landingPage.loginApplication("keshu@gmail.com", "Keshri@123");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		Assert.assertEquals(productCatalogue.getToastMessage(), "Product Added To Cart");
		CartPage cartPage=productCatalogue.goToCartPage();  //method in abstract class which has been extended in productCatalogue page
		Boolean match=cartPage.verifyProductIsDisplayed("ADIDAS FAKE");
		Assert.assertFalse(match);
	}

}
